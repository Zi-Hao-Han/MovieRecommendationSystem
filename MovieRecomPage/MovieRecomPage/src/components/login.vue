<template>
  <el-row type="flex" align="center" justify="center">
    <el-form ref="ruleForm" :model="ruleForm" :rules="rules" label-width="100px" class="demo-ruleForm">
      <el-form-item label="Username" prop="userName">
        <el-input v-model="ruleForm.userName" />
      </el-form-item>
      <el-form-item label="Password" prop="password">
        <el-input v-model="ruleForm.password" type="password" />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="submitForm('ruleForm')">SIGN IN</el-button>
      </el-form-item>
    </el-form>
  </el-row>
</template>

<script>
import axios from 'axios'
import qs from 'qs'
export default {
  name: 'Login',
  data() {
    return {
      ruleForm: {
        userName: '',
        password: ''
      },
      rules: {
        userName: [
          { required: true, message: 'please select username', trigger: 'blur' },
          { min: 3, max: 5, message: 'limit to 3-5 Characters', trigger: 'blur' }
        ],
        password: [
          { required: true, message: 'please select password', trigger: 'blur' },
          { min: 3, max: 5, message: 'limit to 3-5 Characters', trigger: 'blur' }
        ]
      }
    }
  },
  methods: {
    save2localstorage(token) {
      localStorage.setItem('token', token)
    },
    submitForm(formName) {
      var vm = this
      this.$refs[formName].validate(valid => {
        if (valid) {
          var form = this.ruleForm
          axios
            .post('/api/user/login.do', qs.stringify(form))
            .then((response) =>{
			  if(response.data.code == 0){

			    vm.save2localstorage(response.data.data)
				vm.$store.commit('getRates')
				vm.$store.commit('getRec')
				vm.$store.commit('getAllRate')  
				this.$message.success('logs-in successfully')
				vm.$router.go(-1)
			  }else{
				this.$message.error(response.data.msg)
			  }
              
            })
        } else {
          console.log('error submit!!')
          return false
        }
      })
    }
  }
}
</script>

<style lang="css" scoped>
.el-button {
  width: 100%;
}
</style>
