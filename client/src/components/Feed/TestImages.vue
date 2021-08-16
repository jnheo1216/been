<template>
  <div
      class="container"
      @dragover.prevent="dragOver"
      @dragleave.prevent="dragLeave"
      @drop.prevent="drop($event)"
  >
    <div class="drop" v-show="dropped == 2"></div>
    <!-- Error Message -->
    <div v-show="error" class="error">
      {{ error }}
    </div>

    <div v-show="Imgs.length == 0" class="beforeUpload">
      <input
          type="file"
          style="z-index: 1"
          accept="image/*"
          ref="uploadInput"
          @change="previewImgs"
          multiple
      />
      <p class="mainMessage el-icon-camera-solid">
        {{ uploadMsg ? uploadMsg : " 사진" }}
      </p>
    </div>
    <div class="imgsPreview" v-show="Imgs.length > 0">
      <button type="button" class="clearButton el-icon-delete-solid" @click="reset">
        {{ clearAll ? clearAll : "Clear All" }}
      </button>
      <div class="imageHolder" v-for="(img, i) in Imgs" :key="i">
        <img :src="img" />
        <span class="delete" style="color: white" @click="deleteImg(--i)">
          <svg
              class="icon"
              xmlns="http://www.w3.org/2000/svg"
              fill="none"
              viewBox="0 0 24 24"
              stroke="currentColor"
          >
            <path
                stroke-linecap="round"
                stroke-linejoin="round"
                stroke-width="2"
                d="M19 7l-.867 12.142A2 2 0 0116.138 21H7.862a2 2 0 01-1.995-1.858L5 7m5 4v6m4-6v6m1-10V4a1 1 0 00-1-1h-4a1 1 0 00-1 1v3M4 7h16"
            />
          </svg>
        </span>
        <div class="plus" @click="append" v-if="++i == Imgs.length">+</div>
      </div>

    </div>
    <button class="imagebtn" style="float:right; margin-bottom: 30px;
    margin-right:10px; width:20% " @click="onUpload">OK</button>

  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "VueUploadImages", // vue component name
  data() {
    return {
      selectedFile: null,
      error: "",
      files: [],
      dropped: 0,
      Imgs: [],
    };
  },
  props: {
    max: Number,
    uploadMsg: String,
    maxError: String,
    fileError: String,
    clearAll: String,
  },
  methods: {
    dragOver() {
      this.dropped = 2;
    },
    dragLeave() {},
    drop(e) {
      let status = true;
      let files = Array.from(e.dataTransfer.files)
      if (e && files) {
        files.forEach((file) => {
          if (file.type.startsWith("image") === false) status = false;
        });
        if (status == true) {
          if (
              this.$props.max &&
              files.length + this.files.length > this.$props.max
          ) {
            this.error = this.$props.maxError
                ? this.$props.maxError
                : `Maximum files is` + this.$props.max;
          } else {
            this.files.push(...files);
            this.previewImgs();
          }
        } else {
          this.error = this.$props.fileError
              ? this.$props.fileError
              : `Unsupported file type`;
        }
      }
      this.dropped = 0;
    },
    append() {
      this.$refs.uploadInput.click();
    },
    readAsDataURL(file) {
      return new Promise(function (resolve, reject) {
        let fr = new FileReader();
        fr.onload = function () {
          resolve(fr.result);
        };
        fr.onerror = function () {
          reject(fr);
        };
        fr.readAsDataURL(file);
      });
    },
    deleteImg(index) {
      this.Imgs.splice(index, 1);
      this.files.splice(index, 1);
      this.$emit("changed", this.files);
      this.$refs.uploadInput.value = null;
    },
    previewImgs(event) {
      if (
          this.$props.max &&
          event &&
          event.currentTarget.files.length + this.files.length > this.$props.max
      ) {
        this.error = this.$props.maxError
            ? this.$props.maxError
            : `Maximum files is` + this.$props.max;
        return;
      }
      if (this.dropped == 0) this.files.push(...event.currentTarget.files);
      this.error = "";
      this.$emit("changed", this.files);
      let readers = [];
      if (!this.files.length) return;
      for (let i = 0; i < this.files.length; i++) {
        readers.push(this.readAsDataURL(this.files[i]));
      }
      Promise.all(readers).then((values) => {
        this.Imgs = values;
      });
    },
    reset() {
      this.$refs.uploadInput.value = null;
      this.Imgs = [];
      this.files = [];
      this.$emit("changed", this.files);
    },
    onUpload() {
      const fd = new FormData();

      fd.append('image', this.selectedFile, this.selectedFile.name)
      console.log(fd)
      axios.post('#' , fd, {
        onUploadProgress: uploadEvent => {
          console.log('Upload Progress' + Math.round(uploadEvent.loaded / uploadEvent.total *100))
        }
      })
          .then(res => {
            console.log(res)
            // res
          })
    }
  },
};
</script>

<style scoped>
.container {
  width: 80%;
  height: 20%;
  background: #FFFAF4;
  border-radius: 10px;
  padding: 20px;
  position: relative;
  margin-left: 18px;
}
.drop {
  width: 100%;
  height: 50%;
  top: 0;
  border-radius: 10px;
  position: absolute;
  background-color: #f4f6ff;
  left: 0;
  border: 3px dashed #a3a8b1;
}
.error {
  text-align: center;
  color: red;
  font-size: 15px;
}
.beforeUpload {
  position: relative;
  text-align: center;
}
.beforeUpload input {
  width: 100%;
  margin: auto;
  height: 100%;
  opacity: 0;
  position: absolute;
  background: red;
  display: block;
}
.beforeUpload input:hover {
  cursor: pointer;
}
.beforeUpload .icon {
  width: 150px;
  margin: auto;
  display: block;
}
.imgsPreview .imageHolder {
  width: 70px;
  height: 70px;
  background: #fff;
  position: relative;
  border-radius: 10px;
  margin: 5px 5px;
  display: inline-block;
}
.imgsPreview .imageHolder img {
  object-fit: cover;
  width: 100%;
  height: 100%;
}
.imgsPreview .imageHolder .delete {
  position: absolute;
  top: 4px;
  right: 4px;
  width: 20px;
  height: 20px;
  color: #fff;
  background: red;
  border-radius: 50%;
}
.imgsPreview .imageHolder .delete:hover {
  cursor: pointer;
}
.imgsPreview .imageHolder .delete .icon {
  width: 50%;
  height: 50%;
  display: block;
  margin: 4px auto;
}
.imgsPreview .imageHolder .plus {
  color: #2d3748;
  background: #f7fafc;
  border-radius: 50%;
  font-size: 21pt;
  height: 30px;
  width: 30px;
  text-align: center;
  border: 1px dashed;
  line-height: 23px;
  position: absolute;
  right: -42px;
  bottom: 43%;
}
.plus:hover {
  cursor: pointer;
}
.clearButton {
  color: #2d3748;
  position: absolute;
  top: 7px;
  right: 7px;
  background: none;
  border: none;
  cursor: pointer;
}
</style>
