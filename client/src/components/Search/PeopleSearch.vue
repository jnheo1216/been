<template>
  <div class="PeopleSearch">
     <div class="logo">
      <img alt="BEEN LOGO" class="logo-img" src="@/assets/image-logo-resize.png">
    </div>

    <h1 class="text">꿀벌찾기</h1>

    <el-form :inline="true" class="peoplesearchinput">
      <el-form-item>
        <el-input
          input="onInput" 
          v-model="user"
          placeholder="꿀벌 찾기"
          type="text">
        </el-input>
      </el-form-item>
      <el-form-item>
        <el-button @click="searchSubmit" icon="el-icon-search" circle></el-button>
      </el-form-item>
    </el-form>

    <!-- 검색 결과 -->
    <div class="searchResult">
      <!-- 일치하는 검색 결과가 있을 때  -->
      <div class="peoplefound" v-if="users.length">
        <p v-if="isShow">
          일치하는 꿀벌 {{ users.length }}벌
        </p>
        <ul>
          <li @click="toUserProfile" v-for="(user, userId) in users"
            :key="userId">
            <img :src="user.profilePicSrc" alt="" class="rounded-full"><p class="font-bold">{{ user.nickname }}</p>
            <p>{{ user.intro }}</p>
            </li>
        </ul>
      </div>  
      <!-- 일치하는 검색 결과가 없을 때 -->
      <div class="peoplenotfound" v-if="users.length==0">
          <el-alert
            title="다시 찾아보세요"
            type="error"
            description="  찾는 꿀벌이 없습니다"
            show-icon>
          </el-alert>
        <img src="@/assets/lost-bee.png" class="lost-img" alt="lost-bee" v-if="users.length == 0">
        <p>찾는 꿀벌이 없습니다</p>
        <el-button @click="backToSearchmain" type="info" icon="el-icon-back" circle></el-button>
      </div>
    </div>

  </div>
</template>

<script>
import axios from 'axios'

export default ({
  name: 'PeopleSearch',
  data () {
    return {
      user: '',
      users : [],
      search: '',
      isSubmit: false,
      isShow: false
    }
  },
  computed: {

  },
  methods: {
    toUserProfile: function(event) {
      const user = this.users[event.target.dataset.pk]
      const userId = user.userId
      this.$router.replace({
        name: "UserProfile",
        params: { userId: userId }
      })
    },
    getUser: function () {
      axios.get('http://localhost:8080/user/findNickname/{user}')
      .then(res => {
        res
      })
      
    },
    onInput: function(event) {
      this.search = event.target.dataset.search
      this.isSubmit = false
      if (this.search.length === 0) this.isShow = false
      else this.isShow = true
    },
    // searchSubmit : function (event) {

    // }
    backToSearchmain: function () {
      this.$router.push({ name: "SearchMain" })
    }
  }
})
</script>


<style scoped>
  .PeopleSearch {
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

  .peoplesearchinput {
    margin: 50px;
  }
  
  .lost-img {
    width: 150px;
    padding: 0;
  }

  .text {
    font-family: 'Nanum Pen Script', cursive;
  }
</style>
