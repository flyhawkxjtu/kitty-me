<template>
  <div class="todo_box" style="background-color: white">
    <el-tabs style="height: 295px;background-color: white;border-bottom-color: white" type="border-card" tabPosition="top">
      <el-tab-pane>
        <span class="tab-nav-font" slot="label">代办事项</span>
        <!--
        <kt-table permsEdit="" permsDelete=""
                  :data="pageResult" :columns="filterColumns"
                  @findPage="findPage" >
        </kt-table>
        -->
        <el-table
          :data="pageResult.content"
          :header-cell-style="headerCellStyle"
          style="width: 100%">
          <el-table-column
            type="index"
            label="序号">
          </el-table-column>
          <el-table-column
            prop="title"
            label="标题">
            <template slot-scope="scope">
              <span style="color: #F14040;text-decoration:underline #F14040 solid;cursor: pointer " v-on:click="openProcessDialog(scope.row)">{{ scope.row.title }}</span>
            </template>
          </el-table-column>
          <el-table-column
            prop="createTime"
            label="发送日期"
            width="180">
          </el-table-column>
          <el-table-column
            prop="sendUserName"
            label="发送人"
            width="180">
          </el-table-column>
        </el-table>


      </el-tab-pane>
      <el-tab-pane>
        <span class="tab-nav-font" slot="label">已办事项</span>
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
    <!--待办流程处理界面-->
    <el-dialog :title="'流程处理'"  :visible.sync="dialogVisible" :close-on-click-modal="false" >
      <holiday-approve :processDefinationKey="'holiday'" :businessDataId="businessDataId" v-if="processDefinationKey=='holiday'"></holiday-approve>
      <teacher-approve :processDefinationKey="'teacher'" :businessDataId="businessDataId" v-if="processDefinationKey=='teacher'"></teacher-approve>
      <div slot="footer" class="dialog-footer">
        <el-button :size="size" @click.native="dialogVisible = false">{{$t('action.cancel')}}</el-button>
        <el-button :size="size" type="danger" @click.native="submitForm">{{$t('action.submit')}}</el-button>
      </div>
    </el-dialog>



  </div>
</template>

<script>
  import WTabs from '../../../components/Tabs/src/tabs'
  import WTabPane from '../../../components/Tabs/src/tab-pane'
  import HolidayApprove from "@/views/App/Student/HolidayApprove"
  import TeacherApprove from "@/views/App/Teacher/TeacherApprove"
    export default {
      name: "todo-list",
      components:{
        WTabPane,
        WTabs,
        HolidayApprove,
        TeacherApprove
      },
      props: {
      },
      data(){
          return {
            size: 'small',
            dialogVisible: false, // 流程处理界面是否显示
            todoData: [],
            tableData2: [],
            pageRequest: { pageNum: 1, pageSize: 10 },
            pageResult: {},
            filterColumns: [],
            processDefinationKey: '',
            businessDataId: ''
          }
      },
      methods: {
        headerCellStyle({row, rowIndex}){
          return 'background-color: #F14040;color: #fff;font-weight: 500;'
        },
        // 时间格式化
        dateFormat: function (row, column, cellValue, index){
          return format(row[column.property])
        },
        findPage: function (data) {
          if(data !== null) {
            this.pageRequest = data.pageRequest
          }
          this.pageRequest.columnFilters = {}
          this.$api.workflow.queryTodo(this.pageRequest).then((res) => {
            this.pageResult = res.data
          }).then(data!=null?data.callback:'')
        },
        openProcessDialog(row){//根据不同的流程key,显示不同的vue组件
          this.processDefinationKey = row.processDefinationKey
          this.businessDataId=row.businessDataId
          this.dialogVisible = true
        }

      },
      mounted() {
        let pageRequest = new Object()
        pageRequest.pageSize = 10
        pageRequest.pageNum = 1
        let data = new Object();
        data.pageRequest = pageRequest
        this.findPage(data);

      }
    }
</script>

<style scoped>

   /*去掉卡片边框*/
  .el-tabs--border-card{
    -webkit-box-shadow:0 0 0 0;
    box-shadow:0 0 0 0;
  }
  .titile_font:active{
    line-height: 30px;
    background: red;
    color: white;
    display: inline-block;
    font-weight: bold;
    border-radius: 5px;
  }
   .titile_font{
     line-height: 30px;
     display: inline-block;
     font-weight: bold;
     border-radius: 5px;

   }
</style>
<style>
  .todo_box .el-tabs--border-card>.el-tabs__header{
    background: white;
  }
  .todo_box .el-tabs--border-card>.el-tabs__header .el-tabs__item{
    border-color: white;
  }
  .todo_box .el-tabs--border-card>.el-tabs__header{
    border-color: white;
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
  .todo_box .is-active>span{
    display: block;
    background: #F14040;
    width: 110%;
    line-height: 20px;
    color: white;
    border-radius: 5px;
    /*border-style: double;*/
  }
  .todo_box #tab-1{
    margin-left: -12px
  }

  .todo_box .el-tabs--border-card>.el-tabs__header .el-tabs__item:not(.is-disabled):hover{
    color: #909399;
  }
  .todo_box .el-tabs--border-card>.el-tabs__header .el-tabs__item.is-active{
    color: #909399;
  }
  .workflow-dialog-css{
    width: 60% ;
    height: 80%
  }
</style>
