<template>
  <div class="background">
    
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

    <div class="like-box-1" style="font-size: large; color: red;">
      <div v-if="isLike" @click="likeDown"><i class="fas fa-heart"></i></div>
      <div v-else @click="likeUp"><i class="far fa-heart"></i></div>
    </div>

    <div class="comment-form">
      <div v-if="comments.length < 1">댓글이 아직 없습니다.</div>
      <div v-else>
        <div v-for="(comment) in comments" :key="comment.commentId">
          <!-- <div>{{ index }}</div> -->
          <div>{{ comment.comment }}</div>
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
import {getFeedDetail, postLike, postLikeDelete, postIsLike} from '@/api/feed.js'
import {writeComment} from '@/api/comment.js'
import {API_BASE_URL} from "@/config/index.js";

export default {
  name: 'FeedDetail',
  data() {
    return {
      postId: '',
      post: {},
      formNumber: 2,
      isLike: false,
      comments: [],
      newComment: '',
      postUserNickname: '',
      postProfilePicSrc: '',
      user: '',
    }
  },
  created() {
    this.user = this.$store.state.user
    console.log('loading')
    const postId = this.$route.params.feedNumber
    getFeedDetail(
      postId,
      (res) => {
        console.log(res)
        this.postId = res.data.post.postId
        this.post = res.data.post
        const id = res.data.post.userId
        axios.get(API_BASE_URL + `user/{id}?id=${id}`)
          .then(res2 => {
            console.log(res2)
            this.postUserNickname = res2.data.user.nickname
            this.postProfilePicSrc = res2.data.user.profilePicSrc
          })
          .catch(err2 => {
            console.error(err2)
          })
        axios.get(API_BASE_URL + `comment/listByPost/${this.postId}`)
          .then(res3 => {
            console.log(res3)
            this.comments = res3.data.comments
          })
          .catch(err3 => {
            console.error(err3)
          })        
        const like = {
          postId: this.postId,
          userId: this.user.id,
        }
        postIsLike(
          like,
          (res4) => {
            if (res4.data.like) {
              this.isLike = true
            } else {this.isLike = false}
          },
          (err4) => {console.error(err4)}
        )
      },
      (err) => {
        console.error(err)
      }
    )
    // axios.get(`http://localhost:8081/post/${this.$route.params.feedNumber}`)
    //   .then(res => {
    //     console.log(res)
    //     this.postId = res.data.post.postId
    //     this.post = res.data.post
    //     const id = res.data.post.userId
    //     axios.get(`http://localhost:8081/user/{id}?id=${id}`)
    //       .then(res2 => {
    //         console.log(res2)
    //         this.postUserNickname = res2.data.user.nickname
    //         this.postProfilePicSrc = res2.data.user.profilePicSrc
    //       })
    //       .catch(err2 => {
    //         console.error(err2)
    //       })
    //     axios.get(`http://localhost:8081/comment/listByPost/${this.postId}`)
    //       .then(res3 => {
    //         console.log(res3)
    //         this.comments = res3.data.comments
    //       })
    //       .catch(err3 => {
    //         console.error(err3)
    //       })
    //   })
    //   .catch(err => {
    //     console.error(err)
    //   })
  },
  methods: {
    likeUp() {
      this.isLike = true
      const like = {
        postId: this.postId,
        userId: this.user.id
      }
      console.log(like)
      postLike(
        like,
        (res) => {
          console.log(res)
        },
        (err) => {
          console.error(err)
        }
      )
      // axios.post(`http://localhost:8081/post/like`, like)
      //   .then(res => {
      //     console.log(res)
      //   })
      //   .catch(err => {
      //     console.error(err)
      //   })
    },
    likeDown() {
      this.isLike = false
      const like = {
        postId: this.postId,
        userId: this.user.id,
      }
      console.log(like)
      postLikeDelete(
        like,
        (res) => {
          console.log(res)
        },
        (err) => {
          console.error(err)
        }
      )
      // axios.delete(`http://localhost:8081/post/like/delete/${this.postId}/${userSid}`)
      //   .then(res => {
      //     console.log(res)
      //   })
      //   .catch(err => {
      //     console.error(err)
      //   })
    },
    commentWrite() {
      const new_comment = {
        postId: this.postId,
        userId: this.user.id,
        comment: this.newComment,
      }
      writeComment(
        new_comment,
        (res) => {
          console.log(res)
          this.comments.push(new_comment)
        },
        (err) => {
          console.error(err)
        }
      )
      // axios.post(`http://localhost:8081/comment`, new_comment)
      //   .then(res => {
      //     console.log(res)
      //     this.comments.push(new_comment)
      //   })
      //   .catch(err => {
      //     console.error(err)
      //   })
    },
    toProfile() {
      const userId = this.post.userId
      this.$router.push({ name: 'UserProfile2', params: { userId: userId }})
    }
  }
}
</script>

<style>
  .background {
    width: 375px;
    text-align: center;
    background-color: #FFFAF4;
    margin:0 auto;
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