<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>TeamWork</title>
</head>
<body>
<div id="app">
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
                        <span style="cursor: pointer; color: #3a8ee6;">{{item.name}}</span>
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
                        <span style="cursor: pointer; color: #3a8ee6;">{{item.name}}</span>
                    </div>
                    <div>
                        {{item.desc}}
                    </div>
                </el-card>
            </div>
        </div>
    </div>
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