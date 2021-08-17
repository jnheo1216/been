<template>
<div class="back">
  <img src="@/assets/image-logo.png" alt="logobee" width="120">
  <el-progress class="align-vertical" :percentage="percentage" :color="customColors" :format="format"></el-progress>
  <div class='title'>
    <label for="title" >포스트 제목을 입력해주세요</label>
    <br>
    <input type="textinput" id="title" placeholder="제목을 입력해주세요" v-model="title">
  </div>
  <h3 class="head"> 지역과 여행 스타일을 선택해주세요</h3>
    <div class="block">
      <el-cascader v-model="region" size="medium" placeholder="여행 지역"  change-on-select="false"
        :options="options1"
        :props="props1"></el-cascader>
    </div>
  <div class="block">
    <el-cascader v-model="style" size="medium" placeholder="여행 스타일"  change-on-select="false"
                 :options="options2"
                 :props="props2"></el-cascader>
  </div>

  <!--  물음표  -->
    <p id="show-modal" @click="showModal = true" class="question el-icon-question"></p>
    <FeedModal v-if="showModal" @close="showModal = false">   </FeedModal>
  <!--   버튼 wirte2로 넘어가는 버튼 & 뒤로가기-->
  <div class="btns">
    <p class="el-icon-back backbtn" @click="$router.push({name: ''})"></p>
    <button class="writebtn" @click="nextPage()">
      <img class="btnimg" src="@/assets/text-logo-resize.png" >
    </button>
  </div>
  <!--  region, style 확인  
  <button @click="print">dd</button>-->
  </div>
</template>

<script>
import FeedModal from "./FeedModal.vue"
export default {
  name: "FeedWrite1",
  components: {
    FeedModal,
  },
  data() {
    return {
      showModal: false,
      percentage: 20,
      title: '',
      region: '',
      style: [],
      customColors: [
        {color: '#5cb87a', percentage: 60},
        {color: '#1989fa', percentage: 80},
        {color: '#6f7ad3', percentage: 100}
      ],
      props1: { multiple : false },
      options1: [
                 { value: '서울/경기', label: '서울/경기'},
                { value: '강원도', label: '강원도'},
                { value: '충청도', label: '충청도'},
                { value: '전라도', label: '전라도'},
                { value: '경상도', label: '경상도'},
                { value: '제주도', label: '제주도'},
                { value: '일본', label: '일본'},
                { value: '중국', label: '중국'},
                { value: '동남아시아', label: '동남아시아'},
                { value: '중앙아시아', label: '중앙아시아'},
                { value: '서유럽', label: '서유럽'},
                { value: '동유럽', label: '동유럽'},
                { value: '남유럽', label: '남유럽'},
                { value: '북유럽', label: '북유럽'},
                { value: '호주/뉴질랜드', label: '호주/뉴질랜드'},
              ],
      props2: { multiple : true },
      options2: [
        { value: '휴양', label: '휴양'},
        { value: '액티비티', label: '액티비티'},
        { value: '문화체험', label: '문화체험'},
        { value: '쇼핑', label: '쇼핑'},
        { value: '가족여행', label: '가족여행'},
        { value: '우정여행', label: '우정여행'},
        { value: '캠핑, 글램핑', label: '캠핑, 글램핑'},
      ]
    };
  },
  methods: {
    format(percentage) {
      if (this.percentage<50) {
        return percentage !== 100 ? '1단계' : `${percentage}%`;
      }
      return percentage === 100 ? '비잉 완료' : `${percentage}%`;
    },
    nextPage()
    {
        // console.log(this.region[0])
        // console.log(this.style[0][0])
        this.$store.state.postData.title = this.title
        this.$store.state.postData.area = this.region[0]
        this.$store.state.postData.style = this.style[0][0]
        this.$router.push({name: 'FeedWrite2'});
        

    }
  },
    // 데이터 확인
    // print() {
    //   console.log(this.region[0])
    //   let i = 0;
    //   for (i=0; i<this.style.length; i++ ) {
    //     console.log(this.style[i][0])
    //   }
    // },
}
</script>

<style scoped>
.back {
  width: 375px;
  height: 812px;
  background-color: #ffe8c4;
  margin: 0 auto;
}
.question {
  margin-left: 300px;
  display: block;
  margin-right: 10px;
  margin-bottom: 30px;
}
.backbtn{
  margin-left: 20px;
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
.head {
  margin: 20px auto;
}
.block {
  padding: 10px;
  margin-bottom: 30px;
}
#show-modal {
  font-size: 22px;
  margin-right: 20px;
}
</style>
