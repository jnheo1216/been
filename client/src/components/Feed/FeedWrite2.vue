<template>
  <div class="back">
    <img src="@/assets/image-logo.png" alt="logobee" width="120">
    <el-progress class="align-vertical" :percentage="percentage" :color="customColors" :format="format"></el-progress>
    <h5 class="head">사진을 업로드 해주세요</h5>

    <div class="main-container">
        <div class="room-deal-information-container">
            <div class="room-deal-information-title">사진 등록</div>
            <div class="room-file-upload-wrapper">
                <div v-if="!files.length" class="room-file-upload-example-container">
                    <div class="room-file-upload-example">
                        <div class="room-file-image-example-wrapper">이미지</div>
                      
                        <div class="room-file-notice-item room-file-upload-button">
                            <div class="image-box">
                     
                                <label for="file">일반 사진 등록</label>
                                <input type="file" id="file" ref="files" @change="imageUpload" multiple />
                            </div>
                        </div>
                    </div>
                </div>
                <div v-else class="file-preview-content-container">
                    <div class="file-preview-container">
                        <div v-for="(file, index) in files" :key="index" class="file-preview-wrapper">
                            <div class="file-close-button" @click="fileDeleteButton" :name="file.number">
                                x
                            </div>
                            <img :src="file.preview" style="width:80px;height:80px;"/>
                        </div>
                        <div class="file-preview-wrapper-upload">
                            <div class="image-box">
                                <label for="file">추가 사진 등록</label>
                                <input type="file" id="file" ref="files" @change="imageAddUpload" multiple />
                            </div>
                            <!-- <div class="file-close-button" @click="fileDeleteButton" :name="file.number">x</div> -->
                        </div>
                    </div>
                </div>
            </div>
          </div>
        </div>

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
// import TestImages from './TestImages.vue'
export default {
  name: "FeedWrite2",
  // components: { 
  //   TestImages,
  // },
  data() {
    return {
      percentage: 60,
      customColors: [
        {color: '#5cb87a', percentage: 60},
        {color: '#1989fa', percentage: 80},
        {color: '#6f7ad3', percentage: 100}
      ],
      message: this.placeholder,
      files: [],
      filesPreview: [],
      uploadImageIndex: 0
    };
  },
  methods: {
    format(percentage) {
      if (this.percentage<70) {
        return percentage !== 100 ? '2단계' : `${percentage}%`;
      }
      return percentage === 100 ? '비잉 완료' : `${percentage}%`;
    },
    // clickInputTag: function() {
    //   this.$refs['image'].click()
    // },
    // onInputImage() {
    //   this.imageList = this.$refs.serveyImage.files;
    //   console.log(this.imageList)
    // },
    imageUpload() {
                        console.log(this.$refs.files.files);

                        // this.files = [...this.files, this.$refs.files.files];
                        //하나의 배열로 넣기
                        let num = -1;
                        for (let i = 0; i < this.$refs.files.files.length; i++) {
                            this.files = [
                                ...this.files,
                                //이미지 업로드
                                {
                                    //실제 파일
                                    file: this.$refs.files.files[i],
                                    //이미지 프리뷰
                                    preview: URL.createObjectURL(this.$refs.files.files[i]),
                                    //삭제및 관리를 위한 number
                                    number: i
                                }
                            ];
                            num = i;
                            //이미지 업로드용 프리뷰
                            // this.filesPreview = [
                            //   ...this.filesPreview,
                            //   { file: URL.createObjectURL(this.$refs.files.files[i]), number: i }
                            // ];
                        }
                        this.uploadImageIndex = num + 1; //이미지 index의 마지막 값 + 1 저장
                        console.log(this.files);
                        // console.log(this.filesPreview);
                    },

                    imageAddUpload() {
                        console.log(this.$refs.files.files);

                        // this.files = [...this.files, this.$refs.files.files];
                        //하나의 배열로 넣기c
                        let num = -1;
                        for (let i = 0; i < this.$refs.files.files.length; i++) {
                            console.log(this.uploadImageIndex);
                            this.files = [
                                ...this.files,
                                //이미지 업로드
                                {
                                    //실제 파일
                                    file: this.$refs.files.files[i],
                                    //이미지 프리뷰
                                    preview: URL.createObjectURL(this.$refs.files.files[i]),
                                    //삭제및 관리를 위한 number
                                    number: i + this.uploadImageIndex
                                }
                            ];
                            num = i;
                        }
                        this.uploadImageIndex = this.uploadImageIndex + num + 1;

                        console.log(this.files);
                        // console.log(this.filesPreview);
                    },
                    fileDeleteButton(e) {
                        const name = e.target.getAttribute('name');
                        this.files = this.files.filter(data => data.number !== Number(name));
                        // console.log(this.files);
                    },
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
