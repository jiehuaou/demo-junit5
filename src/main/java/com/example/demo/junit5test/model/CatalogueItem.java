package com.example.demo.junit5test.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Data
@AllArgsConstructor
@RequiredArgsConstructor(staticName = "of")
public class CatalogueItem {

    @Min(value = 2L, message = "id must be >= 2L")
    @Max(value = 5000L, message = "id must be <= 5000L")
    private Long id;

    @NonNull
    private String sku;

    @NonNull
    private String name;

    @Email(message = "please input email format")
    @NonNull
    private String email;
}
