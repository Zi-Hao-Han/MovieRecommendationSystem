<template>
  <el-row type="flex" align="center" justify="center">
    <el-button type="primary" @click="logout()">Log out</el-button>
  </el-row>
</template>
<script>
export default {
  data() {
    return {}
  },
  methods: {
    logout() {
	  this.$message.success('Log out successfully！')
      localStorage.removeItem('token')
	  this.$router.push({ path: '/login' })
    }
  }
}
</script>
