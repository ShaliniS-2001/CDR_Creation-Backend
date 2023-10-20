package com.cdr.main.entity;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DataCdr {
	private String dataSessionStartTime;
    private String dataSessionEndTime;
    private double totalDataConsumed;
    private long durationOfDataUsed;
    private String sessionId;
    private String connectionType;
    private String networkProvider;
    private String deviceOS;
    private String usageAlert;

}
