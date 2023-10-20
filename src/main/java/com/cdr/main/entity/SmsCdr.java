package com.cdr.main.entity;

import org.springframework.stereotype.Component;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SmsCdr {
	    private String timestamp;
	    private String deliveryTimestamp;
	    private String senderNumber;
	    private String receiverNumber;
	    private String messageContent;
	    private Integer messageLength;
	    private String messageType;
	    private String messageStatus;
	    private String messageDirection;
	    private String messageID;
	    private String connectionType;

}
