package com.microservice.parent.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaxInformation {
        private String filingStatus;
        private String numberOfAllowencesWithholding;
        private String withholdingsAmount;


}
