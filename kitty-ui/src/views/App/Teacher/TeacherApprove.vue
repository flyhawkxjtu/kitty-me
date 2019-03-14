<template>
  <div>这是教师模块的流程处理组件</div>
</template>

<script>
    export default {
      name: "TeacherApprove",
      components: {

      },
      props: {
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
        this.getHoliday(businessDataId);
      }
    }
</script>

<style scoped>

</style>
