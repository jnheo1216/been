<template>
  <div class="TypeSearch">
    <div class="logo">
      <img alt="BEEN LOGO" src="@/assets/image-logo.png">
    </div>

    <h1 class="text">여행 유형으로 검색하기</h1>
    <!-- <div class="block">
    <el-cascader
      :options="options"
      :props="props"
      clearable></el-cascader>
    </div> -->

    <div class="container">
      <div class="searchbar">
        <el-select v-model="value1" multiple placeholder="장소">
        <el-option
          v-for="item in cityOptions"
          :key="item.value"
          :label="item.label"
          :value="item.value">
        </el-option>
        </el-select>
      </div>

      <div class="search">
        <el-select v-model="value2" multiple placeholder="스타일">
        <el-option
          v-for="item in styleOptions"
          :key="item.value"
          :label="item.label"
          :value="item.value">
        </el-option>
        </el-select>
      </div>

    </div>



    </div>


    <!-- 검색 결과 -->
    <div class="searched">
      <!-- 검색결과 있는 경우 -->

      <!-- 검색결과 없는 경우 -->
    </div>
  <!-- </div> -->
    <el-button @click="backToSearchmain" type="info" icon="el-icon-back" circle></el-button>
</template>

<script>

export default {
  name: 'TypeSearch',
  data() {
    return {
      props: { multiple : true },
      options: [{ value: 1, label: '여행스타일', 
        children: [
          { value: 2, label: '휴양'},
          { value: 3, label: '액티비티'},
          { value: 4, label: '문화체험'},
          { value: 5, label: '쇼핑'},
          { value: 6, label: '가족여행'},
          { value: 7, label: '우정여행'},
          { value: 8, label: '캠핑, 글램핑'},
        ]},
      { value: 9, label: '지역', children: [
        { value: 10, label: '국내', children: [
          { value: 11, label: '서울/경기'}, 
          { value: 12, label: '강원도'}, 
          { value: 13, label: '충청도'},
          { value: 14, label: '전라도'},
          { value: 15, label: '경상도'},
          { value: 16, label: '제주도'},
          ]},
        { value: 17, label: '해외', children: [
          { value: 18, label: '일본'}, 
          { value: 19, label: '중국'}, 
          { value: 20, label: '동남아시아'},
          { value: 21, label: '중앙아시아'},
          { value: 22, label: '서유럽'},
          { value: 23, label: '동유럽'},
          { value: 24, label: '남유럽'},
          { value: 25, label: '북유럽'},
          { value: 26, label: '호주/뉴질랜드'},
          ]},
      ]},
        ],
      city: '',
      cityOptions: [
          { value: 11, label: '서울/경기'}, 
          { value: 12, label: '강원도'}, 
          { value: 13, label: '충청도'},
          { value: 14, label: '전라도'},
          { value: 15, label: '경상도'},
          { value: 16, label: '제주도'},
          { value: 18, label: '일본'}, 
          { value: 19, label: '중국'}, 
          { value: 20, label: '동남아시아'},
          { value: 21, label: '중앙아시아'},
          { value: 22, label: '서유럽'},
          { value: 23, label: '동유럽'},
          { value: 24, label: '남유럽'},
          { value: 25, label: '북유럽'},
          { value: 26, label: '호주/뉴질랜드'}
          ],
      styleOptions: [{
          value: 'Option1',
          label: '휴양'
        }, {
          value: 'Option2',
          label: '액티비티'
        }, {
          value: 'Option3',
          label: '문화체험'
        }, {
          value: 'Option4',
          label: '가족여행'
        }, {
          value: 'Option5',
          label: '우정여행'
        },
        { 
          value: 'Option6', 
          label: '캠핑, 글램핑'},], 
        value1: [],
        value2: []
    }
  },
  methods: {
    backToSearchmain: function () {
      this.$router.push({ name: "SearchMain" })
    }
  }
}
</script>

<style>
  .TypeSearch {
    width: 375px;
    height: 812px;
    background-color: #fffaf4;
    margin: 0 auto;
  }

  .logo {
  padding-top: 10px;
  margin: 0 auto;
  width: 200px;
}
.logo > img {
  width: 100%;
}
  .text {
    font-family: 'Nanum Pen Script', cursive;
  }

  .block {
    justify-content: left;
    margin-top: 20px;
  }

  .searchbar {
    margin-bottom: 30px;
  }
</style>