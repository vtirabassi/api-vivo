package com.vivo.apismartphones.domain.repositories;

import com.vivo.apismartphones.domain.entity.SmartPhone;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SmartPhoneRepository extends JpaRepository<SmartPhone, Integer> {
}
