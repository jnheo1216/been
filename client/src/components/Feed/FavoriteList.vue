<template>
  <div class="favoriteList">
    <div class="logo">
      <img alt="BEEN LOGO" src="@/assets/image-logo.png">
    </div>

    <h1 class="text">{{ this.user.nickname }}님을 위한 맞춤 페이지</h1>

    <div class="curation">
      
      <!-- 추천 게시물이 3건 이하인 경우 -->
      <div v-if="favorite.length >= 3">
        <h2 class="text">{{ favorite.length }}건의 추천 게시물이 있습니다.</h2>
        <h3 class="text">{{ this.user.nickname }}님이 좋아하는 여행 스타일과 여행지역을 추가하시면 더 많은 추천 게시물을 보실 수 있습니다.</h3>

        <div v-for="(post, index) in this.favorite"
          :key="post.postId" @click="toDetail(post.postId)" class="my-4">
          <h1>{{ post.postId }}</h1>
          <!-- 첫번째 육각형 -->
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
            </div>
          </div>
          <!-- 육각형 끝 -->
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
            </div>
        </div>
        </div>
        <el-button plain @click="toSearch" class="my-4">더 많은 게시물 검색하기</el-button>
      </div>

      <!-- 추천 게시물이 3건 이상인 경우 -->
      <div v-else>
        <div v-for="(post) in this.favorite"
            :key="post.postId" class="my-4">
          
          <div class="grid grid-cols-12">
              <div class="wrap block">

                <div class="hex" @click="toDetail">
                  <div class="hex-inner">
                    <div class="content" style="background: #F4DBDB">
                      <h2>{{ post.title }}</h2>
                    </div>
                  </div>
                </div>
                
                <div class="hex" @click="toDetail">
                  <div class="hex-inner">
                    <div v-if="post.postPicSrc" class="content">
                      <img :src="post.postPicSrc" class="hex-image">
                    </div>
                    <div v-else class="content" style="background: url('https://picsum.photos/200/300?grayscale)')"></div>
                  </div>
                </div>

                <div class="hex" @click="toDetail">
                  <div class="hex-inner">
                    <div class="content" style="background: #DDD9D9">
                      <h2>{{ post.region }}</h2>
                    </div>
                  </div>
                </div>
              </div>
            </div> 
        </div>
      </div>
      

      <!-- 추천 게시물이 3건 이상인 경우 -->
      <!-- <div v-else>
        <div v-for="(post) in this.favorite"
          :key="post.postId">
          <div class="grid grid-col-12">
            
          </div>
        </div>
        
        <div class="grid grid-cols-12">
          <div class="wrap block">
            <div class="hex" @click="toDetail">
              <div class="hex-inner">
                <div v-if="favorite.postPicSrc" class="content" style="background: url('{{ favorite.postPicSrc }}')"></div>
                <div v-else class="content" style="background: url('https://picsum.photos/200/300?grayscale)')"></div>
              </div>
            </div>

            <div class="hex" @click="toDetail">
              <div class="hex-inner">
                <div class="content" style="background: url('https://picsum.photos/200/301?grayscale)')">
                </div>
              </div>
            </div>

            <div class="hex" @click="toDetail">
              <div class="hex-inner">
                <div class="content" style="background: url('https://picsum.photos/200/302?grayscale)')">
                </div>
              </div>
            </div>
          </div>
        </div>  

        <div class="grid grid-cols-12">
          <div class="wrap block">
            <div class="hex" @click="toDetail">
              <div class="hex-inner">
                <div class="content" style="background: url('https://picsum.photos/200/303?grayscale)')">
                </div>
              </div>
            </div>

            <div class="hex" @click="toDetail">
              <div class="hex-inner">
                <div class="content" style="background: url('https://picsum.photos/200/304?grayscale)')">
                </div>
              </div>
            </div>

            <div class="hex" @click="toDetail">
              <div class="hex-inner">
                <div class="content" style="background: url('https://picsum.photos/200/305?grayscale)')">
                </div>
              </div>
            </div>
          </div>
        </div>

        <div class="grid grid-cols-12">
          <div class="wrap block">
            <div class="hex" @click="toDetail">
              <div class="hex-inner">
                <div class="content" style="background: url('https://picsum.photos/201/303?grayscale)')">
                </div>
              </div>
            </div>

            <div class="hex" @click="toDetail">
              <div class="hex-inner">
                <div class="content" style="background: url('https://picsum.photos/202/304?grayscale)')">
                </div>
              </div>
            </div>

            <div class="hex" @click="toDetail">
              <div class="hex-inner">
                <div class="content" style="background: url('https://picsum.photos/203/305?grayscale)')">
                </div>
              </div>
            </div>
          </div>
        </div>
      </div> -->


    </div>

  </div>
</template>

<script>
import axios from 'axios'
export default {
  name: 'FavoriteList',
  data() {
    return {
      user: {},
      favorite: []
    }
  },
  created() {
    this.user = this.$store.state.user
    // if (!this.isLogin) {
    //   this.$router.push({ name: "Introduction" })
    // }
    axios.get('http://localhost:8081/post/preferedStyle/' + this.user.id)
      .then((res) => {
        this.favorite.push(res.data.posts)
      })
      .catch((err) => {
        console.log(err)
      }),
    axios.get('http://localhost:8081/post/preferedArea/' + this.user.id)
      .then((res) => {
        this.favorite.push(res.data.posts)
        console.log(this.favorite)
        if (this.favorite.length >= 3 && !this.favorite.length % 3 == 0) {
          const len = this.favorite.length
          if (len % 3 == 1) {
            this.favorite = this.favorite.slice(0, len-1)
          }
          if (len % 3 == 2) {
            this.favorite = this.favorite.slice(0, len-2)
          }
        }  
      })
      .catch((err) => {
        console.log(err)
      })
  },
  methods: {
    toSearch: function () {
      this.$router.push({ name: "SearchMain" })
    },
    toDetail: function (postId) {
      this.$router.replace({ 
        name: "FeedDetail", 
        params: { feedNumber: postId }
        })
    }
  }
}
</script>

<style>
  .favoriteList {
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

  .wrap {
  width: 110%;
  margin: 10px 0px 40px 30px;
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

.hex-image {
  width: 140px;
}
</style>