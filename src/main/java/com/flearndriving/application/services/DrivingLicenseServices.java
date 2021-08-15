package com.flearndriving.application.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flearndriving.application.entities.DrivingLicense;
import com.flearndriving.application.responsitories.DrivingLicenseRepository;

@Service
public class DrivingLicenseServices {
    @Autowired
    DrivingLicenseRepository drivingLicenseRepository;

    public DrivingLicense getOne(Long drivingLicenseId) {
        return drivingLicenseRepository.getOne(drivingLicenseId);
    }

    public List<DrivingLicense> findAllDrivingLicense() {
        return drivingLicenseRepository.findAll();
    }
}
