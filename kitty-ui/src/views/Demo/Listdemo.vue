<template>
    <div class="list_demo_class">
      <!--搜索框-->
      <div class="search_div">
        <el-row type="flex" align="middle" style="margin-top: 10px;height: 50px">
          <el-col :span="2" style="text-align: center">
            <span>编号：</span>
          </el-col>
          <el-col :span="3">
            <el-input v-model="form.bianhao" size="mini"></el-input>
          </el-col>
          <el-col :span="2" style="text-align: center">
            <span>收款方：</span>
          </el-col>
          <el-col :span="3">
            <el-select v-model="form.shoukuanfang"  placeholder="--请选择--" size="mini" style="width: 100%">
              <el-option
                v-for="item in shoukuanfangoptions"
                :key="item.value"
                :label="item.label"
                :value="item.value">
              </el-option>
            </el-select>
          </el-col>
          <el-col :span="2" style="text-align: center">
            <span>日期：</span>
          </el-col>
          <el-col :span="6">
            <el-date-picker
              size="mini"
              v-model="form.riqi"
              type="daterange"
              range-separator="至"
              start-placeholder="开始日期"
              end-placeholder="结束日期">
            </el-date-picker>
          </el-col>
          <el-col :span="6">
            <el-button type="danger" icon="el-icon-search" size="mini">搜索</el-button>
            <el-button type="warning" icon="el-icon-delete" size="mini">清空</el-button>
            <el-button type="warning" icon="el-icon-more" size="mini" @click="moreCondition = !moreCondition">更多</el-button>
          </el-col>
        </el-row>
        <el-collapse-transition>
          <div v-show="moreCondition">
            <el-row  type="flex" align="middle" style="height: 50px">
              <el-col :span="2" style="text-align: center">
                <span>姓名：</span>
              </el-col>
              <el-col :span="3">
                <el-input v-model="form.bianhao" size="mini"></el-input>
              </el-col>
              <el-col :span="2" style="text-align: center">
                <span>付款方：</span>
              </el-col>
              <el-col :span="3">
                <el-select v-model="form.shoukuanfang"  placeholder="--请选择--" size="mini" style="width: 100%">
                  <el-option
                    v-for="item in shoukuanfangoptions"
                    :key="item.value"
                    :label="item.label"
                    :value="item.value">
                  </el-option>
                </el-select>
              </el-col>
              <el-col :span="2" style="text-align: center">
                <span>日期：</span>
              </el-col>
              <el-col :span="6">
                <el-date-picker
                  size="mini"
                  v-model="form.riqi"
                  type="daterange"
                  range-separator="至"
                  start-placeholder="开始日期"
                  end-placeholder="结束日期">
                </el-date-picker>
              </el-col>
            </el-row>
          </div>
        </el-collapse-transition>

      </div>
      <!--table-->
      <div class="table_div">
          <el-row type="flex" align="middle" style="height: 50px">
            <el-col :span="6" style="text-align: left;margin-left: 20px">
              <el-button type="danger"  size="mini">新增</el-button>
              <el-button  size="mini">编辑</el-button>
              <el-button  size="mini">删除</el-button>
              <el-button  size="mini">复制</el-button>
              <el-button  size="mini">打印</el-button>
            </el-col>
          </el-row>
          <el-row>
            <el-col>
              <el-table
                :data="tableData"
                :header-cell-style="headerCellStyle"
                :cell-style="rowstyle"
                :row-class-name="rowClass"
                @row-click="rowClick"
                size="small"
                stripe
                style="width: 97%;margin: 0 auto;">
                <el-table-column
                  type="selection"
                  width="55">
                </el-table-column>
                <el-table-column
                  prop="date"
                  width="100"
                  label="日期">
                </el-table-column>
                <el-table-column
                  width="180"
                  label="编号">
                  <template slot-scope="scope">
                      <span style="color: #F14040;text-decoration:underline #F14040 solid;cursor: pointer ">{{ scope.row.bianhao }}</span>
                  </template>
                </el-table-column>
                <el-table-column
                  prop="shoukuanfangzhanghao"
                  label="收款方-账号"
                  width="180">
                </el-table-column>
                <el-table-column
                  prop="shoukuanfangyinhang"
                  label="收款方-银行"
                  width="180">
                </el-table-column>
                <el-table-column
                  prop="shoukuanfangzhanghao"
                  label="付款方-账号"
                  width="180">
                </el-table-column>
                <el-table-column
                  prop="shoukuanfangyinhang"
                  label="付款方-银行"
                  width="180">
                </el-table-column>
                <el-table-column
                  prop="jine"
                  label="金额"
                  width="100">
                </el-table-column>
                <el-table-column
                  prop="zhuangtai"
                  label="状态"
                  width="100">
                </el-table-column>
                <el-table-column
                  prop="name"
                  label="当前处理人"
                  width="100">
                </el-table-column>
              </el-table>
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

    import ElRow from "element-ui/packages/row/src/row";

    export default {
      components: {ElRow},
      name: "list-demo",
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
  .rowClass{
    background-color: #55a532;
  }
 .list_demo_class{
   width: 98%;
   height: 780px;
   margin: 10px auto;
 }
 .search_div{
   background-color: white;
 }
 .table_div{
   margin-top: 10px;
   background-color: white;
 }
</style>
<style>
  .list_demo_class .el-pagination.is-background .el-pager li:not(.disabled).active{
    background-color: #F14040;
  }
  .list_demo_class .el-pagination.is-background .btn-next, .el-pagination.is-background .btn-prev, .el-pagination.is-background .el-pager li{
    background-color: white;
    border: 1px solid #edf0f5;
  }
  .rowClass:hover{
    background-color: #FFF6F7 !important;
  }
</style>
