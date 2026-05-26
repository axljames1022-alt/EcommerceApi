package com.ws101.busa.balading.ecommerceapiv2.dto;

public record ProductListingEntry(
        Long prodId,
        String prodName,
        Double prodPrice
) {}