package com.doanfpt.application.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.doanfpt.application.entities.DrivingLicense;
import com.doanfpt.application.responsitories.DrivingLicenseRepository;

@Service
public class DrivingLicenseServices {
	@Autowired
	DrivingLicenseRepository drivingLicenseRepository;
	
	public DrivingLicense getOne(Long drivingLicenseId) {
		return drivingLicenseRepository.getOne(drivingLicenseId);
	}
	
	
}
