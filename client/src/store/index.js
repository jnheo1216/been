import { createStore } from 'vuex'

export default createStore({
  state: {    
    isLogin: false,
    // user: {
    //   id: "",
    //   email: "",
    //   password: "",
    //   intro: "",
    //   nickname: "",
    //   name: "",
    //   tier: 0,
    //   followerCnt: 0,
    //   followingCnt: 0,
    //   createdAt: "",
    //   profilePicSrc: "",
    //   profilePicName: "",
    //   emailConfirmation: 0,
    //   status: ''
    // },
    user: {},
    jwtAuthToken: localStorage.getItem('jwt-auth-token') || ''
  },
  getters: {
    isLoggedIn({ jwtAuthToken }) {
      return jwtAuthToken ? true : false
    }
  },
  mutations: {
    setUserInfo(state, data) {
      state.user = data;
      state.isLogin = true;
      state.jwtAuthToken = localStorage.getItem('jwt-auth-token');
    },
    logout(state) {
      localStorage.removeItem('jwt-auth-token')
      state.isLogin = false;
      state.user=null;
    },
  },
  actions: {
    LOGOUT({commit}) {
      commit('logout');
      localStorage.removeItem("jwt-auth-token");
    },
  },
  modules: {
  }
})