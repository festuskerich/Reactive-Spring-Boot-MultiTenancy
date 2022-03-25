package com.franciumsources.Reactive.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class CustomResponse {
    Integer statusCode;
    String statusDescription;
    Object data;

}
