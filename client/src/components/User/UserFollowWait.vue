<template>
  <div class="background">
    
    <div class="logo">
      <img alt="BEEN LOGO" class="logo-img" src="@/assets/image-logo-resize.png">
    </div>

    <div> wait </div>

    <table v-if="followUserWaits.length > 0" style="width: 375px;">
      <thead>
        <tr>
          <th>profile</th><th>email</th><th>nickname</th><th>tier</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="followUserWait in followUserWaits" :key="followUserWait.id">
          <td><div style="width: 50px;"><img style="max-width: 100%;" :src="followUserWait.profilePicSrc" alt="프로필이미지"></div></td>
          <td @click="followOK(followUserWait.id)">{{ followUserWait.email }}</td>
          <td>{{ followUserWait.nickname }}</td>
          <td>{{ followUserWait.tier }}</td>
        </tr>
      </tbody>
    </table>

    <div v-else>대기자가 없습니다.</div>

  </div>
</template>

<script>
// import axios from 'axios'
import {getFollowWait} from '@/api/user.js'
import axios from 'axios'
import {API_BASE_URL} from '@/config/index.js'

export default {
  name: 'UserFollowWait',
  data() {
    return {
      followUserWaits: [],
    }
  },
  created() {
    const id = localStorage.getItem('userId')
    getFollowWait(
      id,
      (res) => {console.log(res)},
      (err) => {console.error(err)}
    )
    // axios.put(`http://localhost:8081/user/beforeFollowList/${id}`, id)
    //   .then(res => {
    //     console.log(res)
    //     // this.followUserWaits = res.data.users
    //   })
    //   .catch(err => {
    //       console.error(err)
    //   })
  },
  methods: {
    followOK(userId) {
      myId = localStorage.getItem('userId')
      axios.put(API_BASE_URL + `user/${userId}/${myId}`)
        .then((res) => {
          console.log(res)
          
          for(let i = 0; i < followUserWaits.length; i++) {
            if(followUserWaits[i]['id'] === userId){
              followUserWaits.splice(i, 1);
              i--;
            }
          }
        })
        .catch((err) => {console.log(err)})
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