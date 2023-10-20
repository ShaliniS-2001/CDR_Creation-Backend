package com.cdr.main.service;

import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
import com.cdr.main.repository.Repository;
import java.text.DecimalFormat;
@Service
public class Cdr_Service {
	@Autowired
	Repository repo;
	
	//To add users to database
	public void addUser(Registration r)
	{
		repo.save(r);
	}
	
	//To check if phone number exist or not
	public List<Registration> findPhone(String phone){
		List<Registration> x=repo.findByPhone(phone);
		System.out.println("From service:"+x);
		return x;
		
	}

	
	//---------------------VOICE CDR-------------------------------------------------------
	
	public List<VoiceCdr> voiceCDR (int quantity)

	{
		    List<VoiceCdr>cdrs = new ArrayList();
	        Random rand = new Random();
	        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	        Date currentDate = new Date();
	        
	        
	        try {
	        	File cdr = new File("cdrVoice.txt");
	            if (cdr.exists()) {
	            	cdr.delete();
	            } 
	        } catch (Exception e) {
	            e.printStackTrace();
	        }

	        	
	        
	        for (int i = 0; i < quantity; i++)
	        {
	            Date startDate = generateRandomDate(2022, currentDate, rand);
	            String callerPhoneNumber = "+91" + (6 + rand.nextInt(4)) + (rand.nextInt(900000000) + 100000000);
	            String receiverPhoneNumber = "+91" + (6 + rand.nextInt(4)) + (rand.nextInt(900000000) + 100000000);
	            String[] callResults = {"Connected","Connected","Busy", "Connected","Missed","Connected"};
	            String callResult = callResults[rand.nextInt(callResults.length)];
	            String callDirection = (callResult.equals("Connected")) ? "Outgoing" : "Incoming";
	            int duration = 0;
	            if (callResult.equals("Connected")) {
	                duration = rand.nextInt(7200 - 5) + 5; // Random duration between 5 seconds and 120 minutes
	            }
	            Date callStartTime = new Date(startDate.getTime() - duration * 1000);
	            Date callEndTime = startDate;
	            String startTimeStr = dateFormat.format(callStartTime);
	            String endTimeStr = dateFormat.format(callEndTime);
	            String callID = callerPhoneNumber.substring(3) + "-" + startTimeStr.substring(0, 4)
	                    + startTimeStr.substring(5, 7) + startTimeStr.substring(8, 10)
	                    + startTimeStr.substring(11, 13) + startTimeStr.substring(14, 16) + startTimeStr.substring(17);

	            VoiceCdr vcdr = new VoiceCdr();
	            vcdr.setCallStartTime(startTimeStr);
	            vcdr.setCallEndTime(endTimeStr);
	            vcdr.setCallDurationMinutes(duration / 60);
	            vcdr.setCallerNumber(callerPhoneNumber);
	            vcdr.setRecipientNumber(receiverPhoneNumber);
	            vcdr.setCallType("Voice");
	            vcdr.setCallResult(callResult);
	            vcdr.setCallID(callID);
	            vcdr.setCallDirection(callDirection);
	            cdrs.add(vcdr);	
	           
	            
	            try {
	                File cdr = new File("cdrVoice.txt");
	                // Use a FileWriter in append mode
	                FileWriter writer = new FileWriter(cdr,true);
	                
	                // Create a BufferedWriter for efficient writing
	                BufferedWriter bufferWriter = new BufferedWriter(writer);
	                
	                // Append the content
	                bufferWriter.write(vcdr.toString() + "\n");
	                
	                // Close the BufferedWriter
	                bufferWriter.close();
	         
	            } catch (Exception e) {
	                e.printStackTrace();
	            }



//	            
	           }
	        return cdrs;
	}
	public Date generateRandomDate(int startYear, Date endDate, Random rand)
	{
        long startMillis = new Date(startYear - 1900, 0, 1).getTime();
        long endMillis = endDate.getTime();
        long randomMillisSinceEpoch = startMillis + (long) (rand.nextDouble() * (endMillis - startMillis));
        return new Date(randomMillisSinceEpoch);

    }
	
   //------------------------------END OF VOICE CDR--------------------------------------------------
	
	
	//-----------------------------------SMS CDR--------------------------------------------------
	
	public List<SmsCdr> smsCdr(int value){
		List<SmsCdr> sms=new ArrayList();
		 Random rand = new Random();
	        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	        String[] messages = {
	    "Hello, how are you?",
	    "Meeting at 3 PM",
	    "Don't forget to call",
	    "Dinner at 7 PM",
	    "See you soon",
	    "Happy birthday!",
	    "Your account balance is low.",
	    "Your loan application has been approved.",
	    "Alert: Unusual activity on your account.",
	    "Your account password has been changed.",
	    "A new payee has been added to your account.",
	    "Your account is overdrawn.",
	    "Please call me",
	    "Need your help",
	    "Great news!",
	    "Miss you",
	    "Running late",
	    "Thanks a lot",
	    "Let's catch up",
	    "Are you free now?",
	    "See you tomorrow",
	    "Emergency, call me",
	    "Good morning!",
	    "Good night!",
	    "How's it going?",
	    "Lunch at 1 PM",
	    "Let's plan a weekend trip",
	    "Need your opinion on something",
	    "Can we chat later?",
	    "Just checking in",
	    "Have a fantastic day!",
	    "I love you!",
	    "How's the weather there?",
	    "Can you pick up some groceries?",
	    "What's for dinner?",
	    "Thinking of you",
	    "I'm here for you",
	    "Feeling great today",
	    "Have a safe journey",
	    "Remember to smile",
	    "Enjoy the little things",
	    "Need some good vibes",
	    "You're amazing!",
	    "Let's celebrate soon",
	    "Sending hugs",
	    "Don't stress, relax",
	    "You got this!",
	    "Life is beautiful",
	    "Stay positive",
	    "Follow your dreams",
	    "Believe in yourself",
	    "Love and laughter",
	    "Good vibes only",
	    "Cherish the moment",
	    "Never give up!",
	    "Explore new horizons",
	    "Boost your productivity",
	    "Confirmation code: 112233 for password reset.",
	    "Verify your phone number with code 444888.",
	    "Use code 777999 to confirm your new account.",
	    "Your access code for online banking is: 987123",
	    "Code 987789 for two-factor authentication.",
	    "Your code for secure transaction: 123789",
	    "Confirmation code: 543210 for account setup.",
	    "Unlock your potential",
	    "Stay focused and driven",
	    "Build your network",
	    "Seize the day with confidence",
	    "Professionalism at its best",
	    "Achieve your goals",
	    "Elevate your career",
	    "Business success is yours",
	    "Your success is our priority",
	    "Innovation and excellence",
	    "Unleash your creativity",
	    "Unlock new opportunities",
	    "Promote your brand with us",
	    "Quality and reliability",
	    "Best deals and discounts",
	    "Exclusive offer just for you",
	    "Limited time offer, don't miss out",
	    "Upgrade your lifestyle",
	    "Invest in your future",
	    "Enhance your skills",
	    "Your satisfaction is our success",
	    "Thank you for your trust and support",
	    "Your account has been credited with a deposit.",
	    "Insufficient funds in your account.",
	    "A new transaction has been made on your account.",
	    "Payment due on your credit card.",
	    "Your credit limit has been increased.",
	    "Transaction declined due to insufficient funds.",
	    "Your account has been locked for security.",
	    "Account statement for this month is available.",
	    "Payment received on your account.",
	    "Your account has been temporarily suspended.",
	    "Your account has been credited with interest.",
	    "Your credit card payment is due soon.",
	    "Transaction confirmed: Thank you for your purchase.",
	    "Your account has been debited for a payment.",
	    "A new direct deposit has been made to your account.",
	    "Account verification successful: Welcome aboard!",
	    "Your one-time verification code is: 123456",
	    "Use code 987654 to confirm your email address.",
	    "Your secure login code is: 654321",
	    
	    };
	        
	        try {
	        	File cdr = new File("cdrSMS.txt");
	            if (cdr.exists()) {
	            	cdr.delete();
	            } 
	          } catch (Exception e) {
	            e.printStackTrace();
	          }


	        for (int i = 0; i < value; i++) {
	            // random timestamps within the year 2022-2023
	            Date timestamp = generateRandomDate1(2022, new Date(), rand);

	            // random sender and receiver phone numbers in Indian format
	            String senderNumber = generateIndianPhoneNumber(rand);
	            String receiverNumber = generateIndianPhoneNumber(rand);

	            // random message from the array
	            String messageContent = messages[rand.nextInt(messages.length)];

	            // Calculate message length
	            int messageLength = messageContent.length();

	            // random message type and status
	            String messageType = "SMS";
	            String[] messageStatus = {"Delivered", "Failed"};
	            String messageStatusResult = messageStatus[rand.nextInt(messageStatus.length)];

	            // random message direction
	            String[] messageDirection = {"Outgoing", "Incoming"};
	            String messageDirectionResult = messageDirection[rand.nextInt(messageDirection.length)];

	            //unique Message ID
	            String messageID = generateMessageID(rand);

	            // random Connection Type
	            String[] connectionTypes = {"4G LTE", "5G LTE", "3G LTE"};
	            String connectionType = connectionTypes[rand.nextInt(connectionTypes.length)];

	            // Format the timestamp
	            String timestampStr = dateFormat.format(timestamp);

	            // Initialize the Delivery Timestamp
	            String deliveryTimestampStr = "N/A"; // Default to "N/A"

	            if (messageStatusResult.equals("Delivered")) {
	                // Generate a random delivery timestamp between 1 to 5 minutes greater than Timestamp
	                long timestampMillis = timestamp.getTime();
	                long deliveryTimestampMillis = timestampMillis + (rand.nextInt(5) + 1) * 60 * 1000;
	                Date deliveryTimestamp = new Date(deliveryTimestampMillis);
	                deliveryTimestampStr = dateFormat.format(deliveryTimestamp);
	            }
	            
	            SmsCdr obj=new SmsCdr();
	            
	            obj.setTimestamp(timestampStr);
	            obj.setDeliveryTimestamp(deliveryTimestampStr);
	            obj.setSenderNumber(senderNumber);
	            obj.setReceiverNumber(receiverNumber);
	            obj.setMessageContent(messageContent);
	            obj.setMessageLength(messageLength);
	            obj.setMessageType(messageType);
	            obj.setMessageStatus(messageStatusResult);
	            obj.setMessageDirection(messageDirectionResult);
	            obj.setMessageID(messageID);
	            obj.setConnectionType(connectionType);
	            
	            sms.add(obj);
	            //--------------CREATING A TEXT FILE FOR SMS CDR--------------------
	            
	            try {
	                File cdr = new File("cdrSMS.txt");
	                // Use a FileWriter in append mode
	                FileWriter writer = new FileWriter(cdr,true);
	                
	                // Create a BufferedWriter for efficient writing
	                BufferedWriter bufferWriter = new BufferedWriter(writer);
	                
	                // Append the content
	                bufferWriter.write(obj.toString() + "\n");
	                
	                // Close the BufferedWriter
	                bufferWriter.close();
	         
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	        }
	        
	        return sms;
	    }

	    public  Date generateRandomDate1(int startYear, Date endDate, Random rand) {
	        long startMillis = new Date(startYear - 1900, 0, 1).getTime();
	        long endMillis = endDate.getTime();
	        long randomMillisSinceEpoch = startMillis + (long) (rand.nextDouble() * (endMillis - startMillis));
	        return new Date(randomMillisSinceEpoch);
	    }

	    public static String generateIndianPhoneNumber(Random rand) {
	        String[] prefixes = {"6", "7", "8", "9"};
	        String phoneNumber = "+91" + prefixes[rand.nextInt(prefixes.length)];
	        for (int i = 0; i < 8; i++) {
	            phoneNumber += rand.nextInt(10);
	        }
	        return phoneNumber;
	    }
	    

	    public static String generateMessageID(Random rand) {
	        String firstPart = String.format("%010d", rand.nextInt(1000000000));
	        String secondPart = String.format("%08d", rand.nextInt(100000000));
	        String thirdPart = String.format("%06d", rand.nextInt(1000000));
	        return firstPart + "-" + secondPart + "-" + thirdPart;
	    }
	   
	 //---------------------------------END OF SMS CDR------------------------------------------------

	//---------------------------------DATA CDR------------------------------------------------
	    public List<DataCdr> dataCdr(int quantity){
			List<DataCdr> dataArr=new ArrayList();
		        Random rand = new Random();
		        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		        
		        try {
		        	File cdr = new File("DataCdr.txt");
		            if (cdr.exists()) {
		            	cdr.delete();
		            } 
		          } catch (Exception e) {
		            e.printStackTrace();
		          }

		        for (int i = 0; i < quantity; i++) {
		            //random Data Session start time within the year 2022-2023
		            Date startTime = generateRandomDateForData(2022, new Date(), rand);

		            // random Data Session end time within the same date as start time
		            long minMillis = 5 * 60 * 1000; // 5 minutes
		            long maxMillis = 120 * 60 * 1000; // 2 hours
		            long timeDifferenceMillis = minMillis + rand.nextInt((int) (maxMillis - minMillis + 1));
		            Date endTime = new Date(startTime.getTime() + timeDifferenceMillis);

		            // "Duration Of Data Used" based on time difference
		            long durationMillis = timeDifferenceMillis / (1000 * 60); // Convert to minutes

		            //"Total Data Consumed" based on the "Duration Of Data Used"
		            double dataConsumed = calculateDataConsumed(durationMillis);	            

		            // a random Session ID with 16 characters (odd: alphabet, even: number)
		            String sessionID = generateRandomSessionID(rand);

		            // random Connection Type
		            String[] connectionTypes = {"5G LTE", "4G LTE", "3G LTE"};
		            String connectionType = connectionTypes[rand.nextInt(connectionTypes.length)];

		            // random Network Provider
		            String[] networkProviders = {"Airtel", "Vodafone", "Jio", "BSNL", "Aircel", "Docomo", "Idea Cellular", "Reliance Communications"};
		            String networkProvider = networkProviders[rand.nextInt(networkProviders.length)];

		            //random Device OS
		            String[] deviceOS = {"Android", "iOS", "Windows 10", "Windows 11", "macOS", "Linux", "Ubuntu", "Chrome OS"};
		            String deviceOSResult = deviceOS[rand.nextInt(deviceOS.length)];

		            //random Usage Alerts
		            String[] usageAlerts = {"Alert at 80% of usage", "Alert at 90% of usage", "Alert at 50% of usage"};
		            String usageAlert = usageAlerts[rand.nextInt(usageAlerts.length)];

		            // Format the timestamps
		            String startTimeStr = dateFormat.format(startTime);
		            String endTimeStr = dateFormat.format(endTime);

		            
		            DataCdr dataObj=new DataCdr();
		            dataObj.setDataSessionStartTime(startTimeStr);
		            dataObj.setDataSessionEndTime(endTimeStr);
		            dataObj.setTotalDataConsumed(dataConsumed);
		            dataObj.setDurationOfDataUsed(durationMillis);
		            dataObj.setSessionId(sessionID);
		            dataObj.setConnectionType(connectionType);
		            dataObj.setNetworkProvider(networkProvider);
		            dataObj.setDeviceOS(deviceOSResult);
		            dataObj.setUsageAlert(usageAlert);
		            
		            dataArr.add(dataObj);
		            System.out.println(dataObj);
		           		           
		            try {
		                File cdr = new File("DataCdr.txt");
		                // Use a FileWriter in append mode
		                FileWriter writer = new FileWriter(cdr,true);
		                
		                // Create a BufferedWriter for efficient writing
		                BufferedWriter bufferWriter = new BufferedWriter(writer);
		                
		                // Append the content
		                bufferWriter.write(dataObj.toString() + "\n");
		                
		                // Close the BufferedWriter
		                bufferWriter.close();
		         
		            } catch (Exception e) {
		                e.printStackTrace();
		            }
		         
		        }
		        System.out.println(dataArr);
				return dataArr;
		    }

		    public static Date generateRandomDateForData(int startYear, Date endDate, Random rand) {
		        long startMillis = new Date(startYear - 1900, 0, 1).getTime();
		        long endMillis = endDate.getTime();
		        long randomMillisSinceEpoch = startMillis + (long) (rand.nextDouble() * (endMillis - startMillis));
		        return new Date(randomMillisSinceEpoch);
		    }

		    public static double calculateDataConsumed(long durationMinutes) {
		        if (durationMinutes >=20 && durationMinutes<=30 ) {
		            return 400; // 120 minutes or more: 1.5GB, 100-119 minutes: 1GB
		        } else if (durationMinutes>30 && durationMinutes<40) {
		            return 500; // 90-99 minutes: 500MB, 80-89 minutes: 1GB
		        } else if (durationMinutes >40 && durationMinutes<50) {
		            return 600; // 70-79 minutes: 500MB, 60-69 minutes: 400MB
		        } else if (durationMinutes >= 40 && durationMinutes<=50) {
		            return 700; // 50-59 minutes: 400MB, 40-49 minutes: 200MB
		        } else if (durationMinutes >50 && durationMinutes<=60) {
		            return 800; // 30-39 minutes: 200MB
		        } else if (durationMinutes>60 && durationMinutes<=70) {
		            return 1000; // 20-29 minutes: 150MB
		        } else if (durationMinutes>70 && durationMinutes<=90) {
		            return 1500; // 20-29 minutes: 150MB
		        } else if (durationMinutes>90 && durationMinutes<=120) {
		            return 2000; // 20-29 minutes: 150MB
		        }else if (durationMinutes>15 && durationMinutes<=20) {
		            return 200; // 20-29 minutes: 150MB
		        } else if (durationMinutes>10 && durationMinutes<=15) {
		            return 100; // 20-29 minutes: 150MB
		        }  else {
		            return 50; // Below 20 minutes: 100MB
		        }
		    }

		    public static String generateRandomSessionID(Random rand) {
		        StringBuilder sessionID = new StringBuilder();
		        for (int i = 0; i < 16; i++) {
		            if (i % 2 == 0) {
		                // Even position:random number (0-9)
		                sessionID.append(rand.nextInt(10));
		            } else {
		                // Odd position: random alphabet (a-z)
		                char randomChar = (char) ('a' + rand.nextInt(26));
		                sessionID.append(randomChar);
		            }
		        }
		        return sessionID.toString();
		    }
		    
	//------------------------------MMS CDR------------------------------------------------
		    
		    public List<MmsCdr> mmsCdr(int value){
				List<MmsCdr> mmsArr=new ArrayList();
			        Random rand = new Random();
			        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			        SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");
			        
			        try {
			        	File cdr = new File("MmsCdr.txt");
			            if (cdr.exists()) {
			            	cdr.delete();
			            } 
			          } catch (Exception e) {
			            e.printStackTrace();
			          }

			        for (int i = 0; i < value; i++) {
			            // random Message Timestamp within the year 2022-2023
			            Date messageTimestamp = generateRandomDateForMms(2022, new Date(), rand);

			            //random Delivery Timestamp within 1-5 minutes of Message Timestamp
			            long deliveryTimeDifferenceMillis = 60 * 1000 + rand.nextInt(5 * 60 * 1000);
			            Date deliveryTimestamp = new Date(messageTimestamp.getTime() + deliveryTimeDifferenceMillis);

			            // random Sender and Recipient numbers
			            String senderNumber = "+91" + generateRandomPhoneNumber(rand);
			            String recipientNumber = "+91" + generateRandomPhoneNumber(rand);

			            // random Media Type
			            String[] mediaTypes = {"Audio", "Video", "Image"};
			            String mediaType = mediaTypes[rand.nextInt(mediaTypes.length)];

			            // random Media Format based on the Media Type
			            String mediaFormat = generateRandomMediaFormat(mediaType, rand);

			            // random Media Status (Delivered or Failed)
			            String[] mediaStatuses = {"Delivered", "Failed"};
			            String mediaStatus = mediaStatuses[rand.nextInt(mediaStatuses.length)];

			            // Message Direction (Outgoing or Incoming)
			            String messageDirection = (rand.nextBoolean()) ? "Outgoing" : "Incoming";

			            // random Network Provider
			            String[] networkProviders = {"Airtel", "Jio", "Aircel", "Docomo", "Vodafone", "BSNL"};
			            String networkProvider = networkProviders[rand.nextInt(networkProviders.length)];

			            // Message ID
			            String year = yearFormat.format(messageTimestamp);
			            String threeLetterCode = generateRandomThreeLetterCode(rand);
			            String eightDigitNumber = generateRandomEightDigitNumber(rand);
			            String messageID = "MMS-" + year + "-" + threeLetterCode + eightDigitNumber;

			            // Format the timestamps
			            String messageTimestampStr = dateFormat.format(messageTimestamp);
			            String deliveryTimestampStr = (mediaStatus.equals("Delivered")) ? dateFormat.format(deliveryTimestamp) : "N/A";

			            MmsCdr mmsObj=new MmsCdr();
			            mmsObj.setMessageTimestamp(messageTimestampStr);
			            mmsObj.setDeliveryTimestamp(deliveryTimestampStr);
			            mmsObj.setSenderNumber(senderNumber);
			            mmsObj.setRecipientNumber(recipientNumber);
			            mmsObj.setMediaType(mediaType);
			            mmsObj.setMediaFormat(mediaFormat);
			            mmsObj.setMediaStatus(mediaStatus);
			            mmsObj.setMessageDirection(messageDirection);
			            mmsObj.setNetworkProvider(networkProvider);
			            mmsObj.setMessageID(messageID);
			            
			            mmsArr.add(mmsObj);
			            
			            try {
			                File cdr = new File("MmsCdr.txt");
			                // Use a FileWriter in append mode
			                FileWriter writer = new FileWriter(cdr,true);
			                
			                // Create a BufferedWriter for efficient writing
			                BufferedWriter bufferWriter = new BufferedWriter(writer);
			                
			                // Append the content
			                bufferWriter.write(mmsObj.toString() + "\n");
			                
			                // Close the BufferedWriter
			                bufferWriter.close();
			         
			            } catch (Exception e) {
			                e.printStackTrace();
			            }
			            
			        }
					return mmsArr;
			    }

			    public static Date generateRandomDateForMms(int startYear, Date endDate, Random rand) {
			        long startMillis = new Date(startYear - 1900, 0, 1).getTime();
			        long endMillis = endDate.getTime();
			        long randomMillisSinceEpoch = startMillis + (long) (rand.nextDouble() * (endMillis - startMillis));
			        return new Date(randomMillisSinceEpoch);
			    }

			    public static String generateRandomPhoneNumber(Random rand) {
			        //10-digit phone number starting with 9, 8, 7, or 6
			        StringBuilder phoneNumber = new StringBuilder();
			        phoneNumber.append(rand.nextInt(4) + 6); 

			        for (int i = 1; i < 10; i++) {
			            phoneNumber.append(rand.nextInt(10));
			        }

			        return phoneNumber.toString();
			    }

			    public static String generateRandomMediaFormat(String mediaType, Random rand) {
			        if (mediaType.equals("Image")) {
			            String[] imageFormats = {"JPEG", "PNG"};
			            return imageFormats[rand.nextInt(imageFormats.length)];
			        } else if (mediaType.equals("Video")) {
			            String[] videoFormats = {"MP4", "AVI", "FLV", "MKV", "MOV"};
			            return videoFormats[rand.nextInt(videoFormats.length)];
			        } else if (mediaType.equals("Audio")) {
			            String[] audioFormats = {"MP3", "AAC", "WAV", "FLAC", "WMA"};
			            return audioFormats[rand.nextInt(audioFormats.length)];
			        }
			        return "Unknown";
			    }
			    
			            // Function to generate a random three-letter code
			     private static String generateRandomThreeLetterCode(Random rand) {
			        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
			        StringBuilder code = new StringBuilder();
			        for (int i = 0; i < 3; i++) {
			            char randomChar = alphabet.charAt(rand.nextInt(alphabet.length()));
			            code.append(randomChar);
			        }
			        return code.toString();
			    }
			    
			    // Function to generate a random eight-digit number
			    private static String generateRandomEightDigitNumber(Random rand) {
			        StringBuilder number = new StringBuilder();
			        for (int i = 0; i < 8; i++) {
			            number.append(rand.nextInt(10));
			        }
			        return number.toString();
			    }

			    public static String generateRandomMessageID(Random rand) {
			        StringBuilder messageID = new StringBuilder();
			        for (int i = 0; i < 11; i++) {
			            if (i % 4 == 0) {
			                
			                messageID.append("-");
			            } else if (i % 2 == 0) {
			                
			                messageID.append(rand.nextInt(10));
			            } else {
			                
			                char randomChar = (char) ('a' + rand.nextInt(26));
			                messageID.append(randomChar);
			            }
			        }
			        return messageID.toString();
				
		    }	
			    
	 //----------------------------VoIP AUDIO CDR-----------------------------------------------
			    public List<VoIPCdr> voipCdr(int value){
					List<VoIPCdr> voipArr=new ArrayList();
					 Random rand = new Random();
				        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				        Date currentDate = new Date();
				        try {
				        	File cdr = new File("VoipAudioCdr.txt");
				            if (cdr.exists()) {
				            	cdr.delete();
				            } 
				          } catch (Exception e) {
				            e.printStackTrace();
				          }
				        for (int i = 0; i < value; i++) {

				            Date startDate = generateRandomDateForVoip(2022, currentDate, rand);
				            // random Caller and Callee IP Addresses
				            String callerIpAddress = generateRandomIpAddress(rand);
				            String calleeIpAddress = generateRandomIpAddress(rand);

				            String[] callResults = {"Connected", "Connected", "Busy", "Connected", "Missed", "Connected"};
				            String callResult = callResults[rand.nextInt(callResults.length)];

				            // an array of possible Jitter values
				            String[] jitterValues = {"5ms", "6ms", "7ms", "8ms", "9ms", "10ms"};
				            // random Jitter value
				            String jitter = jitterValues[rand.nextInt(jitterValues.length)];

				            //  Packet Loss based on Jitter
				            double packetLoss = calculatePacketLoss(jitter);

				            // random Call Type
				            String callType = "VoIP";

				            //Call Direction (Outgoing or Incoming)
				            String callDirection = (rand.nextBoolean()) ? "Outgoing" : "Incoming";

				            int duration = 0;
				            if (callResult.equals("Connected")) {
				                duration = rand.nextInt(7200 - 5) + 5; // Random duration between 5 seconds and 120 minutes
				            }
				            
				            int minutes = duration / 60;
				            int seconds = duration % 60;
				            String duration1=minutes+" minutes "+seconds+" seconds";
				            Date callStartTime = new Date(startDate.getTime() - duration * 1000);
				            Date callEndTime = startDate;
				            String startTimeStr = dateFormat.format(callStartTime);
				            String endTimeStr = dateFormat.format(callEndTime);
				            
				         // Data Consumed based on call duration
				            double dataConsumed = calculateDataConsumedForVoice(minutes);

				            VoIPCdr voipObj=new VoIPCdr();
				            voipObj.setCallStartTime(startTimeStr);
				            voipObj.setCallEndTime(endTimeStr);
				            voipObj.setCallerIpAddress(callerIpAddress);
				            voipObj.setCalleeIpAddress(calleeIpAddress);
				            voipObj.setDuration(duration1);
				            voipObj.setCallType(callType);
				            voipObj.setCallDirection(callDirection);
				            voipObj.setCallResult(callResult);
				            voipObj.setDataConsumed(dataConsumed);
				            voipObj.setJitter(jitter);
				            voipObj.setPacketLoss(packetLoss);
				            
				            voipArr.add(voipObj);
				           
				            try {
				                File cdr = new File("VoipAudioCdr.txt");
				                // Using a FileWriter in append mode
				                FileWriter writer = new FileWriter(cdr,true);
				                
				                // Create a BufferedWriter for efficient writing
				                BufferedWriter bufferWriter = new BufferedWriter(writer);
				                
				                // Appending the content
				                bufferWriter.write(voipObj.toString() + "\n");
				                
				                // Closing the BufferedWriter
				                bufferWriter.close();
				         
				            } catch (Exception e) {
				                e.printStackTrace();
				            }
				        }
				        return voipArr;
				    }

				    public static String generateRandomIpAddress(Random rand) {
				        //a random IP address (in the format "x.x.x.x")
				        StringBuilder ipAddress = new StringBuilder();
				        for (int i = 0; i < 4; i++) {
				            ipAddress.append(rand.nextInt(256)); // Each part is in the range 0-255
				            if (i < 3) {
				                ipAddress.append(".");
				            }
				        }
				        return ipAddress.toString();
				    }

				    public static double calculatePacketLoss(String jitter) {
				        // Packet Loss based on Jitter
				        switch (jitter) {
				            case "5ms":
				                return 0.0;
				            case "6ms":
				                return 0.1;
				            case "7ms":
				                return 0.2;
				            case "8ms":
				                return 0.3;
				            case "9ms":
				                return 0.4;
				            case "10ms":
				                return 0.5;
				            default:
				                return 0.0; 
				        }
				    }
				    public static double calculateDataConsumedForVoice(int minutes) {
				        //Data Consumed based on call duration
				        if (minutes >= 1 && minutes <= 5) {
				            return 0.5; // 10MB for 1-5 minutes
				        } else if (minutes >= 6 && minutes <= 10) {
				            return 2.5; // 20MB for 6-10 minutes
				        } else if (minutes >= 11 && minutes <= 15) {
				            return 5; // 60MB for 11-15 minutes
				        } else if (minutes >= 16 && minutes <= 30) {
				            return 10; // 120MB for 16-30 minutes
				        } else if (minutes >= 31 && minutes <= 60) {
				            return 15; // 250MB for 31-60 minutes
				        } else if (minutes >= 61 && minutes <= 90) {
				            return 40; // 500MB for 61-90 minutes
				        } else if (minutes >= 91 && minutes <= 120) {
				            return 60; // 1000MB for 91-120 minutes
				        }
				        else {
				        	return 0;
				        }
				    }  

				    public static Date generateRandomDateForVoip(int startYear, Date endDate, Random rand) {
				        long startMillis = new Date(startYear - 1900, 0, 1).getTime();
				        long endMillis = endDate.getTime();
				        long randomMillisSinceEpoch = startMillis + (long) (rand.nextDouble() * (endMillis - startMillis));
				        return new Date(randomMillisSinceEpoch);
				    }  
				    
	//----------------------------------------	VoIP VIDEO CDR------------------------------------------------
				    public List<VoIPVideoCdr> voipVideoCdr(int value){
						List<VoIPVideoCdr> voipVideoArr=new ArrayList();
						 Random rand = new Random();
					        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					        Date currentDate = new Date();
					        
					        try {
					        	File cdr = new File("VoipVideoCdr.txt");
					            if (cdr.exists()) {
					            	cdr.delete();
					            } 
					          } catch (Exception e) {
					            e.printStackTrace();
					          }
					        for (int i = 0; i < value; i++) {

					            Date startDate = generateRandomDateForVoip(2022, currentDate, rand);
					            // random Caller and Callee IP Addresses
					            String callerIpAddress = generateRandomIpAddressVoip(rand);
					            String calleeIpAddress = generateRandomIpAddressVoip(rand);

					            String[] callResults = {"Connected", "Connected", "Busy", "Connected", "Missed", "Connected"};
					            String callResult = callResults[rand.nextInt(callResults.length)];

					            // array of possible Jitter values
					            String[] jitterValues = {"5ms", "6ms", "7ms", "8ms", "9ms", "10ms"};
					            // random Jitter value
					            String jitter = jitterValues[rand.nextInt(jitterValues.length)];

					            // Packet Loss based on Jitter
					            double packetLoss = calculatePacketLossVoip(jitter);

					            // random Call Type
					            String callType = "VoIP";

					            //Call Direction (Outgoing or Incoming)
					            String callDirection = (rand.nextBoolean()) ? "Outgoing" : "Incoming";
					            
					            // random Connection Type
					            String[] connectionTypes = {"5G LTE", "4G LTE", "3G LTE"};
					            String connectionType = connectionTypes[rand.nextInt(connectionTypes.length)];

					            //random Network Provider
					            String[] networkProviders = {"Airtel", "Vodafone", "Jio", "BSNL", "Aircel", "Docomo", "Idea Cellular", "Reliance Communications"};
					            String networkProvider = networkProviders[rand.nextInt(networkProviders.length)];
					         

					            int duration = 0;
					            if (callResult.equals("Connected")) {
					                duration = rand.nextInt(7200 - 5) + 5; // Random duration between 5 seconds and 120 minutes
					            }
					            
					            int minutes = duration / 60;
					            int seconds = duration % 60;
					            String duration1=minutes+" minutes "+seconds+" seconds";
					            Date callStartTime = new Date(startDate.getTime() - duration * 1000);
					            Date callEndTime = startDate;
					            String startTimeStr = dateFormat.format(callStartTime);
					            String endTimeStr = dateFormat.format(callEndTime);
					            
					         //Data Consumed based on call duration
					            double dataConsumed = calculateDataConsumed(minutes);

					            VoIPVideoCdr voipObj=new VoIPVideoCdr();
					            voipObj.setCallStartTime(startTimeStr);
					            voipObj.setCallEndTime(endTimeStr);
					            voipObj.setCallerIpAddress(callerIpAddress);
					            voipObj.setCalleeIpAddress(calleeIpAddress);
					            voipObj.setNetworkProviders(networkProvider);
					            voipObj.setConnectionTypes(connectionType);
					            voipObj.setDuration(duration1);
					            voipObj.setCallType(callType);
					            voipObj.setCallDirection(callDirection);
					            voipObj.setCallResult(callResult);
					            voipObj.setDataConsumed(dataConsumed);
					            voipObj.setJitter(jitter);
					            voipObj.setPacketLoss(packetLoss);
					            
					            voipVideoArr.add(voipObj);
					            				            
					            try {
					                File cdr = new File("VoipVideoCdr.txt");
					                // Using a FileWriter in append mode
					                FileWriter writer = new FileWriter(cdr,true);
					                
					                // BufferedWriter for efficient writing
					                BufferedWriter bufferWriter = new BufferedWriter(writer);
					                
					                // Appending the content
					                bufferWriter.write(voipObj.toString() + "\n");
					                
					                // Closing the BufferedWriter
					                bufferWriter.close();
					         
					            } catch (Exception e) {
					                e.printStackTrace();
					            }
					        }
					        return voipVideoArr;
					    }

					    public static String generateRandomIpAddressVoip(Random rand) {
					        // random IP address (in the format "x.x.x.x")
					        StringBuilder ipAddress = new StringBuilder();
					        for (int i = 0; i < 4; i++) {
					            ipAddress.append(rand.nextInt(256)); // Each part is in the range 0-255
					            if (i < 3) {
					                ipAddress.append(".");
					            }
					        }
					        return ipAddress.toString();
					    }

					    public static double calculatePacketLossVoip(String jitter) {
					        // Packet Loss based on Jitter
					        switch (jitter) {
					            case "5ms":
					                return 0.1;
					            case "6ms":
					                return 0.2;
					            case "7ms":
					                return 0.3;
					            case "8ms":
					                return 0.4;
					            case "9ms":
					                return 0.5;
					            case "10ms":
					                return 0.5;
					            default:
					                return 0.0; // Default packet loss
					        }
					    }
					    
					    public static double calculateDataConsumed(int minutes) {
					        //Data Consumed based on call duration
					        if (minutes >= 1 && minutes <= 5) {
					            return 10; // 10MB for 1-5 minutes
					        } else if (minutes >= 6 && minutes <= 10) {
					            return 20; // 20MB for 6-10 minutes
					        } else if (minutes >= 11 && minutes <= 15) {
					            return 60; // 60MB for 11-15 minutes
					        } else if (minutes >= 16 && minutes <= 30) {
					            return 120; // 120MB for 16-30 minutes
					        } else if (minutes >= 31 && minutes <= 60) {
					            return 250; // 250MB for 31-60 minutes
					        } else if (minutes >= 61 && minutes <= 90) {
					            return 500; // 500MB for 61-90 minutes
					        } else if (minutes >= 91 && minutes<= 120) {
					            return 1000; // 1000MB for 91-120 minutes
					        }
					        else {
					        	return 0;
					        }
					    }      

					    public static Date generateRandomDateForVoipVideo(int startYear, Date endDate, Random rand) {
					        long startMillis = new Date(startYear - 1900, 0, 1).getTime();
					        long endMillis = endDate.getTime();
					        long randomMillisSinceEpoch = startMillis + (long) (rand.nextDouble() * (endMillis - startMillis));
					        return new Date(randomMillisSinceEpoch);
					    }  
					    
					    
		//--------------------------------ROAMING VOICE CALL CDR-------------------------------------------------
					    
					    public List<RoamingVoiceCdr> roamingVoiceCdr(int value){
							List<RoamingVoiceCdr> roamingVoiceArr=new ArrayList();
							 Random rand = new Random();
						        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						        Date currentDate = new Date();
						        
						        try {
						        	File cdr = new File("RoamingVoiceCdr.txt");
						            if (cdr.exists()) {
						            	cdr.delete();
						            } 
						          } catch (Exception e) {
						            e.printStackTrace();
						          }
						        for (int i = 0; i < value; i++) {
						        	 Date startDate = generateRandomDateForVoip(2022, currentDate, rand);
						        	 
						        	   // random User ID (12-digit number)
						                String userId = generateRandomUserId(rand);
						                
						        	    //call result 
						        	    String[] callResults = {"Connected", "Connected", "Busy", "Connected", "Missed", "Connected"};
							            String callResult = callResults[rand.nextInt(callResults.length)];
							            
							            // random Call Type
							            String callType = "Roaming Voice";
							            
							            int duration = 0;
							            if (callResult.equals("Connected")) {
							                duration = rand.nextInt(7200 - 5) + 5; // Random duration between 5 seconds and 120 minutes
							            }
							            
							            //duration , start time,end time
							            int minutes = duration / 60;
							            int seconds = duration % 60;
							            String duration1=minutes+" minutes "+seconds+" seconds";
							            Date callStartTime = new Date(startDate.getTime() - duration * 1000);
							            Date callEndTime = startDate;
							            String startTimeStr = dateFormat.format(callStartTime);
							            String endTimeStr = dateFormat.format(callEndTime);
							            
							             //random Sender
							             String callerNumber = "+91" + generateRandomPhoneNumber(rand);
							            
							            // random Caller's Actual Location (IN_3 digit random number)
							             String callerActualLocation = "IN_" + generateRandom3DigitNumber(rand);
							            
							            //Recipient numbers
							            String calleeNumber = generateInternationalMobileNumber(rand);
							            
							           // random Callee Location
							            String calleeLocation = generateRandomCalleeLocation(rand);
							            
							           // Callee Location is different from Caller's Actual Location
							            while (calleeLocation.equals(callerActualLocation)) {
							                calleeLocation = generateRandomCalleeLocation(rand);
							            }

							            //random Roaming Location
							            String roamingLocation = generateRandomRoamingLocation(rand);
							            
							            
							            //random Connection Type
							            String[] connectionTypes = {"5G LTE", "4G LTE", "3G LTE"};
							            String connectionType = connectionTypes[rand.nextInt(connectionTypes.length)];
							            
							            
							            RoamingVoiceCdr obj=new RoamingVoiceCdr();
							            obj.setUserId(userId);
							            obj.setStartTimeStr(startTimeStr);
							            obj.setEndTimeStr(endTimeStr);
							            obj.setDuration(duration1);
							            obj.setCallerNumber(callerNumber);
							            obj.setCallerActualLocation(callerActualLocation);
							            obj.setCalleeNumber(calleeNumber);
							            obj.setCalleeLocation(calleeLocation);
							            obj.setRoamingLocation(roamingLocation);
							            obj.setCallResult(callResult);
							            obj.setCallType(callType);
							            obj.setConnectionType(connectionType);
							            
							            roamingVoiceArr.add(obj);
							            						           
							            try {
							                File cdr = new File("RoamingVoiceCdr.txt");
							                // Using a FileWriter in append mode
							                FileWriter writer = new FileWriter(cdr,true);
							                
							                // Creating a BufferedWriter for efficient writing
							                BufferedWriter bufferWriter = new BufferedWriter(writer);
							                
							                // Appending the content
							                bufferWriter.write(obj.toString() + "\n");
							                
							                // Closing the BufferedWriter
							                bufferWriter.close();
							         
							            } catch (Exception e) {
							                e.printStackTrace();
							            }

						        }
								return roamingVoiceArr;
						        
						        }
					    public static String generateRandomUserId(Random rand) {
					        StringBuilder userId = new StringBuilder();
					        for (int i = 0; i < 12; i++) {
					            userId.append(rand.nextInt(10)); 
					        }
					        return userId.toString();
					    }

					    public static String generateRandom3DigitNumber(Random rand) {
					        return String.format("%03d", rand.nextInt(1000));
					    }

					    public static String generateRandomCalleeLocation(Random rand) {
					        String[] locations = {"IN", "JP", "CA", "AU", "UK", "US", "FR", "DE", "ES", "TH", "MX", "BR"};
					        return locations[rand.nextInt(locations.length)] + "_" + generateRandom3DigitNumber(rand);
					    }

					    public static String generateRandomRoamingLocation(Random rand) {
					        String[] locations = {"JP", "CA", "AU", "UK", "US", "FR", "DE", "ES", "TH", "MX", "BR"};
					        return locations[rand.nextInt(locations.length)] + "_" + generateRandom3DigitNumber(rand);
					    }
					    
			//----------------------------------------------ROAMING SMS CDR-------------------------------------------------
					    
					    public List<RoamingSmsCdr> roamingSmsCdr(int value){
							List<RoamingSmsCdr> roamingSmsArr=new ArrayList();
							 Random rand = new Random();
						        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						        String[] messages = {
						    "Hello, how are you?",
						    "Meeting at 3 PM",
						    "Don't forget to call",
						    "Dinner at 7 PM",
						    "See you soon",
						    "Happy birthday!",
						    "Your account balance is low.",
						    "Your loan application has been approved.",
						    "Alert: Unusual activity on your account.",
						    "Your account password has been changed.",
						    "A new payee has been added to your account.",
						    "Your account is overdrawn.",
						    "Please call me",
						    "Need your help",
						    "Great news!",
						    "Miss you",
						    "Running late",
						    "Thanks a lot",
						    "Let's catch up",
						    "Are you free now?",
						    "See you tomorrow",
						    "Emergency, call me",
						    "Good morning!",
						    "Good night!",
						    "How's it going?",
						    "Lunch at 1 PM",
						    "Let's plan a weekend trip",
						    "Need your opinion on something",
						    "Can we chat later?",
						    "Just checking in",
						    "Have a fantastic day!",
						    "I love you!",
						    "How's the weather there?",
						    "Can you pick up some groceries?",
						    "What's for dinner?",
						    "Thinking of you",
						    "I'm here for you",
						    "Feeling great today",
						    "Have a safe journey",
						    "Remember to smile",
						    "Enjoy the little things",
						    "Need some good vibes",
						    "You're amazing!",
						    "Let's celebrate soon",
						    "Sending hugs",
						    "Don't stress, relax",
						    "You got this!",
						    "Life is beautiful",
						    "Stay positive",
						    "Follow your dreams",
						    "Believe in yourself",
						    "Love and laughter",
						    "Good vibes only",
						    "Cherish the moment",
						    "Never give up!",
						    "Explore new horizons",
						    "Boost your productivity",
						    "Confirmation code: 112233 for password reset.",
						    "Verify your phone number with code 444888.",
						    "Use code 777999 to confirm your new account.",
						    "Your access code for online banking is: 987123",
						    "Code 987789 for two-factor authentication.",
						    "Your code for secure transaction: 123789",
						    "Confirmation code: 543210 for account setup.",
						    "Unlock your potential",
						    "Stay focused and driven",
						    "Build your network",
						    "Seize the day with confidence",
						    "Professionalism at its best",
						    "Achieve your goals",
						    "Elevate your career",
						    "Business success is yours",
						    "Your success is our priority",
						    "Innovation and excellence",
						    "Unleash your creativity",
						    "Unlock new opportunities",
						    "Promote your brand with us",
						    "Quality and reliability",
						    "Best deals and discounts",
						    "Exclusive offer just for you",
						    "Limited time offer, don't miss out",
						    "Upgrade your lifestyle",
						    "Invest in your future",
						    "Your one-time verification code is: 123456",
						    "Use code 987654 to confirm your email address.",
						    "Your secure login code is: 654321",
						    "Enhance your skills",
						    "Your satisfaction is our success",
						    "Thank you for your trust and support",
						    "Your account has been credited with a deposit.",
						    "Insufficient funds in your account.",
						    "A new transaction has been made on your account.",
						    "Payment due on your credit card.",
						    "Your credit limit has been increased.",
						    "Transaction declined due to insufficient funds.",
						    "Your account has been locked for security.",
						    "Account statement for this month is available.",
						    "Payment received on your account.",
						    "Your account has been temporarily suspended.",
						    "Your account has been credited with interest.",
						    "Your credit card payment is due soon.",
						    "Transaction confirmed: Thank you for your purchase.",
						    "Your account has been debited for a payment.",
						    "A new direct deposit has been made to your account.",
						    "Account verification successful: Welcome aboard!"
						    
						    
						    };
						        try {
						        	File cdr = new File("RoamingSmsCdr.txt");
						            if (cdr.exists()) {
						            	cdr.delete();
						            } 
						          } catch (Exception e) {
						            e.printStackTrace();
						          }

						        for (int i = 0; i < value; i++) {
						            //random timestamps within the year 2022-2023
						            Date timestamp = generateRandomDate1(2022, new Date(), rand);
						            
						            //IMSI 
						            String imsi = generateIMSI(rand);

						            // random sender 
						            String senderMSISDN = generateIndianPhoneNumber(rand);
						            
						            //random Caller's Actual Location (IN_3 digit random number)
						             String callerHomeLocation = "IN_" + generateRandom3DigitNumber(rand);
						            
						            //receiver phone numbers in Indian format
						            String recipientMSISDN = generateInternationalMobileNumber(rand);
						            
						            // random Callee Location
						            String recipientLocation = generateRandomCalleeLocation(rand);
						            
						           // Callee Location is different from Caller's Actual Location
						            while (recipientLocation.equals(callerHomeLocation)) {
						            	recipientLocation = generateRandomCalleeLocation(rand);
						            }

						            //random Roaming Location
						            String roamingLocation = generateRandomRoamingLocation(rand);
						            
						            // CELL ID
						            String cellId = generateCellID(rand);

						            // random message from the array
						            String messageContent = messages[rand.nextInt(messages.length)];

						            //random status
						            String[] messageStatus = {"Delivered", "Failed"};
						            String messageStatusResult = messageStatus[rand.nextInt(messageStatus.length)];

						            // random message direction
						            String[] messageDirection = {"Outgoing", "Incoming"};
						            String messageDirectionResult = messageDirection[rand.nextInt(messageDirection.length)];
						            					            

						            // Format the timestamp
						            String messageTimestamp = dateFormat.format(timestamp);

						            // Initializing the Delivery Timestamp
						            String deliveryTimestampStr = "N/A"; // Default to "N/A"

						            if (messageStatusResult.equals("Delivered")) {
						                // random delivery timestamp between 1 to 5 minutes greater than Timestamp
						                long timestampMillis = timestamp.getTime();
						                long deliveryTimestampMillis = timestampMillis + (rand.nextInt(5) + 1) * 60 * 1000;
						                Date deliveryTimestamp = new Date(deliveryTimestampMillis);
						                deliveryTimestampStr = dateFormat.format(deliveryTimestamp);
						            }
						            RoamingSmsCdr obj=new RoamingSmsCdr();
						            obj.setMessageTimestamp(messageTimestamp);
						            obj.setDeliveryTimestampStr(deliveryTimestampStr);
						            obj.setImsi(imsi);
						            obj.setSenderMSISDN(senderMSISDN);
						            obj.setCallerHomeLocation(callerHomeLocation);
						            obj.setRecipientMSISDN(recipientMSISDN);
						            obj.setRecipientLocation(recipientLocation);
						            obj.setRoamingLocation(roamingLocation);
						            obj.setMessageStatusResult(messageStatusResult);
						            obj.setMessageContent(messageContent);
						            obj.setMessageDirectionResult(messageDirectionResult);
						            obj.setCellId(cellId);	
						            
						            roamingSmsArr.add(obj);
						            	            
						            try {
						                File cdr = new File("RoamingSmsCdr.txt");
						                // Using a FileWriter in append mode
						                FileWriter writer = new FileWriter(cdr,true);
						                
						                // Creating a BufferedWriter for efficient writing
						                BufferedWriter bufferWriter = new BufferedWriter(writer);
						                
						                // Appending the content
						                bufferWriter.write(obj.toString() + "\n");
						                
						                // Closing the BufferedWriter
						                bufferWriter.close();
						         
						            } catch (Exception e) {
						                e.printStackTrace();
						            }
						            
						        }
								return roamingSmsArr;
						        
					    } 
						        public static String generateIMSI(Random rand) {
						            StringBuilder imsi = new StringBuilder();
						            for (int i = 0; i < 15; i++) {
						                imsi.append(rand.nextInt(10)); // Generate random digits (0-9)
						            }
						            return imsi.toString();
						        }
						        
						        public static String generateInternationalMobileNumber(Random rand) {
						            String[] countryCodes = {"+1", "+44", "+81", "+61", "+49", "+33", "+1", "+52", "+55"}; // Replace with desired country codes
						            String countryCode = countryCodes[rand.nextInt(countryCodes.length)];
						            StringBuilder number = new StringBuilder();

						            //10 random digits for the mobile number
						            for (int i = 0; i < 10; i++) {
						                number.append(rand.nextInt(10)); // Generate random digits (0-9)
						            }

						            return countryCode + number.toString();
						        }
						        
						        public static String generateCellID(Random rand) {
						            // random Cell ID in the format "Base_3-digit number"
						            String base = "Base";
						            String threeDigitNumber = String.format("%03d", rand.nextInt(1000));
						            return base + "_" + threeDigitNumber;
						        }
					    
			  //----------------------------------------CALL LOCATION CDR------------------------------------------------------------
						        
						        public List<LocationCdr> locationCdr(int value){
									List<LocationCdr> locationArr=new ArrayList();
									 Random rand = new Random();
								        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
								        Date currentDate = new Date();
								        try {
								        	File cdr = new File("CallLocationCdr.txt");
								            if (cdr.exists()) {
								            	cdr.delete();
								            } 
								          } catch (Exception e) {
								            e.printStackTrace();
								          }
								        for (int i = 0; i < value; i++) {
								        	
								        	   Date startDate = generateRandomDateForVoip(2022, currentDate, rand);
								        	 
								        	    // random User ID (12-digit number)
								                String callId = generateRandomUserId(rand);
								                
								                //random Call Type
								                String[] callTypes = {"Voice", "Video"};
								                String callType = callTypes[rand.nextInt(callTypes.length)];

								                // random Call Result
								                String[] callResults = {"Connected", "Failed"};
								                String callResult = callResults[rand.nextInt(callResults.length)];
									            
									            int duration = 0;
									            if (callResult.equals("Connected")) {
									                duration = rand.nextInt(7200 - 5) + 5; // Random duration between 5 seconds and 120 minutes
									            }
									            
									            //duration , start time,end time
									            int minutes = duration / 60;
									            int seconds = duration % 60;
									            String duration1=minutes+" minutes "+seconds+" seconds";
									            Date callStartTime = new Date(startDate.getTime() - duration * 1000);
									            Date callEndTime = startDate;
									            String startTimeStr = dateFormat.format(callStartTime);
									            String endTimeStr = dateFormat.format(callEndTime);
									            
									            // random Sender
									            String callerNumber = "+91" + generateRandomPhoneNumber(rand);
									             
									           //random Caller's Location
									            double callerLatitude = generateRandomLatitude(rand);
									            double callerLongitude = generateRandomLongitude(rand);

									           
									            //Recipient numbers
										        String calleeNumber = generateInternationalMobileNumber(rand);
										       
										       //random Callee's Location
									            double calleeLatitude = generateRandomLatitude(rand);
									            double calleeLongitude = generateRandomLongitude(rand);
										       
										        //call Direction (Outgoing or Incoming)
									            String callDirection = (rand.nextBoolean()) ? "Outgoing" : "Incoming";
									            
									            // Signal Strength based on Call Result
									            int signalStrength;
									            if (callResult.equals("Connected")) {
									                signalStrength = rand.nextInt(31) + 50; // -50 dBM to -80 dBM
									            } else {
									                signalStrength = (rand.nextInt(21) + 90); // -90 dBM to -110 dBM
									            }

									            //Cell Tower
									            String cellTower = "CT" + generateRandom3DigitNumber(rand);	
									            
									            LocationCdr obj=new LocationCdr();
									            obj.setCallId(callId);
									            obj.setStartTimeStr(startTimeStr);
									            obj.setEndTimeStr(endTimeStr);
									            obj.setDuration(duration1);
									            obj.setCallType(callType);
									            obj.setCallResult(callResult);
									            obj.setCallDirection(callDirection);
									            obj.setCallerNumber(callerNumber);
									            obj.setCallerLatitude(callerLatitude);
									            obj.setCallerLongitude(callerLongitude);
									            obj.setCalleeNumber(calleeNumber);
									            obj.setCalleeLatitude(calleeLatitude);
									            obj.setCalleeLongitude(calleeLongitude);
									            obj.setSignalStrength(signalStrength);
									            obj.setCellTower(cellTower);
									            
									            locationArr.add(obj);						            
									            try {
									                File cdr = new File("CallLocationCdr.txt");
									                // Using a FileWriter in append mode
									                FileWriter writer = new FileWriter(cdr,true);
									                
									                // Creating a BufferedWriter for efficient writing
									                BufferedWriter bufferWriter = new BufferedWriter(writer);
									                
									                // Appending the content
									                bufferWriter.write(obj.toString() + "\n");
									                
									                // Closing the BufferedWriter
									                bufferWriter.close();
									         
									            } catch (Exception e) {
									                e.printStackTrace();
									            }
								        }
										return locationArr;
						        }    						       
						        public static double formatDoubleWithRandomDecimals(double value) {
						            int decimalPlaces = new Random().nextInt(4); // 0 to 3 decimal places
						            String formatString = "#.";
						            for (int i = 0; i < decimalPlaces; i++) {
						                formatString += "#";
						            }
						            DecimalFormat df = new DecimalFormat(formatString);
						            return Double.parseDouble(df.format(value));
						        }
						        
						        public static double generateRandomLatitude(Random rand) {
						            double latitude = rand.nextDouble() * 180.0 - 90.0;
						            return formatDoubleWithRandomDecimals(latitude);
						        }

						        public static double generateRandomLongitude(Random rand) {
						            double longitude = rand.nextDouble() * 360.0 - 180.0;
						            return formatDoubleWithRandomDecimals(longitude);
						        }
						        
						        public static double formatDoubleToThreeDecimals(double value) {
						            DecimalFormat df = new DecimalFormat("#.###");
						            return Double.parseDouble(df.format(value));
						        }
						        
						        
						        public static Date generateRandomdate(int startYear, Date endDate, Random rand) {
						            long startMillis = new Date(startYear - 1900, 0, 1).getTime();
						            long endMillis = endDate.getTime();
						            long randomMillisSinceEpoch = startMillis + (long) (rand.nextDouble() * (endMillis - startMillis));
						            return new Date(randomMillisSinceEpoch);
						        }

						        public static String generateRandom3digitNumber(Random rand) {
						            return String.format("%03d", rand.nextInt(1000)); // Ensure 3 digits
						        }
						        
						  //-----------------------------------Subscriber Activity CDR--------------------------------------------
						        
						        public List<SubscriberCdr> subscriberCdr(int value){
									List<SubscriberCdr> subscriberArr=new ArrayList();
									Random rand = new Random();
							        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

							        // start and end dates for the timestamp
							        Date startDate = new Date(122, 0, 1); 
							        Date currentDate = new Date();
							        
							        try {
							        	File cdr = new File("SubscriberCdr.txt");
							            if (cdr.exists()) {
							            	cdr.delete();
							            } 
							          } catch (Exception e) {
							            e.printStackTrace();
							          }

								        for (int i = 0; i < value; i++) {
								        	
								        	 //random 12-digit Subscriber ID
								            String subscriberId = generateRandomSubscriberId(rand);
								            
								            //random user number
								            String userNumber = generateIndianPhoneNumber(rand);
								            
								            //random IP Address
								            String ipAddress = generateRandomIpAddress(rand);

								            //random Activity Type (Always "Account Update" as specified)
								            String activityType = "Account Update";
								            
								            //random timestamp between 2022 and the current date
								            Date timestamp = generateRandomDates(startDate, currentDate, rand);

								            // random Update Type
								            String updateType = generateRandomUpdateType(rand);

								            //Details based on the Update Type
								            String details = generateDetailsForUpdateType(updateType, rand);
								            
								            // random Connection Type
								            String[] connectionTypes = {"5G LTE", "4G LTE", "3G LTE"};
								            String connectionType = connectionTypes[rand.nextInt(connectionTypes.length)];
								            
								            // Generate random Device OS
								            String[] deviceOS = {"Android", "iOS", "Windows 10", "Windows 11", "macOS", "Linux", "Ubuntu", "Chrome OS"};
								            String deviceOSResult = deviceOS[rand.nextInt(deviceOS.length)];
								            
								            SubscriberCdr obj=new SubscriberCdr();
								            obj.setSubscriberId(subscriberId);
								            obj.setUserNumber(userNumber);
								            obj.setIpAddress(ipAddress);
								            obj.setActivityType(activityType);
								            obj.setTimestamp(timestamp);
								            obj.setUpdateType(updateType);
								            obj.setDetails(details);
								            obj.setConnectionType(connectionType);
								            obj.setDeviceOS(deviceOSResult);
								            
								            subscriberArr.add(obj);
								            	         
								            try {
								                File cdr = new File("SubscriberCdr.txt");
								                // Using a FileWriter in append mode
								                FileWriter writer = new FileWriter(cdr,true);
								                
								                // Creating a BufferedWriter for efficient writing
								                BufferedWriter bufferWriter = new BufferedWriter(writer);
								                
								                // Appending the content
								                bufferWriter.write(obj.toString() + "\n");
								                
								                // Closing the BufferedWriter
								                bufferWriter.close();
								         
								            } catch (Exception e) {
								                e.printStackTrace();
								            }

								        }
										return subscriberArr;
								        
						        }
						        public static String generateRandomSubscriberId(Random rand) {
						            StringBuilder subscriberId = new StringBuilder();
						            for (int i = 0; i < 12; i++) {
						                subscriberId.append(rand.nextInt(10)); 
						            }
						            return subscriberId.toString();
						        }

						        public static String generateRandomUpdateType(Random rand) {
						            String[] updateTypes = {"Plan Change", "Payment", "Information Update"};
						            return updateTypes[rand.nextInt(updateTypes.length)];
						        }
						        
						        public static String generateDetailsForUpdateType(String updateType, Random rand) {
						            if (updateType.equals("Plan Change")) {
						                String[] planChangeDetails = {
						                    "Subscriber upgraded from ProTel Max 701 to ProTel Max 901 plan",
						                    "Subscriber upgraded from ProTel Max 501 to ProTel Max 901 plan",
						                    "Subscriber upgraded from ProTel Max 901 to ProTel Max 701 plan",
						                    "Subscriber upgraded from ProTel Max 501 to ProTel Max 701 plan"
						                };
						                return planChangeDetails[rand.nextInt(planChangeDetails.length)];
						            } else if (updateType.equals("Payment")) {
						                return "Subscriber has made payment for the current plan";
						            } else {
						                String[] informationUpdateDetails = {
						                    "Subscriber has updated the account number",
						                    "Subscriber has updated the password"
						                };
						                return informationUpdateDetails[rand.nextInt(informationUpdateDetails.length)];
						            }
						        }
						        
						        public static Date generateRandomDates(Date start, Date end, Random rand) {
						            long startMillis = start.getTime();
						            long endMillis = end.getTime();
						            long randomMillisSinceEpoch = startMillis + (long) (rand.nextDouble() * (endMillis - startMillis));
						            return new Date(randomMillisSinceEpoch);
						        }

						        





						        
}
