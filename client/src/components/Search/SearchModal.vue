<template>
  <transition name="modal">
      <div class="modal-mask">
        <div class="modal-wrapper">
          <div class="modal-container">
            <div class="modal-header">
              <slot name="header">
                검색 도움말
              </slot>
            </div>
            <hr class="under" >
            <div class="modal-body">
              <slot name="body">
                원하는 정보를 검색하세요! <br>
                1. 꿀벌 검색 : 닉네임으로 동료찾기 <br>
                2. 게시물 검색 : 게시물 제목 검색 <br>
                3. 여행 유형별 검색
              </slot>
            </div>
            <div class="modal-footer">
              <slot name="footer">
                  <img @click="$emit('close')" src="@/assets/image-logo-resize.png" alt="logobee" width="120"/>
              </slot>
            </div>
          </div>
        </div>
      </div>
    </transition>
</template>

<script>
export default {
  name: 'SearchModal'
}
</script>

<style>
.modal-mask {
  position: fixed;
  z-index: 9998;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, .5);
  display: table;
  transition: opacity .3s ease;
}

.modal-wrapper {
  display: table-cell;
  vertical-align: middle;
}

.modal-container {
  width: 300px;
  margin: 10px auto;
  padding: 5px 10px;
  background-color: #fff;
  border-radius: 2px;
  box-shadow: 0 2px 8px rgba(0, 0, 0, .33);
  transition: all .3s ease;
  font-family: Helvetica, Arial, sans-serif;
}

.modal-header h3 {
  margin-top: 0;
  color: #42b983;
  margin-bottom: 20px;
}

.modal-body {
  margin: 20px 0;
}
.under {
  margin-left: 30px;
  margin-top: 10px;
  width: 80%;
}


/*
 * The following styles are auto-applied to elements with
 * transition="modal" when their visibility is toggled
 * by Vue.js.
 *
 * You can easily play with the modal transition by editing
 * these styles.
 */


.modal-enter .modal-container,
.modal-leave-active .modal-container {
  -webkit-transform: scale(1.1);
  transform: scale(1.1);
}
</style>