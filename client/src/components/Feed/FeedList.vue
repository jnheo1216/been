<template>
  <suspense>
    <template #default>
      <div class="FeedList">
        
        <!-- <div v-for="(feed, index) in feeds" :key="feed.postId">
          <div>{{ index }}</div>
          <div>{{ feed }}</div>
          <div v-if="index % 2">홀</div>
          <div v-else>짝</div>
        </div> -->

        <div class="logo">
          <img alt="BEEN LOGO" src="@/assets/image-logo.png">
        </div>

        <!-- 전체 게시물 -->
        <div class="articles flex flex-col space-y-4">

          <h1 class="text">{{ this.user.nickname }}님의 뉴스피드</h1>

          <!-- 팔로잉이 없는 경우 -->
        <div v-if="this.user.followingCnt">
          <img src="@/assets/lost-bee.png" alt="길 잃은 꿀벌" class="lost-img">
          <h2 class="text">아직 동료 꿀벌이 없습니다.</h2><br>
          <h3 class="text">꿀바르고 새로운 소식을 받아보세요.</h3>
          <el-button plain>동료 찾으러 가기</el-button>
        </div>

      <!-- 팔로잉이 있는 경우 -->
      <div v-if="this.user.followingCnt == 0">
        <!-- 육각형 한개 -->
        <div v-for="(post, index) in this.feed"
          :key="post.postId" @click="toDetail(post.postId)">


          <div v-if="index % 2 == 0" class="row justify-content-start">
            <div class="wrap">
              <div class="hex" @click="toDetail">
                <div class="hex-inner">
                  <div v-if="post.postPicSrc" class="content" style="background: url('{post.postPicSrc}')"></div>
                  <div v-else class="content" style="background: url('https://picsum.photos/200/300?grayscale)')"></div>
                </div>
              </div>

              <div class="flex flex-col logos-wrap">
                <div class="restaurant" v-if="restaurant"></div>
                <div class="hotel" v-if="hotel"></div>
                <div class="tour" v-if="tour"></div>
              </div>
            </div>
          </div>

        
        <!-- 육각형 한개 끝 -->
          <div v-if="index % 2 == 1" class="row justify-contend-end">
          <div class="wrap flex flex-row-reverse mx-0">
              <div class="hex" @click="toDetail">
                <div class="hex-inner">
                  <div class="content" style="background: url('https://picsum.photos/200/300?grayscale)')">
                  </div>
                </div>
              </div>

              <div class="flex flex-col logos-wrap">
                <div class="restaurant" v-if="restaurant"></div>
                <div class="hotel" v-if="hotel"></div>
                <div class="tour" v-if="tour"></div>
              </div>
            </div>
        </div>
        </div>

        
      </div>
      </div>
    </div>

    </template>

    <template #fallback>
      <div>Loading...</div>
    </template>
  </suspense>

</template>

<script>
import axios from 'axios'
// import {ref} from 'vue'

export default {
  name: 'FeedList',
  data () {
    return {
      Nr: 0,
      img_src: '',
      restaurant: 1,
      hotel: 1,
      tour: 1,
      user: {
        userId: '',
        profilePicSrc: '',
        nickname: '',
        followed_id: []
      },
      feed: []
    }
  },
  // setup () {
  //   const feedBody = ref('')
  // },
  created() {
    this.user = this.$store.state.user
    axios.get('http://localhost:8081/post/followPost/' + this.user.id)
      .then((res) => {
        console.log(res)
        this.feed = res.data.posts
      })
      .catch((err) => {
        console.log(err)
      })


    console.log(this.$store.state.user)
    console.log(this.$store.state.isLogin)
    axios.get('http://localhost:8081/post')
      .then(res => {
        // console.log(res)
        this.feeds = res.data.posts
      })
      .catch(err => {
        console.error(err)
      })
    
  },
  methods: {
    toDetail: function () {
      this.$router.replace({
        name: 'FeedDetail',
        params: { postId: this.feed.postId }
      })
    }
  },

}
</script>

<style>


* {
  margin: 0%;
  padding: 0%;
}

/* .articles:nth-child(2) {
  margin-top: 300px;
} */


.wrap {
  width: 140%;
  margin: 20px 10px 20px 10px;
  display: flex;
  flex-wrap: wrap;
  flex-direction: row;
  right: 10px;
}


.wrap:nth-child(2) {
  position: absolute;
  top: 20%;
  left: 30%;

}


.hex {
  width: 25%;
  margin-bottom: 1.8%;
  position: relative;
  visibility: hidden;
  border: 2px solid red;
  box-shadow: 5px 5px 5px 5px;
  vertical-align: middle;
}

.hex:hover {
  bottom: 3px;
}

.hex:nth-of-type(7n+5) {
  margin: 12.5%;
}

.hex::after {
  content: '';
  display: block;
  padding-bottom: 80%;
}

.hex-inner {
  position: absolute;
  width: 99%;
  padding-bottom: 114.6%;
  overflow: hidden;
  visibility: hidden;
  transform: rotate3d(0,0,1,-60deg) skewY(30deg);
  /* border: 2px solid red; */
}

.hex-inner * {
  position: absolute;
  visibility: visible; 
}

.content {
  position:absolute;
  display: flex;
  width: 100%;
  height: 100%;
  overflow: hidden;
  transform: skewY(-30deg) rotate3d(0,0,1,60deg);
  justify-content: center;
  align-items: center;
  background-repeat : no-repeat;
  background-size : cover;
}

.content2 {
  /* border: 2px solid red; */
  position:absolute;
  display: flex;
  width: 100%;
  height: 100%;
  overflow: hidden;
  transform: skewY(-30deg) rotate3d(0,0,1,60deg);
  background-image: url(https://picsum.photos/200/301?grayscale);
  justify-content: center;
  align-items: center;
  background-repeat : no-repeat;
  background-size : cover;
}


.content p {
  font-size: 3vw;
  text-align: center;
  color: #fff;
}

.logos-wrap {
  margin-left: 0px;
  margin-right: 10px;
  display: flex column;
  justify-content: space-evenly;
  align-items: stretch;
  vertical-align: middle;
  padding: 0;
}

.restaurant {
  margin-left: 20px;
  margin-top: 45px;
  margin-bottom: 5px;
  width: 10px;
  height: 10px;
  border-radius: 5px;
  background-color: green;
}

.hotel {
  margin-left: 20px;
  margin-top: 5px;
  margin-bottom: 5px;
  width: 10px;
  height: 10px;
  border-radius: 5px;
  background-color: yellowgreen;
}

.tour {
  margin-left: 20px;
  margin-top: 5px;
  margin-bottom: 5px;
  width: 10px;
  height: 10px;
  border-radius: 5px;
  background-color: coral;
}

.FeedList {
  position: relative;
  width: 375px;
  height: 812px;
  background-color: #fffaf4;
  margin: 0 auto;
  overflow-x: hidden;
  overflow-y: auto;
}

.logo {
  padding-top: 10px;
  margin: 0 auto;
  width: 200px;
}
.logo > img {
  width: 100%;
}

.text {
  font-family: 'Nanum Pen Script', cursive;
}

.lost-img {
  width: 150px;
  padding: 0;
  margin-left: auto;
  margin-right: auto;
}


</style>