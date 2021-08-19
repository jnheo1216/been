<template>
  <div class="background">

    <table v-if="followerUsers.length > 0" style="width: 375px;">
      <thead>
        <tr>
          <th>profile</th><th>email</th><th>nickname</th><th>tier</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="followerUser in followerUsers" :key="followerUser.id" @click="goProfile(followerUser.id)">
          <td><div style="width: 50px;"><img style="max-width: 100%;" :src="followerUser.profilePicSrc" alt="프로필이미지"></div></td>
          <td>{{ followerUser.email }}</td>
          <td>{{ followerUser.nickname }}</td>
          <td>{{ followerUser.tier }}</td>
        </tr>
      </tbody>
    </table>

    <div v-else>팔로우한 유저가 없습니다.</div>

  </div>
</template>

<script>
// import axios from 'axios'
import {getFollower} from '@/api/user.js'

export default {
  name: 'UserFollower',
  data() {
    return {
      followerUsers: [],
    }
  },
  created() {
    const userId = localStorage.getItem('userId')
    getFollower(
      userId,
      (res) => {
        console.log(res)
        this.followerUsers = res.data.users
      },
      (err) => {
        console.error(err)
      }
    )
    // axios.get(`http://localhost:8081/user/showFollower/${userId}`)
    //   .then(res => {
    //     console.log(res)
    //     this.followerUsers = res.data.users
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

</style>