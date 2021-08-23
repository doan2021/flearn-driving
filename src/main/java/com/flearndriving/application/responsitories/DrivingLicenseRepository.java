package com.flearndriving.application.responsitories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.flearndriving.application.entities.DrivingLicense;

public interface DrivingLicenseRepository extends JpaRepository<DrivingLicense, Long> {

    @Query("SELECT dl FROM DrivingLicense dl WHERE dl.drivingLicenseId = :drivingLicenseId AND dl.isDelete = false")
    DrivingLicense findByDrivingLicenseId(Long drivingLicenseId);
    
    @Query("SELECT dl FROM DrivingLicense dl WHERE dl.isDelete = false")
    List<DrivingLicense> findAll();
}
