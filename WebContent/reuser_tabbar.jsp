<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<!doctype html>
<html>

	<head>
		<meta charset="UTF-8">
		<!-- <header class="mui-bar mui-bar-nav">
			<h1 class="mui-title">订单记录</h1>
		</header> -->
		<title></title>
		<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
		<link href="css/mui.min.css" rel="stylesheet" />
		<link rel="stylesheet" href="css/iconfont.css" />
		<script type="text/javascript" src="js/jquery-3.2.1.min.js"></script>
	</head>
<script>
		$(document).ready(function() {
                  
     			$(".checkmoney").click(function() {
     				var did = $(this).next().val();
     				var didnumber = $(this).next().next().val();
     				mui.prompt("请输入应付金额","请输入金额","金额",function(e){
     					 
     				
     					$.post("makedid.do",{
     							did: did,
     							didmoney:e.value,
     							didnumber:didnumber
     						},
     						function(result) {
     							 window.location.reload();	
 
     						});
     					
     				});

     			});

		});
	</script>
	<body>
		<div class="mui-content">

			<div class="mui-segmented-control" style="margin-top:10px;">
				<a class="mui-control-item mui-active" href="#item1">
					未完成订单
				</a>
				<a class="mui-control-item" href="#item2">
					已完成订单
				</a>
			</div>
			<div style="margin-top: 20px;" id="item1" class="mui-control-content mui-active">
				<ul class="mui-table-view">
	<c:forEach items="${list1}" var="lit1">

				        <li class="mui-table-view-cell" >
				         <span>${lit1.didtime}</span> <br>  
				            订单编号：<span>${lit1.didnum}</span><br>
				       订单内容： <span>${lit1.content}</span><br> 		   
				                     
				         <button type="button" class="mui-btn  mui-btn-outlined checkmoney">确认</button>
				          <input type="hidden" value="${lit1.did }" id="did">
				          <input type="hidden" value="${lit1.didnum}" id="didnumber">
				        </li>
	</c:forEach>


				    </ul>
				    <div style="width:100%; height:50px;">&nbsp;</div>
			</div>
			<div id="item2" class="mui-control-content" style="margin-top: 20px;">
				<ul class="mui-table-view">
	<c:forEach items="${list2}" var="lit2">

				        <li class="mui-table-view-cell">
				         <span>${lit2.didtime}</span> <br>  
				            订单编号：<span>${lit2.didnum}</span><br>
				        订单内容:<span>${lit2.content}</span>
				    <br>
				    金额：<span style="color: red;">￥${lit2.didmoney}</span><br />
				              	 
				               
				         <button type="button" class="mui-btn  mui-btn-outlined" style="display:none">查看详情</button>
				           
				        </li>
	</c:forEach>
				    </ul>
				    <div style="width:100%; height:50px;">&nbsp;</div>
			</div>

		</div>
		<nav class="mui-bar mui-bar-tab">
    <a href="Showlistbyru.do" class="mui-tab-item mui-active">
        <span class="mui-icon iconfont icon-dingdan"></span>
        <span class="mui-tab-label">订单</span>
    </a>
    <a href="reuser_myhome.jsp" class="mui-tab-item">
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