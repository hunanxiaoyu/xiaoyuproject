package com.logistics.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.logistics.domain.NeedEntity.VehicleManage;
import com.logistics.domain.entity.Vehicle;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface VehicleMapper extends BaseMapper<Vehicle> {
    @Select("select status from vehicle")
    List<String> selectALLVehicleStatus();
    List<VehicleManage> getVehicleManagement(String licensePlate,String status);
}
