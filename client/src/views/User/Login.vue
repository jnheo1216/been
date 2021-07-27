<template>
  <div class="background">
    로그인
    <div>
      아이디
      <!-- <el-input placeholder="Please input" v-model="input"></el-input> -->
      <input type="text">
    </div>
    <div>
      비번
      <input type="text">
    </div>
    <div>
      대충 체크박스
    </div>
    <div>
      대충 로그인버튼
    </div>
    <div>
      대충 비번찾기
    </div>
    <div>
      <router-link to="/signup" class="link-info">대충둘러보기버튼</router-link>
      <router-link to="/signup" class="link-info">대충회원가입버튼</router-link>
    </div>
  </div>
</template>

<script>

export default {
  name: 'Login',
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