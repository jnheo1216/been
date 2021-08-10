<template>
  <div class="Search">
    <div class="logo">
      <img alt="BEEN LOGO" src="@/assets/image-logo.png">
    </div>
    <h1 class="text">찾기</h1>
    <div class="friend-search">
      <el-input @keydown="keyDowntoPeopleSearch" placeholder="꿀벌 찾기" v-model="people_input"></el-input>
    </div>

    <div class="type-search">
      <el-input @click="clicktoTypeSearch" placeholder="여행 정보 찾기" suffix-icon="el-icon-arrow-down" v-model="type_input"></el-input>
    </div>

    <div class="article-search">
      <el-input @keydown="keyDowntoArticleSearch" placeholder="게시물 찾기" suffix-icon="el-icon-search" v-model="article_input"></el-input>
    </div>

    <!--  물음표  -->
    <p id="show-modal" @click="showModal = true" class="question el-icon-question"></p>
    <SearchModal v-if="showModal" @close="showModal = false">   </SearchModal>
 


  <el-button @click="backToFeed" type="info" icon="el-icon-back" circle>
  </el-button>


  </div>
</template>

<script>
import { ref } from 'vue'
import SearchModal from "./SearchModal.vue"

export default {
  name: 'SearchMain',
  data() {
    return {
      people_input: '',
      article_input: '',
      showModal: false
    }
  },
  components: {
    SearchModal,
  },
  methods: {
    keyDowntoPeopleSearch: function () {
    this.$router.push({
          name:"PeopleSearch",
        })
    },
    clicktoTypeSearch: function () {
      this.$router.push({
        name: "TypeSearch"
      })
    },
    keyDowntoArticleSearch: function () {
      this.$router.push({
        name: "ArticleSearch"
      })
    },
    backtoFeed: function () {
      this.$router.push({ name: "Home" })
    }
  },

    setup() {
      return {
        // input: ref(''),
        type_input: ref('')
      }
    },
  watch: {

  }
}
</script>

<style>
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
.Search {
  width: 375px;
  height: 812px;
  background-color: #fffaf4;
  position: relative;
  margin: 0 auto;
}

.text {
  font-family: 'Nanum Pen Script', cursive;
}

  .friend-search {
    margin: 50px;
  }

  .type-search {
    margin: 50px;
  }

  .article-search {
    margin: 50px;
  }

  .el-button {
    position: absolute;
    bottom: 25px;
    right: 50px;
  }

  button {
    color: red;
  }

.question {
  margin-left: 300px;
  display: block;
  margin-right: 10px;
  margin-bottom: 30px;
  }

  #show-modal {
  font-size: 22px;
  margin-right: 20px;
  }
</style>
