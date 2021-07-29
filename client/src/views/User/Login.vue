<template>
  <div class="background">
    로그인
    <form action="">
      <div class="">
        <label class="" for="user-id">아이디(이메일)</label><br>
        <input 
          id="user-id"
          v-model="email"
          type="text"
          class=""
          placeholder="아이디를 입력하세요."
        >
        <div>
          <div v-if="error.email">{{ error.email }}</div>
        </div>
      </div>

      <div class="">
        <label class="" for="user-pw">비밀번호</label><br>
        <input 
          id="user-pw"
          v-model="password"
          type="password"
          class="" 
          placeholder="비밀번호를 입력하세요." 
        >
        <div>
          <div v-if="error.password">{{ error.password }}</div>
        </div>
      </div>

      <div>
        <span><el-checkbox v-model="checked"></el-checkbox>로그인 상태 유지</span>
      </div>
      <div>
        <el-button plain>대충로그인버튼</el-button>
      </div>
      <div>
        <router-link to="/signup" class="link-info">대충둘러보기버튼</router-link>
        <router-link to="/signup" class="link-info">대충회원가입버튼</router-link>
      </div>
    </form>
  </div>
</template>

<script>
import PV from "password-validator";
import * as EmailValidator from "email-validator";

export default {
  name: 'Login',
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
    onSubmit(event){
      event.preventDefault();
      localStorage.setItem("access-token", "");
      // const user={
      //   userId: this.email,
      //   userPw: this.password
      // };
      // login(
      //   user,
      //   (res)=>{
      //     // console.log(res.data.user);
      //     const token = res.data['auth-token'];
      //     if(token){
      //       localStorage.setItem("access-token", token);
      //       this.$store.commit("setUserInfo",res.data.user);
      //       this.$router.push("/home");
      //     }
      //     else{
      //       alert(res.data['message']);
      //     }
      //   },
      //   (err)=>{
      //     console.error(err);
      //   }
      // )
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
</style>