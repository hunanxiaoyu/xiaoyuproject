package com.logistics.controller;




import com.logistics.domain.vo.VehicleVO;
import com.logistics.service.VehicleService;
import com.logistics.utils.ResponseResult;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/vehicle")
@RequiredArgsConstructor
public class VehicleController {
    private final VehicleService vehicleService;
    @GetMapping
    public ResponseResult<Map<String,Long>> getVehicleAllStatus(){
        Map<String, Long> vehicleAllStatus= vehicleService.getVehicleAllStatus();
        return ResponseResult.ok(vehicleAllStatus);
    }
    @GetMapping("/getVehicleManagement")
    public ResponseResult<List<VehicleVO>> getVehicleManagement(String licensePlate,String status){
        List<VehicleVO> vehicleManagement= vehicleService.getVehicleManagement(licensePlate,status);
        return ResponseResult.ok(vehicleManagement);
    }
}
