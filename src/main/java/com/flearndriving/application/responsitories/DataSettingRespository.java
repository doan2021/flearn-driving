package com.flearndriving.application.responsitories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.flearndriving.application.dto.DataSettingId;
import com.flearndriving.application.entities.DataSetting;

@Repository
public interface DataSettingRespository extends JpaRepository<DataSetting, DataSettingId> {
}
