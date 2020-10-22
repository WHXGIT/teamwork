/**
 * description: vue 入口文件
 * author: whx
 */
var app = new Vue({
	el: '#app',
	data: {
		msg: 'Hello Vue!',
		lanProjects: [{id: "demo", name: "demo", desc: "demo desc"}],
		i18nProjects: [{id: "demo", name: "demo", desc: "demo desc"}]
	},
	components: {
		HeaderBar: HeaderBar
	}
});