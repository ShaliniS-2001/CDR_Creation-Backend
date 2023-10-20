package com.cdr.main.entity;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LocationCdr {
	    private String callId;
	    private String startTimeStr;
	    private String endTimeStr;
	    private String duration;
	    private String callType;
	    private String callResult;
	    private String callDirection;
	    private String callerNumber;
	    private double callerLatitude;
	    private double callerLongitude;
	    private String calleeNumber;
	    private double calleeLatitude;
	    private double calleeLongitude;
	    private int signalStrength;
	    private String cellTower;
}
