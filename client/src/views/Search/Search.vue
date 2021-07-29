<template>
  <div class="Search">
    <div class="logo">
      <img alt="BEEN LOGO" src="@/assets/image-logo.png">
    </div>

    <div class="friend-search">
      <el-input @keydown="keyDownRedirect" placeholder="꿀벌 찾기" v-model="input"></el-input>
    </div>

    <div class="type-search">
      <el-input placeholder="여행 정보 찾기" v-model="input2"></el-input>
    </div>

    <TypeSearch />
  </div>
</template>

<script>
import TypeSearch from '@/components/Search/TypeSearch.vue'
import { ref } from 'vue'



export default {
  name: 'Search',
  data() {
    return {
      input: ''
    }
  },
  components: {
    TypeSearch,
  },
  methods: {
    toTypeSearch: function () {
      
    },
    keyDownRedirect: function () {
      window.open('https://google.com')
    }
  },
    setup() {
      return {
        // input: ref(''),
        input2: ref('')
      }
    },
    onClickRedirect: function () {
      console.log('안녕')
      window.open('https://google.com')
    },
  watch: {

  }
}
</script>

<style>
  .logo {
    display: block;
  }
  .Search {
    width: 375px;
    height: 812px;
    background-color: #fffaf4;
    margin: 0 auto;
  }

  .friend-search {
    margin: 50px;
  }

  .type-search {
    margin: 50px;
  }
</style>
