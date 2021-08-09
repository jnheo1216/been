<template>
  <div class="background">
    <div>
      <div class="hexagon hexa-right"></div>
      <div class="hexagon hexa-left"></div>
    </div>
    <div>
      {{ postId }}
      {{ post }}
    </div>

    <div v-if="formNumber === 1">
      <div style="width: 375px">
        <img style="max-width: 100%;" :src="post.postPicSrc" alt="이미지">
      </div>
      <div v-if="isLike" @click="likeDown">좋아요취소</div>
      <div v-else @click="likeUp">좋아요</div>
      <div>
        <div>{{ post.title }}</div>
      </div>
      <div>
        <div>{{ post.content }}</div>
      </div>
    </div>

    <div v-else-if="formNumber === 2">
      2번 폼 형태
    </div>

    <div v-else>
      폼폼3
    </div>

    <div class="comment-form">
      <div v-if="comments.length < 1">댓글이 아직 없습니다.</div>
      <div v-else>
        <div v-for="(comment, index) in comments" :key="comment.commentId">
          <div>{{ index }}</div>
          <div>{{ comment }}</div>
        </div>
      </div>
      <div class="comment-input">
        <el-input 
          v-model="newComment"
          type="text"
          class=""
          placeholder="소감을 남겨주세요"
          @keyup.enter="commentWrite"></el-input>
        <button @click="commentWrite">작성</button>
      </div>
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
      post: {},
      formNumber: 1,
      isLike: false,
      comments: [],
      newComment: '',
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
  },
  methods: {
    likeUp() {
      this.isLike = true
      const like = {
        postId: this.postId,
        userId: localStorage.getItem('userId'),
      }
      axios.post(`http://localhost:8081/post/like`, like)
        .then(res => {
          console.log(res)
        })
        .catch(err => {
          console.error(err)
        })
    },
    likeDown() {
      this.isLike = false
      const like = {
        postId: this.postId,
        userId: localStorage.getItem('userId'),
      }
      axios.delete(`http://localhost:8081/post/like`, like)
        .then(res => {
          console.log(res)
        })
        .catch(err => {
          console.error(err)
        })
    },
    commentWrite() {
      const new_comment = {
        postId: this.postId,
        userId: localStorage.getItem('userId'),
        comment: this.newComment,
      }
      axios.post(`http://localhost:8081/comment`, new_comment)
        .then(res => {
          console.log(res)
          this.comments.push(new_comment)
        })
        .catch(err => {
          console.error(err)
        })
    }
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