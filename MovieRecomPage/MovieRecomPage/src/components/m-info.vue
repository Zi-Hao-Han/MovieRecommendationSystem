<template>
  <el-row type="flex" justify="center">
    <el-col :span="8">
      <el-card :body-style="{ padding: '0px'}" shadow="hover">
        <div>
          <img :src="movie.imgName" v-if="movie" class="image">
        </div>
        <div class="right">{{ movie.movieName }}</div>
        <div class="block right" v-if="rate">
          Evaluation by {{rate[2]}} people
          <el-rate v-if="rate1 | rate1 === 0" v-model="rate1" disabled text-color="#ff9900" show-score :allow-half="true" :colors="['#99A9BF', '#F7BA2A', '#FF9900']">
          </el-rate>
          Evaluation by you
          <el-rate v-model="value" :disabled="isread" :allow-half="true" @change="changeHandler" :colors="['#99A9BF', '#F7BA2A', '#FF9900']">
          </el-rate>
        </div>
      </el-card>
    </el-col>
  </el-row>
</template>
<script>
import axios from 'axios'
import qs from 'qs'
export default {
  data() {
    return {
      value1: null
    }
  },
  mounted: function() {
    let vm = this
	axios.interceptors.request.use(
        function(config) {
          const authorization = localStorage.getItem('token')
          config.headers.Authorization = authorization
          return config
        },
        function(error) {
          return Promise.reject(error)
        }
      )
      axios
        .post(
          '/api/movie/setinfo.do',
          qs.stringify({ movieId: vm.movie.id})
        )
        .then(function(response) { 
        })
        .catch(function(error) {
        })
  },
  computed: {
    movie() {
      return this.$store.getters.getMovie(this.$route.params.id)
    },
    value: {
      get: function() {
        return this.$store.getters.getRate(this.$route.params.id)
      },
      set: function(newValue) {
        this.value1 = newValue
      }
    },
    isread() {
      if (this.value === null) {
        return false
      }
      return true
    },
    rate() {
      return this.$store.getters.getAllRate(this.$route.params.id)
    },
    rate1: {
      get: function() {
        return Number(this.rate[1].toFixed(1))
      },
      set: function() {
        if (this.rate[1] === 0) {
          return 0
        }
      }
    }
  },
  methods: {
    changeHandler(value) {
      if (this.$store.getters.getRate(this.$route.params.id) === null) {
        this.changeHandlerMethod(value)
      }
    },
    changeHandlerMethod(value) {
      let vm = this
	  axios.interceptors.request.use(
        function(config) {
          const authorization = localStorage.getItem('token')
          config.headers.Authorization = authorization
          return config
        },
        function(error) {
          return Promise.reject(error)
        }
      )
      axios
        .post(
          '/api/jwt/rate/save.do',
          qs.stringify({ movieId: vm.movie.id, rate: value })
        )
        .then(function(response) {
          vm.$store.commit('getRates')
		  vm.$store.commit('getAllRate')  
        })
        .catch(function(error) {
          vm.$message.error('Authentication failed, jump to login')
          console.log(error)
          vm.$router.push({ path: '/login' })
        })
    }
    
  }
}
</script>

<style lang="css" scoped>
.el-col {
  margin: 10px;
}
img {
  width: 160px;
  height: 220px;
  margin-top: 10px;
  margin-left: 10px;
  margin-right: 10px;
  float: left;
}
.right {
  padding-top: 5px;
  float: right;
  width: 290px;
}
</style>
