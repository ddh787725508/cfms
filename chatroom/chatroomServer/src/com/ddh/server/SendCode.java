package com.ddh.server;

import org.apache.commons.mail.HtmlEmail;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
 

public class SendCode {
	public static boolean send(String phoneNumber, String code) {
		  // 产品名称:云通信短信API产品,开发者无需替换 
	     final String product = "Dysmsapi"; 
	    // 产品域名,开发者无需替换 
	     final String domain = "dysmsapi.aliyuncs.com"; 
	 
	    // 此处需要替换成开发者自己的AK(在阿里云访问控制台寻找) 
	      String accessKeyId = "LTAIpqEu4LNmc49o"; 
	      String accessKeySecret = "2MmSmrR7eyxdD7Ej00iEiivGKtpmSx"; 
	      String signName = "海哥即时通讯"; 
	      String templeteCode = "SMS_135610141"; 
		try {
			 // 可自助调整超时时间 
	        System.setProperty("sun.net.client.defaultConnectTimeout", "10000"); 
	        System.setProperty("sun.net.client.defaultReadTimeout", "10000"); 
	 
	        // 初始化acsClient,暂不支持region化 
	        IClientProfile profile = DefaultProfile.getProfile("cn-beijing", accessKeyId, accessKeySecret); 
	        DefaultProfile.addEndpoint("cn-beijing", "cn-beijing", product, domain); 
	        IAcsClient acsClient = new DefaultAcsClient(profile); 
 
	        // 组装请求对象-具体描述见控制台-文档部分内容 
	        SendSmsRequest request = new SendSmsRequest(); 
	 
	        // 必填:待发送手机号 
	        request.setPhoneNumbers(phoneNumber); 
	        // 必填:短信签名-可在短信控制台中找到 
	        request.setSignName(signName); 
	        // 必填:短信模板-可在短信控制台中找到 
	        request.setTemplateCode(templeteCode); 
	 
	        // 可选:模板中的变量替换JSON串,如模板内容为"尊敬的用户,您的验证码为${code}"时,此处的值为 
	        String jsonParam = "{\"code\":\"" + code + "\"}"; 
	        request.setTemplateParam(jsonParam); 
	       
			SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request); 
			if(sendSmsResponse.getCode().equals("OK")){  //成功
	              System.out.println("sendSmsResponse.getCode().equals('OK')");
				return true;
	        } 
			if(sendSmsResponse.getCode() == null){//失败
				System.out.println("sendSmsResponse.getCode() == null");
	          return false;
	        }
	        System.out.println(sendSmsResponse.getCode());//得到异常错误码。ok即表示成功

	        return false;
		} catch (Exception e) {
			return false; 
		}
		}
		   
	public static boolean sendEmail(String emailaddress, String code) {

		try {
			HtmlEmail email = new HtmlEmail();
			email.setHostName("smtp.163.com");
			email.setCharset("UTF-8");
			email.addTo(emailaddress);// 收件地址

			email.setFrom("dongdahaisea@163.com", "海哥即时通讯");

			email.setAuthentication("dongdahaisea@163.com", "ddh980068470");

			email.setSubject("海哥即时通讯");
			email.setMsg("欢迎您注册海哥即时通讯，验证码是:" + code);

			email.send();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}
	
	
	
//	public static void main(String[] args) {
//		send("18829224828","123456");
//	}
}
