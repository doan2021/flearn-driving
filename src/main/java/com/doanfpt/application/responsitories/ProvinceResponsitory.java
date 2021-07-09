package com.doanfpt.application.responsitories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.doanfpt.application.entities.Province;

@Repository
public interface ProvinceResponsitory  extends JpaRepository<Province, Long> {

}
