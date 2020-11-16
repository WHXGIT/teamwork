/**
 * description: vue 入口文件
 * author: whx
 */
var app = new Vue({
	el: '#app',
	data: {
		msg: 'Hello Vue!',
		lanProjects: [{id: "0", name: "demo", desc: "demo desc"}],
		i18nProjects: [{id: "1", name: "demo", desc: "demo desc"}]
	},
	methods: {
		toProject(id) {
			window.location = location.origin + '/code-submit-list' + "/" + id;
		}
	},
	components: {
		HeaderBar: HeaderBar
	}
});