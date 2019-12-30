package vinid.vinhome.service;

import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.model.MessageAttributeValue;
import com.amazonaws.services.sns.model.PublishRequest;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class SmsService {
	
	private void sendSms( String phoneNumber, String message){
        String AccessKey = "";
        String SecretKey = "";
        
        AmazonSNSClient snsClient = new AmazonSNSClient(new BasicAWSCredentials(AccessKey, SecretKey));
        Map<String, MessageAttributeValue> smsAttributes = 
                new HashMap<String, MessageAttributeValue>();
        //<set SMS attributes>
        smsAttributes.put("AWS.SNS.SMS.SenderID", new MessageAttributeValue()
                .withStringValue("Specialty") //The sender ID shown on the device.
                .withDataType("String"));

        smsAttributes.put("AWS.SNS.SMS.SMSType", new MessageAttributeValue()
                .withStringValue("Transactional") //Sets the type to promotional.
                .withDataType("String"));
        
        snsClient.publish(new PublishRequest()
                .withMessage(message)
                .withPhoneNumber(phoneNumber)
                .withMessageAttributes(smsAttributes));

    }
    
    
    public void sendSMSOTPCode(String phoneNumber, String otp) {
    		sendSms(phoneNumber, String.format("MA OTP CUA BAN LA", otp));
   }
}