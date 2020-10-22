<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>$Title$</title>
</head>
<body>
<style type="text/css">
	#container1 {
		width: 300px;
		height: 180px;
	}
</style>
<video id="my-video" class="video-js vjs-big-play-centered" controls="controls" poster="">
    <source src="/v1/videos/video/1" type='video/mp4'>
    <p class="vjs-no-js">
        你的浏览器貌似不支持 %>_<%
        <a href="http://videojs.com/html5-video-support/" target="_blank">supports HTML5 video</a>
    </p>
</video>
<div id="container1" style="width:100%; height: 600px;"></div>
<script src="https://code.jquery.com/jquery-1.12.4.min.js"
        integrity="sha256-ZosEbRLbNQzLpnKIkEdrPv7lOy9C27hHQ+Xp8a4MxAQ=" crossorigin="anonymous"></script>
<script type="text/javascript"
        src="http://api.map.baidu.com/api?v=3.0&ak=PHZ1G3bKXiPpiC1iZa6D3PQP2csEBqtU"></script>
<script type="text/javascript">
	// 百度地图API功能
	var map = new BMap.Map("container1");
	var geoc = new BMap.Geocoder();
	var marker;
	var zoomSize = 11;
	var lon = 116.404;  //默认为北京市
	var lat = 39.915;

	//业务处理，获取业务中的经纬度，有则处理，没有则用默认北京
	if (typeof getBizPoint == "function") {
		var pointJson = getBizPoint();
		lon = pointJson.lon;
		lat = pointJson.lat;
	}

	//确定中心位置
	var point = new BMap.Point(lon, lat);
	map.centerAndZoom(point, zoomSize);

	//标注
	marker = new BMap.Marker(point);// 创建标注
	marker.addEventListener("dragend", setBizValue);

	// 添加带有定位的导航控件
	var navigationControl = new BMap.NavigationControl({
		// 靠左上角位置
		anchor: BMAP_ANCHOR_TOP_LEFT,
		// LARGE类型
		type: BMAP_NAVIGATION_CONTROL_LARGE,
		// 启用显示定位
		enableGeolocation: true
	});

	/*输入地址事件处理 start*/
	//建立一个自动完成的对象
	var ac = new BMap.Autocomplete({"input": "suggestId", "location": map});

	ac.addEventListener("onhighlight", function (e) {  //鼠标放在下拉列表上的事件
		var str = "";
		var _value = e.fromitem.value;
		var value = "";
		if (e.fromitem.index > -1) {
			value = _value.province + _value.city + _value.district + _value.street + _value.business;
		}
		str = "FromItem<br />index = " + e.fromitem.index + "<br />value = " + value;

		value = "";
		if (e.toitem.index > -1) {
			_value = e.toitem.value;
			value = _value.province + _value.city + _value.district + _value.street + _value.business;
		}
		str += "<br />ToItem<br />index = " + e.toitem.index + "<br />value = " + value;
		/*G("searchInputPanel").innerHTML = str;*/
	});

	var myValue;
	ac.addEventListener("onconfirm", function (e) {    //鼠标点击下拉列表后的事件
		var _value = e.item.value;
		myValue = _value.province + _value.city + _value.district + _value.street + _value.business;
		// G("searchInputPanel").innerHTML = "onconfirm<br />index = " + e.item.index + "<br />myValue = " + myValue;
		setPlace();
	});

	function G(id) {
		return document.getElementById(id);
	}

	//业务方法 start
	//根据事件，设置经纬度和地址
	function setBizValue(e) {
		var point = e.point;
		setBizValueForPoint(point);
	}

	// 定义一个控件类,即function
	function ZoomControl() {
		// 默认停靠位置和偏移量
		this.defaultAnchor = BMAP_ANCHOR_TOP_LEFT;
		this.defaultOffset = new BMap.Size(10, 10);
	}

	// 通过JavaScript的prototype属性继承于BMap.Control
	ZoomControl.prototype = new BMap.Control();

	// 自定义控件必须实现自己的initialize方法,并且将控件的DOM元素返回
	// 在本方法中创建个div元素作为控件的容器,并将其添加到地图容器中
	ZoomControl.prototype.initialize = function (map) {

		// 创建一个DOM元素
		var div = document.createElement("div");
		div.id = "searchInputPanel";
		div.style.cursor = "pointer";
		div.style.border = "1px solid gray";
		div.style.backgroundColor = "white";
		// 添加文字说明
		$(document).ready(function () {
			$("#searchInputPanel").append('<input type="text" placeholder="请输入地址" id="suggestId">')
			$("#searchInputPanel").append('<input type="button" οnclick="addrSearch()" class="btn btn-danger" value="搜索" style="width: 150px;height: 35px">')
		});
		// 添加DOM元素到地图中
		map.getContainer().appendChild(div);
		// 将DOM元素返回
		return div;
	}
	// 创建控件
	var myZoomCtrl = new ZoomControl();
	// 添加到地图当中
	map.addControl(myZoomCtrl);

	//地址转坐标
	function addrSearch(serachAddr) {
		// 创建地址解析器实例
		var myGeo = new BMap.Geocoder();
		// 将地址解析结果显示在地图上,并调整地图视野
		if (!serachAddr) {
			serachAddr = $("#suggestId").val();
		}
		myGeo.getPoint(serachAddr, function (point) {
			if (point) {
				setBizValueForPoint(point);
				map.clearOverlays();
				map.centerAndZoom(point, zoomSize);
				marker = new BMap.Marker(point);
				marker.enableDragging();           // 可拖拽
				map.addOverlay(marker);    //添加标注
				marker.addEventListener("dragend", setBizValue);

			} else {
				console.log("search click no results!")
			}
		});
	}

	function setPlace() {
		map.clearOverlays();    //清除地图上所有覆盖物
		function myFun() {
			var pp = local.getResults().getPoi(0).point;    //获取第一个智能搜索的结果
			setBizValueForPoint(pp);
			map.centerAndZoom(pp, zoomSize);
			marker = new BMap.Marker(pp);
			marker.enableDragging();           // 可拖拽
			map.addOverlay(marker);    //添加标注
			marker.addEventListener("dragend", setBizValue);
		}

		var local = new BMap.LocalSearch(map, { //智能搜索
			onSearchComplete: myFun
		});
		local.search(myValue);
	}


	//业务方法 start
	//根据事件，设置经纬度和地址
	function setBizValue(e) {
		var point = e.point;
		setBizValueForPoint(point);
	}

	//根据Point，设置经纬度和地址
	function setBizValueForPoint(point) {
		lon = point.lng;
		lat = point.lat;
		geoc.getLocation(point, function (rs) {
			var addComp = rs.addressComponents;
			addr = addComp.province + addComp.city + addComp.district + addComp.street + addComp.streetNumber;
			if (typeof setBizFun == "function") {
				setBizFun({lon: lon, lat: lat, addr: addr});
			}
		});
	}

	//根据marker，设置经纬度和地址
	function setBizValueForMarker() {
		var point = marker.getPosition();
		setBizValueForPoint(point);
	}

</script>
</body>
</html>