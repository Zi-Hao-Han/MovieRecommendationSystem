<template>
  <el-row type="flex" align="center" justify="center">
  
  



    <el-form ref="ruleForm" :model="ruleForm" :rules="rules" label-width="100px" class="demo-ruleForm">
      
	  <el-form-item label="Search Type" prop="searchtype">
        <el-select v-model="ruleForm.searchtype" placeholder="Select"> <el-option v-for="item in options" :key="item.value" :label="item.label" :value="item.value"> </el-option>
		</el-select>
      </el-form-item>
	  <el-form-item prop="searchcontent">
        <el-input v-model="ruleForm.searchcontent" type="text" />
      </el-form-item>
      <el-form-item>
         <el-button type="primary" @click="submitForm('ruleForm')">Search</el-button>
      </el-form-item>
    </el-form>
  </el-row>
</template>
<script>
import axios from 'axios'
import qs from 'qs'
export default {
  data() {
    return { 
		options: [
		{ value: 'movieName', label: 'Film Name' }, 
		{ value: 'descrip', label: 'Film Desp' }, 
		{ value: 'timelong', label: 'Film TimeLong' }, 
		{ value: 'shoot', label: 'Film Shoot' },
		{ value: 'language', label: 'Film Language' }, 
		{ value: 'actors', label: 'Film Actor' }, 
		{ value: 'director', label: 'Film Director' }, 
		{ value: 'issue', label: 'Film Issue' }, 
		{ value: 'type', label: 'Film Type' }, 
		],
		ruleForm: {
			searchtype: '',
			searchcontent: ''
		  },
		 rules: {
			searchtype: [
			  { required: true, message: 'Please select search-type' },
			],
			password: [
			  { searchcontent: true, message: 'Please input inot search-content', trigger: 'blur' }        
			]
		 }
	}
  },
  mounted: function() {
    if (localStorage.getItem('token') == null) {
      this.$message.error('Authentication failed, jump to login')
      this.$router.push({ path: '/login' })
    }
  },
  methods: {
    submitForm(formName) {
      var vm = this
      this.$refs[formName].validate(valid => {
        if (valid) {
          var form = this.ruleForm
          axios
            .post('/api/jwt/movie/search.do', qs.stringify(form))
            .then(function(response) {
			  vm.$store.commit('getResult')
            })
		  return true
        } else {
          console.log('error submit!!')
          return false
        }
      })
	  this.$router.push({ path: '/result' })
    }
  }
}
</script>

<style lang="css" scoped>
.el-button {
  width: 100%;
}
</style>
