package com.doanfpt.application.responsitories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.doanfpt.application.entities.DrivingLicense;

public interface DrivingLicenseRepository extends JpaRepository<DrivingLicense, Long> {


}
