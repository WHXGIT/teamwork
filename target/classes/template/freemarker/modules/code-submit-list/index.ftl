<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <style type="text/css">
        [v-cloak] {
            display: none !important;
        }
    </style>
    <title>代码提交清单</title>
</head>
<body>
<div id="app" v-cloak>
    <header-bar></header-bar>
    <div class="common-container">
        <div class="code-submit-condition">
            <label class="code-submit-label">提交人：</label>
            <div class="code-submit-fill">
                <el-select v-model="creator"
                           clearable
                           placeholder="请选择提交人"
                           size="mini">
                    <el-option
                            v-for="item in creators"
                            :key="item.value"
                            :label="item.label"
                            :value="item.value">
                    </el-option>
                </el-select>
            </div>
            <label class="code-submit-label">关键字：</label>
            <div class="code-submit-fill">
                <el-input
                        placeholder="请输入关键字"
                        v-model="keyword"
                        size="mini">
                </el-input>
            </div>
            <label class="code-submit-label">时间范围：</label>
            <div class="code-submit-fill">
                <el-date-picker
                        size="mini"
                        clearable
                        v-model="date"
                        type="daterange"
                        align="right"
                        value-format="yyyy-MM-dd HH:mm:ss"
                        unlink-panels
                        range-separator="至"
                        start-placeholder="开始日期"
                        end-placeholder="结束日期"
                        :picker-options="pickerOptions">
                </el-date-picker>
            </div>
            <div class="code-submit-fill" style="margin-left: 20px;">
                <el-button type="primary" icon="el-icon-search" size="mini" @click="getList">搜索</el-button>
            </div>
        </div>

        <el-divider></el-divider>
        <div class="button-operate-scope">
            <el-button type="primary" size="mini" icon="el-icon-document-add" @click="createHandle">新增申请单</el-button>
            <el-button type="primary" size="mini" icon="el-icon-download" @click="download">下载申请单</el-button>
        </div>

        <el-table
                :data="tableData"
                style="width: 100%"
                :row-class-name="tableRowClassName"
                :v-loading="loading"
                size="mini">
            <el-table-column
                    type="expand"
                    fixed="left">
                <template slot-scope="props">
                    <el-form label-position="left" inline class="demo-table-expand">
                        <el-form-item label="提交时间">
                            <span v-html="props.row.createTime"></span>
                        </el-form-item>
                        <el-form-item label="提交人">
                            <span v-html="props.row.creator"></span>
                        </el-form-item>
                        <el-form-item label="提交目标">
                            <span v-html="props.row.submitTarget"></span>
                        </el-form-item>
                        <el-form-item label="是否自测">
                            <span>{{props.row.isSelfTest | selfTestFilter}}</span>
                        </el-form-item>
                        <el-form-item label="冒烟测试">
                            <span>{{props.row.smokeTest | smokeTestFilter}}</span>
                        </el-form-item>
                        <el-form-item label="Java文件名">
                            <span class="span-break-line" v-html="props.row.javaFiles"></span>
                        </el-form-item>
                        <el-form-item label="代码修改说明">
                            <span class="span-break-line" v-html="props.row.codeModifyDesc"></span>
                        </el-form-item>
                        <el-form-item  label="元数据文件">
                            <span class="span-break-line" v-html="props.row.metaFiles"></span>
                        </el-form-item>
                        <el-form-item label="影响范围">
                            <span class="span-break-line" v-html="props.row.scope"></span>
                        </el-form-item>
                        <el-form-item label="数据库脚本">
                            <span class="span-break-line" v-html="props.row.dbScript"></span>
                        </el-form-item>
                        <el-form-item label="备注">
                            <span class="span-break-line" v-html="props.row.remark"></span>
                        </el-form-item>
                        <el-form-item label="数据状态">
                            <span>{{props.row.status | statusFilter}}</span>
                        </el-form-item>
                    </el-form>
                </template>
            </el-table-column>

            <el-table-column
                    label="提交时间"
                    prop="createTime"
                    width="150">
            </el-table-column>
            <el-table-column
                    label="提交人"
                    prop="creator"
                    width="60">
            </el-table-column>
            <el-table-column
                    label="提交目标"
                    prop="submitTarget"
                    width="80">
            </el-table-column>
            <el-table-column
                    label="代码修改说明"
                    prop="codeModifyDesc"
                    width="150"
                    show-overflow-tooltip>
            </el-table-column>
            <el-table-column
                    label="影响范围"
                    prop="scope"
                    width="150"
                    show-overflow-tooltip>
            </el-table-column>
            <el-table-column
                    label="是否自测"
                    width="100">
                <template slot-scope="scope">{{ scope.row.isSelfTest | selfTestFilter }}</template>
            </el-table-column>
            <el-table-column
                    label="冒烟测试"
                    width="100">
                <template slot-scope="scope">{{ scope.row.smokeTest | smokeTestFilter }}</template>
            </el-table-column>
            <el-table-column
                    label="Java文件名"
                    prop="javaFiles"
                    width="150"
                    show-overflow-tooltip>
            </el-table-column>
            <el-table-column
                    label="元数据文件"
                    prop="metaFiles"
                    width="150"
                    show-overflow-tooltip>
            </el-table-column>
            <el-table-column
                    label="数据库脚本"
                    prop="dbScript"
                    width="150"
                    show-overflow-tooltip>
            </el-table-column>
            <el-table-column
                    label="备注"
                    prop="remark"
                    width="150"
                    show-overflow-tooltip>
            </el-table-column>
            <el-table-column
                    label="数据状态"
                    width="50">
                <template slot-scope="scope">{{ scope.row.status | statusFilter}}</template>
            </el-table-column>
            <el-table-column
                    label="操作"
                    fixed="right"
                    width="300">
                <template slot-scope="scope">
                    <div v-if="scope.row.status === 0">
                        <el-button
                                size="mini"
                                @click="handleEdit(scope.row)">编辑
                        </el-button>
                        <el-button
                                size="mini"
                                type="success"
                                @click="handleFinish(scope.row.id)">完成
                        </el-button>
                        <el-button
                                size="mini"
                                type="warning"
                                @click="handleGiveUp(scope.row.id)">废弃
                        </el-button>
                        <el-button
                                size="mini"
                                type="danger"
                                @click="handleDelete(scope.row.id)">删除
                        </el-button>
                    </div>
                    <div v-if="scope.row.status === 2">
                        <el-button
                                size="mini"
                                type="primary"
                                @click="handleRecovery(scope.row.id)">恢复
                        </el-button>
                    </div>
                </template>
            </el-table-column>
        </el-table>

        <div class="page-select-down">
            <el-pagination
                    @size-change="handleSizeChange"
                    background
                    small
                    @current-change="handleCurrentChange"
                    :current-page="pageInfo.pageNum"
                    :page-sizes="[20, 50, 100, 500]"
                    :page-size="pageInfo.pageSize"
                    layout="total, sizes, prev, pager, next, jumper"
                    :total="pageInfo.total">
            </el-pagination>
        </div>
        <form-list :title="'创建代码提交申请单'"
                   :creators="creators"
                   ref="createFormList"
                   @refresh="getList"></form-list>
        <form-list :title="'修改代码提交申请单'"
                   :creators="creators"
                   ref="modifyFormList"
                   @refresh="getList"></form-list>

    </div>
</div>

<link rel="stylesheet" href="/lib/element-ui/lib/theme-chalk/index.css">
<link rel="stylesheet" href="/css/common.css">
<link rel="stylesheet" href="/css/code-submit-list.css">

<script src="/lib/vue.min.js"></script>
<script src="/lib/axios-v0.18.0.min.js"></script>
<script src="/lib/element-ui/lib/index.js"></script>
<script src="/plugins/clipboard.min.js"></script>

<script src="/modules/header-bar.js"></script>
<script src="/modules/code-submit-list/main.js"></script>
</body>
</html>