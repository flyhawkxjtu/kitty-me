<template>
  <div class="notice_box">
    <el-tabs style="height: 295px" type="border-card">
      <el-tab-pane>
        <span slot="label"><i class="el-icon-date"></i> 通知公告</span>
        <el-table
          :data="tableData"
          :show-header="false"
          style="width: 100%">
          <el-table-column
            prop="address"
            label="地址">
          </el-table-column>
        </el-table>
      </el-tab-pane>
    </el-tabs>
  </div>
</template>

<script>
    export default {
        name: "notice-list",
      data(){
        return {
          tableData: [{
            date: '2016-05-02',
            name: '王小虎',
            address: '上海市普陀区金沙江路 1518 弄'
          }, {
            date: '2016-05-04',
            name: '王小虎',
            address: '上海市普陀区金沙江路 1517 弄'
          },
            {
              date: '2016-05-01',
              name: '王小虎',
              address: '上海市普陀区金沙江路 1519 弄'
            },
            {
              date: '2016-05-01',
              name: '王小虎',
              address: '上海市普陀区金沙江路 1519 弄'
            },  {
            date: '2016-05-03',
            name: '王小虎',
            address: '上海市普陀区金沙江路 1516 弄'
          }]
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
  .notice_box .el-tabs--border-card>.el-tabs__header{
    background: white !important;
  }
  .notice_box .el-tabs--border-card>.el-tabs__header .el-tabs__item{
    border-color: white;
  }
  .notice_box .el-tabs--border-card>.el-tabs__header{
    border-color: white;
  }
  .notice_box .el-tabs--border-card>.el-tabs__header .el-tabs__item.is-active{
    color: #909399;
  }
</style>
