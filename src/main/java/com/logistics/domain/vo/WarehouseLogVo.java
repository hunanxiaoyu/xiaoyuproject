package com.logistics.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;
@Data
public class WarehouseLogVo {
   private String productName;
   private Integer changeType;
   private Integer changeQuantity;
   @JsonFormat(pattern = "yyyy-MM-dd HH-mm-ss")
   private LocalDateTime operationTime;
   private String operator;
   private String remark;

}
