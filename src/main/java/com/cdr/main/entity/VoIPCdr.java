package com.cdr.main.entity;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VoIPCdr {
	
	private String callStartTime;
    private String callEndTime;
    private String callDirection;
    private String duration;
    private String callerIpAddress;
    private String calleeIpAddress;
    private String callType;
    private String callResult;
    private Double dataConsumed;
    private String jitter;
    private double packetLoss;

}
