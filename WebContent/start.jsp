<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<!--	<header class="mui-bar mui-bar-nav">
	  <!--  <a class="mui-action-back mui-icon mui-icon-left-nav mui-pull-left"></a>
	   <!-- <h1 class="mui-title"> </h1>
	</header>-->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
    <title></title>
    <script src="js/mui.min.js"></script>
    <link href="css/mui.min.css" rel="stylesheet"/>  
    <link rel="stylesheet" href="css/iconfont.css" />
</head>
<style>
	.table-mui{ 
		background-color: #ffffff;
	}
</style>
<body>
	<div class="mui-content">
	    <div  id="slider" class="mui-slider" style=" height: 180px; margin: 0px auto;">
  <div class="mui-slider-group mui-slider-loop">
    <!--支持循环，需要重复图片节点-->
    <div class="mui-slider-item mui-slider-item-duplicate"><a href="#"><img src="jpg/pic_5.png" /></a></div>
    <div class="mui-slider-item"><a href="#"><img src="jpg/pic_1.png" /></a></div>
    <div class="mui-slider-item"><a href="#"><img src="jpg/pic_2.png" /></a></div>
    <div class="mui-slider-item"><a href="#"><img src="jpg/pic_3.png" /></a></div>
    <div class="mui-slider-item"><a href="#"><img src="jpg/pic_4.png" /></a></div>
    <!--支持循环，需要重复图片节点-->
    <div class="mui-slider-item mui-slider-item-duplicate"><a href="#"><img src="jpg/pic_5.png" /></a></div>
  </div>
  <div class="mui-slider-indicator">
	    <div class="mui-indicator mui-active"></div>
	    <div class="mui-indicator"></div>
	    <div class="mui-indicator"></div>
	    <div class="mui-indicator"></div>
	  </div>
</div>
<ul class="mui-table-view mui-grid-view mui-grid-9 table-mui" style="background-color: #ffffff;">
<li class="mui-table-view-cell mui-media mui-col-xs-4 mui-col-sm-4">
    <a href="detail_3.html">
        <span class="mui-icon iconfont icon-zhizhanglaji"></span>
        <div class="mui-media-body">废纸</div>
    </a>
</li>
<li class="mui-table-view-cell mui-media mui-col-xs-4 mui-col-sm-4">
     <a href="detail_2.html">
        <span class="mui-icon iconfont icon-xunhuanzailiyonglaji"><!--<span class="mui-badge mui-badge-red"></span>--></span>
        <div class="mui-media-body">塑料</div>
    </a>
</li>
<li class="mui-table-view-cell mui-media mui-col-xs-4 mui-col-sm-4">
     <a href="detail_1.html">
        <span class="mui-icon iconfont icon-bolilaji"></span>
        <div class="mui-media-body">玻璃</div>
    </a>
</li>
<li class="mui-table-view-cell mui-media mui-col-xs-4 mui-col-sm-4">
     <a href="detail_4.html">
        <span class="mui-icon iconfont icon-jinshujian"></span>
        <div class="mui-media-body">金属</div>
    </a>
</li>
<li class="mui-table-view-cell mui-media mui-col-xs-4 mui-col-sm-4">
     <a href="detail_5.html">
        <span class="mui-icon iconfont icon-mobu"></span>
        <div class="mui-media-body">布料</div>
    </a>
</li>
<li class="mui-table-view-cell mui-media mui-col-xs-4 mui-col-sm-4">
    <a href="#">
        <span class="mui-icon iconfont icon-qita"></span>
        <div class="mui-media-body">其他</div>
    </a>
</li>
    </ul>

		<div class="mui-text-center">
		<div style="height: 250px; margin-top: 30px;">
             <a href="PreSend.do"><button type="button" class="mui-btn mui-btn-warning" style="width: 100px; height: 50px; font-size: 20px;">下单</button></a>
		</div>
		</div>

	
	</div>


<nav class="mui-bar mui-bar-tab">
    <a href="start.jsp" class="mui-tab-item mui-active" href="start.jsp">
        <span class="mui-icon mui-icon-home"></span>
        <span class="mui-tab-label">首页</span>
    </a>
 
    <a href="ListDid.do" class="mui-tab-item">
     
        <span class="mui-icon iconfont icon-dingdan"></span> 
        <span class="mui-tab-label">订单</span>
       
    </a> 
    <a href="myhome.jsp" class="mui-tab-item">
        <span class="mui-icon mui-icon-contact"></span>
        <span class="mui-tab-label">我的</span>
    </a>
</nav>
<script type="text/javascript" charset="utf-8">
      	mui.init();
      //获得slider插件对象
	var gallery = mui("#slider");
	gallery.slider({
  	interval:2500//自动轮播周期，若为0则不自动播放，默认为0；
});
	mui('body').on('tap','a',function(){document.location.href=this.href;});
</script>
</body>
</html>