<%@page import="com.iss.bean.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html>

	<head>
		<meta charset="UTF-8">
		<title></title>
		<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
		<link href="css/mui.min.css" rel="stylesheet" />
		<link rel="stylesheet" href="css/iconfont.css" />
	</head>
<%
User user = (User)request.getSession().getAttribute("user");
 
 
%>
	<body>
		<div class="mui-content">
	<ul class="mui-table-view" style="font-size: 14px; font-family: '微软雅黑'; margin-top: 0px;">
					 <li class="mui-table-view-cell mui-text-center" style="background-color: #007AFF; height: 180px;">
					 <img style="width:100px;height:100px;border-radius:50px;border:solid rgb(100,100,100) 0px; margin-top: 25px;" src="<%=user.getImg()%>"/>
					 <div>
					 	<%=user.getName() %>
					 </div>
					 </li>
			        <li class="mui-table-view-cell">
			            <a href="CollectMoney.do" class="mui-navigate-right">
			            	<span class="mui-icon iconfont icon-qianbao" style="margin-left: 5px;"></span>
			                <span style="margin-left: 15px;">我的钱包</span>
			            </a>
			        </li>
			        <li	style="margin-top:0px;" class="mui-table-view-cell">
			            <a class="mui-navigate-right" href="ListAddress.do">
			            	<span class="mui-icon mui-icon mui-icon-location " style="margin-left: 0px;"></span>
			                  <span style="margin-left: 11px;">我的地址</span>
			            </a>
			        </li>
			     
			    </ul>
		</div>
 
		<nav class="mui-bar mui-bar-tab">
    <a href="start.jsp" class="mui-tab-item">
        <span class="mui-icon mui-icon-home"></span>
        <span class="mui-tab-label">首页</span>
    </a>

    <a href="ListDid.do" class="mui-tab-item">
        <span class="mui-icon iconfont icon-dingdan"></span>
        <span class="mui-tab-label">订单</span>
    </a>
    <a href="myhome.jsp" class="mui-tab-item mui-active">
        <span class="mui-icon mui-icon-contact"></span>
        <span class="mui-tab-label">我的</span>
    </a>
</nav>
		<script src="js/mui.min.js"></script>
		<script type="text/javascript">
			mui.init();
			mui('body').on('tap','a',function(){document.location.href=this.href;});
		</script>
	</body>

</html>