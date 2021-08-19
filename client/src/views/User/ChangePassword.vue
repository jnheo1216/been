<template>
  <div>
    <label for="Password">비밀번호 변경</label>
    <input type="password" id="Password" v-model="User.password">

    <button @click="changePassword()">비밀번호 변경</button>
  </div>
</template>

<script>
// import axios from 'axios'
import axios from 'axios'
import {API_BASE_URL} from "@/config/index.js"

export default {
  data() {
    return {
      User: {
        id: "",
        password: ""
      }
    }
  },
  created() {
    this.User.id = this.$store.state.user.id
    console.log(this.User.id)
  },
  methods: {
    changePassword() {
      console.log(this.User)
      axios.put(API_BASE_URL + "user/password", this.User)
      .then(res => {
        console.log(res)
        this.$router.push('/profile')
      })
      .catch(err => {
        console.log(err)
      })
    }
  }
}
</script>

<style>

</style>