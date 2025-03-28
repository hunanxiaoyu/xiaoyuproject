package com.logistics.controller;




import com.logistics.service.VehicleService;
import com.logistics.utils.ResponseResult;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
