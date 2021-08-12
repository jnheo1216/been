<template>
  <div class="favoriteList">
    <div class="logo">
      <img alt="BEEN LOGO" src="@/assets/image-logo.png">
    </div>

    <h1 class="text">{{ this.user.nickname }}님을 위한 맞춤 페이지</h1>

    <div class="curation">

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
    axios.get('http://localhost:8081/post/preferedStyle/' + this.user.id)
      .then((res) => {
        this.favorite.push(res.data.posts)
      })
      .catch((err) => {
        console.log(err)
      }),
    axios.get('http://localhost:8081/post/preferdArea/' + this.user.id)
      .then((res2) => {
        console.log('선호 지역')
        console.log(res2.data.posts)
      })
      .catch((err2) => {
        console.log(err2)
      })
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
</style>