package com.flearndriving.application.responsitories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.flearndriving.application.entities.DrivingLicense;

public interface DrivingLicenseRepository extends JpaRepository<DrivingLicense, Long> {

    public DrivingLicense findByDrivingLicenseId(Long drivingLicenseId);
}
