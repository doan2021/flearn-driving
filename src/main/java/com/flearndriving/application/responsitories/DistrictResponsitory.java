package com.flearndriving.application.responsitories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.flearndriving.application.entities.District;
import com.flearndriving.application.entities.Province;

@Repository
public interface DistrictResponsitory  extends JpaRepository<District, Long> {
    
    public List<District> findByProvince(Province province);

}
