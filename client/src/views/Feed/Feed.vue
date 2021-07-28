<template>
  <div class="mainfeed">
    <div class="logo">
      <img alt="BEEN LOGO" src="@/assets/logo.png">
    </div>
    <FeedList />

  </div>
</template>


<script>
import FeedList from '@/components/Feed/FeedList.vue'

  export default {
    name: 'Feed',
    data () {
      return {
        count: 0
      }
    },
    methods: {
      load () {
        this.count += 2
      }
    },
    components: {
      FeedList
    }
  }
</script>

<style>
  .mainfeed {
    width: 375px;
    height: 812px;
    background-color: #fffaf4;
    margin: 0 auto;
  }

</style>