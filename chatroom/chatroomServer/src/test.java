import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;

public class test {
      // ��Ʒ����:��ͨ�Ŷ���API��Ʒ,�����������滻 
    private static final String product = "Dysmsapi"; 
    // ��Ʒ����,�����������滻 
    private static final String domain = "dysmsapi.aliyuncs.com"; 
 
    // �˴���Ҫ�滻�ɿ������Լ���AK(�ڰ����Ʒ��ʿ���̨Ѱ��) 
    private static String mobile = "18209249715"; 
    private static String accessKeyId = "LTAIpqEu4LNmc49o"; 
    private static String accessKeySecret = "2MmSmrR7eyxdD7Ej00iEiivGKtpmSx"; 
    private static String signName = "ddh"; 
    private static String templeteCode = "SMS_135610141"; 
 
    // ���ö��Žӿ� 
    public static void main(String[] args) { 
        try { 
            System.out.println("��ʼ���ͣ�");
            sendSms(); 
        } catch (ClientException e) { 
        } 
    } 
 
    // ���Ͷ��ŷ��� 
    public static SendSmsResponse sendSms() throws ClientException { 
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
        request.setPhoneNumbers(mobile); 
        // ����:����ǩ��-���ڶ��ſ���̨���ҵ� 
        request.setSignName(signName); 
        // ����:����ģ��-���ڶ��ſ���̨���ҵ� 
        request.setTemplateCode(templeteCode); 
 
        // ��ѡ:ģ���еı����滻JSON��,��ģ������Ϊ"�𾴵��û�,������֤��Ϊ${code}"ʱ,�˴���ֵΪ 
        String jsonParam = "{\"code\":\"123\"}"; 
        request.setTemplateParam(jsonParam); 
 
        // hint �˴����ܻ��׳��쳣��ע��catch 
        SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request); 
       
        if(sendSmsResponse.getCode() == null){//ʧ��
            System.out.println("sendSmsResponse.getCode() == null");
        }
        
        System.out.println(sendSmsResponse.getCode());//�õ��쳣�����롣ok����ʾ�ɹ�

        if(sendSmsResponse.getCode().equals("OK")){  //�ɹ�
              System.out.println("sendSmsResponse.getCode().equals('OK')");
        } 
        return sendSmsResponse; 
    } 
 
}