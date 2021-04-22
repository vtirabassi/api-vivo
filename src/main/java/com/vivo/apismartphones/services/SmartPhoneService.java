package com.vivo.apismartphones.services;

import com.vivo.apismartphones.domain.entity.SmartPhone;

public interface SmartPhoneService {
    SmartPhone create(SmartPhone request);

    SmartPhone getById(Integer smartId);

    SmartPhone updateSmartPhone(Integer smartId, SmartPhone request);

    void deleteSmartPhone(Integer smartId);
}
