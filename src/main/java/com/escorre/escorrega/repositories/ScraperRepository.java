package com.escorre.escorrega.repositories;

import com.escorre.escorrega.model.ProductModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Repository
public interface ScraperRepository extends JpaRepository<ProductModel, UUID> {
    List<ProductModel> findByName(String name);
    List<ProductModel> findByPrice(float price);
    List<ProductModel> findByLink(String link);
    List<ProductModel> findByDate(Date date);
}
