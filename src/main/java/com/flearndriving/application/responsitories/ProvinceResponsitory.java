package com.flearndriving.application.responsitories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.flearndriving.application.entities.Province;

@Repository
public interface ProvinceResponsitory  extends JpaRepository<Province, Long> {

}
