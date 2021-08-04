<template>
    <transition name="modal">
      <div class="modal-mask">
        <div class="modal-wrapper">
          <div class="modal-container">
            <div class="modal-header">
              <slot name="header">
                비잉하기 도움말
              </slot>
            </div>
            <hr class="under" >
            <div class="modal-body">
              <slot name="body">
                여행의 추억을 공유하세요! <br>
                꿀벌은 꽃이 있는 곳을 동료에게
                알리기 위해 춤을 추고 비잉하는 소리를 냅니다. <br>
                꿀벌이 되어 열심히 아름다운 곳을
                알려주세요!
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
  name: "FeedModal"
}
</script>

<style scoped>
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
