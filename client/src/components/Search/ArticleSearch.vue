<template>
  <div class="ArticleSearch">
    <div class="logo">
      <img alt="BEEN LOGO" class="logo-img" src="@/assets/image-logo.png">
    </div>

    <h1 class="text">게시물 찾기</h1>

     <form @submit="searchSubmit" class="articlesearchinput">
      <el-input
        input="onInput" 
        v-model="search"
        placeholder="게시물 찾기"
        type="text">
      </el-input>
    </form>

  </div>
  
</template>

<script>
export default {
  name: "ArticleSearch",
  data () {
    return {
      
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
  }

  .logo {
    margin: 10px;
    padding: 0;
  }
  .logo-img {
    width: 100px;
    padding: 0;
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
</style>