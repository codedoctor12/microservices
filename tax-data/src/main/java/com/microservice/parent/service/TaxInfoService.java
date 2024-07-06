package com.microservice.parent.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.microservice.parent.dto.TaxInfoDto;
import com.microservice.parent.dto.TaxInformation;
import com.microservice.parent.model.TaxInfo;
import com.microservice.parent.repository.TaxInfoRepository;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class TaxInfoService {
    
    
    @Autowired
    private Environment env;
    private final TaxInfoRepository taxInfoRepository;
    private final WebClient.Builder webClientBuilder;
  
    public TaxInfo saveTaxInfo(TaxInfoDto taxInfoDto)
    {

    TaxInfo taxInfo = new TaxInfo();
        
    taxInfo = TaxInfo.builder()
        .fullName(taxInfoDto.getFullName())
        .address(taxInfoDto.getAddress())
        .ssNumber(taxInfoDto.getSsNumber())
        .employerAddress(taxInfoDto.getEmployerAddress())
        .withholdingsAmount(taxInfoDto.getWithholdingsAmount())
        .numberOfAllowencesWithholding(taxInfoDto.getNumberOfAllowencesWithholding())
        .filingStatus(taxInfoDto.getFilingStatus())
        .employersName(taxInfoDto.getEmployersName())
        .email(taxInfoDto.getEmail())
        .build();
        
    TaxInformation taxInformation = new TaxInformation();
        taxInformation = TaxInformation.builder()
        .filingStatus(taxInfo.getFilingStatus())
        .withholdingsAmount(taxInfo.getWithholdingsAmount())
        .numberOfAllowencesWithholding(taxInfo.getNumberOfAllowencesWithholding())
        .build();
        
    int returnAmount = webClientBuilder.build().post()
        .uri(env.getProperty("calc.path"))
        .body(Mono.just(taxInformation),TaxInformation.class)
        .retrieve()
        .bodyToMono(Integer.class)
        .block();
    
        taxInfo.setCalculated_tax_return(Integer.toString(returnAmount));
        System.out.println("works");

    taxInfoRepository.save(taxInfo);
        
    return taxInfo;

    }

}
