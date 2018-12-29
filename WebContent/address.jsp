<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>

	<head>
		<header class="mui-bar mui-bar-nav">
		    <a href="myhome.jsp" class="  mui-icon mui-icon-left-nav mui-pull-left"></a>
		    <h1 class="mui-title">我的地址</h1>
		</header>
		<meta charset="UTF-8">
		<title></title>
		<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
		<link href="css/mui.min.css" rel="stylesheet" />
		<script type="text/javascript" src="js/jquery-3.2.1.min.js" ></script>
	    <script>
	    	$(document).ready(function(){
	    		$("#changebtn").click(function(){
	    			 
	    			$(".changeadd1").toggle();
	    			$(".changeadd2").toggle();
	    		});
	    		
	    	});
	    	
	    </script>
	    <style>
	     
	    </style>
	
	</head>

	<body style="font-family: '微软雅黑'; font-size: 14px;">
		<div class="mui-content">
			<div>
		    	<ul  class="mui-table-view">
		        	<li class="mui-table-view-cell" style="line-height:33px;">		          
		               我的地址
		            <button type="button"  id="changebtn" class="mui-pull-right">管理</button>	            
		        	</li>
		    	</ul>
		    </div>	 
<c:forEach items="${list}" var="lit">

		    
		     <div style="margin-top: 10px;" >
		 			<ul class="mui-table-view">
		         <li class="mui-table-view-cell">
		         	<table>
		         		<tr>
		         			<td style="width: 100%;"> <span style="font-weight:bold">${lit.address }</span>
		         			 <div style="font-size: 13px;">${lit.detailaddress }</div>
		            <div style="margin-top: 10px;"><span style="color: grey;">  <span>${lit.name}</span><span>&nbsp;(${lit.appname })</span>	<span>${lit.phonenumber }</span></span></div></td>
		         			<td style="text-align: right;" id="changeadd">
		         				<a href="OneAddress.do?aid=${lit.aid }"><button class="changeadd1">修改</button></a>
                                <a href="DeleteAddress.do?aid=${lit.aid }"><button style="display: none;" class="changeadd2">删除</button></a>      			
		         			</td>
		         		</tr>
		        
		         
		         </table>
		        </li>
		     		</ul>
		    </div>
</c:forEach>
<div style="margin-top:60px;">&nbsp;</div>		    		 
		</div>
		<nav class="mui-bar mui-bar-tab">
		    <a href="address3.jsp" class="mui-tab-item mui-active">
		    	<span class="mui-icon mui-icon-plusempty"></span>
		  新增收获地址
		    </a>
		</nav>
		<script src="js/mui.min.js"></script>
		<script type="text/javascript">
			mui.init();
			mui(document.body).on('tap', '.mui-btn', function(e) {
    mui(this).button('loading');
    setTimeout(function() {
        mui(this).button('reset');
    }.bind(this), 2000);
});
	 mui('body').on('tap','a',function(){document.location.href=this.href;});
		</script>
	</body>

</html>