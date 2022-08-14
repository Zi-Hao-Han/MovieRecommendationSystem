<template>
  <el-row type="flex" align="center" justify="center">
    <el-form ref="ruleForm" :model="ruleForm" :rules="rules" label-width="100px" class="demo-ruleForm">
      <el-form-item label="Username" prop="userName">
        <el-input v-model="ruleForm.userName" />
      </el-form-item>
      <el-form-item label="Password" prop="password">
        <el-input v-model="ruleForm.password" type="password" />
      </el-form-item>
	  <el-form-item label="Gender" prop="sex">
        <el-select v-model="ruleForm.sex" placeholder="select"> <el-option v-for="item in options" :key="item.value" :label="item.label" :value="item.value"> </el-option>
		</el-select>
      </el-form-item>
	  <el-form-item label="age" prop="age">
	    <el-input v-model="ruleForm.age" type="number" oninput="value=value.replace(/[^\d]/g,'')"/>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="submitForm('ruleForm')">SIGN UP</el-button>
      </el-form-item>
    </el-form>
  </el-row>
</template>

<script>
import axios from 'axios'
import qs from 'qs'
export default {
  name: 'Sign',
  data() {
    var validateName = (rule, value, callback) => {
      axios
        .post('/api/user/validate.do', qs.stringify({ userName: value }))
        .then(function(response) {
          if (response.data) {
            callback()
          } else {
            callback(new Error('Username registered'))
          }
        })
    }
    return {
	  options: [
		{ value: '1', label: 'male' }, 
		{ value: '0', label: 'female' }, 
		],
      ruleForm: {
        userName: '',
        password: '',
		sex:'',
		age:''
      },
      rules: {
        userName: [
          { validator: validateName, trigger: 'blur' },
          { required: true, message: 'please input username', trigger: 'blur' },
          { min: 3, max: 5, message: 'limit to 3-5 Characters', trigger: 'blur' }
        ],
        password: [
          { required: true, message: 'please input password', trigger: 'blur' },
          { min: 3, max: 5, message: 'limit to 3-5 Characters', trigger: 'blur' }
        ],
		sex:[
          { required: true, message: 'please select gender', trigger: 'blur' }
        ],
		age:[
          { required: true, message: 'please select age', trigger: 'blur' },
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
            .post('/api/user/sign.do', qs.stringify(form))
            .then(function(response) {
              vm.save2localstorage(response.data.data)
              vm.$message({
                message: 'Successful registration and login',
                type: 'success'
              })
              vm.$store.commit('getRates')
              vm.$store.commit('getRec')
              vm.$router.push({ path: '/' })
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
