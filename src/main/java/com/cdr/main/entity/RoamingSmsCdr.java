package com.cdr.main.entity;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoamingSmsCdr {
	
	    private String imsi;
	    private String messageTimestamp;
	    private String deliveryTimestampStr;
	    private String senderMSISDN;
	    private String callerHomeLocation;
	    private String recipientMSISDN;
	    private String recipientLocation;
	    private String roamingLocation;
	    private String messageContent;
	    private String messageStatusResult;
	    private String messageDirectionResult;    
	    private String cellId;
}
