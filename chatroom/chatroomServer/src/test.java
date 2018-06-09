import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;

public class test {
      // 产品名称:云通信短信API产品,开发者无需替换 
    private static final String product = "Dysmsapi"; 
    // 产品域名,开发者无需替换 
    private static final String domain = "dysmsapi.aliyuncs.com"; 
 
    // 此处需要替换成开发者自己的AK(在阿里云访问控制台寻找) 
    private static String mobile = "18209249715"; 
    private static String accessKeyId = "LTAIpqEu4LNmc49o"; 
    private static String accessKeySecret = "2MmSmrR7eyxdD7Ej00iEiivGKtpmSx"; 
    private static String signName = "ddh"; 
    private static String templeteCode = "SMS_135610141"; 
 
    // 调用短信接口 
    public static void main(String[] args) { 
        try { 
            System.out.println("开始发送！");
            sendSms(); 
        } catch (ClientException e) { 
        } 
    } 
 
    // 发送短信方法 
    public static SendSmsResponse sendSms() throws ClientException { 
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
        request.setPhoneNumbers(mobile); 
        // 必填:短信签名-可在短信控制台中找到 
        request.setSignName(signName); 
        // 必填:短信模板-可在短信控制台中找到 
        request.setTemplateCode(templeteCode); 
 
        // 可选:模板中的变量替换JSON串,如模板内容为"尊敬的用户,您的验证码为${code}"时,此处的值为 
        String jsonParam = "{\"code\":\"123\"}"; 
        request.setTemplateParam(jsonParam); 
 
        // hint 此处可能会抛出异常，注意catch 
        SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request); 
       
        if(sendSmsResponse.getCode() == null){//失败
            System.out.println("sendSmsResponse.getCode() == null");
        }
        
        System.out.println(sendSmsResponse.getCode());//得到异常错误码。ok即表示成功

        if(sendSmsResponse.getCode().equals("OK")){  //成功
              System.out.println("sendSmsResponse.getCode().equals('OK')");
        } 
        return sendSmsResponse; 
    } 
 
}