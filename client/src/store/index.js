import { createStore } from 'vuex'

export default createStore({
  state: {    
    isLogin: false,
    user: {
      id: "",
      email: "",
      password: "",
      intro: "",
      nickname: "",
      name: "",
      tier: 0,
      followerCnt: 0,
      followingCnt: 0,
      createdAt: "",
      profilePicSrc: "",
      profilePicName: "",
      emailConfirmation: 0,
      status: ''
    },
  },
  mutations: {
    setUserInfo(state, data) {
      state.user = data;
      state.isLogin = true;
    },
    logout(state) {
      state.isLogin = false;
      state.user=null;
    },
  },
  actions: {
    LOGOUT({commit}) {
      commit('logout');
      localStorage.removeItem("access-token");
    },
  },
  modules: {
  }
})