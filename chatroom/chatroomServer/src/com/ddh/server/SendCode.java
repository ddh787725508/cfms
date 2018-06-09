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
		  // ��Ʒ����:��ͨ�Ŷ���API��Ʒ,�����������滻 
	     final String product = "Dysmsapi"; 
	    // ��Ʒ����,�����������滻 
	     final String domain = "dysmsapi.aliyuncs.com"; 
	 
	    // �˴���Ҫ�滻�ɿ������Լ���AK(�ڰ����Ʒ��ʿ���̨Ѱ��) 
	      String accessKeyId = "LTAIpqEu4LNmc49o"; 
	      String accessKeySecret = "2MmSmrR7eyxdD7Ej00iEiivGKtpmSx"; 
	      String signName = "���缴ʱͨѶ"; 
	      String templeteCode = "SMS_135610141"; 
		try {
			 // ������������ʱʱ�� 
	        System.setProperty("sun.net.client.defaultConnectTimeout", "10000"); 
	        System.setProperty("sun.net.client.defaultReadTimeout", "10000"); 
	 
	        // ��ʼ��acsClient,�ݲ�֧��region�� 
	        IClientProfile profile = DefaultProfile.getProfile("cn-beijing", accessKeyId, accessKeySecret); 
	        DefaultProfile.addEndpoint("cn-beijing", "cn-beijing", product, domain); 
	        IAcsClient acsClient = new DefaultAcsClient(profile); 
 
	        // ��װ�������-��������������̨-�ĵ��������� 
	        SendSmsRequest request = new SendSmsRequest(); 
	 
	        // ����:�������ֻ��� 
	        request.setPhoneNumbers(phoneNumber); 
	        // ����:����ǩ��-���ڶ��ſ���̨���ҵ� 
	        request.setSignName(signName); 
	        // ����:����ģ��-���ڶ��ſ���̨���ҵ� 
	        request.setTemplateCode(templeteCode); 
	 
	        // ��ѡ:ģ���еı����滻JSON��,��ģ������Ϊ"�𾴵��û�,������֤��Ϊ${code}"ʱ,�˴���ֵΪ 
	        String jsonParam = "{\"code\":\"" + code + "\"}"; 
	        request.setTemplateParam(jsonParam); 
	       
			SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request); 
			if(sendSmsResponse.getCode().equals("OK")){  //�ɹ�
	              System.out.println("sendSmsResponse.getCode().equals('OK')");
				return true;
	        } 
			if(sendSmsResponse.getCode() == null){//ʧ��
				System.out.println("sendSmsResponse.getCode() == null");
	          return false;
	        }
	        System.out.println(sendSmsResponse.getCode());//�õ��쳣�����롣ok����ʾ�ɹ�

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
			email.addTo(emailaddress);// �ռ���ַ

			email.setFrom("dongdahaisea@163.com", "���缴ʱͨѶ");

			email.setAuthentication("dongdahaisea@163.com", "ddh980068470");

			email.setSubject("���缴ʱͨѶ");
			email.setMsg("��ӭ��ע�ả�缴ʱͨѶ����֤����:" + code);

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
