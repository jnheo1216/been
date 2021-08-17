<template>
  <suspense>
    <template #default>
      <div class="background">
        <div class="logo">
          <img alt="BEEN LOGO" class="logo-img" src="@/assets/image-logo-resize.png">
        </div>
        <form @submit="onSignUp">
          <div class="login-input-box">
            <div class="login-checkbox">
              <label class="" for="prefer-region"><i class="el-icon-check icon-color-must"></i>당신의 선호 지역</label><br>
            </div>
            <el-input 
              id="prefer-region"
              v-model="preferedArea"
              type="text"
              class="" 
              placeholder="어디든지!" 
            ></el-input>
          </div>
          
          <div class="login-input-box">
            <div class="login-checkbox">
              <label class="" for="trip-style"><i class="el-icon-check icon-color-must"></i>당신의 여행 스타일</label><br>
            </div>
            <el-input 
              id="trip-style"
              v-model="preferedStyle"
              type="text"
              class="" 
              placeholder="어떤 유형이든" 
            ></el-input>
          </div>

          <div class="login-input-box">
            <div class="login-checkbox">
              <label class="" for="profile"><i class="el-icon-check icon-color-must"></i>프로필 소개</label><br>
            </div>
            <el-input 
              id="profile"
              v-model="user.intro"
              type="textarea"
              class="" 
              placeholder="프로필 소개" 
            ></el-input>
          </div>
          
          <div @click="onSignUp" class="login-button">
            <img style="max-height: 100%;" src="@/assets/text-logo-resize.png" alt="logo">
          </div>

        </form>
      </div>
    </template>

    <template #fallback>
      <div>Loading...</div>
    </template>
  </suspense>
</template>

<script>
import {join} from '@/api/user.js';

export default {
  name: 'SignupUserFavorite',
  data: () => {
    return {
      user:{
        nickname: "",
        email: "",
        password: "",
        intro: "",
        name: "",
      },
      email: "",
      preferedArea: "",
      preferedStyle: "",
    }
  },
  created() {
    this.user.nickname = this.$route.params.nickname
    this.user.email = this.$route.params.email
    this.email = this.$route.params.email
    this.user.password = this.$route.params.password
    this.user.intro = this.$route.params.intro
    join(
      this.user,
      (res)=>{
        // console.log('되는지좀알려줘라')
        console.log(res.data)
      },
      (err)=>{
        console.error(err);
      }
    )
  },
  methods: {
    onSignUp(){
      const info = {
        preferedArea: this.preferedArea,
        preferedStyle: this.preferedStyle,
        email: this.email
      }
      this.$router.push({
        name:"SignupSuccess",
        params:{
          ...info
        }
      })
    }
  }
}
</script>

<style>
  .icon-color-must {
    color: #31D89C;
  }
  .logo {
    padding-top: 10px;
    margin: 0 auto;
    width: 200px;
  }
  .logo > img {
    width: 100%;
  }

</style>