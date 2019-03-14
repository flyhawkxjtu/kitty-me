<template>
  <div>
    <el-row style="margin-top: 20px;" :gutter="20">
      <el-col :span="18">
        <!--待办事项-->
        <todo-list></todo-list>
      </el-col>
      <el-col :span="6">
        <calendar></calendar>
      </el-col>
    </el-row>
    <el-row style="margin-top: 30px;" :gutter="20">
      <el-col :span="18">
        <!--提醒事项-->
        <tips-list></tips-list>
      </el-col>
      <el-col :span="6">
        <notice-list></notice-list>
      </el-col>
    </el-row>
  </div>
</template>

<script>
  import TodoList from './components/TodoList'
  import TipsList from './components/TipsList'
  import NoticeList from './components/NoticeList'
  import Calendar from '../../components/Calendar'
  import WTabs from "../../components/Tabs/src/tabs";
  import WTabPane from "../../components/Tabs/src/tab-pane";
  import ElRow from "element-ui/packages/row/src/row";
  export default {
    name: 'DashboardAdmin',
    components:{
      ElRow,
      WTabPane,
      WTabs,
      TodoList,
      TipsList,
      NoticeList,
      Calendar
    },
    data() {
      return {
        lineChartData: ''
      }
    },
    methods: {

    }
  }
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
  .dashboard-editor-container {
    padding: 32px;
    background-color: rgb(240, 242, 245);
    .chart-wrapper {
      background: #fff;
      padding: 16px 16px 0;
      margin-bottom: 32px;
    }
  }
</style>
