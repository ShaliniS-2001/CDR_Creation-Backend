package com.cdr.main.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cdr.main.entity.DataCdr;
import com.cdr.main.entity.LocationCdr;
import com.cdr.main.entity.MmsCdr;
import com.cdr.main.entity.Registration;
import com.cdr.main.entity.RoamingSmsCdr;
import com.cdr.main.entity.RoamingVoiceCdr;
import com.cdr.main.entity.SmsCdr;
import com.cdr.main.entity.SubscriberCdr;
import com.cdr.main.entity.VoIPCdr;
import com.cdr.main.entity.VoIPVideoCdr;
import com.cdr.main.entity.VoiceCdr;
import com.cdr.main.service.Cdr_Service;

@RestController
public class FrontController {
	@Autowired
	Cdr_Service  service;
	
	@CrossOrigin(origins="http://localhost:4200")
	@PostMapping("/register")
	public String userRegistration(@RequestBody Registration reg)
	{
		service.addUser(reg);
		return "USER ADDED";
	} 
    
	@CrossOrigin(origins="http://localhost:4200")
	@GetMapping("/register/search/{phone}")
	@ResponseBody
	public List<Registration> searchPhone(@PathVariable("phone") String phone){
		return service.findPhone(phone);
	}
	
	//-----------------------------VOICE CDR--------------------------------------------
	@CrossOrigin(origins="http://localhost:4200")
	@GetMapping("/voice/{quantity}")
	@ResponseBody
	public List<VoiceCdr> displayVoiceCdr(@PathVariable("quantity") int quantity){
		return service.voiceCDR(quantity);
	}
	
	//-----------------------------SMS CDR--------------------------------------------
	@CrossOrigin(origins="http://localhost:4200")
	@GetMapping("/sms/{value}")
	@ResponseBody
	public List<SmsCdr> displaySmsCdr(@PathVariable("value") int value){
		return service.smsCdr(value);
	}
	
	//-------------------------DATA CDR---------------------------------------------------
	@CrossOrigin(origins="http://localhost:4200")
	@GetMapping("/data/{quantity}")
	@ResponseBody
	public List<DataCdr> displayDataCdr(@PathVariable("quantity") int quantity){
		return service.dataCdr(quantity);
	}
	
	//----------------------------------MMS CDR-------------------------------------------
	
	@CrossOrigin(origins="http://localhost:4200")
	@GetMapping("/mms/{value}")
	@ResponseBody
	public List<MmsCdr> displayMmsCdr(@PathVariable("value") int value){
		return service.mmsCdr(value);
	}
	
	//-----------------------------VoIP AUDIO CDR------------------------------------------------
	
	@CrossOrigin(origins="http://localhost:4200")
	@GetMapping("/voip/{value}")
	@ResponseBody
	public List<VoIPCdr> displayVoipCdr(@PathVariable("value") int value){
		System.out.println(service.voipCdr(value));
		return service.voipCdr(value);
	}
	
	//---------------------------------VoIP VIDEO CDR-----------------------------------------------------
	@CrossOrigin(origins="http://localhost:4200")
	@GetMapping("/voipvideo/{value}")
	@ResponseBody
	public List<VoIPVideoCdr> displayVoipVideoCdr(@PathVariable("value") int value){
		System.out.println(service.voipVideoCdr(value));
		return service.voipVideoCdr(value);
	}
	
	//----------------------------Roaming Voice CDR-----------------------------------------------------
	
	@CrossOrigin(origins="http://localhost:4200")
	@GetMapping("/roaming/{value}")
	@ResponseBody
	public List<RoamingVoiceCdr> displayRoamingVoiceCdr(@PathVariable("value") int value){
		
		return service.roamingVoiceCdr(value);
	}
	//-----------------------------------Roaming sms CDR--------------------------------------------------
	
	@CrossOrigin(origins="http://localhost:4200")
	@GetMapping("/roamingsms/{value}")
	@ResponseBody
	public List<RoamingSmsCdr> displayRoamingSmsCdr(@PathVariable("value") int value){
		System.out.println(service.roamingSmsCdr(value));
		return service.roamingSmsCdr(value);
	}
	//----------------------------------Location CDR-------------------------------------------------------
	
	@CrossOrigin(origins="http://localhost:4200")
	@GetMapping("/location/{value}")
	@ResponseBody
	public List<LocationCdr> displayLocationCdr(@PathVariable("value") int value){
		System.out.println(service.locationCdr(value));
		return service.locationCdr(value);
	}
	//---------------------------------Subscriber Activity CDR---------------------------------------------
	
	@CrossOrigin(origins="http://localhost:4200")
	@GetMapping("/subscriber/{value}")
	@ResponseBody
	public List<SubscriberCdr> displaySubscriberCdr(@PathVariable("value") int value){
		return service.subscriberCdr(value);
	}
	
	
	
	
}
