<template>
  <div class="tips_box">
    <el-tabs style="height: 295px" type="border-card">
      <el-tab-pane>
        <span slot="label"> 提醒事项</span>
        <el-table
          :data="tableData"
          :header-cell-style="headerCellStyle"
          style="width: 100%">
          <el-table-column
            type="index"
            label="序号">
          </el-table-column>
          <el-table-column
            prop="address"
            label="标题">
          </el-table-column>
          <el-table-column
            prop="date"
            label="发送日期"
            width="180">
          </el-table-column>
          <el-table-column
            prop="name"
            label="发送人"
            width="180">
          </el-table-column>
        </el-table>
      </el-tab-pane>
      <el-tab-pane>
        <span slot="label"> 承办事项</span>
        <el-table
          :data="tableData2"
          :header-cell-style="headerCellStyle"
          style="width: 100%">
          <el-table-column
            type="index"
            label="序号">
          </el-table-column>
          <el-table-column
            prop="address"
            label="标题">
          </el-table-column>
          <el-table-column
            prop="date"
            label="发送日期"
            width="180">
          </el-table-column>
          <el-table-column
            prop="name"
            label="发送人"
            width="180">
          </el-table-column>

        </el-table>
      </el-tab-pane>
      <el-tab-pane name="more" class="more-btn"><span class="more_font" slot="label"><router-link to="/page/wholeList/wholeList">更多&nbsp;></router-link></span></el-tab-pane>
    </el-tabs>
  </div>
</template>

<script>
  export default {
    name: "tips-list",
    data(){
      return {
        tableData: [{
          date: '2016-05-02',
          name: '王小虎',
          address: '上海市普陀区金沙江路 1518 弄上海市普陀区金沙江路上海市普陀区金沙江路'
        }, {
          date: '2016-05-04',
          name: '王小虎',
          address: '上海市普陀区金沙江路 1518 弄上海市普陀区金沙江路上海市普陀区金沙江路'
        }, {
          date: '2016-05-03',
          name: '王小虎',
          address: '上海市普陀区金沙江路 1518 弄上海市普陀区金沙江路上海市普陀区金沙江路'
        }],
        tableData2: [{
          date: '2016-05-02',
          name: '王小虎',
          address: '上海市普陀区金沙江路 1518 弄上海市普陀区金沙江路上海市普陀区金沙江路'
        }, {
          date: '2016-05-04',
          name: '王小虎',
          address: '上海市普陀区金沙江路 1518 弄上海市普陀区金沙江路上海市普陀区金沙江路'
        }]
      }
    },
    methods: {
      headerCellStyle({row, rowIndex}){
        return 'background-color: #F14040;color: #fff;font-weight: 500;'
      }
    }
  }
</script>

<style scoped>

  /*去掉卡片边框*/
  .el-tabs--border-card{
    -webkit-box-shadow:0 0 0 0;
    box-shadow:0 0 0 0;
  }
</style>
<style>
  .tips_box .el-tabs--border-card>.el-tabs__header{
    background: white !important;
  }
  .tips_box .el-tabs--border-card>.el-tabs__header .el-tabs__item{
    border-color: white;
  }
  .tips_box .el-tabs--border-card>.el-tabs__header{
    border-color: white;
  }
  .more_font>a{
    color: #909399;
    text-decoration: none;
    font-size: 14px;
    font-weight: 500;
  }
  #tab-more{
    right: -800px;
  }
  .tips_box .is-active>span{
    display: block;
    background: #F14040;
    width: 110%;
    line-height: 20px;
    color: white;
    border-radius: 5px;
/*    border-style: double;*/
  }
  .tips_box #tab-1{
    margin-left: -12px
  }
  .tips_box .el-tabs--border-card>.el-tabs__header .el-tabs__item:not(.is-disabled):hover{
    color: #909399;
  }
  .tips_box .el-tabs--border-card>.el-tabs__header .el-tabs__item.is-active{
    color: #909399;
  }
</style>
