package com.escorre.escorrega.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

public record ProductRecordDTO (@NotBlank String name, @NotNull String price, Date date){
}
