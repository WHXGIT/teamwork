/**
 * description: vue 入口文件
 * author: whx
 */
var app = new Vue({
	el: '#app',
	data: {
		msg: 'Hello Vue!',
		lanProjects: [{id: "0", name: "bos-bd", desc: "行政区划"}],
		i18nProjects: [{id: "1", name: "bos-tc", desc: "翻译平台"}],
		dialogFormVisible: false,
		form: {
			name: '',
			region: '',
			date1: '',
			date2: '',
			delivery: false,
			type: [],
			resource: '',
			desc: ''
		},
		formLabelWidth: '120px'
	},
	methods: {
		toProject(id) {
			window.location = location.origin + '/code-submit-list' + "/" + id;
		},
		showProjectDetail(id) {
			this.dialogFormVisible = true
		}
	},
	components: {
		HeaderBar: HeaderBar
	}
});