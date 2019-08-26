<template>
  <body id="poster">
  <div>
    <nav-menu></nav-menu>
    <LSideMenu></LSideMenu>
    <router-view/>
  </div>
  </body>
</template>

<script>
import NavMenu from './common/NavMenu'
import LSideMenu from './common/LSideMenu'
export default {
  name: 'HelloWorld',
  components: {LSideMenu, NavMenu}
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
  #poster {
    background:url("../assets/timg.jpg") no-repeat;
    background-position: center;
    height: 100%;
    width: 100%;
    background-size: cover;
    position: fixed;
  }
  body{
    margin: 0px;
  }
</style>
