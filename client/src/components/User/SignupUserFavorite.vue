<template>
  <div>
    대충 회원가입 2
    <router-link to="/signup/success" class="link-info">대충 완료 화면으로</router-link>
  </div>
</template>

<script>

export default {
  name: 'SignupUserFavorite',
  data: () => {
    return {
      user:{
        userName: "",
        userId: "",
        userPw: "",
        myTag:[],
      },
      component: this,
    }
  },
}
</script>

<style>

</style>