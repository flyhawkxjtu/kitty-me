<template>
  <el-card class="box-card">
    <el-tabs :before-leave="moreState">
      <el-tab-pane label="最新">最新</el-tab-pane>
      <el-tab-pane label="新闻">新闻</el-tab-pane>
      <el-tab-pane label="公告">公告</el-tab-pane>
      <el-tab-pane label="活动">活动</el-tab-pane>
      <el-tab-pane name="more" class="more-btn"><span slot="label"><router-link to="/page/wholeList/wholeList">更多+</router-link></span></el-tab-pane>
    </el-tabs>
  </el-card>
</template>

<script>
  export default {
    name: "HomeNewsList",
    date(){
      return{
        // moreState:false,
      }
    },
    methods:{
      moreState(tab, event){
        if(tab == 'more'){
          console.log("/////",tab, event );
          return false;
        }
      }
    }
  }
</script>

<style scoped>
  .box-card{
    width: 600px;
  }
  .box-card .el-tabs__item.is-top:last-child{
    color: #ff4949;
  }

</style>
