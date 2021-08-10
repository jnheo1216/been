<template>
  <div class="background">
    <!-- <div>
      <div class="hexagon hexa-right"></div>
      <div class="hexagon hexa-left"></div>
    </div>
    <div>
      {{ postId }}
      {{ post }}
    </div> -->
    
    <div class="to-profile" @click="toProfile">
      <el-row :gutter="10">
        <el-col :span="4">
          <div class="post-profile-image">
            <img style="max-width: 100%;" :src="postProfilePicSrc" alt="프로필이미지">
          </div>
        </el-col>
        <el-col :span="20">
          <div class="post-user-nickname">
            <h3>{{ postUserNickname }}</h3>
          </div>
        </el-col>
      </el-row>
    </div>

    <div v-if="formNumber === 1" class="post-box-1">
      <div class="image-box-1">
        <img style="max-width: 100%;" :src="post.postPicSrc" alt="이미지">
      </div>
      <div class="title-box-1">
        <div><h2>{{ post.title }}</h2></div>
      </div>
      <div class="content-box-1">
        <div>{{ post.content }}</div>
      </div>
    </div>
    
    <div v-else-if="formNumber === 2">
      <div class="backimg" v-bind:style="{backgroundImage:'url('+this.post.postPicSrc+')'}">
          <el-container>
            <el-header></el-header>
            <el-container>
              <el-aside width="200px"></el-aside>
              <el-container>
                <el-main></el-main>
                <el-footer>

                  <div class="text-box">
                    <div class="title-box-1">
                      <div><h2>{{ post.title }}</h2></div>
                    </div>
                    <div class="content-box-1">
                      <div>{{ post.content }}</div>
                    </div>
                  </div>

                </el-footer>
              </el-container>
            </el-container>
          </el-container>
      </div>
    </div>

    <div v-else-if="formNumber === 3">
      <div class="backimg" v-bind:style="{backgroundImage:'url('+this.post.postPicSrc+')'}">
          <el-container>
            <el-header></el-header>
            <el-container>
              <el-aside width="150px">
                
                  <div class="text-box" style="margin-top: 200px;">
                    <div class="title-box-1">
                      <div><h2>{{ post.title }}</h2></div>
                    </div>
                    <div class="content-box-1">
                      <div>{{ post.content }}</div>
                    </div>
                  </div>

              </el-aside>
              <el-container>
                <el-main></el-main>
                <el-footer>
                </el-footer>
              </el-container>
            </el-container>
          </el-container>
      </div>
    </div>

    <div v-else>
      폼폼4?
    </div>

    <div class="like-box-1">
      <div v-if="isLike" @click="likeDown">좋아요취소</div>
      <div v-else @click="likeUp">좋아요</div>
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
        style="padding: 10px;"
          v-model="newComment"
          type="text"
          class=""
          placeholder="소감을 남겨주세요"
          size="small"
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
      formNumber: 3,
      isLike: false,
      comments: [],
      newComment: '',
      postUserNickname: '',
      postProfilePicSrc: '',
    }
  },
  created() {
    console.log('loading')
    axios.get(`http://localhost:8081/post/${this.$route.params.feedNumber}`)
      .then(res => {
        console.log(res)
        this.postId = res.data.post.postId
        this.post = res.data.post
        const id = res.data.post.userId
        axios.get(`http://localhost:8081/user/{id}?id=${id}`)
          .then(res2 => {
            console.log(res2)
            this.postUserNickname = res2.data.user.nickname
            this.postProfilePicSrc = res2.data.user.profilePicSrc
          })
          .catch(err2 => {
            console.error(err2)
          })
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
      console.log(like)
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
      var userSid = localStorage.getItem('userId')
      userSid *= 1
      axios.delete(`http://localhost:8081/post/like/delete/${this.postId}/${userSid}`)
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
    },
    toProfile() {
      const userId = this.post.userId
      this.$router.push({ name: 'UserProfile', params: { userId: userId }})
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
  .post-profile-image {
    margin: 10px;
  }
  .post-user-nickname {
    margin: 10px;
    position: relative;
    float: left;
    top: 10px;
  }
  .post-box-1 {
    background-color: white;
  }
  .like-box-1 {
    margin: 10px;
  }
  .text-box {
    margin: 10px;
    background-color: lightgray;
    border-radius: 10px;
  }
  .comment-form {
    margin: 10px;
  }
  .title-box-1 {
    padding: 10px;
  }
  .content-box-1 {
    padding: 10px;
  }
  .backimg {
    width: 375px;
    height: 375px;
    background-size: 375px;
  }
</style>