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
public class SubscriberCdr {
	
	private String subscriberId;
    private String userNumber;
    private String ipAddress;
    private String activityType;
    private Date timestamp;
    private String updateType;
    private String details;
    private String connectionType;
    private String deviceOS;

}
