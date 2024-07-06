package com.microservice.parent.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.parent.dto.TaxInformation;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class TaxCalcController {
    
    @PostMapping("/tax-calc")
    public int test(@RequestBody TaxInformation taxInformation)
    {

        int getNumberOfAllowencesWithholding = Integer.parseInt(taxInformation.getNumberOfAllowencesWithholding());
        int withholdingsAmount = Integer.parseInt(taxInformation.getWithholdingsAmount());
        String fillingStatus = taxInformation.getFilingStatus();
        if(fillingStatus.equals("Single"))
        {
            return withholdingsAmount + getNumberOfAllowencesWithholding;
        }
        else
        {
            return withholdingsAmount * getNumberOfAllowencesWithholding;
        }
    }

}
