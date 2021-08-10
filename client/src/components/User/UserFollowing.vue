<template>
  <div>
    <div> following </div>
    <div> {{ followingUsers }} </div>
  </div>
</template>

<script>
import axios from 'axios'

export default {
  name: 'UserFollowing',
  data() {
    return {
      followingUsers: [],
    }
  },
  created() {
    const userId = localStorage.getItem('userId')
    axios.get(`http://localhost:8081/user/showFollowing/${userId}`)
      .then(res => {
        console.log(res)
        this.followingUsers = res.data.users
      })
      .catch(err => {
          console.error(err)
      })
  },
}
</script>

<style>

</style>