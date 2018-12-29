package com.iss.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.iss.bean.SNSUserInfo;
import com.iss.bean.WeixinAouth2Token;
import com.iss.until.AdvancedUtil;

/**
 * Servlet implementation class OAuthServlet
 */
@WebServlet("/OAuthServlet")
public class OAuthServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OAuthServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String code = request.getParameter("code");
		String admin = request.getParameter("admin");
		String appid = "wx74baf233f698cf83";
		String appsecret="4531fd6943457407d420f68ffd122dc4";
		//用户同意授权
		if(!"authdeny".equals(code)){
			WeixinAouth2Token weixinOauth2Token = 
			AdvancedUtil.getOauth2AccessToken("wx74baf233f698cf83", "4531fd6943457407d420f68ffd122dc4", code);
			//网页授权接口访问凭证
			String accessToken = weixinOauth2Token.getAccessToken();
			 
			//用户标识
			String openId = weixinOauth2Token.getOpenId();
			System.out.println("openId"+openId+",accessToken:"+accessToken);
			//获取用户信息
			SNSUserInfo snsUserInfo = AdvancedUtil.getSNSUserInfo(accessToken,openId);
		    AdvancedUtil.getSNSUserInfobyopenId(accessToken, openId);
			System.out.println("user-nick"+snsUserInfo.getNickname());
			//设置要传递参数
			request.setAttribute("snsUserInfo", snsUserInfo);
/* 		  //底部菜单的创建测试
			AdvancedUtil.creatbottomlist();  */ 
			//模板信息测试
		
/*			String wxText = AdvancedUtil.model_send2("osbMV0rKOhf-F-XF10ujnSFHi0TY", "www.baidu.com", "振兴");
*//*			 String wxText = AdvancedUtil.model_send1("osbMV0rKOhf-F-XF10ujnSFHi0TY", "www.baidu.com", "12313213", "666", "多放点辣椒");
*/		/*	String wxText =  AdvancedUtil.model_send3("osbMV0rKOhf-F-XF10ujnSFHi0TY", "www.baidu.com", "振兴", "￥66");
			AdvancedUtil.sendmodelinformation(openid, appsecret, wxText);*/
		}
		if(admin.equals("1")){
			request.getRequestDispatcher("adminServlet.do").forward(request,response);	
		}else{
		request.getRequestDispatcher("loginServlet.do").forward(request,response);
		}
 	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
