<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>TeamWork</title>
    <style type="text/css">
		[v-cloak] {
			display: none !important;
		}
    </style>
</head>
<body>
<div id="app" v-cloak>
    <header-bar></header-bar>
    <div class="common-container">
        <div class="idx_split">
            <div class="idx_split_each">
                <el-card shadow="hover"
                         class="idx_split_card"
                         v-for="item in lanProjects"
                         :key="item.id"
                >
                    <div slot="header" class="clearfix">
                        <span style="cursor: pointer; color: #3a8ee6;" @click="toProject(item.id)">{{item.name}}</span>
                        <el-button style="float: right; padding: 3px 0" type="text" @click="showProjectDetail(item.id)">详情>></el-button>
                    </div>
                    <div>
                        {{item.desc}}
                    </div>
                </el-card>
            </div>
            <div style="width: 1px; height: auto; background-color: #E2E7EF;">
            </div>
            <div class="idx_split_each">
                <el-card shadow="hover"
                         class="idx_split_card"
                         v-for="item in i18nProjects"
                         :key="item.id"
                >
                    <div slot="header" class="clearfix">
                        <span style="cursor: pointer; color: #3a8ee6;" @click="toProject(item.id)">{{item.name}}</span>
                        <el-button style="float: right; padding: 3px 0" type="text" @click="showProjectDetail(item.id)">详情>></el-button>
                    </div>
                    <div>
                        {{item.desc}}
                    </div>
                </el-card>
            </div>
        </div>
    </div>
    <el-dialog title="项目详情" :visible.sync="dialogFormVisible">
        <el-form :model="form">
            <el-form-item label="活动名称" :label-width="formLabelWidth">
                <el-input v-model="form.name" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="活动区域" :label-width="formLabelWidth">
                <el-select v-model="form.region" placeholder="请选择活动区域">
                    <el-option label="区域一" value="shanghai"></el-option>
                    <el-option label="区域二" value="beijing"></el-option>
                </el-select>
            </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
            <el-button @click="dialogFormVisible = false" size="mini">取 消</el-button>
            <el-button type="primary" @click="dialogFormVisible = false" size="mini">确 定</el-button>
        </div>
    </el-dialog>
</div>

<link rel="stylesheet" href="/lib/element-ui/lib/theme-chalk/index.css">
<link rel="stylesheet" href="/css/common.css">
<link rel="stylesheet" href="/css/code-submit-list.css">
<link rel="stylesheet" href="/css/index.css">
<script src="/lib/vue.min.js"></script>
<script src="/lib/axios-v0.18.0.min.js"></script>
<script src="/lib/element-ui/lib/index.js"></script>

<script src="/modules/header-bar.js"></script>
<script src="/modules/main.js"></script>
</body>
</html>