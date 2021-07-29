<template>
  <suspense>
    <template #default>
      <div>
        <form @submit="onSubmit">
          <div class="">
            <label class="" for="user-id">이메일</label><br>
            <input 
              id="user-id"
              v-model="email"
              type="text"
              class=""
              placeholder="아이디를 입력하세요."
            >
            <div>
              <div v-if="error.email">{{ error.email }}</div>
            </div>
          </div>
          
          <div class="">
            <label class="" for="user-pw">비밀번호</label><br>
            <input 
              id="user-pw"
              v-model="password"
              type="password"
              class="" 
              placeholder="비밀번호를 입력하세요." 
            >
            <div>
              <div v-if="error.password">{{ error.password }}</div>
            </div>
          </div>

          <div class="">
            <label class="" for="userpw_check">비밀번호 확인</label><br>
            <input 
              id="userpw_check"
              v-model="passwordCheck"
              type="password"
              class="" 
              placeholder="비밀번호를 다시 입력해주세요." 
            >
            <div>
              <div v-if="error.passwordCheck">{{ error.passwordCheck }}</div>
            </div>
          </div>

          <button @click="onSubmit">다음</button>

        </form>
      </div>
    </template>

    <template #fallback>
      <div>Loading...</div>
    </template>
  </suspense>
</template>


<script>
import PV from "password-validator";
import * as EmailValidator from "email-validator";

export default {
  name: 'SignupUserInfo',
  data: () => {
    return {
      username: "",
      email: "",
      password: "",
      passwordCheck: "",
      passwordSchema: new PV(),
      error:{
        username: false,
        email: false,
        password: false,
        passwordCheck: false,
      },
      isChecked:false,
      component: this,
    }
  },
  created(){
    this.component = this;
    this.passwordSchema
      .is()
      .min(8)
      .is()
      .max(20)
      .has()
      .digits()
      .has()
      .letters();
  },
  watch: {
    username: function() {
      this.isChecked=false;
      this.error.username='닉네임 중복여부를 확인해주세요.';
    },
    password: function() {
      this.checkForm();
    },
    passwordCheck: function() {
      this.checkForm();
    },
    email: function() {
      this.checkForm();
    }
  },  
  methods: {
    checkForm(){
      if(this.email.length >= 0 && !EmailValidator.validate(this.email))
        this.error.email ="이메일 형식이 아닙니다";
      else this.error.email=false;
      if(this.password.length >= 0 && !this.passwordSchema.validate(this.password))
        this.error.password = "영문, 숫자 포함 8 자리 이상이어야 합니다";
      else this.error.password = false;
      if(this.passwordCheck.length >= 0 && !this.passwordSchema.validate(this.passwordCheck))
        this.error.passwordCheck = "영문, 숫자 포함 8 자리 이상이어야 합니다";
      else if(this.passwordCheck.length >= 0 && this.password!==this.passwordCheck)
        this.error.passwordCheck = "비밀번호가 일치하지 않습니다."
      else this.error.passwordCheck = false;
    },
    onSubmit(event){
      event.preventDefault();
      if(this.error.username){
        alert(this.error.username);
      }
      else if(this.error.email){
        alert(this.error.email);
      }
      else if(this.error.password){
        alert(this.error.password);
      }
      else if(this.error.passwordCheck){
        alert(this.error.passwordCheck);
      }
      else{
        const user={
          userName: this.username,
          userId: this.email,
          userPw: this.password,
        }
        this.$router.push({
          name:"SignupUserFavorite",
          params:{
            ...user,
          }
        })
      }
    }
  }
}
</script>

<style>

</style>