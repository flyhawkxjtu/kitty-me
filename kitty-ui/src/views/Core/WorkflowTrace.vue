<template>
  <div class="muti-tabs" style="width: 80%;height: 250px; background-color: #EDF0F5;margin: 20px auto">
    <el-tabs style="background-color: #EDF0F5;border-bottom-color: white" type="border-card" tabPosition="top">
      <el-tab-pane>
        <span class="tab-nav-font" slot="label">处理信息</span>
        <el-table
          :data="pageResult.content"
          size="small"
          style="width: 100%">
          <el-table-column
            prop="taskName"
            label="节点名称">
          </el-table-column>
          <el-table-column
            prop="opTime"
            width="200"
            label="处理时间">
          </el-table-column>
          <el-table-column
            prop="opUserName"
            label="处理人"
            width="180">
          </el-table-column>
          <el-table-column
            prop="opType"
            label="处理类型"
            width="180">
          </el-table-column>
          <el-table-column
            prop="opComment"
            label="处理意见"
            width="180">
          </el-table-column>
        </el-table>
        <el-row type="flex" align="middle" style="height: 50px" justify="end">
          <el-pagination layout="total,prev, pager, next, sizes, jumper" @size-change="handleSizeChange" @current-change="refreshPageRequest" background
                         :current-page="pageRequest.pageNum" :page-sizes="[10,20,50,100]" :page-size="pageRequest.pageSize" :total="pageResult.totalSize" style="margin-top: 15px;margin-right:15px">
          </el-pagination>
        </el-row>
      </el-tab-pane>
      <el-tab-pane>
        <span class="tab-nav-font" slot="label">流程图</span>
        <el-button  @click="handleClick">
         查看流程图
        </el-button>
        <el-button  @click="handleClick2">
          查看流程定义文件
        </el-button>
      </el-tab-pane>
    </el-tabs>
  </div>
</template>

<script>
    export default {
      name: "WorkflowTrace",
      props: {
        procDefKey: {
          type: String,
          default: ""
        },
        tasKId: {
          type: String,
          default: ""
        },
        opDataId: {
          type: String,
          default: ""
        }
      },
      data (){
        return {
          pageRequest: { pageNum: 1, pageSize: 10 },
          pageResult: {},
        }
      },
      methods: {
        // 换页刷新
        refreshPageRequest: function (pageNum) {
          this.pageRequest.pageNum = pageNum
          this.queryWorkflowTraceLog()
        },
        // 切换每页显示的数据条数
        handleSizeChange: function (pageSize,pageNum) {
          this.pageRequest.pageSize = pageSize
          this.pageRequest.pageNum = pageNum
          this.queryWorkflowTraceLog()
        },
        queryWorkflowTraceLog(){
          this.pageRequest.columnFilters = {procDefKey: {name:'procDefKey', value:this.procDefKey}, opDataId: {name:'opDataId',value:this.opDataId}}
          this.$api.workflow.queryWorkflowTraceLog(this.pageRequest).then((res) => {
            this.pageResult = res.data
          })

        },
        handleClick(){
          this.$api.workflow.queryWorkflowTraceImage('22501')
        },
        handleClick2(){
          this.$api.workflow.getProcessDefination('22501')
        }
      },
      mounted: function () {
        this.queryWorkflowTraceLog()
      }
    }
</script>

<style scoped>
  .muti-tabs .el-tabs--border-card>.el-tabs__header{
    background: #EDF0F5;
    height: 40px;
  }
  .muti-tabs .el-tabs--border-card>.el-tabs__header .el-tabs__item{
    border-color: #EDF0F5;
  }
  .muti-tabs .el-tabs--border-card>.el-tabs__header{
    border-color: #EDF0F5;
  }
  .muti-tabs .el-tabs--border-card>.el-tabs__header .el-tabs__item.is-active{
    background-color: #EDF0F5;
  }
  .muti-tabs .el-tabs--border-card{
    border: 0px solid white;
    box-shadow: 0 0 0 0;
  }

</style>
