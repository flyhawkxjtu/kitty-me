<template>
  <div>这是学生模块的流程处理组件
    <workflow-trace :procDefKey="processDefinationKey" :opDataId="businessDataId"></workflow-trace>
  </div>

</template>

<script>
  import WorkflowTrace from "@/views/Core/WorkflowTrace"

    export default {
      name: "HolidayApprove",
      components: {
        WorkflowTrace
      },
      props: {
        processDefinationKey: {//流程定义Key，查流程处理日志使用
          type: String,
          default: ""
        },
        processInstanceId: {//流程实例id
          type: String,
          default: ""
        },
        taskId: {//task  id
          type: String,
          default: ""
        },
        businessDataId: {//业务数据id（主键）
          type: String,
          default: ""
        }
      },
      data() {
          return {

          }
      },
      methods: {
        getHoliday(){

        }
      },
      mounted() {
        this.getHoliday();
        console.log('流程定义id：'+this.processDefinationKey)
        console.log('流程定义id：'+this.businessDataId)
      }
    }
</script>

<style scoped>

</style>
