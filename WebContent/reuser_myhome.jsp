<%@page import="com.iss.bean.recover"%>
<%@page import="com.iss.bean.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
recover rec = (recover)request.getSession().getAttribute("rec");
%>
	<body>
		<div class="mui-content">
	<ul class="mui-table-view" style="font-size: 14px; font-family: '微软雅黑'; margin-top: 0px;">
					 <li class="mui-table-view-cell mui-text-center" style="background-color: #007AFF; height: 180px;">
					 <img style="width:100px;height:100px;border-radius:50px;border:solid rgb(100,100,100) 0px; margin-top: 25px;" src="<%=rec.getImgurl()%>"/>
					 <div>
					 	回收员：<%=rec.getName() %>
					 </div>
					 </li>
			        <li class="mui-table-view-cell">
			            <a href="reuseroutall.do" class="mui-navigate-right">
			            	<span class="mui-icon iconfont icon-qianbao" style="margin-left: 5px;"></span>
			                <span style="margin-left: 15px;">支出详情</span>
			            </a>
			        </li>
			        <!-- <li	style="margin-top:0px;" class="mui-table-view-cell">
			            <a class="mui-navigate-right" href="ListAddress.do">
			            	<span class="mui-icon mui-icon mui-icon-location " style="margin-left: 0px;"></span>
			                  <span style="margin-left: 11px;">我的地址</span>
			            </a>
			        </li> -->
			     
			    </ul>
		</div>
		<nav class="mui-bar mui-bar-tab">
    <a href="Showlistbyru.do" class="mui-tab-item">
        <span class="mui-icon iconfont icon-dingdan"></span>
        <span class="mui-tab-label">订单</span>
    </a>
    <a href="reuser_myhome.jsp" class="mui-tab-item mui-active">
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