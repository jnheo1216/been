<template>
  <div class="favoriteList">
    <div class="logo">
      <img alt="BEEN LOGO" src="@/assets/image-logo.png">
    </div>

    <h1 class="text">{{ this.user.nickname }}님을 위한 맞춤 페이지</h1>

    <div class="curation">
      
      <h2 class="text">{{ favorite.length }}건의 추천 게시물이 있습니다.</h2>
      <h3 class="text">{{ this.user.nickname }}님이 좋아하는 여행 스타일과 여행지역을 추가하시면 더 많은 추천 게시물을 보실 수 있습니다.</h3>


      <div v-if="this.favorite.length < 20">
        <div v-for="(post, $index) in this.favorite"
          :key="$index" class="my-4">
          
          <div class="grid grid-cols-12">
              <div class="wrap block">

                <div class="hex2" @click="toDetail">
                  <div class="hex-inner">
                    <div class="content" style="background: #F4DBDB">
                      <h2>{{ post.title }}</h2>
                    </div>
                  </div>
                </div>
                
                <div class="hex" @click="this.$router.push(`/feed/${post.postId}`)">
                  <div class="hex-inner">
                    <div v-if="post.postPicSrc" class="content">
                      <img :src="post.postPicSrc" class="hex-image">
                    </div>
                    <div v-else class="content" style="background: url('https://picsum.photos/200/300?grayscale)')"></div>
                  </div>
                </div>

                <div class="hex2" @click="toDetail">
                  <div class="hex-inner">
                    <div class="content" style="background: #DDD9D9">
                      <h2>{{ post.area }}</h2>
                    </div>
                  </div>
                </div>

              </div>
            </div> 
        </div>
      </div>
      <!-- 추천 게시물이 20건 이상인 경우 -->
      <div v-else>
      <header>
      </header>
      <template>
      <div v-for="(post, $index) in this.favorite"
          :key="$index" class="my-4">
        
        <div class="grid grid-cols-12">
            <div class="wrap block">

              <div class="hex" @click="toDetail">
                <div class="hex-inner">
                  <div class="content" style="background: #F4DBDB">
                    {{ post.title }}
                  </div>
                </div>
              </div>
              
              <div class="hex" @click="this.$router.push(`/feed/${post.postId}`)">
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
                    {{ post.area }}
                  </div>
                </div>
              </div>

            </div>
          </div> 
      </div>
      </template>

      <infinite-loading @infinite="infiniteHandler"></infinite-loading>
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
import {API_BASE_URL} from "@/config/index.js"
import InfiniteLoading from 'vue-infinite-loading'

export default {
  name: 'FavoriteList',
  data() {
    return {
      user: {},
      favorite: [],
      page: 1,
    }
  },
  components: {
    InfiniteLoading,
  },
  created() {
    this.user = this.$store.state.user
    // if (!this.isLogin) {
    //   this.$router.push({ name: "Introduction" })
    // }
    axios.get(API_BASE_URL + 'post/preferedStyle/' + this.user.id + `/${this.page}`)
      .then((res) => {
        // this.favorite.push(res.data.posts)
        Array.prototype.push.apply(this.favorite, res.data.posts)
      })
      .catch((err) => {
        console.log(err)
      }),
    axios.get(API_BASE_URL + 'post/preferedArea/' + this.user.id + `/${this.page}`)
      .then((res) => {
        // this.favorite.push(res.data.posts)
        Array.prototype.push.apply(this.favorite, res.data.posts)
        console.log(this.favorite)
        this.page += 1
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
    },
    infiniteHandler($state) {
      axios.get(API_BASE_URL + 'post/preferedArea/' + this.user.id + `/${this.page}`)
        .then((res) => {
          if (res.data.posts.length) {
            Array.prototype.push.apply(this.favorite, res.data.posts)
            console.log(this.favorite)
            this.page += 1
            $state.loaded();
          } else {$state.complete()}
        })
        .catch((err) => {
          console.error(err)
        })
    }
  }
}
</script>

<style>
  .favoriteList {
    position: relative;
    width: 375px;
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

.hex2 {
  width: 15%;
  margin-bottom: 1.8%;
  position: relative;
  visibility: hidden;
  border: 2px solid red;
  box-shadow: 5px 5px 5px 5px;
  vertical-align: middle;
}

.hex2:hover {
  bottom: 3px;
}

.hex2:nth-of-type(7n+5) {
  margin: 12.5%;
}

.hex2::after {
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