<template>
  <el-row type="flex" align="center" justify="center">
    <el-col :span="18">
      <el-menu :default-active="activeIndex" :router="true" class="el-menu-demo" mode="horizontal" background-color="#545c64" text-color="#fff" active-text-color="#ffd04b">
        <el-menu-item index="/">HOME</el-menu-item>
        <el-menu-item index="/movie/rec">RECOMMENDATION</el-menu-item>
        <el-menu-item index="/movie/add">ADD</el-menu-item>
		<el-menu-item index="/movie/search">SEARCH</el-menu-item>
        <el-menu-item index="/login">SIGN IN</el-menu-item>
		<el-menu-item index="/logout">LOGOUT</el-menu-item>
        <el-menu-item index="/sign">SIGN UP</el-menu-item>
      </el-menu>
    </el-col>
  </el-row>
</template>
<script>
export default {
  data() {
    return {}
  },
  computed: {
    activeIndex() {
      return this.$route.path
    }
  },
  mounted: function() {
    this.$store.commit('getMovies')
    if (localStorage.getItem('token') !== null) {
      this.$store.commit('getRates')
      this.$store.commit('getRec')
    }
    this.$store.commit('getAllRate')
  }
}
</script>
<style lang="css" scoped>
</style>
