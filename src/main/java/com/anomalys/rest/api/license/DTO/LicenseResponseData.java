package com.anomalys.rest.api.license.DTO;

import com.anomalys.rest.api.license.Entity.TypeStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LicenseResponseData {
    private String licenseKey;
    private TypeStatus status;
}
