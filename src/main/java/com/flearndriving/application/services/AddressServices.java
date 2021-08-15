package com.flearndriving.application.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flearndriving.application.entities.District;
import com.flearndriving.application.entities.Province;
import com.flearndriving.application.entities.Ward;
import com.flearndriving.application.responsitories.DistrictResponsitory;
import com.flearndriving.application.responsitories.ProvinceResponsitory;
import com.flearndriving.application.responsitories.WardResponsitory;

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
