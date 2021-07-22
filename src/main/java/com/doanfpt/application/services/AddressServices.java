package com.doanfpt.application.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.doanfpt.application.entities.District;
import com.doanfpt.application.entities.Province;
import com.doanfpt.application.entities.Ward;
import com.doanfpt.application.responsitories.DistrictResponsitory;
import com.doanfpt.application.responsitories.ProvinceResponsitory;
import com.doanfpt.application.responsitories.WardResponsitory;

@Service
public class AddressServices {

    @Autowired
    ProvinceResponsitory provinceResponsitory;

    @Autowired
    DistrictResponsitory districtResponsitory;
    
    @Autowired
    WardResponsitory wardResponsitory;

    public List<Province> findAllProvince() {
        return provinceResponsitory.findAll();
    }

    public List<District> findAllDistrictByProvinceId(Long provinceId) {
        Province province = provinceResponsitory.getOne(provinceId);
        return districtResponsitory.findByProvince(province);
    }
    
    public List<Ward> findAllWardByDistrictId(Long districtId) {
        District district = districtResponsitory.getOne(districtId);
        return wardResponsitory.findByDistrict(district);
    }
}
