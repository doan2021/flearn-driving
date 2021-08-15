package com.flearndriving.application.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.flearndriving.application.dto.DataSettingId;
import com.flearndriving.application.entities.DataSetting;
import com.flearndriving.application.responsitories.DataSettingRespository;

@Component
public class DataSettingComponent {

    public static final DataSettingId SETTING_UPLOAD_IMAGE = new DataSettingId("ULF", 0);
    
    public static final DataSettingId SETTING_UPLOAD_PDF = new DataSettingId("ULF", 1);

    @Autowired
    DataSettingRespository dataSettingRespository;

    public DataSetting getDataSettingUploadImage() {
        return dataSettingRespository.getOne(SETTING_UPLOAD_IMAGE);
    }
    
    public DataSetting getDataSettingUploadPdf() {
        return dataSettingRespository.getOne(SETTING_UPLOAD_PDF);
    }
}
