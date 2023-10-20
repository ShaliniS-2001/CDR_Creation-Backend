package com.cdr.main.entity;

import java.util.Date;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoamingVoiceCdr {
    private String userId;
    private String startTimeStr;
    private String endTimeStr;
    private String duration;
    private String callerNumber;
    private String callerActualLocation;
    private String calleeNumber;
    private String calleeLocation;
    private String roamingLocation;
    private String callResult;
    private String callType;
    private String connectionType;
    
}
