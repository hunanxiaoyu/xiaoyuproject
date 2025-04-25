package com.logistics.service.impl;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.extension.toolkit.Db;
import com.logistics.domain.NeedEntity.VehicleLocation;
import com.logistics.domain.NeedEntity.VehicleManage;
import com.logistics.domain.entity.Vehicle;
import com.logistics.domain.vo.VehicleVO;
import com.logistics.mapper.VehicleMapper;
import com.logistics.service.VehicleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.sql.SQLOutput;
import java.util.*;
import java.util.stream.Collectors;
@RequiredArgsConstructor
@Service
public class VehicleServiceImpl extends ServiceImpl<VehicleMapper, Vehicle> implements VehicleService {
    private final WebClient webClient;

    @Override
    public Map<String, Long> getVehicleAllStatus() {
        List<String> status = baseMapper.selectALLVehicleStatus();
        return status.stream().collect(Collectors.groupingBy(s -> s, Collectors.counting()));
    }

    @Override
    public List<VehicleVO> getVehicleManagement(String licensePlate,String status) {
        List<VehicleVO> vehicleVOS = new ArrayList<>();
        List<VehicleManage> vehicleManagement = baseMapper.getVehicleManagement(licensePlate,status);
        String key="1036eb2980148542feacd406553700c1";
        String url="/v3/geocode/regeo";
        vehicleManagement.forEach(manage -> {
            String location=manage.getLongitude() + "," + manage.getLatitude();

                VehicleLocation vehicleLocation= webClient.get().uri(uriBuilder ->
                    uriBuilder
                            .path(url)
                            .queryParam("key",key)
                            .queryParam("location",location)
                            .build()
                ).retrieve().bodyToMono(VehicleLocation.class).block();
                if(vehicleLocation==null){
                    throw new RuntimeException("用户坐标异常");
                }
            VehicleVO vehicleVO = getVehicleVO(manage, vehicleLocation);
            vehicleVOS.add(vehicleVO);
        });
        return vehicleVOS;
    }

    private static VehicleVO getVehicleVO(VehicleManage manage, VehicleLocation vehicleLocation) {
        VehicleVO vehicleVO = new VehicleVO();
        vehicleVO.setCurrentLocation(vehicleLocation.getRegeocode().getAddressComponent().getProvince()+
                vehicleLocation.getRegeocode().getAddressComponent().getDistrict()+
                vehicleLocation.getRegeocode().getAddressComponent().getStreetNumber().getStreet()+
                vehicleLocation.getRegeocode().getAddressComponent().getStreetNumber().getNumber());
        vehicleVO.setName(manage.getName());
        vehicleVO.setStatus(manage.getStatus());
        vehicleVO.setLatitude(manage.getLatitude());
        vehicleVO.setLongitude(manage.getLongitude());
        vehicleVO.setLicensePlate(manage.getLicensePlate());
        return vehicleVO;
    }
}
