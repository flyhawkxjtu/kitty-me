<template>
  <page404 v-bind="data"></page404>
</template>

<script>
  import page404 from '../../components/ErrorPage/404'
  export default {
    name: 'Page404',
    components: {
      page404
    },
    data(){
      return {
        data: {
          title: '哎呀',
          message: '您不能进入这个页面...',
          info: '请检查您输入的网址是否正确，请点击以下按钮返回主页'
        }
      }
    }
  }
</script>

