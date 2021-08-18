<template>
  <div class="background">    
    <div class="logo">
      <img alt="BEEN LOGO" class="logo-img" src="@/assets/image-logo-resize.png">
    </div>
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
import {getArea, getStyle} from '@/api/user.js';
import {getUserInfoEmail} from '@/api/profile.js';

export default {
  name: 'SignupSuccess',
  data() {
    return {
      preferedStyle: "",
      preferedArea: "",
      email: "",
    }
  },
  created() {
    this.preferedStyle = this.$route.params.preferedStyle
    this.preferedArea = this.$route.params.preferedArea
    this.email = this.$route.params.email
    // join(
    //   this.user,
    //   (res)=>{
    //     // console.log('되는지좀알려줘라')
    //     console.log(res.data)
    //   },
    //   (err)=>{
    //     console.error(err);
    //   }
    // )
    getUserInfoEmail(
      this.email,
      (res) => {
        console.log(this.email)
        console.log(res.data)
        console.log(res.data.users[0].id)
        const userId = res.data.users[0].id
        const infoArea = {'area': this.preferedArea, 'latitude': 128, 'longitude': 36, 'userId': userId}
        const infoStyle = {'style': this.preferedStyle, 'userId': userId}
        getArea(
          infoArea,
          (res2) => {console.log(res2)},
          (err2) => {console.error(err2)}
        )
        getStyle(
          infoStyle,
          (res3) => {console.log(res3)},
          (err3) => {console.error(err3)}
        )
      },
      (err) => {
        console.error(err)
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
  .logo > img {
    width: 100%;
  }

</style>