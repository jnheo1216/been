import { createStore } from 'vuex'
import router from '../router'

export default createStore({
  state: {
    user: {},
    jwtAuthToken: localStorage.getItem('jwt-auth-token') || '',
    postData: {
      area: "",
      content: "",
      createdAt: "",
      postId: 0,
      postPicName: "",
      postPicSrc: "",
      style: "",
      title: "",
      updatedAt: "",
      userId: 0
    },
    postPics: []
  },
  getters: {
    isLoggedIn({ jwtAuthToken }) {
      return jwtAuthToken ? true : false
    }
  },
  mutations: {
    setUserInfo(state, data) {
      state.user = data;
      state.jwtAuthToken = localStorage.getItem('jwt-auth-token');
    },
    LOGOUT(state) {
      localStorage.removeItem("jwt-auth-token")
      state.jwtAuthToken = ''
      localStorage.removeItem('userId')
      state.user = ''
    },
  },
  actions: {
    logout({ commit }) {
      commit('LOGOUT');
      router.push({name: 'Login'})
    },
  },
  modules: {
  }
})