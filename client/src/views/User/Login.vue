<template>
  <div class="background">

    
    <!-- <div class="map-box">
      <MyMap :visitedDatas="[[30, 40],[50, 40]]"/>
    </div> -->

    <div class="logo">
      <img alt="BEEN LOGO" class="logo-img" src="@/assets/image-logo-resize.png">
    </div>
    <form @submit="onSubmit" class="login-form">
      <div class="login-input-box">
        <el-input 
          id="user-id"
          v-model="email"
          type="text"
          class=""
          placeholder="이메일을 입력하세요."
        ></el-input>
        <div class="text-color-danger">
          <div v-if="error.email">{{ error.email }}</div>
        </div>
      </div>

      <div class="login-input-box">
        <el-input 
          id="user-pw"
          v-model="password"
          type="password"
          class="" 
          placeholder="비밀번호를 입력하세요." 
        ></el-input>
        <div class="text-color-danger">
          <div v-if="error.password">{{ error.password }}</div>
        </div>
      </div>

      <div class="login-checkbox">
        <span class="login-input-box"><el-checkbox v-model="checked"></el-checkbox>로그인 상태 유지</span>
      </div>
      <div @click="onSubmit" class="login-button">
        <img style="max-height: 100%; margin: 0 auto;" src="@/assets/text-logo-resize.png" alt="logo">
      </div>
    </form>

    <!-- <div class="google-login">
      <Google class=""/>
    </div> -->
    
    <div>
      <router-link to="/introduction" class="link-info">Been이란?</router-link> | 
      <router-link to="/signup" class="link-info">회원가입</router-link>
    </div>
  </div>
</template>


<script>
import PV from "password-validator";
import * as EmailValidator from "email-validator";
// import Google from "@/components/User/Google.vue" 
// import axios from "axios";
import {login} from '@/api/user.js'
// import MyMap from '@/components/MyMaps/MyMap.vue'


export default {
  name: 'Login',
  components: {
    // Google,
    // MyMap
  },
  data: () => {
    return {
      vWidth: 0,
      vHeight: 0,
      email: "",
      password: "",
      passwordSchema: new PV(),
      checked: false,
      error:{
        email: false,
        password: false,
      },
      component: this,
    }
  },  
  created(){
    if (this.$store.state.isLogin === true) {
      this.$router.push("/feed");
    }
    this.component = this;
    this.passwordSchema
      .is()
      .min(8)
      .is()
      .max(20)
      .has()
      .digits()
      .has()
      .letters();
  },  
  watch: {
    password: function() {
      this.checkForm();
    },
    email: function() {
      this.checkForm();
    }
  },
  methods: {
    checkForm(){
      if(this.email.length >= 0 && !EmailValidator.validate(this.email))
        this.error.email ="이메일 형식이 아닙니다";
      else this.error.email=false;
      if(this.password.length >= 0 && !this.passwordSchema.validate(this.password))
        this.error.password = "영문, 숫자 포함 8 자리 이상이어야 합니다";
      else this.error.password = false;
    },
    onSubmit() {
      // event.preventDefault();
      localStorage.setItem("jwt-auth-token", "");
      localStorage.setItem("userInfo", "");
      const user={
        email: this.email,
        password: this.password
      };
      login(
        user,
        (res) => {
          console.log(res)
          localStorage.setItem('jwt-auth-token', res.headers['jwt-auth-token'])
          localStorage.setItem('userId', res.data.data['id'])
          this.$store.commit("setUserInfo",res.data.data)
          console.log(this.$store.state.user)
          console.log(this.$store.state.user['id'])
          this.$router.push("/feed");
        },
        (err) => {
          console.error(err);
        }
      )
      // axios.post('http://localhost:8081/user/signin', user)
      //   .then(res => {
      //     console.log(res)
      //     localStorage.setItem('jwt-auth-token', res.headers['jwt-auth-token'])
      //     localStorage.setItem('userId', res.data.data['id'])
      //     this.$store.commit("setUserInfo",res.data.data)
      //   })
      //   .then(() => {
      //     this.$router.push("/feed");
      //     console.log(this.$store.state.user)
      //     console.log(this.$store.state.isLogin)
      //   })
      //   .catch(err => {
      //     console.error(err)
      //   })
    }
  }
}
</script>

<style>
  .background {
    width: 375px;
    height: 812px;
    text-align: center;
    background-color: #FFFAF4;
    margin:0 auto;
  }
  .login-button {
    width: 300px;
    height: 40px;
    background-color: #F4DBDB;
    margin: 0 auto;
    cursor: pointer;
  }
  .login-form {
    padding: 10px;
  }
  .login-checkbox {
    text-align: left;
    margin: 10px;
  }
  .login-input-box {
    margin: 20px;
  }
  .google-login {
    margin: 0 auto;
  }
  .text-color-danger {
    color: crimson;
    text-align: right;
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
