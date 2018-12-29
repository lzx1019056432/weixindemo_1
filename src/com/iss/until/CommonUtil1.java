package com.iss.until;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSONObject;
public class CommonUtil1 { 
		
	/*
	 * 发送https请求
	 * @param requestUrl 请求地址
	 * @param requestMethod 请求方法
	 * @param JSONObject 解析获取的json数据 
	 * 
	 * */
	public static List<String> httpsRequest(String requestUrl,String requestMethod,String outputStr){
		JSONObject jsonObject = null;
		List<String> cookie = null;
		//创建SSLContext对象
		 //--
		try {
			URL url = new URL(requestUrl);
			HttpURLConnection conn;
			try {
				conn = (HttpURLConnection) url.openConnection();
				conn.setDoOutput(true);
				conn.setDoInput(true);
				conn.setUseCaches(true);
				//设置请求方式
				conn.setRequestMethod(requestMethod);
				 
				if(null!=outputStr){
					OutputStream outputStream = conn.getOutputStream();
					outputStream.write(outputStr.getBytes("UTF-8"));
					outputStream.close();			
				}
				cookie = conn.getHeaderFields().get("Set-Cookie");
				//从输入流读取返回内容
				InputStream inputStream = conn.getInputStream();
				InputStreamReader inputStreamReader = new InputStreamReader(inputStream,"utf-8");
				BufferedReader bufferedReadr = new BufferedReader(inputStreamReader);
				String str = null;
				StringBuffer buffer = new StringBuffer();
				while((str = bufferedReadr.readLine())!=null){
					buffer.append(str);
				}
				bufferedReadr.close();
				inputStream.close();
				inputStreamReader.close();
				inputStream = null;
				conn.disconnect();
				jsonObject = JSONObject.parseObject(buffer.toString());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return cookie;   
 
	}
	public static JSONObject httpsRequest1(String requestUrl,String requestMethod){
		JSONObject jsonObject = null;

		//创建SSLContext对象
		 //--
		try {
			URL url = new URL(requestUrl);
			HttpURLConnection conn;
			try {
				conn = (HttpURLConnection) url.openConnection();
				conn.setDoOutput(true);
				conn.setDoInput(true);
				conn.setUseCaches(true);
				//设置请求方式
				conn.setRequestMethod(requestMethod);
				/*for (String cookie : cookies) {
					conn.addRequestProperty("Cookie", cookie.split(";",2)[0]);
				}*/
				
		 
				//从输入流读取返回内容
				InputStream inputStream = conn.getInputStream();
				InputStreamReader inputStreamReader = new InputStreamReader(inputStream,"utf-8");
				BufferedReader bufferedReadr = new BufferedReader(inputStreamReader);
				String str = null;
				StringBuffer buffer = new StringBuffer();
				while((str = bufferedReadr.readLine())!=null){
					buffer.append(str);
				}
				bufferedReadr.close();
				inputStream.close();
				inputStreamReader.close();
				inputStream = null;
				conn.disconnect();
				jsonObject = JSONObject.parseObject(buffer.toString());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return jsonObject;
 
	}

}
