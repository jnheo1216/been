<template>
  <suspense>
    <template #default>
      <div class="background">
        <img src="@/assets/image-logo.png" alt="image-logo">
        <form @submit="onSignUp">
          <div class="login-input-box">
            <div class="login-checkbox">
              <label class="" for="prefer-region"><i class="el-icon-check icon-color-must"></i>당신의 선호 지역</label><br>
            </div>
            <el-cascader
              id="prefer-region"
              :options="options_region"
              :props="props_region"
              clearable></el-cascader>
          </div>
          
          <div class="login-input-box">
            <div class="login-checkbox">
              <label class="" for="trip-style"><i class="el-icon-check icon-color-must"></i>당신의 여행 스타일</label><br>
            </div>
            <el-cascader
              id="trip-style"
              :options="options_style"
              :props="props_style"
              clearable></el-cascader>
          </div>

          <div class="login-input-box">
            <div class="login-checkbox">
              <label class="" for="wish-region"><i class="el-icon-check icon-color-must"></i>당신이 원하는 여행 장소</label><br>
            </div>
            <el-cascader
              id="wish-region"
              :options="options_wish"
              :props="props_wish"
              clearable></el-cascader>
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
import axios from "axios";

function createInstance() {
  const instance = axios.create({
    baseURL: 'http://localhost:8080/',
    headers:{
      "Content-Type": "application/json"
    }
  });
  return instance;
}

const instance = createInstance();
function join(user, success, fail){
  instance
  .post("user", JSON.stringify(user))
  .then(success)
  .catch(fail);
}

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
      region: "",
      tripstyle: "",
      component: this,
      props_region: { multiple: true },
      props_style: { multiple: true },
      props_wish: { multiple: true },
      options_region: [
        {
          value: 1,
          label: '아시아',
        },
        {
          value: 2,
          label: '유럽',
        },
        {
          value: 3,
          label: '미국',
        },
        {
          value: 4,
          label: '호주',
        },
      ],      
      options_style: [
        {
          value: 1,
          label: '힐링',
        },
        {
          value: 2,
          label: '액티비티',
        },
        {
          value: 3,
          label: '가족',
        },
        {
          value: 4,
          label: '역사',
        },
      ],
      options_wish: [
        {
          value: 1,
          label: '경기',
        },
        {
          value: 2,
          label: '서울',
        },
        {
          value: 3,
          label: '강원',
        },
      ]
    }
  },
  created() {
    this.user.nickname = this.$route.params.nickname
    this.user.email = this.$route.params.email
    this.user.password = this.$route.params.password
    this.user.intro = this.$route.params.intro
  },
  methods: {
    onSignUp(){
      join(
        this.user,
        (res)=>{
          // console.log('되는지좀알려줘라')
          console.log(res.data)
          if(typeof res.data==='object'){
            this.$router.push({
              name:"SignupSuccess",
              params:{
                ...this.user,
              }
            })
          }
          else{
            console.log(res.data)
          }
        },
        (err)=>{
          console.error(err);
        }
      )
    }
  }
}
</script>

<style>
  .icon-color-must {
    color: #31D89C;
  }

</style>