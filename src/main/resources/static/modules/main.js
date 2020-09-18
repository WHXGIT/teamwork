/**
 * description: vue 入口文件
 * author: whx
 */
var app = new Vue({
	el: '#app',
	data: {
		msg: 'Hello Vue!'
	},
	components: {
		HeaderBar: HeaderBar
	}
});