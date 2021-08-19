<template>
  <suspense>
    <template #default>
      <div class="FeedList">

        <div class="logo">
          <img alt="BEEN LOGO" src="@/assets/image-logo.png">
        </div>

        <!-- 전체 게시물 -->
        <div class="articles flex flex-col space-y-2">

          <h1 class="text bg-white">{{ this.user.nickname }}님의 뉴스피드</h1>
          <!-- 추천 게시물 -->
          <h2 class="text">맞춤 추천게시물</h2>
          <el-carousel :interval="4000" type="card" height="200px">
            <el-carousel-item v-for="post in this.recommended" :key="post.postId">
              <img @click="toDetail(post.postId)" :src="post.postPicSrc" class="recommended-image medium" alt="">
            </el-carousel-item>
          </el-carousel>
          <!-- <el-button plain @click="toFavorite">추천 게시물 더 보기</el-button> -->

  
      <div class="map-box">
        <div>
          <MyMap :visitedDatas="usersArea"/>
        </div>
        <div>
          <button style="font-size:150%; padding: 10px" type="button" class="btn btn-outline-warning" @click="this.$router.push(`/mymapadd`)">흔적남기기!</button>
        </div>
      </div>

      <div class="post-button">
        <el-button type="warning" icon="el-icon-edit" circle @click="this.$router.push(`/write`)"></el-button>
      </div>

          <!-- 팔로잉이 없는 경우 -->
        <div v-if="this.user.followingCnt == 0">
          <img src="@/assets/lost-bee.png" alt="길 잃은 꿀벌" class="lost-img">
          <h2 class="text">아직 동료 꿀벌이 없습니다.</h2><br>
          <h3 class="text">꿀바르고 새로운 소식을 받아보세요.</h3>
          <el-button plain>동료 찾으러 가기</el-button>
        </div>

      <!-- 팔로잉이 있는 경우 -->
      <div v-if="this.user.followingCnt" class="my-4">
      <h2 class="text">{{ this.user.nickname }}님의 동료 꿀벌들의 소식</h2>
        <!-- 육각형 한개 -->
        <div v-for="(post, index) in this.feed"
          :key="post.postId" @click="toDetail(post.postId)">


          <div v-if="index % 2 == 0" class="row justify-content-start mx-0">
            <div class="wrap">
              <div class="hex" @click="toDetail">
                <div class="hex-inner">
                  <div v-if="post.postPicSrc" class="content">
                    <img :src="post.postPicSrc" class="hex-image">
                  </div>
                  <div v-else class="content" style="background: url('https://picsum.photos/200/300?grayscale)')">
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

        
        <!-- 육각형 한개 끝 -->
          <div v-if="index % 2 == 1" class="row justify-contend-end mx-0">
          <div class="wrap flex flex-row-reverse mx-0">
              <div class="hex" @click="toDetail">
                <div class="hex-inner">
                  <div v-if="post.postPicSrc" class="content">
                    <img :src="post.postPicSrc" class="hex-image">
                  </div>
                  <div v-else class="content" style="background: url('https://picsum.photos/200/301?grayscale)')">
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
import MyMap from '@/components/MyMaps/MyMap.vue'
import axios from 'axios'
// import {ref} from 'vue'
import {getFeedFollowPost} from '@/api/feed.js'
import {API_BASE_URL} from "@/config/index.js"
import {getUsersArea} from '@/api/user.js'

export default {
  name: 'FeedList',
  data () {
    return {
      restaurant: 1,
      hotel: 1,
      tour: 1,
      user: {},
      feed: [],
      recommended: [],
      usersArea: []
    }
  },
  components: {
    MyMap
  },
  created() {
    // if (!this.isLogin) {
    //   this.$router.push({ name: 'Introduction' })}
    this.user = this.$store.state.user
    console.log(this.user)
    axios.get(API_BASE_URL + 'post/preferedStyle/' + this.user.id + '/1')
      .then((res) => {
        this.recommended = res.data.posts
        if (this.recommended.length >= 4) {
          this.recommended = this.recommended.slice(0, 2)
        }
        console.log(this.recommended)
      })
    getUsersArea(
      this.user.id,
      (res3) => {
        // console.log(res3.data.areas)
        const areas = res3.data.areas
        for (var i in areas) {
          // console.log([Number(areas[i]['latitude']), Number(areas[i]['longitude'])])
          this.usersArea.push([Number(areas[i]['latitude']), Number(areas[i]['longitude'])])
        }
        console.log(this.usersArea)
        console.log(this.usersArea.length)
      },
      (err3) => {console.error(err3)}
    )
    getFeedFollowPost(
      this.user.id,
      (res2) => {this.feed = res2.data.posts},
      (err2) => {console.error(err2)}
    )
  },
  methods: {
    toDetail: function (postId) {
      this.$router.replace({
        name: 'FeedDetail',
        params: { feedNumber: postId }
      })
    },
    toFavorite: function () {
      this.$router.push({
        name: "FavoriteList"
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
  margin: 10px 10px 10px 10px;
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
  width: 30%;
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


.content p {
  font-size: 2vw;
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

.hex-image {
  width: 140px;
}


.el-carousel__item h3 {
  color: #475669;
  font-size: 14px;
  opacity: 0.75;
  line-height: 200px;
  margin: 0;
  }

.recommended-image {
  width: 200px;
  margin-left: auto;
  margin-right: auto;
}

.el-carousel__item:nth-child(2n) {
  background-color: #99a9bf;
}

.el-carousel__item:nth-child(2n+1) {
  background-color: #d3dce6;
}
.map-box {
  width: 320px;
  margin: 0 auto;
}
.post-button {
  position: fixed;
  bottom: 60px;
  left: 0;
  right: 0;
}
</style>