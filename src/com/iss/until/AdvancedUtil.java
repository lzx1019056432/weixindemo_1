package com.iss.until;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.iss.bean.SNSUserInfo;
import com.iss.bean.WeixinAouth2Token;

public class AdvancedUtil {
	private static String appid = "wx74baf233f698cf83";
	private static String appsecret="4531fd6943457407d420f68ffd122dc4";
	/*
	 * 获取网页授权凭证
	 * 
	 * @param appid
	 * @param appSecret
	 * @param code
	 * @return WeixinAouth2Token
	 */
public static WeixinAouth2Token getOauth2AccessToken(String appId,String appSecret,String code){
	
	WeixinAouth2Token wat = null;
	//拼接请求地址
	System.out.println("code:"+code);
	String requestUrl = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
	requestUrl = requestUrl.replace("APPID", appId);
	requestUrl = requestUrl.replace("SECRET", appSecret);
	requestUrl = requestUrl.replace("CODE", code);
	//获取网页授权凭证
	JSONObject jsonObject = CommonUtil.httpsRequest(requestUrl, "GET", null);
	System.out.println("jsonObject:"+jsonObject);
	if(null!=jsonObject){
		try{
		
		wat = new WeixinAouth2Token();
		wat.setAccessToken(jsonObject.getString("access_token"));
		wat.setExpiresIn(jsonObject.getIntValue("expres_in"));
		wat.setRefreshToken(jsonObject.getString("refresh_token"));
		wat.setOpenId(jsonObject.getString("openid"));
		wat.setScope(jsonObject.getString("scope"));
		} catch (Exception e){
			wat = null;
			int errorCode = jsonObject.getIntValue("errcode");
			String errorMsg = jsonObject.getString("errmsg");
			System.out.println(errorCode+"-----"+errorMsg);
			
		}
		
	}
 
	return wat;
	
	
	
}
/*
 * 刷新网页授权凭证
 * @param appId 公众号唯一标识
 * @param refreshToken
 * @return WeixinAoth2Token
 * 
 * 
 */
public static WeixinAouth2Token refreshOauth2AccessToken(String appId,String refreshToken){
	WeixinAouth2Token wat = null;
	
	String requestUrl = "https://api.weixin.qq.com/sns/oauth2/refresh_token?appid=APPID&grant_type=refresh_token&refresh_token=REFREH_TOKEN";
	requestUrl = requestUrl.replace("APPID", appId);
	requestUrl = requestUrl.replace("REFREH_TOKEN", refreshToken);
	//获取网页授权凭证
	JSONObject jsonObject = CommonUtil.httpsRequest(requestUrl, "GET", null);
	if(null!=jsonObject){
		try{
		wat = new WeixinAouth2Token();
		wat.setAccessToken(jsonObject.getString("access_token"));
		wat.setExpiresIn(jsonObject.getIntValue("expres_in"));
		wat.setRefreshToken(jsonObject.getString("refresh_token"));
		wat.setRefreshToken(jsonObject.getString("openid"));
		wat.setScope(jsonObject.getString("scope"));
		} catch (Exception e){
			wat = null;
			int errorCode = jsonObject.getIntValue("errcode");
			String errorMsg = jsonObject.getString("errmsg");
			System.out.println(errorCode+"-----"+errorMsg);
			
		}
		
	}
 
	return wat;

}
/*通过网页守授权获取用户信息
 * 
 * @param accessToken 网页授权借口凭证
 * @param openId 用户标识
 * @param SNSUserInfo
 */
@SuppressWarnings({"deprecation","unchecked"})
public static SNSUserInfo getSNSUserInfo(String accessToken,String openId){
	SNSUserInfo snsUserInfo = null;
	//拼接请求地址
	String requestUrl = "https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
	requestUrl = requestUrl.replace("ACCESS_TOKEN", accessToken).replace("OPENID", openId);
	System.out.println(requestUrl);
	 //通过网页授权获取用户信息
	JSONObject jsonObject = CommonUtil.httpsRequest(requestUrl, "GET", null);
	System.out.println("------------jsonObject"+jsonObject);
	if(null!=jsonObject){
		try{
			System.out.println("--------------正在获取用户");
			snsUserInfo = new SNSUserInfo();
			snsUserInfo.setOpenId(jsonObject.getString("openid"));
			snsUserInfo.setNickname(jsonObject.getString("nickname"));
			snsUserInfo.setSex(jsonObject.getIntValue("sex"));
			snsUserInfo.setCountry(jsonObject.getString("country"));
			snsUserInfo.setProvince(jsonObject.getString("province"));
			snsUserInfo.setCity(jsonObject.getString("city"));
			snsUserInfo.setHeadImgUrl(jsonObject.getString("headimgurl"));
			snsUserInfo.setPrivilegeList(JSONArray.toJavaObject(jsonObject.getJSONArray("privilege"), List.class));
		} catch (Exception e){ 
			snsUserInfo = null;
			int errorCode = jsonObject.getIntValue("errcode");
			String errorMsg = jsonObject.getString("errmsg");
			System.out.println(errorCode+"-----"+errorMsg);
			
		}
		
	}
 
	return snsUserInfo;
	
}

/*
 *  根据用户id获取
 *@param source
 *@return
 */
@SuppressWarnings({"deprecation","unchecked"})
public static SNSUserInfo getSNSUserInfobyopenId(String accessToken,String openId){
	SNSUserInfo snsUserInfo = null;
	//拼接请求地址
	String requestUrl = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
	requestUrl = requestUrl.replace("ACCESS_TOKEN", accessToken).replace("OPENID", openId);
	System.out.println(requestUrl);
	 //通过网页授权获取用户信息
	JSONObject jsonObject = CommonUtil.httpsRequest(requestUrl, "GET", null);
	
	
	 
	return null;
	
	
	
	
}
/*
 * 获取access——token
 *@param appid
 *@param appsecret
 *@return
 */
@SuppressWarnings({"deprecation","unchecked"})
public static String getsaccess_token(String appid,String appsecret){
	String reqeustUrl1 = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
	reqeustUrl1 = reqeustUrl1.replace("APPID", appid).replace("APPSECRET", appsecret);
	//获取到accesstoken
	JSONObject jsonObject1 = CommonUtil.httpsRequest(reqeustUrl1, "GET", null);
	String accessToken = jsonObject1.getString("access_token");
	
	
	return accessToken;
	
}
/*时间的封装*/
public static String getdatatime(){
	 Date date=new Date();  
     DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
	
	return format.format(date);
}
/*
 * 模板消息一：回收员的收单提醒
 *@param appid
 *@param appsecret
 *@return
 */
public static String model_send1(String touser,String url,String ordernumber,String orderdetail,String addcontent){
	
	String wxText = "{\"touser\":\""+touser+"\","
			+ "\"template_id\":\"iUP30TPL4Ll684S3Od5KcNi0V52QNpwMgx5FOq8zjUU\",\"url\":\""+url+"\","
					+ "\"data\":{\"first\":{\"value\":\"有新的订单，请及时查看\",\"color\":\"#000\"},"
							+ "\"keyword1\":{\"value\":\""+orderdetail+"\",\"color\":\"#173177\"},"
									+ "\"keyword2\":{\"value\":\""+ordernumber+"\",\"color\":\"#173177\"},"
											+ "\"keyword3\":{\"value\":\""+AdvancedUtil.getdatatime()+"\",\"color\":\"#173177\"},"
											+ "\"remark\":{\"value\":\""+addcontent+"\",\"color\":\"#5d5c5c\"}}}";
	
	
	
	return wxText;
	
	
	
}
/*
 * 模板消息二：用户的订单已被接受
 *@param appid
 *@param appsecret
 *@return
 */
public static String model_send2(String touser,String url,String orderpeople){
	
	String wxText = "{\"touser\":\""+touser+"\","
			+ "\"template_id\":\"klhiO8oUNYnCN8gpsWqtCB4buZhwWsvE1dE5ooPkbr8\",\"url\":\""+url+"\","
					+ "\"data\":{\"first\":{\"value\":\"您的订单已被接受\",\"color\":\"#000\"},"
							+ "\"keyword1\":{\"value\":\""+orderpeople+"\",\"color\":\"#173177\"},"
									+ "\"keyword3\":{\"value\":\""+AdvancedUtil.getdatatime()+"\",\"color\":\"#173177\"}}}";
	
	
	
	return wxText;
	
	
	
}
/*
 * 模板消息三：交易完成
 *@param appid
 *@param appsecret
 *@return
 */
public static String model_send3(String touser,String url,String orderpeople,float money,String didnumber){
	
	String wxText = "{\"touser\":\""+touser+"\","
			+ "\"template_id\":\"WTzowVUnvUHAKHZO1x0cLz22xlLimoEaxxlBBEU1xl0\",\"url\":\""+url+"\","
					+ "\"data\":{\"first\":{\"value\":\"交易已完成\",\"color\":\"#000\"},"
							+ "\"keyword1\":{\"value\":\""+didnumber+"\",\"color\":\"#173177\"},"
									+ "\"keyword2\":{\"value\":\""+orderpeople+"\",\"color\":\"#173177\"},"
									+ "\"keyword3\":{\"value\":\""+money+"\",\"color\":\"#173177\"},"
											+ "\"keyword4\":{\"value\":\""+AdvancedUtil.getdatatime()+"\",\"color\":\"#173177\"}}}";
	
	
	System.out.println(wxText);
	return wxText;
	
	
	
}
/*
 * 模板消息的发送
 *@param source
 *@return
 */
@SuppressWarnings({"deprecation","unchecked"})
public static SNSUserInfo sendmodelinformation(String wxText){
	 
 
	String accessToken = AdvancedUtil.getsaccess_token(appid, appsecret);
	//拼接请求地址
System.out.println("accessToken"+accessToken);
	String requestUrl = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=ACCESS_TOKEN";
	requestUrl = requestUrl.replace("ACCESS_TOKEN", accessToken);
	 //模板消息的发送 
	JSONObject jsonObject = CommonUtil.httpsRequest(requestUrl, "POST", wxText);
	System.out.println("jsonObject的模板信息"+jsonObject);
	return null;
	
	 
	  
	
}

/*
 *  URL编码
 *@param source
 *@return
 */
public static String urlEncodeUTF8(String source){
	String result = source;
	try {
		result = java.net.URLEncoder.encode(source, "utf-8");
	} catch (UnsupportedEncodingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	
	
	
	
	return result;
	
}
/*
 * 地址选择返回json
 *@param source
 *@return
 */
public static JSONObject Addressreback(String query,String region) throws UnsupportedEncodingException{
	String querys = URLEncoder.encode(query, "UTF-8");
	String regions = URLEncoder.encode(region, "UTF-8");
	System.out.println("显示数据"+query+"数据2："+regions);
	String requestUrl =  "http://api.map.baidu.com/place/v2/suggestion?region=REGION&query=QUERY&city_limit=true&output=json&ak=FoH3GcjXoraCczyFLU4GBFtov2Frhkxx";
	 
	requestUrl = requestUrl.replace("QUERY",querys).replace("REGION",regions);
	JSONObject jsonObject = CommonUtil.httpsRequest(requestUrl, "POST", "");
    System.out.println("显示json数据"+jsonObject);
	
	
	
	return jsonObject;
	
}
/*
 * 创建底部菜单
 * @param appid
 * @param appsecret
 * return
 * 
 * */
public static String creatbottomlist(){
	String accessToken = AdvancedUtil.getsaccess_token(appid, appsecret);
	//拼接请求地址
	System.out.println("accessToken:"+accessToken);
	String requestUrl =  "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";
	requestUrl = requestUrl.replace("ACCESS_TOKEN", accessToken);
	String createlist = "{\"button\": [{"
			+"\"type\": \"view\","
			+"\"name\": \"用户\","
			+"\"url\": \"https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx74baf233f698cf83&redirect_uri=http%3A%2F%2Fpobing.duapp.com%2Fweixindemo_1%2FOAuthServlet?admin=0&response_type=code&scope=snsapi_userinfo&state=STATE&#wechat_redirect\"},"
		+"{"
			+"\"type\": \"view\","
			+"\"name\": \"回收员\","
			+"\"url\": \"https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx74baf233f698cf83&redirect_uri=http%3A%2F%2Fpobing.duapp.com%2Fweixindemo_1%2FOAuthServlet?admin=1&response_type=code&scope=snsapi_userinfo&state=STATE&#wechat_redirect\""
		+"}"
	+"]"
+"}";
	//底部菜单的创建
	JSONObject jsonObject = CommonUtil.httpsRequest(requestUrl, "POST", createlist);
	System.out.println("底部菜单的创建"+jsonObject);
	return "";
}
/*
 * 15为订单编号的生成
 * 
 * 前13位为时间戳，后两位是随机数
 * 
 * 
 * */
public static String creatListnum(){
    Date date = new Date();
    long ts = date.getTime();
    int num =   (int)(Math.random()*100);
    String number = ts+""+num;
	
	return number;
}
/*public static void main(String[] args) {
	 String url="http://pobing.duapp.com/weixindemo_1/OAuthServlet";
	System.out.println(urlEncodeUTF8(url)); 
}*/
}
