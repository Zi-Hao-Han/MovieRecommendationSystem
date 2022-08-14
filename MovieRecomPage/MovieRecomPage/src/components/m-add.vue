<template>
  <el-row type="flex" justify="center">

    <el-col :span="4">
      <el-input v-model="input" placeholder="Film Name" />

      <el-upload ref="upload" :headers="headers" :data="movieName" :on-exceed="handleExceed" :on-change="handleChange" :on-success="handleSuccess" :file-list="fileList" :auto-upload="false" :limit="1" class="upload-demo" action="/api/jwt/movie/upload.do">
        <el-button slot="trigger" size="small" type="primary">select image</el-button>
        <el-button size="small" type="success" @click="submitUpload">upload film</el-button>
        <div slot="tip" class="el-upload__tip">Only one jpg/png file can be uploaded, and it is not more than 500kb</div>
      </el-upload>
    </el-col>
  </el-row>
</template>
<script>
export default {
  data() {
    return {
      fileList: [],
      input: ''
    }
  },
  computed: {
    movieName() {
      return { movieName: this.input }
    },
    headers() {
      return { authorization: localStorage.getItem('token') }
    }
  },
  methods: {
    handleChange(file, fileList) {
      if (file.size > 512000) {
        this.$message.warning(`it is more than 500k`)
        this.$refs.upload.clearFiles()
      }
    },
    submitUpload() {
      if (this.input === '') {
        this.$message.warning(`Please fill in the username`)
      } else {
        this.$refs.upload.submit()
      }
    },
    handleSuccess(response, file, fileList) {
      this.input = ''
      this.$refs.upload.clearFiles()
      this.$store.commit('getMovies')
    },
    handleExceed(files, fileList) {
      this.$message.warning(
        `The number of files is limited to 1. Currently, ${files.length} files are selected`
      )
    }
  }
}
</script>

<style lang="css" scoped>
.el-input {
  margin-bottom: 10px;
}
.el-button {
  margin-left: 12px;
}
</style>
