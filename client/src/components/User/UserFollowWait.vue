<template>
  <div class="background">
    <div> wait </div>
    <div> {{ followUserWaits }} </div>

    <table v-if="followUserWaits.length > 0" style="width: 375px;">
      <thead>
        <tr>
          <th>profile</th><th>email</th><th>nickname</th><th>tier</th><th>follower</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="followUserWait in followUserWaits" :key="followUserWait.id">
          <td><div style="width: 50px;"><img style="max-width: 100%;" :src="followUserWait.profilePicSrc" alt="프로필이미지"></div></td>
          <td>{{ followUserWait.email }}</td>
          <td>{{ followUserWait.nickname }}</td>
          <td>{{ followUserWait.tier }}</td>
          <td>{{ followUserWait.followerCnt }}</td>
        </tr>
      </tbody>
    </table>

    <div v-else>대기자가 없습니다.</div>

  </div>
</template>

<script>
import axios from 'axios'

export default {
  name: 'UserFollowWait',
  data() {
    return {
      followUserWaits: [],
    }
  },
  created() {
    const id = localStorage.getItem('userId')
    axios.put(`http://localhost:8081/user/beforeFollowList/${id}`, id)
      .then(res => {
        console.log(res)
        // this.followUserWaits = res.data.users
      })
      .catch(err => {
          console.error(err)
      })
  },
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