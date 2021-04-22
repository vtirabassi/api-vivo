package com.vivo.apismartphones.controllers;

import com.vivo.apismartphones.domain.entity.SmartPhone;
import com.vivo.apismartphones.services.SmartPhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RestController
@RequestMapping("v1/smarts")
public class SmartPhoneController {

    @Autowired
    private SmartPhoneService smartPhoneService;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public SmartPhone createSmartPhone(@RequestBody @Valid SmartPhone request){

        return smartPhoneService.create(request);
    }

    @GetMapping("{smart_id}")
    @ResponseStatus(HttpStatus.OK)
    public SmartPhone getById(@PathVariable("smart_id") Integer smartId){
        return smartPhoneService.getById(smartId);
    }

    @PutMapping("{smart_id}")
    @ResponseStatus(HttpStatus.OK)
    public SmartPhone updateSmatPhone(@PathVariable("smart_id") Integer smartId, @RequestBody @Valid SmartPhone request){
        return smartPhoneService.updateSmartPhone(smartId, request);
    }

    @DeleteMapping("{smart_id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteSmartPhone(@PathVariable("smart_id") Integer smartId) {
        smartPhoneService.deleteSmartPhone(smartId);
    }

}
