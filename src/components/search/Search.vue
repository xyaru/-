<template>
  <div>
    <el-input v-model="input" placeholder="请输入内容" style="margin-left: 150px"></el-input>
    <el-button icon="el-icon-search">全文搜索</el-button>
    <el-button icon="el-icon-search">关键字/标签搜索</el-button>
  </div>
</template>

<script>
export default {
  data () {
    return {
      input: ''
    }
  }
}
</script>

<style scoped>

</style>
