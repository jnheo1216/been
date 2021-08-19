<template>
  <div class="Background">

	<div class="container">

		<div class="profile">

			<div class="profile-image">

				<img :src="this.User.profilePicSrc" alt="">

			</div>

			<div class="profile-user-settings">

				<!-- <h1 class="profile-user-name">{{this.User.nickname}}</h1> -->
        <label for="nickName">닉네임 수정:</label>
        <input type="text" placeholder="닉네임" id="nickName" v-model="User.nickname">
				<!-- <button class="btn profile-edit-btn">Edit Profile</button> -->

				<!-- <button class="btn profile-settings-btn" aria-label="profile settings"><i class="fas fa-cog" aria-hidden="true"></i></button> -->

			</div>

			<div class="profile-stats">

				<!-- <ul>
					<li><span class="profile-stat-count">{{this.postCnt}}</span> posts</li>
					<li><span class="profile-stat-count" @click="this.alertFollower()">{{this.User.followerCnt}}</span> followers</li>
					<li><span class="profile-stat-count">{{this.User.followingCnt}}</span> following</li>
				</ul> -->

			</div>

			<div class="profile-bio">

				<p><span class="profile-real-name">{{this.User.email}}</span><br> {{this.User.intro}}</p>
        <label for="intro">자기소개 수정:</label>
        <input type="text" placeholder="자기소개" id="intro" v-model="User.intro">

			</div>
      <label for="password">비밀번호 수정:</label>
      <input type="password" placeholder="비밀번호 수정" id="password" v-model="User.password">

      <button @click="submitEdit">수정완료</button>

		</div>
		<!-- End of profile section -->

	</div>
  </div>
	<!-- End of container -->
</template>

<script>
import axios from 'axios'
import {API_BASE_URL} from "@/config/index.js"

export default {
  methods: {
    getUserProfile() {
      this.User = this.$store.state.user
      console.log(this.User)
    },
    submitEdit(){
      axios.put(API_BASE_URL + "user",this.User)
      .then(res => {
        console.log(res)
        this.$router.replace(`/profile/${this.User.id}`)
      })
      .catch(err => {
        this.$router.replace(`/profile/${this.User.id}`)
        console.log(err)
      })
    }
  },
  data() {
    return {
      User: {
        id: "",
            email: "",
            password: "",
            intro: "",
            nickname: "",
            name: "",
            tier: 0,
            followerCnt: 0,
            followingCnt: 0,
            createdAt: "",
            profilePicSrc: "",
            profilePicName: "",
            emailConfirmation: 0,
            status: ''
      }
    }
  },
  created() {
    this.getUserProfile()
  }
}
</script>

<style>

</style>