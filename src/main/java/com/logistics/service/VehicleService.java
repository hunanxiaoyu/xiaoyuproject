package com.logistics.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.logistics.domain.entity.Vehicle;
import com.logistics.domain.vo.VehicleVO;

import java.util.List;
import java.util.Map;

public interface VehicleService extends IService<Vehicle> {
    Map<String,Long> getVehicleAllStatus();

    List<VehicleVO> getVehicleManagement(String licensePlate,String status);
}
