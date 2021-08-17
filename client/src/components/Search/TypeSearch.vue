<template>
  <div class="TypeSearch">
    <div class="logo">
      <img alt="BEEN LOGO" src="@/assets/image-logo.png">
    </div>

    <h1 class="text">여행 유형으로 검색하기</h1>
    <!-- <div class="block">
    <el-cascader
      :options="options"
      :props="props"
      clearable></el-cascader>
    </div> -->

    <div class="container">
      <div class="searchbar">
        <el-input
          input="onInput" 
          v-model="style"
          placeholder="스타일 서치"
          type="text"></el-input>
        <el-button @click="searchSubmitStyle" icon="el-icon-search" circle></el-button>
      </div>

      <div class="search">
        <el-input
          input="onInput" 
          v-model="area"
          placeholder="지역 서치"
          type="text"></el-input>
        <el-button @click="searchSubmitArea" icon="el-icon-search" circle></el-button>
      </div>

    </div>



    </div>


    <!-- 검색 결과 -->
    <div class="searched">
      <!-- 검색결과 있는 경우 -->

      <!-- 검색결과 없는 경우 -->
    </div>
  <!-- </div> -->
    <el-button @click="backToSearchmain" type="info" icon="el-icon-back" circle></el-button>
</template>

<script>

import {searchUserNickname} from '@/api/search.js'
export default {
  name: 'TypeSearch',
  data() {
    return {
      style: '',
      area: '',
      searchedStyles: [],
      searchedAreas: [],
    }
  },
  methods: {
    backToSearchmain: function () {
      this.$router.push({ name: "SearchMain" })
    },    
   searchSubmitStyle: function () {
     const style = this.style
     searchUserNickname(
       style,
       (res) => {
         this.users = res.data.users
       },
       (err) => {
         console.log(err)
       }
     )
   },
  }
}
</script>

<style>
  .TypeSearch {
    width: 375px;
    height: 812px;
    background-color: #fffaf4;
    margin: 0 auto;
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

  .block {
    justify-content: left;
    margin-top: 20px;
  }

  .searchbar {
    margin-bottom: 30px;
  }
</style>