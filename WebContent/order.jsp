<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html>

	<head>
		<meta charset="UTF-8">
		<header class="mui-bar mui-bar-nav" id="did_html1_header">
		    <a class="mui-action-back mui-icon mui-icon-left-nav mui-pull-left"></a>
			<h1 class="mui-title">订单页面</h1>
		</header>
		 <header class="mui-bar mui-bar-nav display_none" id="did_html2_header">
		    <a class=" mui-icon mui-icon-left-nav mui-pull-left" id="reback"></a>
		    <h1 class="mui-title">地址选择</h1>
		</header>
		<title></title>
		<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
		<link href="css/mui.min.css" rel="stylesheet" />
		<link rel="stylesheet" href="css/address.css" />
		<script type="text/javascript" src="js/jquery-3.2.1.min.js" ></script>
		<script>
			$(document).ready(function(){
				$("#selectadd").click(function(){
				$("#did_html1").addClass("display_none"); 
				$("#did_html1_header").addClass("display_none");
				$("#did_html2_header").removeClass("display_none");
				$("#did_html2").removeClass("display_none");
				});
				//选择地址并将该地址的值赋给目前模板。
				$(".selectaddress").click(function(){
				
				var address1=$(this).find("tr").find("td").find('span[id="text_1"]').text();
				var address2=$(this).find("tr").find("td").find('span[id="text_1"]').next().text();
				var address3=$(this).find("tr").find("td").find('span[id="text_1"]').next().next().find('span[id="text_3"]').text();
				var address4=$(this).find("tr").find("td").find('span[id="text_1"]').next().next().find('span[id="text_4"]').text();
				var address5=$(this).find("tr").find("td").find('span[id="text_1"]').next().next().find('span[id="text_5"]').text();
				var address6=$(this).find("tr").find('td[id="changeadd"]').find("input").val(); 
				$("#address_1").text(address1);
				$("#address_2").text(address2);
				$("#address_3").text(address3);
				$("#address_4").text(address4);
				$("#address_5").text(address5);
				$("#aid").val(address6);
				$("#address").removeClass("display_none");
				$("#did_html2").addClass("display_none");
				$("#did_html2_header").addClass("display_none");
				$("#did_html1_header").removeClass("display_none");
				$("#did_html1").removeClass("display_none");
				});
				
				$("#reback").click(function(){
				$("#did_html2").addClass("display_none");
				$("#did_html2_header").addClass("display_none");
				$("#did_html1_header").removeClass("display_none");
				$("#did_html1").removeClass("display_none");	
				});
			});
		</script>
		<style>
			.display_none{
				display: none;
			}
		</style>
		<script>
		$(document).ready(function(){
			$("#test").click(function(){
				var checkboxs = $("input:checked").length;
				 var id = $("#aid").val().length;
				 var textareas = $("#textareas").val().length;
				 if(checkboxs<1){
				 	alert("请选择一项产品");
				 }else if(id<1){
				 	alert("请选择地址");
				 }else{
					 if(textareas==0){
				 	$("#textareas").val("无");
					 }
				 	$("#form1").submit();
				 }
			});
		});
		
		
		</script>
	</head>

	<body>
		
		<div class="mui-content" id="did_html1">
		<form class="mui-input-row " action="Senddd.do" method="post" id="form1">
			<ul class="mui-table-view">
				<li class="mui-table-view-cell mui-collapse mui-active">
					<a class="mui-navigate-right" href="#">选择物品</a>
					<div class="mui-collapse-content">
						<div class="mui-input-row mui-checkbox ">
							<label>废纸</label>
							<input name="Checkbox" type="checkbox" value="废纸">
						</div>
						<div class="mui-input-row mui-checkbox ">
							<label>塑料</label>
							<input name="Checkbox" type="checkbox" value="塑料">
						</div>
						<div class="mui-input-row mui-checkbox ">
							<label>玻璃</label>
							<input name="Checkbox" type="checkbox" value="玻璃">
						</div>
						<div class="mui-input-row mui-checkbox ">
							<label>金属</label>
							<input name="Checkbox" type="checkbox" value="金属">
						</div>
						<div class="mui-input-row mui-checkbox ">
							<label>布料</label>
							<input name="Checkbox" type="checkbox" value="布料">
						</div>
						<div class="mui-input-row mui-checkbox ">
							<label>其他</label>
							<input name="Checkbox" type="checkbox" value="其他">
						</div>

					</div>
				</li>
			</ul>
			<ul class="mui-table-view">
				<li class="mui-table-view-cell">
					<a class="mui-navigate-right" id="selectadd">
			               选择地址
			            </a>
			            <input type="hidden" name="aid" id="aid">
				</li>

			</ul>
					     <div style="margin-top: 10px;" id="address" class="display_none">
		 			<ul class="mui-table-view">
		         <li class="mui-table-view-cell">
		         	<table>
		         		<tr>
		         			<td style="width: 100%;"> <span style="font-weight:bold" id="address_1">天津工  </span>
		         			 <div style="font-size: 13px;" id="address_2">天津</div>
		            <div style="margin-top: 10px;"><span style="color: grey;">  <span id="address_3">梁某某</span>&nbsp;<span id="address_4">先生</span>	<span id="address_5">17695449122</span></span></div></td>
		         			<td style="text-align: right;" id="changeadd">
		         				     			
		         			</td>
		         		</tr>
		        
		         
		         </table>
		        </li>
		     		</ul>
		    </div>
			
				<p>备注</p>
				<textarea class="mui-input-clear" type="text" placeholder="请填写您的详细需求" name="Remarks" id="textareas"></textarea>
				</form>	
				<div class="mui-text-center" ><button class="btn mui-btn-blue" style="height:40px; width:20%;" name="" id="test" >提交</button></div>
				<div style="height:50px; width:100%;">
				&nbsp;
				</div>
 
		 
		</div>
	<!--

    	描述：选择地址页面
    -->	 
<div class="mui-content display_none" id="did_html2">
 
<c:forEach items="${list}" var="lit">		    
		     <div style="margin-top: 10px;" >
		 			<ul class="mui-table-view">
		         <li class="mui-table-view-cell">
		         	<table class="selectaddress">
		         		<tr>
		         			<td style="width: 100%;"> <span style="font-weight:bold" id="text_1" class="text_1">${lit.address } </span>
		         			 <div style="font-size: 13px;" id="text_2">${lit.detailaddress }</div>
		            <div style="margin-top: 10px;"><span style="color: grey;"><span id="text_3">${lit.name}</span>&nbsp;<span id="text_4">(${lit.appname })</span>	<span id="text_5">${lit.phonenumber }</span></span></div></td>
		         			<td style="text-align: right;" id="changeadd">
		         			<input type="hidden" id="text_6" value="${lit.aid}">
		         			</td>
		         		</tr>
		        
		         
		         </table>
		        </li>
		     		</ul>
		    </div>
		    </c:forEach>
		    <div class="mui-text-center" style="margin-top:30px;"> 
		    <a href="address3.jsp" class="mui-text-center"><button class="btn mui-btn-green">点击添加地址</button></a>
            </div>
		</div>
		<script src="js/mui.min.js"></script>
		<script type="text/javascript">
			mui.init()
				//	mui.confirm("测试效果")
		</script>
	</body>

</html>