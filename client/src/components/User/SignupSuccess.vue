<template>
  <div class="background">
    <img src="@/assets/image-logo-resize.png" alt="image-logo">
    <div class="char-box">
      <h2>회원가입 성공!</h2>
      <div class="char-box">
        <h4>이메일이 발송되었습니다.</h4>
        <h4> 최종 인증을 마무리 해주세요!!</h4>
      </div>
    </div>
    <router-link to="/" class="link-info">로그인 화면으로</router-link>
  </div>
</template>

<script>
import {join} from '@/api/user.js';

export default {
  name: 'SignupSuccess',
  data() {
    return {
      user:{
        nickname: "",
        email: "",
        password: "",
        intro: "",
        name: "",
      },
    }
  },
  created() {
    this.user.nickname = this.$route.params.nickname
    this.user.name = this.$route.params.nickname
    this.user.email = this.$route.params.email
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
  .char-box {
    text-align: center;
    margin: 10px;
  }

</style>