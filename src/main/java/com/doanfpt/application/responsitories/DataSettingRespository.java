package com.doanfpt.application.responsitories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.doanfpt.application.dto.DataSettingId;
import com.doanfpt.application.entities.DataSetting;

@Repository
public interface DataSettingRespository extends JpaRepository<DataSetting, DataSettingId> {
}
