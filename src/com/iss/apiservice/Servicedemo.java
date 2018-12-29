package com.iss.apiservice;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.iss.bean.Newsa;
import com.iss.until.CommonUtil;
import com.iss.until.CommonUtil1;

public class Servicedemo {
	/*
	 *发送http请求 
	 * @param requestUrl 请求地址
	 * @return String
	 * 
	 * 
	 * 
	 **/
	private static String httpRequest(String requestUrl){
		StringBuffer buffer = new StringBuffer();
		try {
			URL url = new URL(requestUrl);
			HttpURLConnection httpUrlConn = (HttpURLConnection)url.openConnection();
			httpUrlConn.setDoInput(true);
			httpUrlConn.setRequestMethod("GET");
			httpUrlConn.connect();
			//将返回的输入流转化成字符串
			InputStream inputStream = httpUrlConn.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream,"utf-8");
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
		String str = null;
		while((str = bufferedReader.readLine())!=null){
			buffer.append(str);
		}
		bufferedReader.close();
		inputStreamReader.close();
		//释放资源
		inputStream.close();
		inputStream = null;
		httpUrlConn.disconnect();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return buffer.toString();
	
		
	}
	public static List newschear(){
		List<Newsa> list = new ArrayList<Newsa>();
		String js = httpRequest("http://api.avatardata.cn/TouTiao/Query?key=d9f0f3ab1d324ab5ab4a66467cbee1d9&type=top");
		JSONObject jsonObj=JSONObject.parseObject(js);
		JSONObject result=jsonObj.getJSONObject("result");
		JSONArray data=result.getJSONArray("data");
		for (int i = 0; i < data.size(); i++) {
			Newsa news = new Newsa();
			JSONObject resultsObject=(JSONObject) data.get(i);

			news.setTittle(resultsObject.getString("title"));
			news.setPic(resultsObject.getString("thumbnail_pic_s"));
			news.setUrl(resultsObject.getString("url"));
			list.add(news);
		}
		
		return list;
		
	}
/*	public static String Loginin(){
		String result = "";
		String requestUrl = "http://www.tubangzhu.com/editorApi/v1/member/login";
		String requestUrl1 = "http://www.tubangzhu.com/editorApi/v1/member/sign";
		String namepass = "name=15122888063&password=2685563a";
		List<String> cookies = CommonUtil1.httpsRequest(requestUrl, "POST", namepass);
		//JSONObject jsonObject =  CommonUtil1.httpsRequest1(requestUrl1, "POST", cookies);
		if(jsonObject.getString("msg").equals("OK")){
			result = "图帮主签到成功";
		}else{
			result = "今日已签到，请明天再来";
		}
		
		 
		  
		
		return result;
		
		
	}*/
	public static String sharehongbao(){
		String result = "1";
		String requestUrl = "https://h5.ele.me/hongbao/#hardware_id=&is_lucky_group=True&lucky_number=6&track_id=&platform=0&sn=10fc53cb3e86f440&theme_id=2889&device_id=&refer_user_id=268126338";
		//String requestUrl1 = "http://www.tubangzhu.com/editorApi/v1/member/sign";
		//String namepass = " ";
		//String text = CommonUtil1.httpsRequest(requestUrl, "POST", namepass);
		JSONObject jsonObject =  CommonUtil1.httpsRequest1(requestUrl, "POST");
		System.out.println(jsonObject);
		/*if(jsonObject.getString("msg").equals("OK")){
			result = "图帮主签到成功";
		}else{
			result = "今日已签到，请明天再来";
		}
		*/
		 
		  
		
		return result;
		
		
	}
public static void main(String[] args) {
	sharehongbao();
	
}

	/*public static void main(String[] args){
	System.out.println(	httpRequest("http://api.avatardata.cn/TouTiao/Query?key=d9f0f3ab1d324ab5ab4a66467cbee1d9&type=top"));
	//Newsa news = new Newsa();
	List<Newsa> list = new ArrayList<Newsa>();
	String js = httpRequest("http://api.avatardata.cn/TouTiao/Query?key=d9f0f3ab1d324ab5ab4a66467cbee1d9&type=top");
	JSONObject jsonObj=JSONObject.parseObject(js);
	JSONObject result=jsonObj.getJSONObject("result");
	JSONArray data=result.getJSONArray("data");
	for (int i = 0; i < data.size(); i++) {
		Newsa news = new Newsa();
		JSONObject resultsObject=(JSONObject) data.get(i);
System.out.println(resultsObject.getString("title"));
		news.setTittle(resultsObject.getString("title"));
		news.setPic(resultsObject.getString("thumbnail_pic_s"));
		news.setUrl(resultsObject.getString("url"));
		list.add(news);
	}
	System.out.println(list.get(4).getTittle());
		String content= "";
		String contents = "";
		List<String> liststring = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			content = "No"+i+"."+list.get(i).getTittle()+"\n"+list.get(i).getUrl()+"\n";
			contents+=content;
		}
		System.out.println(contents+"\n");
	}*/
}
