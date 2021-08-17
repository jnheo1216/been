<template>
<div>
 <!-- <div v-if="isLoggedIn">
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
      <div class="container-fluid">
        <a class="navbar-brand" href="#">Navbar</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
          <ul class="navbar-nav">
            <li class="nav-item">
              <a class="nav-link active" aria-current="page" href="#">Home</a>
            </li>
            <li class="nav-item" @click="this.$router.push('/feed')">
              <a class="nav-link" href="#">feed</a>
            </li>
            <li class="nav-item" @click="this.$router.push('/profile')">
              <a class="nav-link" href="#" >profile</a>
            </li>
            <li class="nav-item" @click="logout">
              <a class="nav-link">logout</a>
            </li>
            <li class="nav-item">
              <a class="nav-link disabled" href="#" tabindex="-1" aria-disabled="true">Disabled</a>
            </li>
          </ul>
        </div>
      </div>
    </nav>
  </div> -->

<div>
  <router-view/>
</div>

  <ul class="nav justify-content-center" v-if="isLoggedIn" style="position: fixed; bottom: 0; left: 0; right: 0;">
    <li class="nav-item align-self-end">
      <a class="nav-link active text-warning" data-bs-toggle="modal" data-bs-target="#staticBackdrop">Logout</a>
    </li>
    <li class="nav-item align-self-end">
      <router-link :to="'/feed/favorite'">
        <a class="nav-link text-warning">Curation</a>
      </router-link>
    </li>
    <li class="nav-item">
      <!-- <a class="nav-link text-warning" @click="goMain">Main</a> -->
      <router-link :to="'/feed'">
        <div class="button-box">
          <img src="./assets/beenMainbutton.png" alt="button" style="max-width: 100%;">
        </div>
      </router-link>
    </li>
    <li class="nav-item align-self-end">
      <router-link :to="'/search'">
        <a class="nav-link text-warning">Search</a>
      </router-link>
    </li>
    <li class="nav-item align-self-end">
      <router-link :to="'/profile'">
        <a class="nav-link text-warning">MyProfile</a>
      </router-link>
    </li>
  </ul>

  <div class="modal fade" id="staticBackdrop" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered" style="width: 310px;">
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
        </div>
        <div class="modal-body">
          로그아웃 하시겠습니까?
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">No</button>
          <button type="button" class="btn btn-danger" data-bs-dismiss="modal" @click="logout">LOGOUT</button>
        </div>
      </div>
    </div>
  </div>

</div>
</template>

<script>
import { mapGetters, mapActions } from 'vuex'

export default {
  name: 'App',
  computed: {
    ...mapGetters([
      'isLoggedIn'
    ]),
  },
  methods: {
    ...mapActions([
      'logout'
    ])
  },
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
.button-box {
  width: 60px;
}
</style>
