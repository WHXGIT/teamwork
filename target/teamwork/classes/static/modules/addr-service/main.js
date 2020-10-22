/**
 * description: vue 入口文件
 * author: whx
 */
var app = new Vue({
	el: '#app',
	data: {
		radio1: 'Baidu',
		radio2: 'Baidu',
		radio3: 'Baidu',
		radio4: 'Baidu',
		radio5: 'Baidu',
		radioOptions: [{label: "Baidu", value: "百度"},
			{label: "AutoNavi", value: "高德"},
			{label: "Google", value: "谷歌"}],
		address: '',
		formatAddress: '',
		latitudeAndLongitude: '',
		latAndLng: '',
		timezoneId: '',
		dst: '',
		rowOffset: '',
		addrOrLocation: '',
		ip: '',
		position: '',
		detailPosition: '',
		checkAddress: '',
		addressTips: ''
	},
	mounted() {

	},
	created: function () {

	},
	filters: {},
	methods: {
		reverseGeoCoder: function () {
			var _this = this;
			axios.get('/addr-service/reverse-geocoder', {
				params: {
					provider: _this.radio2,
					location: _this.latAndLng
				}
			}).then(function (response) {
				if (response.status === 200) {
					var data = response.data.data;
					_this.formatAddress = JSON.parse(data).result.formatted_address
					console.log(data)
				} else {
					_this.$message.error('网络异常！');
				}
			}).catch(function (error) {
				_this.$message.error('服务器发生异常！');
				_this.loading = false;
				console.log(error);
			});
		},
		geoCoder: function () {
			var _this = this;
			axios.get('/addr-service/geocoder', {
				params: {
					provider: _this.radio1,
					address: _this.address
				}
			}).then(function (response) {
				if (response.status === 200) {
					var data = response.data.data;
					var lngAndLat = JSON.parse(data).result.location
					var lat = lngAndLat.lat;
					var lng = lngAndLat.lng;

					_this.latitudeAndLongitude = "lat,lng: " + lat + ',' + lng
					console.log(data)
				} else {
					_this.$message.error('网络异常！');
				}
			}).catch(function (error) {
				_this.$message.error('服务器发生异常！');
				_this.loading = false;
				console.log(error);
			});
		},

		getTimezone: function () {
			var _this = this;
			axios.get('/addr-service/timezone', {
				params: {
					provider: _this.radio3,
					addrOrLocation: _this.addrOrLocation
				}
			}).then(function (response) {
				if (response.status === 200) {
					var data = response.data.data;
					var result = JSON.parse(data);
					_this.timezoneId = result.timezone_id;
					_this.dst = result.dst_offset;
					_this.rowOffset = result.raw_offset;
					console.log(data)
				} else {
					_this.$message.error('网络异常！');
				}
			}).catch(function (error) {
				_this.$message.error('服务器发生异常！');
				_this.loading = false;
				console.log(error);
			});
		},
		getPosition: function () {
			var _this = this;
			axios.get('/addr-service/check-address', {
				params: {
					provider: _this.radio5,
					checkAddress: _this.checkAddress
				}
			}).then(function (response) {
				if (response.status === 200) {
					var data = response.data.data;
					var result = JSON.parse(data);
					_this.addressTips = result;
					console.log(data)
				} else {
					_this.$message.error('网络异常！');
				}
			}).catch(function (error) {
				_this.$message.error('服务器发生异常！');
				_this.loading = false;
				console.log(error);
			});
		},

		getCheckAddress: function () {
			var _this = this;
			axios.get('/addr-service/position', {
				params: {
					provider: _this.radio4,
					ip: _this.ip
				}
			}).then(function (response) {
				if (response.status === 200) {
					var data = response.data.data;
					var result = JSON.parse(data);
					_this.position = result.address;
					_this.detailPosition = result.content.address;
					console.log(data)
				} else {
					_this.$message.error('网络异常！');
				}
			}).catch(function (error) {
				_this.$message.error('服务器发生异常！');
				_this.loading = false;
				console.log(error);
			});
		},
	},
	components: {
		HeaderBar: HeaderBar
	}
});