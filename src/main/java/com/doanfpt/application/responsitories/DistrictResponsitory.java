package com.doanfpt.application.responsitories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.doanfpt.application.entities.District;
import com.doanfpt.application.entities.Province;

@Repository
public interface DistrictResponsitory  extends JpaRepository<District, Long> {
    
    public List<District> findByProvince(Province province);

}
