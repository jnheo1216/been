<template>
  <div class="background">
    
    <div class="logo">
      <img alt="BEEN LOGO" class="logo-img" src="@/assets/image-logo-resize.png">
    </div>

    <table v-if="followingUsers.length > 0" style="width: 375px;">
      <thead>
        <tr>
          <th>profile</th><th>email</th><th>nickname</th><th>tier</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="followingUser in followingUsers" :key="followingUser.id" @click="goProfile(followingUser.id)">
          <td><div style="width: 50px;"><img style="max-width: 100%;" :src="followingUser.profilePicSrc" alt="프로필이미지"></div></td>
          <td>{{ followingUser.email }}</td>
          <td>{{ followingUser.nickname }}</td>
          <td>{{ followingUser.tier }}</td>
        </tr>
      </tbody>
    </table>

    <div v-else>팔로잉한 유저가 없습니다.</div>

  </div>
</template>

<script>
// import axios from 'axios'
import {getFollowing} from '@/api/user.js'

export default {
  name: 'UserFollowing',
  data() {
    return {
      followingUsers: [],
    }
  },
  created() {
    const userId = localStorage.getItem('userId')
    getFollowing(
      userId,
      (res) => {
        console.log(res)
        this.followingUsers = res.data.users
      },
      (err) => {
        console.error(err)
      }
    )
    // axios.get(`http://localhost:8081/user/showFollowing/${userId}`)
    //   .then(res => {
    //     console.log(res)
    //     this.followingUsers = res.data.users
    //   })
    //   .catch(err => {
    //       console.error(err)
    //   })
  },
  methods: {
    goProfile(id) {
      this.$router.push(`/userprofile/${id}`)
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

  .logo {
    padding-top: 10px;
    margin: 0 auto;
    width: 200px;
  }
  .logo > img {
    width: 100%;
  }
</style>