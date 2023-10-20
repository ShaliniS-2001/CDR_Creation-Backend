package com.cdr.main.entity;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MmsCdr {
	
	private String messageTimestamp;
    private String deliveryTimestamp;
    private String senderNumber;
    private String recipientNumber;
    private String mediaType;
    private String mediaFormat;
    private String mediaStatus;
    private String messageDirection;
    private String networkProvider;
    private String messageID;

}
