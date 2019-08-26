<template>
  <body id="poster">
  <el-form class="regist-container" label-position="left"
           label-width="0px">
    <h3 class="regist_title">注册博客</h3>
    <el-form-item>
      <el-input type="text" v-model="registForm.username"
                auto-complete="off" placeholder="账号"></el-input>
    </el-form-item>
    <el-form-item>
      <el-input type="password" v-model="registForm.password"
                auto-complete="off" placeholder="密码"></el-input>
    </el-form-item>
    <el-form-item>
      <el-input type="password" v-model="registForm.repasswd"
                auto-complete="off" placeholder="确认密码"></el-input>
    </el-form-item>
    <el-form-item style="width: 100%">
      <el-button type="primary" style="width: 100%;background: #505458;border: none" v-on:click="login">注册</el-button>
    </el-form-item>
  </el-form>
  </body>
</template>

<script>
export default {
  name: 'Regist',
  data () {
    return {
      registForm: {
        username: '',
        password: '',
        repasswd: ''
      },
      responseResult: []
    }
  },
  methods: {
    login () {
      var _this = this
      console.log(this.$store.state)
      this.$axios
        .post('http://localhost:8443/api/login', {
          username: this.loginForm.username,
          password: this.loginForm.password
        })
        .then(successResponse => {
          if (successResponse.data.code === 200) {
            alert('登录成功')
            _this.$store.commit('login', _this.loginForm)
            var path = this.$route.query.redirect
            this.$router.replace({path: path === '/' || path === undefined ? '/index' : path})
          } else {
            alert('账号密码错误')
          }
        })
        .catch(failResponse => {
        })
    }
  }
}
</script>

<style scoped>
  .regist-container {
    border-radius: 15px;
    background-clip: padding-box;
    margin: 90px auto;
    width: 350px;
    padding: 35px 35px 15px 35px;
    background: #fff;
    border: 1px solid #eaeaea;
    box-shadow: 0 0 25px #cac6c6;
  }

  .regist_title {
    margin: 0px auto 40px auto;
    text-align: center;
    color: #505458;
  }
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
