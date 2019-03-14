<template>
    <div class="wf_kitty_table_box">
      <div class="wf_table_search_box">
        <!--搜索插槽-->
        <slot name="search_box"></slot>
      </div>
      <div class="table_div">
        <el-row type="flex" align="middle" style="height: 50px">
          <el-col :span="6" style="text-align: left;margin-left: 20px">
            <!--操作插槽-->
           <slot name="operate_box"></slot>
          </el-col>
        </el-row>
        <el-row>
          <el-col>
            <slot name="table_box"></slot>
          </el-col>
        </el-row>
        <el-row type="flex" align="middle" style="height: 50px" justify="end">
          <el-pagination style="margin-top: 15px;margin-right:15px"
                         background
                         :page-sizes="[100, 200, 300, 400]"
                         :page-size="100"
                         layout="prev, pager, next, sizes, jumper"
                         :total="1000">
          </el-pagination>
        </el-row>
        <el-row>&nbsp;</el-row>
      </div>
    </div>
</template>

<script>
    export default {
      name: "WfTable",
      data(){
        return {
          moreCondition:false,
          form:{
            bianhao:'',
            fukuanfang:'',
            riqi:'',
            shoukuanfang:''
          },
          shoukuanfangoptions:[
            {
              value: '1',
              label: '收款方A'
            }, {
              value: '2',
              label: '收款方B'
            }, {
              value: '3',
              label: '收款方C'
            }
          ],
          tableData: [{
            date: '2016-05-02',
            zhuangtai:'初始态',
            jine:'50000.0000',
            bianhao: 'ZJDB201710110002',
            shoukuanfangzhanghao:'622200021116132408',
            shoukuanfangyinhang:'兴业银行朝外支行',
            name: '王小虎',
            address: '上海市普陀区金沙江路 1518 弄上海市普陀区金沙江路上海市普陀区金沙江路'
          }, {
            date: '2016-05-04',
            zhuangtai:'审核中',
            name: '王小虎',
            jine:'50000.0000',
            bianhao: 'ZJDB201710110002',
            shoukuanfangzhanghao:'622200021116132408',
            shoukuanfangyinhang:'兴业银行朝外支行',
            address: '上海市普陀区金沙江路 1517 弄上海市普陀区金沙江路上海市普陀区金沙江路'
          }, {
            date: '2016-05-04',
            zhuangtai:'审核中',
            name: '王小虎',
            jine:'50000.0000',
            bianhao: 'ZJDB201710110002',
            shoukuanfangzhanghao:'622200021116132408',
            shoukuanfangyinhang:'兴业银行朝外支行',
            address: '上海市普陀区金沙江路 1517 弄上海市普陀区金沙江路上海市普陀区金沙江路'
          }, {
            date: '2016-05-04',
            zhuangtai:'审核中',
            name: '王小虎',
            jine:'50000.0000',
            bianhao: 'ZJDB201710110002',
            shoukuanfangzhanghao:'622200021116132408',
            shoukuanfangyinhang:'兴业银行朝外支行',
            address: '上海市普陀区金沙江路 1517 弄上海市普陀区金沙江路上海市普陀区金沙江路'
          }, {
            date: '2016-05-04',
            zhuangtai:'审核中',
            name: '王小虎',
            jine:'50000.0000',
            bianhao: 'ZJDB201710110002',
            shoukuanfangzhanghao:'622200021116132408',
            shoukuanfangyinhang:'兴业银行朝外支行',
            address: '上海市普陀区金沙江路 1517 弄上海市普陀区金沙江路上海市普陀区金沙江路'
          }, {
            date: '2016-05-04',
            zhuangtai:'审核中',
            name: '王小虎',
            jine:'50000.0000',
            bianhao: 'ZJDB201710110002',
            shoukuanfangzhanghao:'622200021116132408',
            shoukuanfangyinhang:'兴业银行朝外支行',
            address: '上海市普陀区金沙江路 1517 弄上海市普陀区金沙江路上海市普陀区金沙江路'
          }, {
            date: '2016-05-04',
            zhuangtai:'审核中',
            name: '王小虎',
            jine:'50000.0000',
            bianhao: 'ZJDB201710110002',
            shoukuanfangzhanghao:'622200021116132408',
            shoukuanfangyinhang:'兴业银行朝外支行',
            address: '上海市普陀区金沙江路 1517 弄上海市普陀区金沙江路上海市普陀区金沙江路'
          }, {
            date: '2016-05-04',
            zhuangtai:'审核中',
            name: '王小虎',
            jine:'50000.0000',
            bianhao: 'ZJDB201710110002',
            shoukuanfangzhanghao:'622200021116132408',
            shoukuanfangyinhang:'兴业银行朝外支行',
            address: '上海市普陀区金沙江路 1517 弄上海市普陀区金沙江路上海市普陀区金沙江路'
          }, {
            date: '2016-05-04',
            zhuangtai:'审核中',
            name: '王小虎',
            jine:'50000.0000',
            bianhao: 'ZJDB201710110002',
            shoukuanfangzhanghao:'622200021116132408',
            shoukuanfangyinhang:'兴业银行朝外支行',
            address: '上海市普陀区金沙江路 1517 弄上海市普陀区金沙江路上海市普陀区金沙江路'
          }, {
            date: '2016-05-01',
            jine:'50000.0000',
            zhuangtai:'审核中',
            bianhao: 'ZJDB201710110002',
            shoukuanfangzhanghao:'622200021116132408',
            shoukuanfangyinhang:'兴业银行朝外支行',
            name: '王小虎',
            address: '上海市普陀区金沙江路 1519 弄上海市普陀区金沙江路上海市普陀区金沙江路'
          }]
        }
      },
      methods:{
        headerCellStyle({row, rowIndex}){
          return 'background-color: #F8F9FD;color: #636364;font-weight: bold;text-align:center;'
        },
        rowstyle({row,rowIndex}){
          return 'text-align:center;'
        },
        rowClass({row,rowIndex}){
          return 'rowClass'
        },
        rowClick(row, event, column){
          console.log(row,event,column)
        }

      }
    }
</script>

<style scoped>
  .wf_kitty_table_box{
    width: 98%;
    height: 780px;
    margin: 10px auto;
  }
  .wf_table_search_box{
    background-color: white;
  }
  .table_div{
    margin-top: 10px;
    background-color: white;
  }
</style>
<style>
  .wf_kitty_table_box .el-pagination.is-background .el-pager li:not(.disabled).active{
    background-color: #F14040;
  }
  .wf_kitty_table_box .el-pagination.is-background .btn-next, .el-pagination.is-background .btn-prev, .el-pagination.is-background .el-pager li{
    background-color: white;
    border: 1px solid #edf0f5;
  }
  .rowClass:hover{
    background-color: #FFF6F7 !important;
  }
</style>
