package com.microservice.parent.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TaxInfoDto {
    private String id;
    private String fullName;
    private String ssNumber;
    private String address;
    private String filingStatus;
    private String numberOfAllowencesWithholding;
    private String employersName; 
    private String employerAddress;
    private String withholdingsAmount;
    private String email;
    private String calculated_tax_return;

}
