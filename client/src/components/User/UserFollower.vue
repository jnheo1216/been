<template>
  <div>
    <div> follower </div>
    <div> {{ followerUsers }} </div>
  </div>
</template>

<script>
import axios from 'axios'

export default {
  name: 'UserFollower',
  data() {
    return {
      followerUsers: [],
    }
  },
  created() {
    const userId = localStorage.getItem('userId')
    axios.get(`http://localhost:8081/user/showFollower/${userId}`)
      .then(res => {
        console.log(res)
        this.followerUsers = res.data.users
      })
      .catch(err => {
          console.error(err)
      })
  },
}
</script>

<style>

</style>