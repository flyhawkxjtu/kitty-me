<template>
  <div id="tabledemo" class="tabledemo">
    <el-row style="height: 50px;line-height: 50px;">
      <el-col :span="24">
        <span class="title_font">自有资金调拨单</span>
      </el-col>
    </el-row>
    <el-row>
      <el-col :span="24">
        <div style="width: 80%;height: 400px; background-color: #EDF0F5;margin: 10px auto">
          <el-row>&nbsp;</el-row>
          <el-row type="flex" align="middle" style="height: 40px;">
            <el-col :span="2" style="text-align: right">
              <span>日期：</span>
            </el-col>
            <el-col :span="4">
              <el-input v-model="form.riqi" placeholder="请输入内容" size="mini"></el-input>
            </el-col>
            <el-col :span="2" style="text-align: right">
              <span>编号：</span>
            </el-col>
            <el-col :span="4">
              <el-input v-model="form.bianhao" placeholder="请输入内容" size="mini"></el-input>
            </el-col>
          </el-row>
          <el-row type="flex" align="middle" style="height: 40px;">
            <el-col :span="2" style="text-align: right">
              <span>收款方：</span>
            </el-col>
            <el-col :span="10">
              <el-select v-model="form.shoukuanfang"  placeholder="--请选择--" size="mini" style="width: 100%">
                <el-option
                  v-for="item in shoukuanfangoptions"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value">
                </el-option>
              </el-select>
            </el-col>
          </el-row>
          <el-row type="flex" align="middle" style="height: 40px;">
            <el-col :span="2" style="text-align: right">
              <span>户名：</span>
            </el-col>
            <el-col :span="4">
              <el-input v-model="form.huming" placeholder="请输入内容" size="mini"></el-input>
            </el-col>
            <el-col :span="2" style="text-align: right">
              <span>开户行：</span>
            </el-col>
            <el-col :span="4">
              <el-input v-model="form.zhifuhang" placeholder="请输入内容" size="mini"></el-input>
            </el-col>
            <el-col :span="2" style="text-align: right">
              <span>账号：</span>
            </el-col>
            <el-col :span="4">
              <el-input v-model="form.zhanghao" placeholder="请输入内容" size="mini"></el-input>
            </el-col>
            <el-col :span="2" style="text-align: right">
              <span>币种：</span>
            </el-col>
            <el-col :span="3">
              <el-input v-model="form.bizhong1" placeholder="请输入内容" size="mini"></el-input>
            </el-col>
          </el-row>
          <el-row type="flex" align="middle" style="height: 40px;">
            <el-col :span="2" style="text-align: right">
              <span>收款方：</span>
            </el-col>
            <el-col :span="10">
              <el-select v-model="form.shoukuanfang"  placeholder="--请选择--" size="mini" style="width: 100%">
                <el-option
                  v-for="item in shoukuanfangoptions"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value">
                </el-option>
              </el-select>
            </el-col>
          </el-row>
          <el-row type="flex" align="middle" style="height: 40px;">
            <el-col :span="2" style="text-align: right">
              <span>户名：</span>
            </el-col>
            <el-col :span="4">
              <el-input v-model="form.huming" placeholder="请输入内容" size="mini"></el-input>
            </el-col>
            <el-col :span="2" style="text-align: right">
              <span>开户行：</span>
            </el-col>
            <el-col :span="4">
              <el-input v-model="form.zhifuhang" placeholder="请输入内容" size="mini"></el-input>
            </el-col>
            <el-col :span="2" style="text-align: right">
              <span>账号：</span>
            </el-col>
            <el-col :span="4">
              <el-input v-model="form.zhanghao" placeholder="请输入内容" size="mini"></el-input>
            </el-col>
            <el-col :span="2" style="text-align: right">
              <span>币种：</span>
            </el-col>
            <el-col :span="3">
              <el-input v-model="form.bizhong2" placeholder="请输入内容" size="mini"></el-input>
            </el-col>
          </el-row>
          <el-row type="flex" align="middle" style="height: 40px;">
            <el-col :span="2" style="text-align: right">
              <span>金额：</span>
            </el-col>
            <el-col :span="4">
              <el-input v-model="form.jine" placeholder="请输入内容" size="mini"></el-input>
            </el-col>
          </el-row>
          <el-row type="flex" align="top" style="height: 40px;margin-top: 5px">
            <el-col :span="2" style="text-align: right">
              <span>事由：</span>
            </el-col>
            <el-col :span="21">
              <el-input type="textarea" :autosize="{ minRows: 4, maxRows: 6 }" v-model="form.shiyou" placeholder="请输入内容" size="mini"></el-input>
            </el-col>
          </el-row>
          <el-row type="flex" justify="center" style="margin-top: 60px">
            <el-col :span="6">
              <el-button type="danger" size="mini">保存</el-button>
              <el-button size="mini">提交审核</el-button>
              <el-button size="mini">收回</el-button>
            </el-col>
          </el-row>
        </div>
      </el-col>
    </el-row>

    <el-row>
      <el-col :span="24">
        <div class="tabledemo_box" style="width: 80%;height: 250px; background-color: #EDF0F5;margin: 20px auto">
            <el-tabs style="background-color: #EDF0F5;border-bottom-color: white" type="border-card" tabPosition="top">
              <el-tab-pane>
                <span class="tab-nav-font" slot="label">处理信息</span>
                <el-table
                  :data="tableData"
                  :header-cell-style="headerCellStyle"
                  size="small"
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
                <el-pagination style="margin-top: 10px"
                  background
                  :page-sizes="[100, 200, 300, 400]"
                  :page-size="100"
                  layout="prev, pager, next, sizes, jumper"
                  :total="1000">
                </el-pagination>
              </el-tab-pane>
              <el-tab-pane>
                <span class="tab-nav-font" slot="label">流程图</span>
                <el-table
                  :data="tableData2"
                  :header-cell-style="headerCellStyle"
                  size="small"
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
                <el-pagination style="margin-top: 10px"
                  background
                  layout="prev, pager, next"
                  :total="1000">
                </el-pagination>
              </el-tab-pane>
            </el-tabs>
          </div>
      </el-col>
    </el-row>
  </div>
</template>

<script>


    export default {
      name: "tabledemo",
      data(){
        return {
          form:{
            riqi:'20171021',
            bianhao:'20171027001',
            shoukuanfang:'',
            huming:'民生证券股份有限公司',
            zhifuhang:'招商银行崇文门支行',
            zhanghao:'11110000222323',
            bizhong1:'人民币',
            bizhong2:'美元',
            jine:12322122,
            shiyou:'事由...'
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
            name: '王小虎',
            address: '上海市普陀区金沙江路 1518 弄上海市普陀区金沙江路上海市普陀区金沙江路'
          }, {
            date: '2016-05-04',
            name: '王小虎',
            address: '上海市普陀区金沙江路 1517 弄上海市普陀区金沙江路上海市普陀区金沙江路'
          }, {
            date: '2016-05-01',
            name: '王小虎',
            address: '上海市普陀区金沙江路 1519 弄上海市普陀区金沙江路上海市普陀区金沙江路'
          }],
          tableData2: [{
            date: '2016-05-02',
            name: '王小虎',
            address: '上海市普陀区金沙江路 1518 弄上海市普陀区金沙江路上海市普陀区金沙江路'
          }]
        }
      },
      methods: {
        headerCellStyle({row, rowIndex}){
          return 'background-color: #dcdfe6;color: #636364;font-weight: bold;'
        }
      }
    }
</script>

<style scoped>
   .tabledemo{
     background-color: white;
     width: 98%;
     height: 780px;
     margin: 10px auto;
   }
   .title_font{
     font-size: 20px;
     font-weight: bold;
   }
</style>
<style>
  .tabledemo_box .el-tabs--border-card>.el-tabs__header{
    background: #EDF0F5;
    height: 40px;
  }
  .tabledemo_box .el-tabs--border-card>.el-tabs__header .el-tabs__item{
    border-color: #EDF0F5;
  }
  .tabledemo_box .el-tabs--border-card>.el-tabs__header{
    border-color: #EDF0F5;
  }
  .tabledemo_box .el-tabs--border-card>.el-tabs__header .el-tabs__item.is-active{
    background-color: #EDF0F5;
  }
  .tabledemo_box .el-tabs--border-card{
    border: 0px solid white;
    box-shadow: 0 0 0 0;
  }
  .more_font>a{
    color: #909399;
    text-decoration: none;
    font-size: 14px;
    font-weight: 500;
  }
  #tab-more{
    right: -290%;
  }
  .tabledemo_box .is-active>span{
    display: block;
    background: #F14040;
    width: 110%;
    line-height: 20px;
    color: white;
    border-radius: 5px;
  }
  .tabledemo_box .el-pagination.is-background .el-pager li:not(.disabled).active{
    background-color: #F14040;
  }
  .tabledemo_box .el-pagination.is-background .btn-next, .el-pagination.is-background .btn-prev, .el-pagination.is-background .el-pager li{
    background-color: white;
  }
</style>
