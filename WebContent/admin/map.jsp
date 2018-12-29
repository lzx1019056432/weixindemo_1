<%@page import="com.iss.bean.address"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE HTML> 
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
	<style type="text/css">
		body, html,#allmap {width: 100%;height: 100%;overflow: hidden;margin:0;font-family:"微软雅黑";}
	</style>
	<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=FoH3GcjXoraCczyFLU4GBFtov2Frhkxx"></script>
	<link rel="stylesheet" href="css/bootstrap.min.css" />
	<script type="text/javascript" src="js/jquery.min.js" ></script>
	<script type="text/javascript" src="js/layer/2.4/layer.js"></script>
	<title>浏览器定位</title>
</head> 
<body>
<%
 String reuid = request.getParameter("reuid");
%>
	<div id="allmap" style="height: 400px;  "></div>
	<div class="text-center">
	 
	<button class=" btn btn-primary" style="margin-top: 20px;" id="submitlng">确定</button>
	<input type="hidden" name="lng" id="lng">
	<input type="hidden" name="lat" id="lat">
	<input type="hidden" name="reuid" id="reuid" value="<%=reuid%>">
	 
	</div>
</body>
</html>
<script type="text/javascript"> 
	// 百度地图API功能
	var map = new BMap.Map("allmap");
	map.enableScrollWheelZoom();
	var point = new BMap.Point(116.331398,39.897445);
	map.centerAndZoom(point,12);
    var points = null;
	var geolocation = new BMap.Geolocation();
	layer.msg('正在定位，请稍等...',{time:5000});
	geolocation.getCurrentPosition(function(r){
		if(this.getStatus() == BMAP_STATUS_SUCCESS){
			var mk = new BMap.Marker(r.point);
			map.addOverlay(mk);
			map.panTo(r.point);
			point = r.point;
		}
		else {
			alert('failed'+this.getStatus());
		}        
	},{enableHighAccuracy: true});
		map.addEventListener("click",function(e){
			 var mk = new BMap.Marker(e.point);
			 map.clearOverlays();
			map.addOverlay(mk);
			map.panTo(e.point);	
			document.getElementById("lng").value=e.point.lng;
			document.getElementById("lat").value=e.point.lat;
			
			 
		 
	});
</script>
<script>
	$(document).ready(function(){
		$("#submitlng").click(function(){
		$.post("../changereuseradd.do",
		{lng:$("#lng").val(),
		 lat:$("#lat").val(),
		 reuid:$("#reuid").val()
		},
		function(result){
    layer.msg('已修改，点击右上角关闭按钮',{time:8000});
  });
		 
		}); 
		
	});

</script>