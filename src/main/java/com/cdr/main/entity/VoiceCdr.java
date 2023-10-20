package com.cdr.main.entity;

import org.springframework.stereotype.Component;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VoiceCdr {
	
	private String callStartTime;
    private String callEndTime;
    private String callerNumber;
    private String recipientNumber;
    private int callDurationMinutes;
    private String callType;
    private String callResult;
    private String callID;
    private String callDirection;

}
