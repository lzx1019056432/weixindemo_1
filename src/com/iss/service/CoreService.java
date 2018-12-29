package com.iss.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.Session;

import com.iss.apiservice.Servicedemo;
import com.iss.bean.Newsa;
import com.iss.message.resp.Article;
import com.iss.message.resp.NewsMessage;
import com.iss.message.resp.TextMessage;
import com.iss.until.MessageUtil;

public class CoreService {
/*
 * 处理微信发来的请求
 * @param request
 * @return xml
 * */
	public static String processRequest(HttpServletRequest request){
		//xml格式的消息数据
		String respXml = null;
		//默认返回的文本消息内容
		String respContent = "未知的消息类型！";
		try {
			//调用parseXml方法解析请求消息
			Map<String,String> requestMap = MessageUtil.parseXml(request);
		    //发送方账号
			String fromUserName = requestMap.get("FromUserName");
			//开发者微信号
			String toUserName = requestMap.get("ToUserName");
			//消息类型
			String msgType = requestMap.get("MsgType");
			//回复文本消息
			TextMessage textMessage = new TextMessage();
			textMessage.setToUserName(fromUserName);
			textMessage.setFromUserName(toUserName);
			textMessage.setCreateTime(new Date().getTime());
			textMessage.setMsgType(MessageUtil.REQ_MESSAGE_TYPE_TEXT);
			//获取到的文本消息内容
			String text = requestMap.get("Content");
		    //文本消息
			if(msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)){
				/*if(text.equals("11")){
					Servicedemo serdemo1 = new Servicedemo();
					String result;
					System.out.println("0.0.0.0.0"+result);
					textMessage.setContent(result);	
					  respXml = MessageUtil.messageToXml(textMessage);
				}*/
				if(text.equals("新闻")){
				Servicedemo serdemo = new Servicedemo();
				String content = "";
				String contents = "";
				List<Newsa> list = serdemo.newschear();
				for (int i = 0; i < 10; i++) {
					content = "No"+i+"."+list.get(i).getTittle()+"\n"+list.get(i).getUrl()+"\n";
					contents += content; 
				}
				textMessage.setContent(contents);	
				  respXml = MessageUtil.messageToXml(textMessage);
				}

			}
		    //图片消息
			else if(msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_IMGAGE))
			{  Article article = new Article();
			   article.setTitle("惊动人们的大事情");
			  article.setPicUrl("");
			  article.setURL("http://www.baidu.com");
			  List<Article> articlelist = new ArrayList<Article>();
			  articlelist.add(article);
			  NewsMessage newsMessage = new NewsMessage();
			  newsMessage.setToUserName(fromUserName);
			  newsMessage.setFromUserName(toUserName);
			  newsMessage.setCreateTime(new Date().getTime());
			  newsMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
			  newsMessage.setArticleCount(articlelist.size());
			  newsMessage.setArticles(articlelist);
			  respXml = MessageUtil.messageToXml(newsMessage);
			  
			}
		    //语音消息
			else if(msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VOICE))
			{ respContent = "您发送的是语音消息";}
		    //视频消息
			else if(msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VIDEO))
			{ respContent = "您发送的是视频消息";}
		    //地理位置接消息
			else if(msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LOCATION))
			{ respContent = "您发送的是地理位置消息";}
		    //链接消息
			else if(msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LINK))
		    {respContent = "您发送的是链接消息";}
		    //事件推送
			else if(msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT))
		    {
				//事件类型
				String eventType = requestMap.get("Event");
				//关注
				if(eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)){
					respContent = "谢谢您的关注";
				}
				//取消关注
				else if(eventType.equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE))
				{
					//TODO 无需处理
				}
				//扫描带参数二维码
				else if(eventType.equals(MessageUtil.EVENT_TYPE_SCAN)){
					//TODO 处理此事件
				}
				//上报地理位置
				else if(eventType.equals(MessageUtil.EVENT_TYPE_LOCATION)){
					String lat = requestMap.get("Latitude"); 
					String lng = requestMap.get("Longitude");
					request.getSession().setAttribute("lat", lat);
					request.getSession().setAttribute("lng", lng);
					System.out.println(lat+"00000000000000"+lng);
				}
				//自定义菜单
				else if(eventType.equals(MessageUtil.EVENT_TYPE_CLICK)){
					//TODO 处理菜单点击事件
				 }
		    }
			//设置文本消息的内容
			//textMessage.setContent(respContent);
			//将文本消息对象转换成XML
			//respXml = MessageUtil.messageToXml(textMessage);
 
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return respXml;
		
	}
}
