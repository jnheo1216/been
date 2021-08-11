<template>
  <div class="introduction">
    <div class="logo">
      <img alt="BEEN LOGO" src="@/assets/image-logo.png">
    </div>

  <div class="container">

    <el-collapse v-model="activeNames" @change="handleChange">
    <el-collapse-item title="BEEN에 관해" name="1">
      <div><i class="fab fa-forumbee"></i>여행의, 여행에 의한, 여행을 위한 SNS</div>
      <div>꽃이 있는 아름다운 곳을 찾아가는 벌들처럼 아름다운 곳을 여행하고 공유해주세요!</div>
    </el-collapse-item>
    <el-collapse-item title=" 뉴스피드" name="2">
      <div><i class="fas fa-newspaper"></i>동료 꿀벌들과 소통하기</div>
      <div>동료 꿀벌들을 팔로우하고 최신 소식을 받아보세요!</div>
    </el-collapse-item>
    <el-collapse-item title=" 글 쓰기" name="3">
      <div><i class="fas fa-pencil-alt"></i>쉽게 쓰는 여행이야기</div>
      <div>여행 후기를 써주세요! 예쁘게 구성은 저희가 해드려요!</div>
      <div></div>
    </el-collapse-item>
    <el-collapse-item title=" 큐레이션" name="4">
      <div><i class="fas fa-heart"></i>나를 위한 여행게시물</div>
      <div>나의 스타일에 맞게, 내가 가고 싶은 지역에 맞게, 게시물을 추천해드려요</div>
    </el-collapse-item>
    </el-collapse>

    <el-button-group class="my-4">
      <el-button @click="clicktoSignup" type="primary"><i class="fab fa-forumbee"></i>꿀벌되기</el-button>
      <el-button type="warning" @click="clicktoLogin">꿀벌 로그인하기</el-button>
    </el-button-group>
  </div>

  </div>
</template>

<script>
export default {
  name: 'Introduction',
  data() {
    return {
      activeNames: ['1']
    }
  },
  methods: {
    handleChange(val) {
      console.log(val)
    },
    clicktoSignup: function () {
      this.$router.push({
        name: "Signup"
      })
    },
    clicktoLogin: function () {
      this.$router.push({ name: "Login" })
    }
  }

}
</script>

<style>
.introduction {
  width: 375px;
  height: 812px;
  background-color: #fffaf4;
  position: relative;
  margin: 0 auto;
  overflow-y: auto;
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