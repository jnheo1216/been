<template>

  <div class="FeedList">
    <div class="logo">
      <img alt="BEEN LOGO" src="@/assets/image-logo.png">
    </div>

    <!-- 전체 게시물 -->
    <div class="articles space-y-2">

      <!-- 육각형 한개 -->
      <!-- v-for="post in feed" :key="post.postId" @click="toDetail(post.postId)" -->
      <div class="wrap block">
        <div class="hex" @click="toDetail">
          <div class="hex-inner">
            <div class="content" style="background: url('https://picsum.photos/200/300?grayscale)')">
            </div>
          </div>
        </div>

        <div class="logos-wrap">
          <div class="restaurant" v-if="restaurant"></div>
          <div class="hotel" v-if="hotel"></div>
          <div class="tour" v-if="tour"></div>
        </div>
      </div>
    <!-- 육각형 한개 끝 -->

      <div class="wrap2 block">
        <div class="logos-wrap">
          <div class="restaurant" v-if="restaurant"></div>
          <div class="hotel" v-if="hotel"></div>
          <div class="tour" v-if="tour"></div>
        </div>

        <div class="hex" @click="toDetail">
          <div class="hex-inner">
            <div class="content" style="background: url('https://picsum.photos/200/301?grayscale)')">
            </div>
          </div>
        </div>
      </div>

      <div class="wrap block">
        <div class="hex" @click="toDetail">
          <div class="hex-inner">
            <div class="content" style="background: url('https://picsum.photos/200/300?grayscale)')">
            </div>
          </div>
        </div>

        <div class="logos-wrap">
          <div class="restaurant" v-if="restaurant"></div>
          <div class="hotel" v-if="hotel"></div>
          <div class="tour" v-if="tour"></div>
        </div>
      </div>

      <div class="wrap2 block">
        <div class="logos-wrap">
          <div class="restaurant" v-if="restaurant"></div>
          <div class="hotel" v-if="hotel"></div>
          <div class="tour" v-if="tour"></div>
        </div>

        <div class="hex" @click="toDetail">
          <div class="hex-inner">
            <div class="content" style="background: url('https://picsum.photos/200/301?grayscale)')">
            </div>
          </div>
        </div>
      </div>

    </div>

  </div>

  <el-button @click="drawer = true" type="info" icon="el-icon-search" style="margin-left: 250px;" circle>
  </el-button>
  <el-drawer
    title="BEEN 서비스"
    v-model="drawer"
    :direction="direction"
    :before-close="handleClose">
    <span>BEEN 사용법</span>
  </el-drawer>
  

</template>

<script>

export default {
  name: 'FeedList2',
  data () {
    return {
      drawer: false,
      direction: 'btt',

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
  methods: {
    toDetail: function () {
      this.$router.replace({
        name: 'FeedDetail',
        params: {}
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
  width: 110%;
  margin: 60px 20px 40px 20px;
  display: flex;
  flex-wrap: wrap;
  flex-direction: row;
  right: 10px;
}

.wrap2 {
  width: 110%;
  margin: 80px 0px 10px 100px;
  display: flex;
  flex-wrap: wrap;
  flex-direction: row;
}

.wrap:nth-child(2) {
  position: absolute;
  top: 20%;
  left: 30%;
}

.wrap2:nth-child(2) {
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
}

.logo {
  margin: 10px;
  padding: 0;

}

img {
  width: 100px;
  padding: 0;
}

.el-button {
  position: absolute;
  bottom: 25px;
  right: 50px;
}

</style>