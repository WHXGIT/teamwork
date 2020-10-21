/**
 * description: vue 入口文件
 * author: whx
 */

var FormList = {
	props: {
		title: String,
		creators: Array
	},
	data: function () {
		return {
			dialogFormVisible: false,
			submitTargets: [
				{value: "分支到主干", label: "分支到主干"},
				{value: "主干到基线", label: "主干到基线"}
			],
			ruleForm: {
				id: '',
				bugNo: '-',
				creator: '',
				submitTarget: '分支到主干',
				requireDesc: '-',
				codeModifyDesc: '【修改原因】填写BUG号或需求说明\r\n' +
					'【修改内容】\r\n' +
					'【修改人】' + '\r\n' +
					'【检查人】',
				scope: '',
				isSelfTest: false,
				smokeTest: false,
				javaFiles: '',
				metaFiles: '',
				dbScript: '',
				remark: ''
			},
			rules: {
				creator: [
					{required: true, message: '请选择申请人', trigger: 'change'}
				],
				submitTarget: [
					{required: true, message: '请选择提交目标', trigger: 'change'}
				],
				codeModifyDesc: [
					{required: true, message: '请填写代码修改说明', trigger: 'blur'}
				],
				scope: [
					{required: true, message: '请填写影响范围', trigger: 'blur'}
				]
			}
		}
	},
	watch: {
		'ruleForm.creator': {
			handler: function (newName, oldName) {
				if (typeof newName === "string" && newName !== "") {
					var desc = this.ruleForm.codeModifyDesc;
					var strArr = desc.split('【修改人】');
					// var preStr = strArr[0] +
					this.ruleForm.codeModifyDesc = strArr[0] +
						'【修改人】' + newName + '\r\n' +
						'【检查人】' + newName;
				}
			},
			immediate: true
		}
	},
	mounted: function () {
		if (this.rowjsonbak instanceof Array && this.rowjsonbak.length > 0) {
			this.ruleForm = this.rowjsonbak;
		}
	},
	methods: {
		showDialogFormVisible: function () {
			this.dialogFormVisible = true;
		},
		initEditRuleForm: function (ruleForm) {
			this.ruleForm = ruleForm;
		},
		submitForm: function (formName) {
			var _this = this;
			this.$refs[formName].validate(function (valid) {
				if (valid) {
					axios.post('/tw-csl/code-submit-list', _this.ruleForm)
						.then(function (response) {
							if (response.status === 200) {
								_this.resetForm('ruleForm');
								_this.$emit('refresh');
								_this.dialogFormVisible = false;
								console.log(response)
							} else {
								_this.$message.error('网络异常！');
							}
						})
						.catch(function (error) {
							_this.$message.error('服务器发生异常！');
							_this.dialogFormVisible = false;
							console.log(error);
						});
				} else {
					console.log('error submit!!');
					return false;
				}
			});
		},
		modifyForm: function (formName) {
			var _this = this;
			this.$refs[formName].validate(function (valid) {
				if (valid) {
					axios.put('/tw-csl/code-submit-list', _this.ruleForm)
						.then(function (response) {
							if (response.status === 200) {
								_this.resetForm('ruleForm');
								_this.$emit('refresh');
								_this.dialogFormVisible = false;
								console.log(response)
							} else {
								_this.$message.error('网络异常！');
							}
						})
						.catch(function (error) {
							_this.$message.error('服务器发生异常！');
							_this.dialogFormVisible = false;
							console.log(error);
						});
				} else {
					console.log('error submit!!');
					return false;
				}
			});
		},
		resetForm: function (formName) {
			this.$refs[formName].resetFields();
		}
	},
	template:
		'	<el-dialog :title="title" :visible.sync="dialogFormVisible">' +
		'<el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="150px" class="demo-ruleForm">' +
		'	<el-form-item label="申请人" prop="creator">' +
		'		<el-select v-model="ruleForm.creator" placeholder="请选择申请人" size="mini">' +
		'			<el-option' +
		'					v-for="(item, index) in creators"' +
		'					:label="item.label"' +
		'					:value="item.value"' +
		'					:key="index"' +
		'			></el-option>' +
		'		</el-select>' +
		'	</el-form-item>' +
		'	<el-form-item label="提交目标" prop="submitTarget">' +
		'		<el-select v-model="ruleForm.submitTarget" placeholder="请选择提交目标" size="mini">' +
		'			<el-option' +
		'					v-for="(item, index) in submitTargets"' +
		'					:label="item.label"' +
		'					:value="item.value"' +
		'					:key="index"' +
		'			></el-option>' +
		'		</el-select>' +
		'	</el-form-item>' +
		'	<el-form-item label="代码修改说明" prop="codeModifyDesc">' +
		'		<el-input type="textarea" :autosize="{ minRows: 2, maxRows: 10}" v-model="ruleForm.codeModifyDesc"></el-input>' +
		'	</el-form-item>' +
		'	<el-form-item label="影响范围" prop="scope">' +
		'		<el-input type="textarea" :autosize="{ minRows: 2, maxRows: 10}" v-model="ruleForm.scope"></el-input>' +
		'	</el-form-item>' +
		'	<el-form-item label="是否自测" prop="isSelfTest">' +
		'		<el-switch v-model="ruleForm.isSelfTest"></el-switch>' +
		'	</el-form-item>' +
		'	<el-form-item label="9090冒烟测试" prop="smokeTest">' +
		'		<el-switch v-model="ruleForm.smokeTest"></el-switch>' +
		'	</el-form-item>' +
		'	<el-form-item label="Java文件" prop="javaFiles">' +
		'		<el-input type="textarea" :autosize="{ minRows: 2, maxRows: 10}" v-model="ruleForm.javaFiles"></el-input>' +
		'	</el-form-item>' +
		'	<el-form-item label="元数据文件" prop="metaFiles">' +
		'		<el-input type="textarea" :autosize="{ minRows: 2, maxRows: 10}" v-model="ruleForm.metaFiles"></el-input>' +
		'	</el-form-item>' +
		'	<el-form-item label="数据库脚本" prop="dbScript">' +
		'		<el-input type="textarea" :autosize="{ minRows: 2, maxRows: 10}" v-model="ruleForm.dbScript"></el-input>' +
		'	</el-form-item>' +
		'	<el-form-item label="备注" prop="remark">' +
		'		<el-input type="textarea" :autosize="{ minRows: 2, maxRows: 10}" v-model="ruleForm.remark"></el-input>' +
		'	</el-form-item>' +
		'	<el-form-item>' +
		'		<el-button v-if="ruleForm.id === \'\' ' +
		'" type="primary" @click="submitForm(\'ruleForm' +
		'\')" size="mini">立即创建</el-button>' +
		'		<el-button v-else type="primary" @click="modifyForm(' +
		'\'ruleForm\')" size="mini">确认修改</el-button>' +
		'		<el-button @click="resetForm(' +
		'\'ruleForm\')" size="mini">重置</el-button>' +
		'	</el-form-item>' +
		'</el-form>'
};

var app = new Vue({
	el: '#app',
	data: {
		date: '',
		pageInfo: {
			pageNum: 1,
			pageSize: 20,
			total: 0
		},
		bugNo: '',
		creator: '',
		keyword: '',
		loading: true,
		formLabelWidth: '120px',
		tableData: [],
		creators: [],
		pickerOptions: {
			shortcuts: [{
				text: '最近一个月',
				onClick: function (picker) {
					const end = new Date();
					const start = new Date();
					start.setTime(start.getTime() - 3600 * 1000 * 24 * 30);
					picker.$emit('pick', [start, end]);
				}
			}, {
				text: '最近三个月',
				onClick: function (picker) {
					const end = new Date();
					const start = new Date();
					start.setTime(start.getTime() - 3600 * 1000 * 24 * 90);
					picker.$emit('pick', [start, end]);
				}
			}]
		},
		activeIndex: '1',
	},
	computed: {
		startTime: function () {
			return this.date instanceof Array ? this.date[0] : '';
		},
		endTime: function () {
			return this.date instanceof Array ? this.date[1] : '';
		}
	},
	created: function () {
		this.listUsers();
		this.getList();
	},
	filters: {
		selfTestFilter: function (val) {
			if (val === 0) {
				return '否';
			} else {
				return '是';
			}
		},
		smokeTestFilter: function (val) {
			if (val === 0) {
				return '否';
			} else {
				return '是';
			}
		},
		statusFilter: function (val) {
			var result = '';
			if (val === 0) {
				result = '草稿';
			} else if (val === 1) {
				result = '完成';
			} else if (val === 2) {
				result = '废弃';
			} else if (val === 3) {
				result = '删除';
			}
			return result;
		}
	},
	created: function () {
		this.listUsers();
		this.getList();
	},
	methods: {
		listUsers: function () {
			var _this = this;
			axios.get('/user/users')
				.then(function (response) {
					if (response.status === 200) {
						var users = response.data.data;
						_this.creators = users;
					} else {
						_this.$message.error('网络异常！');
					}
				}).catch(function (error) {
				_this.$message.error('服务器发生异常！');
			});
		},
		tableRowClassName: function ({row, rowIndex}) {
			var status = row.status;
			if (status === 0) {
				return '';
			} else if (status === 1) {
				return 'success-row';
			} else if (status === 2) {
				return 'giveup-row';
			} else if (status === 3) {
				return '';
			} else {
				return '';
			}

		},
		createHandle: function () {
			this.$refs.createFormList.showDialogFormVisible();
		},
		handleEdit: function (rows) {
			var bakRuleForm = JSON.stringify(rows);
			var rowJsonBak = JSON.parse(bakRuleForm);
			if (rowJsonBak.isSelfTest === 1) {
				rowJsonBak.isSelfTest = true;
			}
			if (rowJsonBak.smokeTest === 1) {
				rowJsonBak.smokeTest = true;
			}
			this.$refs.modifyFormList.initEditRuleForm(rowJsonBak);
			this.$refs.modifyFormList.showDialogFormVisible();
		},
		handleFinish: function (id) {
			var _this = this;
			axios.put('/tw-csl/code-submit-list/finish/' + id)
				.then(function (response) {
					if (response.status === 200) {
						_this.getList();
					} else {
						_this.$message.error('网络异常！');
					}
				}).catch(function (error) {
				_this.$message.error('服务器发生异常！');

				console.log(error);
			});
		},
		handleGiveUp: function (id) {
			var _this = this;
			axios.put('/tw-csl/code-submit-list/give-up/' + id)
				.then(function (response) {
					if (response.status === 200) {
						_this.getList();
					} else {
						_this.$message.error('网络异常！');
					}
				}).catch(function (error) {
				_this.$message.error('服务器发生异常！');
				console.log(error);
			});
		},
		handleRecovery: function (id) {
			var _this = this;
			axios.put('/tw-csl/code-submit-list/recovery/' + id)
				.then(function (response) {
					if (response.status === 200) {
						_this.getList();
					} else {
						_this.$message.error('网络异常！');
					}
				}).catch(function (error) {
				_this.$message.error('服务器发生异常！');
				console.log(error);
			});
		},
		handleDelete: function (id) {
			var _this = this;
			axios.delete('/tw-csl/code-submit-list/delete/' + id)
				.then(function (response) {
					if (response.status === 200) {
						_this.getList();
					} else {
						_this.$message.error('网络异常！');
					}
				}).catch(function (error) {
				_this.$message.error('服务器发生异常！');
				console.log(error);
			});
		},
		handleSizeChange: function (pageSize) {
			this.pageInfo.pageSize = pageSize;
			this.getList();
		},
		handleCurrentChange: function (pageNum) {
			this.pageInfo.pageNum = pageNum;
			this.getList();
		},
		download: function () {
			var a = document.createElement('a')
			var href = '/tw-csl/download?bugNo=' + this.bugNo + '&creator=' + this.creator + '&keyword='
				+ this.keyword + '&startTime=' + this.startTime
				+ '&endTime=' + this.endTime;
			// a.setAttribute('download','代码提交申请单')
			a.setAttribute('href', href);
			a.setAttribute('download', '');// download属性
			a.click();// 自执行点击事件
		},
		getList: function () {
			var _this = this;
			this.loading = true;
			axios.get('/tw-csl/list', {
				params: {
					pageNum: _this.pageInfo.pageNum,
					pageSize: _this.pageInfo.pageSize,
					bugNo: _this.bugNo,
					creator: _this.creator,
					keyword: _this.keyword,
					startTime: _this.date instanceof Array ? _this.date[0] : '',
					endTime: _this.date instanceof Array ? _this.date[1] : ''
				}
			}).then(function (response) {
				if (response.status === 200) {
					var res = response.data.data;
					_this.tableData = res.list;
					_this.pageInfo.pageNum = res.pageNum;
					_this.pageInfo.pageSize = res.pageSize;
					_this.pageInfo.total = res.total;

					_this.loading = false;
				} else {
					_this.$message.error('网络异常！');
				}
			}).catch(function (error) {
				_this.$message.error('服务器发生异常！');
				_this.loading = false;
				console.log(error);
			});
		},
		enter: function (index) {

			console.log("enter")
		},
		leave: function () {
			console.log("leave")

		}
	},
	components: {
		FormList: FormList,
		HeaderBar: HeaderBar
	}
});