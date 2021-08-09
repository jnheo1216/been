<template>
  <div class="background">
    상세
    <div>
      {{ postId }}
      {{ post }}
    </div>
    <div>
      <div class="hexagon hexa-right"></div>
      <div class="hexagon hexa-left"></div>
    </div>
  </div>
</template>

<script>
import axios from 'axios'

export default {
  name: 'FeedDetail',
  data() {
    return {
      postId: '',
      post: null
    }
  },
  created() {
    console.log('loading')
    axios.get(`http://localhost:8081/post/${this.$route.params.feedNumber}`)
      .then(res => {
        console.log(res)
        this.postId = res.data.post.postId
        this.post = res.data.post
      })
      .catch(err => {
        console.error(err)
      })
  }
}
</script>

<style>
  .background {
    width: 375px;
    height: 812px;
    text-align: center;
    background-color: #FFFAF4;
    margin:0 auto;
  }
  .hexagon {
    width: 100px;
    height: 55px;
    background: pink;
    position: relative;
  }
  .hexagon:before {
    content: "";
    position: absolute;
    top: -25px;
    left: 0;
    width: 0;
    height: 0;
    border-left: 50px solid transparent;
    border-right: 50px solid transparent;
    border-bottom: 25px solid pink;
  }
  .hexagon:after {
    content: "";
    position: absolute;
    bottom: -25px;
    left: 0;
    width: 0;
    height: 0;
    border-left: 50px solid transparent;
    border-right: 50px solid transparent;
    border-top: 25px solid pink;
  }
  .hexa-right {
    float: right;
  }
  .hexa-left {
    float: left;
  }
</style>