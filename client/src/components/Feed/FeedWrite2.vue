<template>
  <div class="back">
    <img src="@/assets/image-logo.png" alt="logobee" width="120">
    <el-progress class="align-vertical" :percentage="percentage" :color="customColors" :format="format"></el-progress>
    <h5 class="head">사진을 업로드 해주세요</h5>
<!--    <FeedWriteImage></FeedWriteImage>-->
    <TestImages></TestImages>
    <h5 class="head" style="margin-top: 20px">어떤 여행을 하셨나요?</h5>
    <textarea class='textinput' placeholder="여행기록을 남겨주세요" v-model="message"></textarea>
    <div class="btns">
      <p class="el-icon-back backbtn" @click="$router.push({name: 'FeedWrite1'})"></p>
      <button class="writebtn" @click="$router.push({name: 'FeedWrite3'})">
        <img class="btnimg" src="@/assets/text-logo-resize.png" >
      </button>
    </div>

  </div>
</template>
<script>
// import FeedWriteImage from './FeedWriteImage.vue'
import TestImages from './TestImages.vue'
export default {
  name: "FeedWrite2",
  components: { 
    TestImages,
  },
  data() {
    return {
      percentage: 60,
      customColors: [
        {color: '#5cb87a', percentage: 60},
        {color: '#1989fa', percentage: 80},
        {color: '#6f7ad3', percentage: 100}
      ],
      message: this.placeholder,
    };
  },
  methods: {
    format(percentage) {
      if (this.percentage<70) {
        return percentage !== 100 ? '2단계' : `${percentage}%`;
      }
      return percentage === 100 ? '비잉 완료' : `${percentage}%`;
    },
    clickInputTag: function() {
      this.$refs['image'].click()
    }
  }
}

</script>

<style scoped>
.back {
  width: 375px;
  height: 812px;
  background-color: #ffe8c4;
  margin: 0 auto;
}
.head {
  margin: 20px auto;
}

.btns {
  display: flex;
  justify-content: space-between;
}
.btnimg {
  width: 80px;
}
.writebtn {
  display: block;
  width: 27%;
  height: 40px;
  background-color: #f4dbdb;
  border: none;
  border-radius: 10px;
  margin-right: 20px;
}
.writebtn:hover {
  box-shadow: 0 1px 4px 0 rgba(0, 0, 0, 0.50);
}
.backbtn{
  margin-left: 20px;
}
.textinput {
  display: inline-flex;
  width: 80%;
  height: 80px;
  padding: 10px 10px;
  font-size: 12px;
  background-color: #FFFAF4;
  border: none;
  border-radius: 10px;
  resize: none;
  margin-bottom: 20px;
}
</style>
