<template>
  <el-menu
    :default-active="'/'"
    router
    mode="horizontal"
    background-color="transparent"
    text-color="#222"
    active-text-color="red"
    style="width: 100%">
    <el-menu-item v-for="(item,i) in navList" :key="i" :index="item.name" style="color: white">
      {{ item.navItem }}
    </el-menu-item>
    <router-link :to="{ path: 'login' }" style="color: white;float: right;padding: 20px;">登录</router-link>
    <i class="el-icon-menu" style="float:right;font-size: 45px;color: #222;padding-top: 8px;color: white"></i>
    <span style="position: absolute;padding-top: 20px;right: 43%;font-size: 20px;font-weight: bold;color: white">技术分享博客网站</span>
  </el-menu>
</template>

<script>
export default {
  name: 'NavMenu',
  data () {
    return {
      navList: [
        {name: '/', navItem: '首页'},
        {name: '/search', navItem: '搜索'},
        {name: '/write', navItem: '写博客'},
        {name: '/admin', navItem: '个人中心'}
      ]
    }
  }
}
</script>

<style scoped>
  a{
    text-decoration: none;
  }

  span {
    pointer-events: none;
  }
</style>
