<template>
  <div class="ArticleSearch">
    <div class="logo">
      <img alt="BEEN LOGO" class="logo-img" src="@/assets/image-logo.png">
    </div>

    <h1 class="text">게시물 찾기</h1>

     <el-form :inline="true" class="peoplesearchinput">
      <el-form-item>
        <el-input
          input="onInput" 
          v-model="search"
          placeholder="게시물 찾기"
          type="text">
        </el-input>
      </el-form-item>
      <el-form-item>
        <el-button @click="searchSubmit" icon="el-icon-search" circle></el-button>
      </el-form-item>
    </el-form>

    <div class="searchResult">
      <!-- 검색하기 전에 -->
      <div v-if="isSubmit == false">
        <img src="@/assets/bee-search.png" alt="" class="search-img">
        <h1 class="text">게시물 제목을 입력해서 게시물을 찾아보세요</h1>
      </div>

      <!-- 일치하는 검색 결과가 있을 때 -->
      <div class="found">
         <div v-for="(post, index) in this.results"
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
            </div>
        </div>
        </div>
      </div>

      <!-- 일치하는 검색 결과가 없을 때 -->

    </div>

    <el-button @click="backToSearchmain" type="info" icon="el-icon-back" circle></el-button>

  </div>
  
</template>

<script>
import axios from 'axios'
export default {
  name: "ArticleSearch",
  data () {
    return {
      search: '',
      results: [],
      isSubmit: false
    }
  },
  methods: {
    searchSubmit: function() {
      axios.get('http://localhost:8081/post/searchByTitle/' + this.search)
      .then((res) => {
        this.results.push(res.data.posts)
        this.isSubmit = true
        console.log(this.results)
      })
      .catch((err) => {
        console.log(err)
      })
    },
    toDetail: function(postId) {
      this.$router.push({
        name: "FeedDetail",
        params: { feedNumber : postId }
      })
    },
    backToSearchmain: function () {
      this.$router.push({ name: "SearchMain" })
    }
  }
}
</script>

<style>
  .ArticleSearch {
    position: relative;
    width: 375px;
    height: 812px;
    background-color: #fffaf4;
    margin: 0 auto;
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

  .lost-img {
    width: 150px;
    padding: 0;
  }

  .articlesearchinput {
    margin: 50px;
  }

  .text {
    font-family: 'Nanum Pen Script', cursive;
  }

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
  font-size: 3vw;
  text-align: center;
  color: #fff;
}

.hex-image {
  width: 140px;
}

 .search-img {
    width: 200px;
    padding: 0;
    margin-left: auto;
    margin-right: auto;
  }
</style>