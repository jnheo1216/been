<template>
  <p class="siteTitle">Form</p>
  <main>
    <div class="card" @click="chooseCard(1)">
      <div class="inner inner1" >
        <h2 class="title">지역 :꿀벌은 꽃이 있는 곳 </h2>
        <p class="subtitle">여행 스타일: 친구, 힐링</p>
      </div>
    </div>

    <div class="card card2" @click="chooseCard(2)">
      <div class="inner inner2">
        <h2 class="title">지역 :꿀벌은 꽃이 있는 곳 </h2>
        <p class="subtitle">여행 스타일: 친구, 힐링</p>
      </div>
    </div>

    <div class="card card3" @click="chooseCard(3)">
      <div class="inner inner3">
        <h2 class="title">지역 :꿀벌은 꽃이 있는 곳 </h2>
        <p class="subtitle">여행 스타일: 친구, 힐링</p>
      </div>
    </div>


    </main>

</template>

<script>
export default {
  name: "TestForm",
  // data() {
  //   return {
  //     cards: [
  //                { 'title': '지역 :꿀벌은 꽃이 있는 곳', 'subtitle': '여행 스타일: 친구, 힐링',},
  //                { 'title': '', 'subtitle': '',},
  //                { 'title': '', 'subtitle': '',},
  //             ],
  //   }
  // }
  methods: {
    chooseCard(num){
      this.$store.state.postData.formNumber = num
      // console.log(this.$store.state.postData.cardNum)
      // num
    }
  }
}
</script>

<style scoped>


main {
  max-width: 300px;
  margin: 0 auto;
}

.card {
  height: 200px;
  position: relative;
  padding: 15px;
  box-sizing: border-box;
  display: flex;
  align-items: flex-end;
  border: 0.5px solid ffe8c4;
  border-radius: 20px;
  margin-bottom: 15px;
  background-image: url("https://helpx.adobe.com/content/dam/help/en/photoshop/using/convert-color-image-black-white/jcr_content/main-pars/before_and_after/image-before/Landscape-Color.jpg");
  background-size: cover;
}


.inner1 {
  height: 50%;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  background: white;
  box-sizing: border-box;
  padding: 10px;
  margin-top: 80px;
  border-radius: 10px;
}
.inner2 {
  width: 40%;
  height: 100%;
  display: flex;
  flex-direction: column;
  justify-content: right;
  align-items: center;
  background: white;
  box-sizing: border-box;
  padding: 10px;
  border-radius: 10px;
}
.inner3 {
  width: 40%;
  height: 100%;
  display: flex;
  flex-direction: column;
  margin-left: 160px;
  align-items: center;
  background: white;
  box-sizing: border-box;
  padding: 10px;
  border-radius: 10px;
}


.title {
  font-size: 16px;
  text-align: center;
  font-weight: 700;
  color: #181818;
  text-shadow: 0px 2px 2px #F4DBDB;
  position: relative;
  margin: 0 0 10px 0;
}

.subtitle {
  color: #2d3748;
  text-align: center;
  margin: 0;
}

.card2 {
  background-image: url("https://upload.wikimedia.org/wikipedia/commons/thumb/b/b6/Image_created_with_a_mobile_phone.png/220px-Image_created_with_a_mobile_phone.png");
}

.card3 {
  background-image: url("https://images.unsplash.com/photo-1557576842-3bc6093a5085?ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=1950&q=80");
}


</style>
