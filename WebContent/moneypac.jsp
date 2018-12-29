<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
    <title></title>
    		<header class="mui-bar mui-bar-nav">
		    <a class="mui-action-back mui-icon mui-icon-left-nav mui-pull-left"></a>
		    <h1 class="mui-title">我的钱包</h1>
		</header>
    <script src="js/mui.min.js"></script>
    <link href="css/mui.min.css" rel="stylesheet"/>
    <link rel="stylesheet" href="css/iconfont.css" />
    <script type="text/javascript" charset="UTF-8">
      	mui.init();
    </script>
</head>
<body>
<div class="mui-content" style="margin-top: 20px;">
			<div>
		    	<ul  class="mui-table-view">
		        	<li class="mui-table-view-cell">		          
		               <span>我的钱包</span> <br>
		               <img src="jpg/钱包.png" style="width: 30px; margin-top: 10px; float:left;"> 
		               <span style="margin-left: 25px; font-size: 20px; float: left; margin-top: 15px;" class="mui-text-center">￥${num}</span>
		            <button type="button"  id="changebtn" class="mui-pull-right">提现</button>	            
		        	</li>
		    	</ul>
		    </div>		
</div>	
<div class="mui-content" style="margin-top: -15px;">
			<div>
		    	<ul  class="mui-table-view">
		        	<li class="mui-table-view-cell">		          
		               <span>收入明细</span> <br>
<c:forEach items="${list}" var="lit">	                
		                <div style="border-top: 1px solid #CCCCCC;padding-top: 10px; margin-top: 10px;">
		                	订单号：<span>${lit.didnum}</span>
		                 <button type="button" id="changebtn" class="mui-pull-right" style="border: 0px; color: red; font-size: 20px;">+${lit.didmoney}</button>
		                  <br>
		                 <div>
		                 	<span style="font-size: 13px; color: #6D6D72;">${lit.didtime}</span>
		                 </div>
		                </div>
</c:forEach>
  
		        	</li>
		    	</ul>
		    </div>		
</div>	
</body>
</html>