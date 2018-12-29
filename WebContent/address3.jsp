<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
			<header class="mui-bar mui-bar-nav">
		    <a class="mui-action-back mui-icon mui-icon-left-nav mui-pull-left"></a>
		    <h1 class="mui-title">新增地址</h1>
		</header>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
    <title></title>
    <script src="js/mui.min.js"></script>
    	<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=FoH3GcjXoraCczyFLU4GBFtov2Frhkxx"></script>
    <link href="css/mui.min.css" rel="stylesheet"/>
    <link rel="stylesheet" href="css/address.css" />
    <script type="text/javascript" src="js/jquery-3.2.1.min.js" ></script>
<!--     <script type="text/javascript" charset="UTF-8">
      	mui.init();
      	 </script> -->
    <script>
    $(document).ready(function(){
    	/*修改上面文字*/
    $("#tittle").click(function(){ 	
  	$("#tittle").text("搜索地址");
  	
  	
  	  });

  $("#selectaddress").click(function(){
   
  	$("#html_1").addClass("hiddenhtml");
  	$("#html_2").removeClass("hiddenhtml");
  	
  	  });
  $(".addresstop").on("click",".addressli1",function(){
/*  $(".addressli1").on("click",function(){ */
 	  
 	$("#selectaddress").val($(this).text());
 	   
 	$("#lng").val($(this).next().next().val());
 	$("#lat").val($(this).next().val()); 
 	 $("#html_2").addClass("hiddenhtml");
  	$("#html_1").removeClass("hiddenhtml");
 });
 
   });
    
    </script>
 
    <script>
  $(document).ready(function(){
  $(".selectsex").click(function(){
  	$(this).next().removeClass("selectsex2");
  	$(this).prev().removeClass("selectsex2");
  	$(this).prev().css("color","#4CD964");
  	$(this).next().css("color","#4CD964");
   $(this).addClass("selectsex2");
   $(this).css("color","#FFFFFF");
  $("#sex").val($(this).val());
  });
});
    	
    </script>
      <script>
  $(document).ready(function(){
  $(".keydownsearch").keydown(function(){
	  $(".addresstop").empty();
   $.post("Userseladd.do",
  {
    region:$("#hidden1_region").val(),
    query:$("#query").val()
  },
  function(result){
	   
	  var jsobject = JSON.parse(result);
	  
	  for (var i = 0; i < jsobject.result.length; i++) {
	   
			$(".addresstop").append("<div class='addressli'>"
				 	+"<div class='addressli1'>"+jsobject.result[i].name+"</div>"
  				 	+"<input type='hidden' class='lat' value='"+jsobject.result[i].location.lat+"'><input type='hidden' class='lng' value='"+jsobject.result[i].location.lng+"'>"
  				 	+"<div class='addressli2'>"+jsobject.result[i].city+""+jsobject.result[i].district+"</div></div>");
	}


  });
  });
  
  $("#checkform").click(function(){
	  	 var name=$("#name").val().length;
	  	 var appname=$("#sex").val().length;
	  	 var testnumber = /^(((13[0-9]{1})|(14[0-9]{1})|(17[0]{1})|(15[0-3]{1})|(15[5-9]{1})|(18[0-9]{1}))+\d{8})$/;    
	  	 var phone = $("#phonenumber").val();
	  	 var address = $("#lng").val().length;
	  	 var addressdetail = $("#detailaddress").val().length;
	  	 if(name<2){
	  	 	alert("您输入不少于两个汉字的名字");
	  	 }else if(appname<1){
	  	 	alert("请选择称谓");
	  	 	
	  	 }else if(!testnumber.test(phone)){
	  	 	alert("请输入正确的手机号");
	  	 }else if(address<1){
	  	 	alert("请选择地址");
	  	 }else{
	  		 if(addressdetail<1){
	  			$("#detailaddress").val("无");
	  		 }
	  	 	$("#form1").submit();
	  	 }
	  });
	});
    	
    </script>
</head>
<body>
<div id="allmap" style="display: none;"></div>
<input type="hidden" id="hidden1_region">
	<div id="html_1">
<form class="mui-input-group" style="margin-top: 50px;" action="SubmitAddress.do" name="form1" id="form1">
    <div class="mui-input-row">
        <label>联系人</label>
    <input type="text" class="mui-input-clear" placeholder="姓名" name="name" id="name">
    </div>
    <div class="mui-button-row" style="border-bottom: 1px solid #E3E2E5; margin-left: 15px;">
         <button type="button" class="mui-btn mui-btn-green mui-btn-outlined selectsex" value="先生">男士</button>
         <button type="button" class="mui-btn mui-btn-green mui-btn-outlined selectsex" value="女士">女士</button>
         <input type="hidden" name="appname"  id="sex"/>

    </div>
     
    <div class="mui-input-row">
        <label>电话</label>
        <input type="number" class="mui-input-numbox" placeholder="手机号码" name="phonenumber" id="phonenumber">
    </div>
     <div class="mui-input-row">
        <label>地址</label>
        <input type="text" class="mui-input-clear" placeholder="选择收货地址" readonly="readonly" id="selectaddress" name="address">
    </div>
         <div class="mui-input-row">
        <label>详细地址</label>
        <input type="text" class="mui-input-clear" placeholder="例：软件园1号楼5xx" name="detailaddress" id="detailaddress">
        <input type="hidden" name="lng" value="" id="lng">
        <input type="hidden" name="lat" value="" id="lat">
        <input type="hidden" name="add_uid" value="1">
    </div>

</form>
<div style="margin-top: 15px;" class="mui-text-center">
<button type="button" class="mui-btn mui-btn-success" style="width: 90%; height:" id="checkform">确定</button>

</div>
</div>
<!--
 
	描述：下面是选择地址
-->
 <div class="hiddenhtml" id="html_2">
 <div class="mui-input-row mui-search" style="margin-top: 46px;">
    <input type="search" class="mui-input-clear keydownsearch" placeholder="请输入地址" id="query">
</div>
 	
  
 <div style="background-color: #ffffff;" class="addresstop">
  
 </div>
 </div>
 <script type="text/javascript">
	// 百度地图API功能
	var map = new BMap.Map("allmap");
	var point = new BMap.Point(116.331398,39.897445);
	map.centerAndZoom(point,12);

	function myFun(result){
		var cityName = result.name;
		map.setCenter(cityName);
		document.getElementById("hidden1_region").value=cityName;
		 
	}
	var myCity = new BMap.LocalCity();
	myCity.get(myFun);
</script>
</body>
</html>