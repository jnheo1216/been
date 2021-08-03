<template>
  <div class="PeopleSearch">
     <div class="logo">
      <img alt="BEEN LOGO" class="logo-img" src="@/assets/image-logo.png">
    </div>

    <h1 class="text">꿀벌찾기</h1>

    <form @submit="searchSubmit" class="peoplesearchinput">
      <el-input
        input="onInput" 
        v-model="search"
        placeholder="꿀벌 찾기"
        type="text">
      </el-input>
    </form>

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
            <img :src="user.profilePicSrc" alt=""><p>{{ user.nickname }}</p>
            <p>{{ user.intro }}</p>
            </li>
        </ul>
      </div>  
      <!-- 일치하는 검색 결과가 없을 때 -->
      <div class="peoplenotfound" v-if="users.length==0">
        <img src="@/assets/lost-bee.png" class="lost-img" alt="lost-bee" v-if="users.length == 0">
        <p>찾는 꿀벌이 없습니다</p>
        <el-button @click="backToSearchmain" type="info" icon="el-icon-back" circle></el-button>
      </div>
    </div>

  </div>
</template>

<script>

export default ({
  name: 'PeopleSearch',
  data () {
    return {
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
    find: function () {
      
    },
    onInput: function(event) {
      this.search = event.target.dataset.search
      this.isSubmit = false
      if (this.search.length === 0 ) this.isShow = false
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
