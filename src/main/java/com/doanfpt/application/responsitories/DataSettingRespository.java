package com.doanfpt.application.responsitories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.doanfpt.application.entities.DataSetting;
import com.doanfpt.application.model.DataSettingId;

@Repository
public interface DataSettingRespository extends JpaRepository<DataSetting, DataSettingId> {
}
