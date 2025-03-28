package com.logistics.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.logistics.domain.entity.Vehicle;

import java.util.Map;
import java.util.Stack;

public interface VehicleService extends IService<Vehicle> {
    Map<String,Long> getVehicleAllStatus();
}
