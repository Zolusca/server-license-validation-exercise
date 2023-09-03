package com.anomalys.rest.api.license.DTO;

import lombok.*;
import org.springframework.http.HttpStatus;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WebResponse<T> {
    private String message;
    private HttpStatus statusResponse;
    private T data;
}
