package com.microservice.parent.model;

import org.springframework.data.mongodb.core.mapping.Document;


import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(value = "taxInfo")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TaxInfo {
    @Id
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
