<template>
  <div>
    <h1>FeedList</h1>

    <div class="wrap">
      <div class="hex">
        <p>Picture</p>
          <!-- <div class="content">
            <ul class="infinite-list" v-infinite-scroll="load" style="overflow:auto">
              <li class="infinite-list-item">{{ i }}</li>
              <router-link to="">
            </ul>
          </div> -->

      </div>
      <div class="logos-wrap">
       <div class="restaurant"></div>
       <div class="hotel"></div>
       <div class="tour"></div>
      </div>
    </div>

  </div>
</template>

<script>

export default {
  name: 'FeedList',
}
</script>

<style>
  .wrap {
    width: 60%;
    margin: 100px auto;
    display: flex;
    flex-wrap: wrap;
  }

  .hex {
    width: 4vw;
    height: calc(4 * 1.7320vw);
    position: relative;
    background: #000000;
    border: 5px solid pink;

  }

  .hex::before {
    content: '';
    display: block;
    width: 100%;
    height: 100%;
    background: inherit;
    position: absolute;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%) rotate(60deg);
    border-top: 5px solid pink;
    border-bottom: 5px solid pink;
    
  }

  .hex::after {
    content: '';
    display: block;
    width: 100%;
    height: 100%;
    background: inherit;
    position: absolute;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%) rotate(-60deg);
    border-top: 5px solid pink;
    border-bottom: 5px solid pink;
    /* box-shadow: 5px none 3px 1.5px gray; */
  }

/* 
  .content {
    display: flex;
    width: 100%;
    height: 100%;
    overflow: hidden;
    justify-content: center;
    align-items: center;
  } */

  .logos-wrap {
    display: flex column;
    justify-content: space-evenly;
    align-items: stretch;
  }

  .restaurant {
    margin-left: 50px;
    margin-top: 5px;
    margin-bottom: 5px;
    width: 10px;
    height: 10px;
    border-radius: 5px;
    background-color: green;
  }

  .hotel {
    margin-left: 50px;
    margin-top: 5px;
    margin-bottom: 5px;
    width: 10px;
    height: 10px;
    border-radius: 5px;
    background-color: yellowgreen;
  }

  .tour {
    margin-left: 50px;
    margin-top: 5px;
    margin-bottom: 5px;
    width: 10px;
    height: 10px;
    border-radius: 5px;
    background-color: coral;
  }
</style>