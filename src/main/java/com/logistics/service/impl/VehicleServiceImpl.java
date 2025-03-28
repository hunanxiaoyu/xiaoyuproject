package com.logistics.service.impl;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.extension.toolkit.Db;
import com.logistics.domain.entity.Vehicle;
import com.logistics.mapper.VehicleMapper;
import com.logistics.service.VehicleService;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class VehicleServiceImpl extends ServiceImpl<VehicleMapper, Vehicle> implements VehicleService {
    @Override
    public Map<String,Long> getVehicleAllStatus() {
        List<String> status= baseMapper.selectALLVehicleStatus();
        return status.stream().collect(Collectors.groupingBy(s -> s, Collectors.counting()));
    }
}
