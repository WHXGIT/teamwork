/**
 * description:
 * author: whx
 */
var HeaderBar = {
	data: function () {
		return {
			activeIndex: '1'
		}
	},
	mounted: function () {
		var path = location.pathname;
		if (path === "/code-submit-list") {
			this.activeIndex = '1';
		} else if (path === "/config-mgt") {
			this.activeIndex = '2';
		} else if (path === '/addr-service') {
			this.activeIndex = '3';
		} else {
			this.activeIndex = '0';
		}
	},
	methods: {
		handleSelect: function (key, keyPath) {
			if (key === '1' && this.activeIndex !== '1') {
				location.href = "/code-submit-list";
			} else if (key === '2' && this.activeIndex !== '2') {
				location.href = "/config-mgt";
			} else if (key === '3' && this.activeIndex !== '3'){
				location.href = "/addr-service";
			} else {
				location.href = "/";
			}
		}
	},
	template:
	'<div class="common-header">' +
	'	<div class="common-header-container">' +
	'		<div class="common-header-logo"></div>' +
	'		<div class="header-nav">' +
	'			<el-menu :default-active="activeIndex" class="el-menu-demo" mode="horizontal" @select="handleSelect">' +
	'				<el-menu-item index="0">项目</el-menu-item>' +
	'				<el-menu-item index="1">代码申请单</el-menu-item>' +
	/*'				<el-menu-item index="2">配置管理</el-menu-item>' +*/
	/*'				<el-menu-item index="3">地址服务</el-menu-item>' +*/
	'			</el-menu>' +
	'		</div>' +
	'	</div>' +
	'</div>'
};