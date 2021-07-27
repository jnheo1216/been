<template>
  <div class="mainfeed">
    <div class="logo">
      <img alt="BEEN LOGO" src="/assets/logo.png">
    </div>

    <div class="hex">
      <ul class="infinite-list" v-infinite-scroll="load" style="overflow:auto">
        <li class="infinite-list-item">{{ i }}</li>
      </ul>
    </div>
  </div>
</template>

<script>
  export default {
    name: 'Feed',
    data () {
      return {
        count: 0
      }
    },
    methods: {
      load () {
        this.count += 2
      }
    }
  }
</script>

<style>
  .mainfeed {
    width: 375px;
    height: 812px;
    background-color: #ffe8c4
  }

  .hex {
    width: 25%;
    position: relative;
  }
</style>