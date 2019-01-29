package com.iss.Controll;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.iss.bean.Listshow;
import com.iss.bean.SNSUserInfo; 
import com.iss.bean.User;
import com.iss.bean.address;
import com.iss.bean.did;
import com.iss.bean.didrecord;
import com.iss.bean.recover;
import com.iss.dao.ReuserDao;
import com.iss.dao.UserDao;
import com.iss.until.AdvancedUtil;
import com.mysql.fabric.xmlrpc.base.Array;

@Controller
public class controll {
	@Autowired
	private UserDao userdao;
	@Autowired
	private ReuserDao reuserdao;
		@RequestMapping("loginServlet")
		public String test1(HttpServletRequest request,HttpServletResponse response){
			SNSUserInfo userinfo= (SNSUserInfo)request.getAttribute("snsUserInfo");
			User us = userdao.checkuser(userinfo.getOpenId());
			User user = new User();
			if(us==null){ 
				 userdao.InsertUser(userinfo.getOpenId());
				 User us1 = userdao.checkuser(userinfo.getOpenId()); 
				 user.setUid(us1.getUid());
			}else{
				user.setUid(us.getUid());
			}
			user.setImg(userinfo.getHeadImgUrl());
			user.setOpenid(userinfo.getOpenId());
			user.setName(userinfo.getNickname());
			request.getSession().setAttribute("user", user);
			request.getSession().setMaxInactiveInterval(600);
			

			return "start";
			
	}	 
////////添加地址---success
		@RequestMapping("SubmitAddress")
		public String test2(HttpServletRequest request,HttpServletResponse response,address add){
			/*String p1 = request.getParameter("name1");
			String p2 = request.getParameter("name2");
			String wxText = AdvancedUtil.model_send1("osbMV0rKOhf-F-XF10ujnSFHi0TY", "www.baidu.com", "20", "000", "haha");
			AdvancedUtil.sendmodelinformation(wxText);*/
			User user = (User)request.getSession().getAttribute("user");
			add.setAdd_uid(Integer.parseInt(user.getUid()));
			userdao.SupAddress(add);
			
			return "redirect:ListAddress.do";
			
	}	 
////////列出地址---success
		@RequestMapping("ListAddress")
		public String test3(HttpServletRequest request,HttpServletResponse response,ModelMap mp){
			 User user = (User)request.getSession().getAttribute("user");
 
			List<address> list =  userdao.ListAddress(Integer.parseInt(user.getUid()));
			 
			mp.addAttribute("list", list);
			return "address";     
			   
	}	 
////////列出单一地址--- success
		@RequestMapping("OneAddress")
		public String test4(HttpServletRequest request,HttpServletResponse response,ModelMap mp){
			int aid = Integer.parseInt(request.getParameter("aid"));
			address add = userdao.oneAddress(aid);
			System.out.println(add.getAid());
			 mp.addAttribute("add", add);
			
			return "address_1";
			
	}	
////////删除地址--- success 
		@RequestMapping("DeleteAddress")
		public String test5(String aid){		 
			userdao.DeleteAddress(Integer.parseInt(aid));	
			return "redirect:ListAddress.do";
	}	
///////修改地址--- success 
		@RequestMapping("ReviseAddress")
		public String test6(ModelMap mp,address add){		 
			  userdao.ReviseAddress(add);
			  System.out.println("00.0.0"+add.getDetailaddress());
			return "redirect:ListAddress.do";
			
	}	
////////用户订单的显示---success  
		@RequestMapping("ListDid")
		public String test7(ModelMap mp,HttpServletRequest request){	
			List<didrecord> list1 = new ArrayList<>(); 
			List<didrecord> list2 = new ArrayList<>();
			 User user = (User)request.getSession().getAttribute("user");
			List<didrecord> list = userdao.ListDid(Integer.parseInt(user.getUid()));
			System.out.println(list.size());
			for (didrecord drd : list) {
				 drd.setDidtime(drd.getDidtime().substring(0, drd.getDidtime().length()-2));
				if(drd.getState().equals("0")){
					list1.add(drd);
				}else{
					list2.add(drd);
				}
			}
			System.out.println("list.size:"+list1.size()+"list2:"+list2.size());
			mp.addAttribute("list1", list1);//未完成
			mp.addAttribute("list2", list2);//已完成
			return "tabbar";
			
	}		
////////用户订单的提交--- success 
		@RequestMapping("SendDid")
		public String test8(ModelMap mp,did dd){		 
			 userdao.SendDid(dd);
			 
			 
			return " ";
			
	}	
////////用户订单状态的修改---success  
		@RequestMapping("ReviseDid")
		public String test9(ModelMap mp){		 
			  userdao.ReviseDid(2, 1);
			 
			 
			return " ";
			
	}
////////显示用户总金额,和用户收入详情---success  
		@RequestMapping("CollectMoney")
		public String test10(ModelMap mp,HttpServletRequest request){	
			 User user = (User)request.getSession().getAttribute("user");
			 String num = "";
			if(userdao.CollectMoney(Integer.parseInt(user.getUid()))==null){
				num = "0";
			}
			else{
				num = userdao.CollectMoney(Integer.parseInt(user.getUid()));
			}
			mp.addAttribute("num", num);
			List<did> list1 = new ArrayList<>();
			 List<did> list = userdao.MoneyDetail(Integer.parseInt(user.getUid()));
			 System.out.println("0000"+list.size());
			 for (did did : list) {
				 
				 if(did.getState().equals("1")){
					 did.setDidtime(did.getDidtime().substring(0, did.getDidtime().length()-2));
					list1.add(did);
				} 
			}
			 
			 mp.addAttribute("list", list1); 
			return "moneypac";
			
	}
////////显示用户收入情况---success  
		@RequestMapping("MoneyDetail")
		public String test11(ModelMap mp,HttpServletRequest request){	
			User user = (User)request.getSession().getAttribute("user");
		 List<did> list = userdao.MoneyDetail(Integer.parseInt(user.getUid()));
			 
			return " ";
			
	}
////////用户选择地址---   
		@RequestMapping("Userseladd")
		public void test12(ModelMap mp,HttpServletResponse response,HttpServletRequest request) throws IOException{	
			 String region = request.getParameter("region");
			String query = request.getParameter("region"); 
			if(request.getParameter("query")!=null){
				query =  request.getParameter("query").replaceAll(" +",""); 
			}else{
				
			}
			System.out.println("query:"+query);
			System.out.println("region:"+region);
 
			  String jsonStr = JSON.toJSONString(AdvancedUtil.Addressreback(query,region));
			 
			PrintWriter out = response.getWriter();
			    out.print(jsonStr);
			    out.close();
		 
			 
			 
		 
			
	}
////////实现用户准备下订单功能--- 
		@RequestMapping("PreSend")
		public String test12(ModelMap mp,HttpServletRequest request){	
			 User user = (User)request.getSession().getAttribute("user");	
			List<address> list =  userdao.ListAddress(Integer.parseInt(user.getUid()));
			mp.addAttribute("list", list);
			return "order";
			
	}
////////实现用户下订单功能，并且派发给回收员---success
		@RequestMapping("Senddd")
		public String test13(ModelMap mp,did dd,String[] Checkbox,HttpServletRequest request){
			User user = (User)request.getSession().getAttribute("user");
			String content = "";
			 for (String string : Checkbox) {
					content +=string+"+";
				}
			 
				content =  content.substring(0, content.length()-1);
			String Listnumber = AdvancedUtil.creatListnum();
			dd.setContent(content);
			dd.setDidnum(Listnumber);
			dd.setDidmoney("0");
			dd.setDidtime(AdvancedUtil.getdatatime());
			dd.setState("0"); 
			dd.setUid(user.getUid());
			userdao.SendDid(dd);
			int did = reuserdao.selectdid(Listnumber);
			address add = reuserdao.selectlatlng(Integer.parseInt(dd.getAid()));
		    recover rec =reuserdao.selectreuser(add.getLat(), add.getLng());
		    reuserdao.insertdid(did,Integer.parseInt(rec.getReuid()));
		    String wxText =  AdvancedUtil.model_send1(rec.getU_openid(), "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx74baf233f698cf83&redirect_uri=http%3A%2F%2Fpobing.duapp.com%2Fweixindemo_1%2FOAuthServlet?admin=1&response_type=code&scope=snsapi_base&state=STATE&#wechat_redirect", Listnumber, dd.getContent(), dd.getRemarks());
			AdvancedUtil.sendmodelinformation(wxText);
			return "start";
			
	}
////////--- 
		@RequestMapping("receive")
		public String test14(ModelMap mp,did dd,String[] Checkbox){	
			 String content = "";
			 
		
			return " ";
			
	}
		/*****************回收员操作******************/
////////回收员进入----
		@RequestMapping("adminServlet")
		public String test13(HttpServletRequest request,HttpServletResponse response,address add){
			SNSUserInfo userinfo= (SNSUserInfo)request.getAttribute("snsUserInfo");
			 userinfo.getOpenId();
			 userinfo.getHeadImgUrl();
			 //从数据库里查询是否有回收员，如果没有进入一个页面，如果有的话，再通过判定
			 //state状态进行下列操作。 
			recover rec =  reuserdao.selectrec(userinfo.getOpenId()); 
			request.getSession().setAttribute("openid", userinfo.getOpenId());
			 if(rec!=null){
				 if(rec.getState()==0){
					return "redirect:registersuccess.html"; 
				 }else{
					 rec.setImgurl(userinfo.getHeadImgUrl());
						rec.setU_openid(userinfo.getOpenId());
						 
					 request.getSession().setAttribute("rec", rec);
					 return "redirect:Showlistbyru.do";
				 }
				 
			 }else{
				 return "redirect:rebackuser.html";
			 }
 
			
	}	
		/*2*/
////////---回收员申请信息提交
		@RequestMapping("Sendreuserifo")
		public String test15(ModelMap mp,recover rec,HttpServletRequest request){	
			 rec.setU_openid(request.getSession().getAttribute("openid").toString());
			reuserdao.insertreuserinfo(rec);	
			System.out.println(rec.getPhotonum()+"0000000000000");
			return "redirect:registersuccess.html";
			 
	}
////////---回收员显示自己已完成或者未完成的订单
		@RequestMapping("Showlistbyru")
		public String test21(ModelMap mp,HttpServletRequest request){	
			List<didrecord> list1 = new ArrayList<>(); 
			List<didrecord> list2 = new ArrayList<>();
		 
			 recover rec = (recover)request.getSession().getAttribute("rec");
			 
            List<didrecord> list = reuserdao.showonelist(Integer.parseInt(rec.getReuid()));	  
			System.out.println(list.size());
			for (didrecord drd : list) {
				 drd.setDidtime(drd.getDidtime().substring(0, drd.getDidtime().length()-2));
				if(drd.getState().equals("0")){
					list1.add(drd);
				}else{
					list2.add(drd);
				}
			}
			System.out.println("list.size:"+list1.size()+"list2:"+list2.size());
			mp.addAttribute("list1", list1);//未完成
			mp.addAttribute("list2", list2);//已完成
			return "reuser_tabbar";
			
	}
////////---回收员进行输入金额完成订单操作-success
		@RequestMapping("makedid")
		public void test21(ModelMap mp,float didmoney,int did,String didnumber,HttpServletResponse response,HttpServletRequest request) throws IOException{	
			 recover rec = (recover)request.getSession().getAttribute("rec");
			 String touser =  reuserdao.showuseropenid(did);
			 reuserdao.didsuccess(did, didmoney,1);
			 String wxText = AdvancedUtil.model_send3(touser, "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx74baf233f698cf83&redirect_uri=http%3A%2F%2Fpobing.duapp.com%2Fweixindemo_1%2FOAuthServlet?admin=0&response_type=code&scope=snsapi_base&state=STATE&#wechat_redirect", rec.getName(), didmoney,didnumber);
			 AdvancedUtil.sendmodelinformation(wxText);
				PrintWriter out = response.getWriter();
			    out.print("1"); 
			    out.close(); 
 
	} 		
////////---回收员支出详情-success 
		@RequestMapping("reuseroutall")
		public String test22(ModelMap mp,HttpServletRequest request) throws IOException{	
			 recover rec = (recover)request.getSession().getAttribute("rec"); 
			 String num = "";
			 if(reuserdao.reuseroutall(Integer.parseInt(rec.getReuid()))==null){
				  num="0";
			 }else{
				 num=reuserdao.reuseroutall(Integer.parseInt(rec.getReuid()));
			 }
			 
			mp.addAttribute("num", num);
			List<did> list1 = new ArrayList<>();
			 List<did> list = reuserdao.reuserouts(Integer.parseInt(rec.getReuid()));
			 for (did dd : list) {
				 
				 if(dd.getState().equals("1")){
					 dd.setDidtime(dd.getDidtime().substring(0, dd.getDidtime().length()-2));
					list1.add(dd);
				} 
			}
			 
			 mp.addAttribute("list", list1); 
			 return "reuser_moneypac";
 
	} 	


		/*****************回收员操作******************/
		             /*-------------*/
		
		/*****************管理员操作******************/
////////---显示所有订单-success
		@RequestMapping("showalllist")
		public String test18(ModelMap mp){	
			 List<Listshow>  list = reuserdao.showalllist();
			System.out.println("successchangestate"+list.size()+""+list.toString());
			mp.addAttribute("list", list);
			return "admin/article-list";
			 
	} 
////////---显示所有回收员state = 0-success
		@RequestMapping("showallreuser") 
		public String test19(ModelMap mp){	
			 List<recover>  list = reuserdao.showallreuser();
			System.out.println("successchangestate"+list.size()+""+list.toString());
			mp.addAttribute("list", list);
			return "admin/member-del";
			 
	} 
////////---显示所有回收员state = 1-success
		@RequestMapping("showallreuser1") 
		public String test20(ModelMap mp){	
			 List<recover>  list = reuserdao.showallreuser1();
			mp.addAttribute("list", list);
			return "admin/member-list";
			 
	} 
////////---回收员地址信息的修改--
		@RequestMapping("changereuseradd")
		public String test16(ModelMap mp,recover rec,HttpServletResponse response) throws IOException{	
			System.out.println("00000000000000000000");
			reuserdao.reuseraddressbyadmin(rec);
			System.out.println("successaddress");
			PrintWriter out = response.getWriter();
		    out.print("1");
		    out.close();
			return " "; 
			 
	} 
////////---回收员state修改
		@RequestMapping("changereuserstate")
		public String test17(ModelMap mp,int reuid){	
			reuserdao.rechangestate(1, reuid);
			System.out.println("successchangestate");
			return " ";
			
	}
////////---回收员state修改
		@RequestMapping("deluserinfo")
		@ResponseBody
		public String test18(ModelMap mp,int reuid){	
			int flag = reuserdao.delreuserinfo(reuid);
			System.out.println("return"+flag);
			if(flag==1)	
			return "success";
			else
			return "false";
			
	}
}
