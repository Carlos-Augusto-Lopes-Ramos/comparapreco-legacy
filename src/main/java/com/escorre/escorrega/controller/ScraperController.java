package com.escorre.escorrega.controller;

import com.escorre.escorrega.model.ProductModel;
import com.escorre.escorrega.repositories.ScraperRepository;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static java.lang.Float.parseFloat;


@CrossOrigin(origins = "*")
@RestController
public class ScraperController {

    @Autowired
    ScraperRepository scraperRepository;

    @GetMapping(path = "/{product}")
    public ResponseEntity<List<ProductModel>> getPrice(@PathVariable String product) {
        Document documento;

        {
            try {
                documento = Jsoup.connect(product.replaceAll(",", "/")).get();
                var model = new ProductModel();

                String link = product.replaceAll(",", "/");
                Element reais = documento.getElementsByClass("andes-money-amount__fraction").first();
                int centavo;
                try {
                    Element centavos = documento.getElementsByClass("andes-money-amount__cents andes-money-amount__cents--superscript-36").first();
                    centavo = Integer.parseInt(centavos.html());
                }catch (NullPointerException e) {
                    centavo = 0;
                }
                String name = documento.getElementsByClass("ui-pdp-title").first().html();
                float price = parseFloat(reais.html() + "." + centavo);
                model.setName(name);
                model.setPrice(price);
                Date dt = new Date();
                SimpleDateFormat formato = new SimpleDateFormat("dd/mm/yyyy");
                formato.format(dt);
                model.setDate(dt);
                model.setLink(link);
                System.out.println(dt);
                    scraperRepository.save(model);

                List<ProductModel> finalProduct = scraperRepository.findByLink(name);
                return ResponseEntity.status(HttpStatus.OK).body(finalProduct);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }
}
