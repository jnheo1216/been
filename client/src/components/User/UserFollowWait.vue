<template>
  <div>
    <div> wait </div>
    <div> {{ followUserWaits }} </div>
  </div>
</template>

<script>
import axios from 'axios'

export default {
  name: 'UserFollowWait',
  data() {
    return {
      followUserWaits: [],
    }
  },
  created() {
    const id = localStorage.getItem('userId')
    axios.put(`http://localhost:8081/user/beforeFollowList/${id}`, id)
      .then(res => {
        console.log(res)
        // this.followUserWaits = res.data.users
      })
      .catch(err => {
          console.error(err)
      })
  },
}
</script>

<style>

</style>