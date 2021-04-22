package com.vivo.apismartphones.services.impl;

import com.vivo.apismartphones.domain.entity.SmartPhone;
import com.vivo.apismartphones.domain.repositories.SmartPhoneRepository;
import com.vivo.apismartphones.excpetions.NegocioException;
import com.vivo.apismartphones.services.SmartPhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class SmartPhoneImpl implements SmartPhoneService {

    @Autowired
    private SmartPhoneRepository smartPhoneRepository;

    @Override
    public SmartPhone create(SmartPhone request) {
        return smartPhoneRepository.save(request);
    }

    @Override
    public SmartPhone getById(Integer smartId) {
       return smartPhoneRepository.findById(smartId)
                .orElseThrow(() -> new NegocioException("SmartPhone não encontrado: " + smartId));
    }

    @Override
    public SmartPhone updateSmartPhone(Integer smartId, SmartPhone request) {
        return smartPhoneRepository.findById(smartId)
                .map(p -> {
                    request.setId(p.getId());
                    return smartPhoneRepository.save(request);
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "SmartPhone não encontrado"));

    }

    @Override
    public void deleteSmartPhone(Integer smartId) {
        smartPhoneRepository.findById(smartId)
                .map(p -> {
                    smartPhoneRepository.deleteById(p.getId());
                    return Void.class;
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "SmartPhone não encontrado"));
    }
}
