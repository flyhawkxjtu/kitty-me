<template>
  <div class="headbar">
    <!--左侧LOGO和系统名称-->
    <span style="float: left;">
      <img :src="logo" class="logo">
      <span class="appName">{{appName}}</span>
    </span>
    <!-- 右侧工具栏 -->
    <span style="float: right">
      <el-menu class="el-menu-demo" mode="horizontal">
        <el-tooltip content="全屏切换">
          <el-menu-item index="2" @click="fullScreen">
               <li style="color:grey;" class="fa fa-arrows-alt fa-lg"></li>
          </el-menu-item>
        </el-tooltip>
        <el-menu-item index="3" v-popover:popover-message>
          <el-badge :value="5" :max="99" class="badge" type="danger">
            <li style="color:grey;" class="fa fa-envelope-o fa-lg"></li>
          </el-badge>
          <el-popover ref="popover-message" placement="bottom-end" trigger="click">
            <message-panel></message-panel>
          </el-popover>
        </el-menu-item>
        <el-menu-item index="4" v-popover:popover-notice>
          <!-- 系统通知 -->
          <el-badge :value="4" :max="99" class="badge" type="danger">
            <li style="color:grey;" class="fa fa-bell-o fa-lg"></li>
          </el-badge>
          <el-popover ref="popover-notice" placement="bottom-end" trigger="click">
            <notice-panel></notice-panel>
          </el-popover>
        </el-menu-item>
        <el-menu-item index="5" v-popover:popover-personal>
          <!-- 用户信息 -->
          <span class="user-info" style="font-size: 18px">
            <li style="color:#F14040;" class="fa fa-user-circle fa-lg"><span class="user-info-name">{{user.name}}</span></li>
          </span>
          <el-popover ref="popover-personal" placement="bottom-end" trigger="click" :visible-arrow="false">
            <personal-panel :user="user"></personal-panel>
          </el-popover>
        </el-menu-item>
      </el-menu>
    </span>

  </div>
</template>

<script>
import { mapState } from 'vuex'
import mock from "@/mock/index"
import Hamburger from "@/components/Hamburger"
import ThemePicker from "@/components/ThemePicker"
import LangSelector from "@/components/LangSelector"
import Action from "@/components/Toolbar/Action"
import NoticePanel from "@/views/Core/NoticePanel"
import MessagePanel from "@/views/Core/MessagePanel"
import PersonalPanel from "@/views/Core/PersonalPanel"
import logo from '../../assets/main_logo.png'
import avatar from '../../assets/user.png'
import screenfull from 'screenfull'
export default {
  components:{
        Hamburger,
        ThemePicker,
        LangSelector,
        Action,
        NoticePanel,
        MessagePanel,
        PersonalPanel
  },
  data() {
    return {
      logo,
      user: {
        name: "",
        avatar: avatar,
        role: "",
        registeInfo: ""
      },
      activeIndex: '1',
      langVisible: false,
      appName: '微信后管系统'
    }
  },
  methods: {
    openWindow(url) {
      window.open(url)
    },
    selectNavBar(key, keyPath) {
      console.log(key, keyPath)
    },
    // 折叠导航栏
    onCollapse: function() {
      this.$store.commit('onCollapse')
    },
    // 切换主题
    onThemeChange: function(themeColor) {
      this.$store.commit('setThemeColor', themeColor)
    },
    // 语言切换
    changeLanguage(lang) {
      lang === '' ? 'zh_cn' : lang
      this.$i18n.locale = lang
      this.langVisible = false
    },
    fullScreen() {
      if (!screenfull.enabled) {
        this.$message({
          message: 'you browser can not work',
          type: 'warning'
        })
        return false
      }
      screenfull.toggle()
    }
  },
  mounted() {
    this.sysName = "Kitty Platform"
    var user = sessionStorage.getItem("user")
    if (user) {
      this.user.name = sessionStorage.getItem("user.name")
      this.user.avatar = require("@/assets/user.png")
    }
  },
  computed:{
    ...mapState({
      themeColor: state=>state.app.themeColor,
      collapse: state=>state.app.collapse
    })
  }
}
</script>

<style scoped lang="scss">
@font-face {
  font-family: "pingfang-zhunjian";
  src:url("../../assets/font/pingfang.ttf");
  font-style: normal;
}
.headbar {
  position: fixed;
  top: 0;
  right: 0;
  z-index: 1030;
  background: white;
  width: 100%;
  height: 60px;
  line-height: 60px;
  border-bottom: 1px solid #e6e6e6;
}
.logo{
  width: 30px;
  height: 30px;
  vertical-align: middle;
  line-height: 60px;
  margin-left: 10px;
  margin-top: -8px;
}
.appName{
  font-size: 22px;
  color: #424040;;
  text-align: left;
  line-height: 60px;
  font-family: pingfang-zhongjian;
  font-weight: bold;
  letter-spacing: 2px;
  margin-left: 2px;
}
.toolbar {
  float: right;
}
.lang-item {
  font-size: 16px;
  padding-left: 8px;
  padding-top: 8px;
  padding-bottom: 8px;
  cursor: pointer;
}
.lang-item:hover {
  font-size: 18px;
  background: #b0d6ce4d;
}
.user-info-img {
  width: 40px;
  height: 40px;
  border-radius: 2em;

}
.user-info-name{
  font-size: 16px;
  color: #635d5d;
  cursor: pointer;
  margin-left: 10px;
  font-weight: bold;
}
.badge {
  line-height: 18px;
}
.position-left {
  /*left: 200px;*/
  left: 0;
}
.position-collapse-left {
  /*left: 65px;*/
  left: 0;
}
.el-menu--horizontal>.el-menu-item.is-active{
  border-bottom: 0px solid #e6e6e6;
}
</style>
