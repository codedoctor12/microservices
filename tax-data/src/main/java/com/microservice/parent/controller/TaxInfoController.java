package com.microservice.parent.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.parent.dto.TaxInfoDto;
import com.microservice.parent.model.TaxInfo;
import com.microservice.parent.repository.TaxInfoRepository;
import com.microservice.parent.repository.MessageRespository;

import com.microservice.parent.service.TaxInfoService;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.microservice.parent.model.Messages;


@RestController
@RequestMapping("/api/tax-data")
@CrossOrigin("*")
@RequiredArgsConstructor
public class TaxInfoController {

    private final TaxInfoService taxInfoService; 
    private final TaxInfoRepository taxInfoRepository;
    private final MessageRespository messageRepos;
    // @Value("${URI}")
    @PostMapping("/saveData")
    public ResponseEntity<TaxInfo> saveTaxData(@RequestBody TaxInfoDto taxInfoDto) {
        TaxInfo taxInfo = taxInfoService.saveTaxInfo(taxInfoDto);
        return new ResponseEntity<TaxInfo>(taxInfo,HttpStatus.OK);
        //        return  new ResponseEntity<List<WareHouseDTO>>(products, HttpStatus.OK);

    }
    @GetMapping("/getData")
    public ResponseEntity<List<TaxInfo>> getData() {
        
        List<TaxInfo> info = taxInfoRepository.findAll();
        return new ResponseEntity<List<TaxInfo>>(info,HttpStatus.OK);
        //        return  new ResponseEntity<List<WareHouseDTO>>(products, HttpStatus.OK);

    }
    @PostMapping("/sendMessage")
    public ResponseEntity<Messages> sendMessage(@RequestBody Messages message) {
        
        messageRepos.save(message);
        System.out.println(message);
        return new ResponseEntity<Messages>(message,HttpStatus.OK);
        //        return  new ResponseEntity<List<WareHouseDTO>>(products, HttpStatus.OK);

    }
    // @GetMapping("/gettData")
    // public void testData() 
    // {
        
    //    System.out.println(test);

    // }
    

}
