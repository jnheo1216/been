<template>
  <div class="Background">

	<div class="container">

		<div class="profile">

			<div class="profile-image">

				<img :src="this.User.profilePicSrc" alt="">

			</div>

			<div class="profile-user-settings">

				<h1 class="profile-user-name">{{this.User.nickname}}</h1>

				<button class="btn profile-edit-btn">Edit Profile</button>

				<button class="btn profile-settings-btn" aria-label="profile settings"><i class="fas fa-cog" aria-hidden="true"></i></button>

			</div>

			<div class="profile-stats">

				<ul>
					<li><span class="profile-stat-count">{{this.postCnt}}</span> posts</li>
					<li><span class="profile-stat-count" @click="this.alertFollower()">{{this.User.followerCnt}}</span> followers</li>
					<li><span class="profile-stat-count">{{this.User.followingCnt}}</span> following</li>
				</ul>

			</div>

			<div class="profile-bio">

				<p><span class="profile-real-name">{{this.User.email}}</span><br> {{this.User.intro}}</p>
                

			</div>

            <div class="follow-btn" @click="followUser()">꿀 바르기</div>
		</div>
		<!-- End of profile section -->

	</div>
	<!-- End of container -->


	<div class="container">
        
		<div class="gallery">


			<div class="gallery-item" tabindex="0" v-for="(post,idx) in this.postList" :key='idx' @click="this.$router.push(`/feed/${post.postId}`)">

				<img :src="post.postPicSrc" alt="" style="max-width:375px; max-height:375px;">

				<div class="gallery-item-type">

					<span class="visually-hidden">Video</span><i class="fas fa-video" aria-hidden="true"></i>

				</div>

				<div class="gallery-item-info">

					<ul>
						<li class="gallery-item-likes"><span class="visually-hidden">Likes:</span><i class="fas fa-heart" aria-hidden="true"></i> {{post.likeCnt}}</li>
						<li class="gallery-item-comments"><span class="visually-hidden">Comments:</span><i class="fas fa-comment" aria-hidden="true"></i></li>
					</ul>

				</div>

			</div>

		</div>
		<!-- End of gallery -->

		<div class="loader"></div>

	</div>
	<!-- End of container -->

  </div>
</template>

<script>
import axios from 'axios'
// import {getFeedUserId} from '@/api/feed.js'
// import {getFollower, getFollowing} from '@/api/user.js'
import {API_BASE_URL} from "@/config/index.js"


export default {
  name: 'UserProfile',
  data() {
      return {
          User: {
            // id: "",
            // email: "",
            // password: "",
            // intro: "",
            // nickname: "",
            // name: "",
            // tier: 0,
            // followerCnt: 0,
            // followingCnt: 0,
            // createdAt: "",
            // profilePicSrc: "",
            // profilePicName: "",
            // emailConfirmation: 0,
            // status: ''
        },
        postCnt: 0,
        PostList: [],
        FollowerList: [],
        FollowingList: [],
        alreadyFollowing: false
    }
  },
  methods: {
    getUserProfile() {
        console.log('프로필임')
        // console.log(this.$store.state.user)
        this.User.id = this.$route.params.userId
        axios.get(API_BASE_URL + "post/user/"+this.User.id)
        // axios.get('http://localhost:8081/user/{id}?id='+this.User.id)
        .then(res => {
            this.User = res.data.user
            console.log(this.User)
        })
        .catch(err => {
            console.log(err)
        })
    },
    getUserPost(){
        // const id = localStorage.getItem('userId')
        // 여기서 동적 라우팅으로 id만 설정해주면 됨
        // getFeedUserId(
        //     id,
        //     (res) => {
        //         var postCnt = res.data.posts.length
        //         this.postCnt = postCnt
        //         this.postList = res.data.posts
        //         console.log(res.data.posts)
        //     },
        //     (err) => {
        //         console.log(err)
        //     }
        // )
        axios.get(API_BASE_URL + "post/userId/"+this.User.id+'/1')
        .then(res => {
            var postCnt = res.data.posts.length
            this.postCnt = postCnt
            this.postList = res.data.posts
            console.log(res.data.posts)
        })
        .catch(err => {
            console.log(err)
        })
    },
    getUserFollower() {
        // const id = localStorage.getItem('userId')
        // getFollower(
        //     id,
        //     (res) => {
        //         var FollowerCnt = res.data.users.length;
        //         this.User.followerCnt = FollowerCnt;
        //         this.FollowerList = res.data.users
        //     },
        //     (err) => {
        //         console.log(err)
        //     }
        // )
        axios.get(API_BASE_URL + "user/showFollower/"+this.User.id+'/1')
        .then(res => {
            // console.log(res)
            var FollowerCnt = res.data.users.length;
            this.User.followerCnt = FollowerCnt;
            this.FollowerList = res.data.users
        })
        .catch(err => {
            console.log(err)
        })
    },
    getUserFollowing() {
        // const id = localStorage.getItem('userId')
        // getFollowing(
        //     id,
        //     (res) => {
        //         var FollowingCnt = res.data.users.length;
        //         this.User.followingCnt = FollowingCnt;
        //         this.FollowingList = res.data.users
        //     },
        //     (err) => {
        //         console.log(err)
        //     }
        // )
        axios.get(API_BASE_URL + "user/showFollowing/"+this.User.id+'/1')
        .then(res => {
            // console.log(res)
            var FollowingCnt = res.data.users.length;
            this.User.followingCnt = FollowingCnt;
            this.FollowingList = res.data.users
        })
        .catch(err => {
            console.log(err)
        })
    },
    getAlreadyFollowing() {
        console.log("이미 팔로우")
        axios.get(API_BASE_URL+`user/showFollowing/${this.$store.state.user.id}/1`)
        .then(res => {
            console.log(res)
        })
        .catch(err => {
            console.log(err)
        })
    },
    followUser(){
        // var data = {
        //     followedId = "",
        //     followerId = ""
        // }
        var followedId = this.$route.params.userId
        var followerId = this.$store.state.user.id
        // data.followedId = this.$route.params.userId
        // data.followerId = this.$store.state.user.id

        axios.post(API_BASE_URL+`user/${followedId}/${followerId}`)
        .then(res => {
            console.log("성공")
            console.log(res)
        })
        .catch(err => {
            console.log(this.$store.state.user)
            console.log(err)
        })
    }
  },
  created() {
    this.getUserProfile()
    this.getUserPost()
    this.getUserFollower()
    this.getAlreadyFollowing()
    this.getUserFollowing()
  }
}
</script>

<style>
.Background{
    /*  */
    width: 375px;
    height: 812px;
    text-align: center;
    background-color: #FFFAF4;
    margin:0 auto;
}
/*

All grid code is placed in a 'supports' rule (feature query) at the bottom of the CSS (Line 310). 
        
The 'supports' rule will only run if your browser supports CSS grid.

Flexbox and floats are used as a fallback so that browsers which don't support grid will still recieve a similar layout.

*/

/* Base Styles */

:root {
    font-size: 10px;
}

*,
*::before,
*::after {
    box-sizing: border-box;
}

body {
    font-family: "Open Sans", Arial, sans-serif;
    min-height: 100vh;
    background-color: #fafafa;
    color: #262626;
    padding-bottom: 3rem;
}

img {
    display: block;
}

.container {
    max-width: 93.5rem;
    margin: 0 auto;
    padding: 0 2rem;
}

.btn {
    display: inline-block;
    font: inherit;
    background: none;
    border: none;
    color: inherit;
    padding: 0;
    cursor: pointer;
}

.btn:focus {
    outline: 0.5rem auto #4d90fe;
}

.visually-hidden {
    position: absolute !important;
    height: 1px;
    width: 1px;
    overflow: hidden;
    clip: rect(1px, 1px, 1px, 1px);
}

/* Profile Section */

.profile {
    padding: 5rem 0;
}

.profile::after {
    content: "";
    display: block;
    clear: both;
}

.profile-image {
    float: left;
    width: calc(33.333% - 1rem);
    display: flex;
    justify-content: center;
    align-items: center;
    margin-right: 3rem;
}

.profile-image img {
    border-radius: 50%;
}

.profile-user-settings,
.profile-stats,
.profile-bio {
    float: left;
    width: calc(66.666% - 2rem);
}

.profile-user-settings {
    margin-top: 1.1rem;
}

.profile-user-name {
    display: inline-block;
    font-size: 3.2rem;
    font-weight: 300;
}

.profile-edit-btn {
    font-size: 1.4rem;
    line-height: 1.8;
    border: 0.1rem solid #dbdbdb;
    border-radius: 0.3rem;
    padding: 0 2.4rem;
    margin-left: 2rem;
}

.profile-settings-btn {
    font-size: 2rem;
    margin-left: 1rem;
}

.profile-stats {
    margin-top: 2.3rem;
}

.profile-stats li {
    display: inline-block;
    font-size: 1.6rem;
    line-height: 1.5;
    margin-right: 4rem;
    cursor: pointer;
}

.profile-stats li:last-of-type {
    margin-right: 0;
}

.profile-bio {
    font-size: 1.6rem;
    font-weight: 400;
    line-height: 1.5;
    margin-top: 2.3rem;
}

.profile-real-name,
.profile-stat-count,
.profile-edit-btn {
    font-weight: 600;
}

/* Gallery Section */

.gallery {
    display: flex;
    flex-wrap: wrap;
    margin: -1rem -1rem;
    padding-bottom: 3rem;
}

.gallery-item {
    position: relative;
    flex: 1 0 22rem;
    margin: 1rem;
    color: #fff;
    cursor: pointer;
    max-width: 375px;
    max-height: 375px;
}

.gallery-item:hover .gallery-item-info,
.gallery-item:focus .gallery-item-info {
    display: flex;
    justify-content: center;
    align-items: center;
    position: absolute;
    top: 0;
    width: 100%;
    height: 100%;
    background-color: rgba(0, 0, 0, 0.3);
}

.gallery-item-info {
    display: none;
}

.gallery-item-info li {
    display: inline-block;
    font-size: 1.7rem;
    font-weight: 600;
}

.gallery-item-likes {
    margin-right: 2.2rem;
}

.gallery-item-type {
    position: absolute;
    top: 1rem;
    right: 1rem;
    font-size: 2.5rem;
    text-shadow: 0.2rem 0.2rem 0.2rem rgba(0, 0, 0, 0.1);
}

.fa-clone,
.fa-comment {
    transform: rotateY(180deg);
}
.gallery-image {
    width: 100%;
    height: 100%;
    object-fit: cover;
}

/* Loader */

.loader {
    width: 5rem;
    height: 5rem;
    border: 0.6rem solid #999;
    border-bottom-color: transparent;
    border-radius: 50%;
    margin: 0 auto;
    animation: loader 500ms linear infinite;
}

/* Media Query */

@media screen and (max-width: 40rem) {
    .profile {
        display: flex;
        flex-wrap: wrap;
        padding: 4rem 0;
    }

    .profile::after {
        display: none;
    }

    .profile-image,
    .profile-user-settings,
    .profile-bio,
    .profile-stats {
        float: none;
        width: auto;
    }

    .profile-image img {
        width: 7.7rem;
    }

    .profile-user-settings {
        flex-basis: calc(100% - 10.7rem);
        display: flex;
        flex-wrap: wrap;
        margin-top: 1rem;
    }

    .profile-user-name {
        font-size: 2.2rem;
    }

    .profile-edit-btn {
        order: 1;
        padding: 0;
        text-align: center;
        margin-top: 1rem;
    }

    .profile-edit-btn {
        margin-left: 0;
    }

    .profile-bio {
        font-size: 1.4rem;
        margin-top: 1.5rem;
    }

    .profile-edit-btn,
    .profile-bio,
    .profile-stats {
        flex-basis: 100%;
    }

    .profile-stats {
        order: 1;
        margin-top: 1.5rem;
    }

    .profile-stats ul {
        display: flex;
        text-align: center;
        padding: 1.2rem 0;
        border-top: 0.1rem solid #dadada;
        border-bottom: 0.1rem solid #dadada;
    }

    .profile-stats li {
        font-size: 1.4rem;
        flex: 1;
        margin: 0;
    }

    .profile-stat-count {
        display: block;
    }
}

/* Spinner Animation */

@keyframes loader {
    to {
        transform: rotate(360deg);
    }
}

/*

The following code will only run if your browser supports CSS grid.

Remove or comment-out the code block below to see how the browser will fall-back to flexbox & floated styling. 

*/

@supports (display: grid) {
    .profile {
        display: grid;
        grid-template-columns: 1fr 2fr;
        grid-template-rows: repeat(3, auto);
        grid-column-gap: 3rem;
        align-items: center;
    }

    .profile-image {
        grid-row: 1 / -1;
    }

    .gallery {
        display: grid;
        grid-template-columns: repeat(auto-fit, minmax(22rem, 1fr));
        grid-gap: 2rem;
    }

    .profile-image,
    .profile-user-settings,
    .profile-stats,
    .profile-bio,
    .gallery-item,
    .gallery {
        width: auto;
        margin: 0;
    }

    @media (max-width: 40rem) {
        .profile {
            grid-template-columns: auto 1fr;
            grid-row-gap: 1.5rem;
        }

        .profile-image {
            grid-row: 1 / 2;
        }

        .profile-user-settings {
            display: grid;
            grid-template-columns: auto 1fr;
            grid-gap: 1rem;
        }

        .profile-edit-btn,
        .profile-stats,
        .profile-bio {
            grid-column: 1 / -1;
        }

        .profile-user-settings,
        .profile-edit-btn,
        .profile-settings-btn,
        .profile-bio,
        .profile-stats {
            margin: 0;
        }
    }
}

</style>