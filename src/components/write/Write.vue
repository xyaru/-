<template>
  <div>
    <el-input
      type="text"
      style="margin-left: 150px; width: 85%"
      placeholder="请输入100字内的标题"
      v-model="text"
      maxlength="100"
      show-word-limit
    >
    </el-input>
    <div style="margin: 20px 0;"></div>
    <el-input
      type="textarea"
      style="margin-left: 150px; width: 85%"
      :autosize="{ minRows: 8, maxRows: 25}"
      placeholder="请输入内容"
      v-model="textarea1">
    </el-input>
    <el-button style="float: right; margin-right: 2%">发表</el-button>
  </div>
</template>

<script>
export default {
  data () {
    return {
      text: '',
      textarea1: ''
    }
  }
}
</script>

<style scoped>

</style>
