<template>
  <suspense>
    <template #default>
  <div>
    {{ feeds }}
  </div>
    </template>

    <template #fallback>
      <div>Loading...</div>
    </template>
  </suspense>
</template>

<script>
import axios from 'axios'

export default {
  name: 'FeedFollowList',
  data() {
    return {
      feeds: []
    }
  },
  created() {
    const userId = localStorage.getItem('userId')
    axios.get(`http://localhost:8081/post/followPost/${userId}`)
      .then(res => {
        console.log(res)
        this.feeds = res.data.posts
      })
      .catch(err => {
          console.error(err)
      })
  }
}
</script>

<style>

</style>