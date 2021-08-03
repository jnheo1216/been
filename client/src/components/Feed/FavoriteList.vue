<template>
  <div class="favoriteList">
    <div class="logo">
      <img alt="BEEN LOGO" src="@/assets/image-logo.png">
    </div>

    <h1 class="text">{{ user }}님을 위한 맞춤 페이지</h1>

    <div class="curation">

      <div class="wrap">
        <div class="hex" @click="toDetail">
          <div class="hex-inner">
            <div class="content" style="background: url('https://picsum.photos/200/300?grayscale)')">
            </div>
          </div>
        </div>
      </div>
      <div class="wrap">
        <div class="hex" @click="toDetail">
          <div class="hex-inner">
            <div class="content" style="background: url('https://picsum.photos/200/300?grayscale)')">
            </div>
          </div>
        </div>
      </div>
      <div class="wrap">
        <div class="hex" @click="toDetail">
          <div class="hex-inner">
            <div class="content" style="background: url('https://picsum.photos/200/300?grayscale)')">
            </div>
          </div>
        </div>
      </div>

    </div>

  </div>
</template>

<script>

export default {
  name: 'FavoriteList',
  data() {
    return {

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
  }

  .logo {
    margin: 10px;
    padding: 0;
  }
  img {
    width: 100px;
    padding: 0;
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