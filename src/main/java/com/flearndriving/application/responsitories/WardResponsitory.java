package com.flearndriving.application.responsitories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.flearndriving.application.entities.District;
import com.flearndriving.application.entities.Ward;

@Repository
public interface WardResponsitory  extends JpaRepository<Ward, Long> {
    
    public List<Ward> findByDistrict(District district);

}
