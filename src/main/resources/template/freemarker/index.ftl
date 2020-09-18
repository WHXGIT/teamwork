<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Index(首页)</title>


</head>
<body>
<div id="app">
    <header-bar></header-bar>
	<div class="common-container">
		<a href="/code-submit-list">代码提交申请单</a>
	</div>
</div>

<link rel="stylesheet" href="/lib/element-ui/lib/theme-chalk/index.css">
<link rel="stylesheet" href="/css/common.css">
<link rel="stylesheet" href="/css/code-submit-list.css">

<script src="/lib/vue.min.js"></script>
<script src="/lib/axios-v0.18.0.min.js"></script>
<script src="/lib/element-ui/lib/index.js"></script>
<script type="text/javascript">
	var HeaderBar = {
		props: {
			title: String,
			creators: Array
		},
		data: function () {
			return {
				activeIndex: '1'
			}
		},
		methods: {
			handleSelect: function (key, keyPath) {
				console.log(key, keyPath);
			}
		},
		template:
		'<div class="common-header">' +
		'	<div class="common-header-container">' +
		'		<image src="/image/logo.png" class="common-header-logo"></image>' +
		'		<div class="header-nav">' +
		'			<el-menu :default-active="activeIndex" class="el-menu-demo" mode="horizontal" @select="handleSelect">' +
		'				<el-menu-item index="1">代码申请单</el-menu-item>' +
		'				<el-menu-item index="2">配置管理</el-menu-item>' +
		'				<el-menu-item index="3">地址服务</el-menu-item>' +
		'			</el-menu>' +
		'		</div>' +
		'	</div>' +
		'</div>'
	};
</script>
<script src="/modules/header-bar.js"></script>
<script src="/modules/main.js"></script>
</body>
</html>