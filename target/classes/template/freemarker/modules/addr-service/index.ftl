<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <style type="text/css">
		[v-cloak] {
			display: none !important;
		}
    </style>
    <#--<script src="https://polyfill.io/v3/polyfill.min.js?features=default"></script>
    <script defer
            src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDMG25XEEtUPjYWNGtF4CIwctk62_Z-6pE&callback=initMap"></script>-->
    <title>地址服务</title>
</head>
<body>
<div id="app" v-cloak>
    <header-bar></header-bar>
    <#--<div id="map" style="width:100%; height: 300px;"></div>-->
    <style type="text/css">
		/* Always set the map height explicitly to define the size of the div
         * element that contains the map. */
		#map {
			width: 300px;
			height: 180px;
		}

		#container {
			width: 300px;
			height: 180px;
		}

		#container1 {
			width: 300px;
			height: 180px;
		}
    </style>
    <#--<script>

		function initMap() {
			var map = new google.maps.Map(document.getElementById("map"), {
				center: {
					lat: 36.675807,
					lng: 117.000923
				},
				zoom: 8
			});
		}
    </script>-->

    <div id="container1" style="width:100%; height: 600px;"></div>
    <script type="text/javascript"
            src="http://api.map.baidu.com/api?v=3.0&ak=PHZ1G3bKXiPpiC1iZa6D3PQP2csEBqtU&callback=initbm"></script>
    <script type="text/javascript">
		window.initbm = function () {
			var map = new BMap.Map("container1");
			// 创建地图实例
			var point = new BMap.Point(150.644000, -34.397000);
			// 创建点坐标
			map.centerAndZoom(point, 7);
			// 初始化地图，设置中心点坐标和地图级别
			map.enableScrollWheelZoom(true);     //开启鼠标滚轮缩放

			// 定义一个控件类,即function
			var ZoomControl = function () {
				// 默认停靠位置和偏移量
				this.defaultAnchor = BMAP_ANCHOR_TOP_LEFT;
				this.defaultOffset = new BMap.Size(10, 10);
			}

			// 创建控件
			var myZoomCtrl = new ZoomControl();
			// 添加到地图当中
			map.addControl(myZoomCtrl);
			// 通过JavaScript的prototype属性继承于BMap.Control
			ZoomControl.prototype = new BMap.Control();

			// 自定义控件必须实现自己的initialize方法,并且将控件的DOM元素返回
			// 在本方法中创建个div元素作为控件的容器,并将其添加到地图容器中
			ZoomControl.prototype.initialize = function (map) {
				// 创建一个DOM元素
				var div = document.createElement("div");
				// 添加文字说明
				div.appendChild(document.createTextNode("放大2级"));
				// 设置样式
				div.style.cursor = "pointer";
				div.style.width = "50px";
				div.style.height = "30px";
				div.style.border = "1px solid gray";
				div.style.backgroundColor = "white";
				// 绑定事件,点击一次放大两级
				div.onclick = function (e) {
					map.setZoom(map.getZoom() + 2);
				}
				// 添加DOM元素到地图中
				map.getContainer().appendChild(div);
				// 将DOM元素返回
				return div;
			}
		}
    </script>

    <#--<div id="container" style="width:100%; height: 300px;"></div>
    <script type="text/javascript">
		window.init = function () {
			var map = new AMap.Map('container', {
				center: [117.000923, 36.675807],
				zoom: 11
			});
		}
    </script>
    <script src="https://webapi.amap.com/maps?v=1.4.15&key=1fca6ac01460ba30da59296034c691a4&callback=init"></script>-->
    <div class="common-container">
        <div class="code-submit-condition">
            <#--地理编码分析-->
            <div class="title-font">
                <span class="el-icon-map-location">地理编码</span>
            </div>
            <div class="query-radio">
                <el-radio-group v-model="radio1" class="query-radio-style">
                    <el-radio v-for="(item, index) in radioOptions" :key="index" :label="item.label">
                        {{item.value}}
                    </el-radio>
                </el-radio-group>
                <div class="query-input">
                    <el-input v-model="address" placeholder="请输入地址" size="mini"></el-input>
                    <span>&nbsp;&nbsp;</span>
                    <el-button type="primary" size="mini" @click="geoCoder">地理编码</el-button>
                </div>
            </div>

            <div class="query-radio">
                <el-radio-group v-model="radio2">
                    <el-radio v-for="(item, index) in radioOptions" :key="index" :label="item.label">
                        {{item.value}}
                    </el-radio>
                </el-radio-group>
                <div class="query-input">
                    <el-input v-model="latAndLng" placeholder="请输入经纬度: lat,lng" size="mini"></el-input>
                    <span>&nbsp;&nbsp;</span>
                    <el-button type="primary" size="mini" @click="reverseGeoCoder">逆地理编码</el-button>
                </div>
            </div>
            <div class="desc-card">
                <el-card class="box-card" style="display: flex;">
                    经纬度： {{latitudeAndLongitude}}
                </el-card>
            </div>
            <div class="desc-card">
                <el-card class="box-card" style="display: flex;">
                    行政区划分： {{formatAddress}}
                </el-card>
            </div>
            <el-divider></el-divider>
            <#--时区分析-->
            <div class="title-font">
                <span class="el-icon-time">时区分析</span>
            </div>
            <div class="query-radio">
                <el-radio-group v-model="radio3">
                    <el-radio v-for="(item, index) in radioOptions" :key="index" :label="item.label">
                        {{item.value}}
                    </el-radio>
                </el-radio-group>
                <div class="query-input">
                    <el-input v-model="addrOrLocation" placeholder="请输入经纬度: lat,lng 或 地址" size="mini"></el-input>
                    <span>&nbsp;&nbsp;</span>
                    <el-button type="primary" size="mini" @click="getTimezone">时区</el-button>
                </div>
            </div>
            <div class="desc-card">
                <el-card class="box-card" style="display: flex;">
                    <ul>
                        <li>时区ID： {{timezoneId}}</li>
                        <li>夏令时时间偏移： {{dst}} s</li>
                        <li>较协调世界时偏移： {{rowOffset}} s</li>
                    </ul>
                </el-card>
            </div>
            <el-divider></el-divider>
            <#--地址定位-->
            <div class="title-font">
                <span class="el-icon-time">地址定位</span>
            </div>
            <div class="query-radio">
                <el-radio-group v-model="radio4">
                    <el-radio v-for="(item, index) in radioOptions" :key="index" :label="item.label">
                        {{item.value}}
                    </el-radio>
                </el-radio-group>
                <div class="query-input">
                    <el-input v-model="ip" placeholder="请输入" size="mini"></el-input>
                    <span>&nbsp;&nbsp;</span>
                    <el-button type="primary" size="mini" @click="getPosition">定位</el-button>
                </div>
            </div>
            <div class="desc-card">
                <el-card class="box-card" style="display: flex;">
                    <span>本机地址：{{position}}</span>
                </el-card>
            </div>

            <#--地址输入提示-->
            <div class="title-font">
                <span class="el-icon-time">地址输入提示</span>
            </div>
            <div class="query-radio">
                <el-radio-group v-model="radio5">
                    <el-radio v-for="(item, index) in radioOptions" :key="index" :label="item.label">
                        {{item.value}}
                    </el-radio>
                </el-radio-group>
                <div class="query-input">
                    <el-input v-model="checkAddress" placeholder="请输入地址" size="mini"></el-input>
                    <span>&nbsp;&nbsp;</span>
                    <el-button type="primary" size="mini" @click="getCheckAddress">确定</el-button>
                </div>
            </div>
            <div class="desc-card">
                <el-card class="box-card" style="display: flex;">
                    <span>提示地址：{{addressTips}}</span>
                </el-card>
            </div>
        </div>
    </div>

</div>

<link rel="stylesheet" href="/lib/element-ui/lib/theme-chalk/index.css">
<link rel="stylesheet" href="/css/common.css">
<link rel="stylesheet" href="/css/addr-service.css">

<script src="/lib/vue.min.js"></script>
<script src="/lib/axios-v0.18.0.min.js"></script>
<script src="/lib/element-ui/lib/index.js"></script>
<script src="/modules/header-bar.js"></script>
<script src="/modules/addr-service/main.js"></script>

</body>
</html>