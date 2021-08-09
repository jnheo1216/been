<template>
  <div class="background">
    <img src="@/assets/image-logo.png" alt="image-logo">
    <div class="char-box">
      <h2>회원가입 성공!</h2>
      <div class="char-box">
        <h4>이메일이 발송되었습니다.</h4>
        <h4> 최종 인증을 마무리 해주세요!!</h4>
      </div>
    </div>
    <router-link to="/login" class="link-info">로그인 화면으로</router-link>
  </div>
</template>

<script>

export default {
  name: 'SignupSuccess',
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