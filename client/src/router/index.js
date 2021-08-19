import { createRouter, createWebHistory } from 'vue-router'
import Signup from '@/views/User/Signup.vue'
import Login from '@/views/User/Login.vue'
import Feed from '@/views/Feed/Feed.vue'
import FeedWrite from '@/views/Feed/FeedWrite.vue'
import Search from '@/views/Search/Search.vue'
import Profile from '@/views/User/Profile.vue'
import Follow from '@/views/User/Follow.vue'
import NotFound from '@/components/NotFound/NotFound.vue'
import Introduction from '@/views/Introduction/Introduction.vue'
import MyPositionAdd from '@/components/MyMaps/MyPositionAdd.vue'
import UserProfile from '@/views/User/UserProfile.vue'
import ProfileEdit from '@/views/User/ProfileEdit.vue'
// import { mapGetters } from 'vuex'


// const requireAuth = () => async (to, from, next) => {
//   // let token = localStorage.getItem('jwt-auth-token');
//   if (!mapGetters.isLoggedIn) {
//     next('/');
//   }
// };


const routes = [
  {
    path: '/',
    name: 'Login',
    component: Login,
  },
  {
    path: '/introduction',
    name: 'Introduction',
    component: Introduction
  },
  {
    path: '/mymapadd',
    name: 'MyPositionAdd',
    component: MyPositionAdd
  },
  {
    path: '/signup',
    name: 'Signup',
    component: Signup,
    children:[
      {
        path: "",
        name: "SignupUserInfo",
        component: () => import("@/components/User/SignupUserInfo.vue")
      },
      {
        path: "favorite",
        name: "SignupUserFavorite",
        component: () => import("@/components/User/SignupUserFavorite.vue"),
        props: true
      },
      {
        path: "success",
        name: "SignupSuccess",
        component: () => import("@/components/User/SignupSuccess.vue")
      }
    ]
  },
  
  {
    path: "/feed",
    // beforeEnter: requireAuth(),
    name: "Feed",
    component: Feed,
    children:[
      {
        path:"",
        name:"Feed",
        component: ()=> import("@/components/Feed/FeedList.vue")
      },
      {
        path:":feedNumber",
        name:"FeedDetail",
        component: ()=> import("@/components/Feed/FeedDetail.vue")
      },
      {
        path:"follow",
        name:"FeedFollowList",
        component: ()=> import("@/components/Feed/FeedFollowList.vue")
      },
      {
        path:"favorite",
        name:"FavoriteList",
        component: ()=> import("@/components/Feed/FavoriteList.vue")
      },
    ],
    redirect: () => {
      return "/feed";
    }
  },
  
  {
    path: "/search",
    // beforeEnter: requireAuth(),
    name: "Search",
    component: Search,
    children:[
      {
        path: "",
        name: "SearchMain",
        component: ()=> import("@/components/Search/SearchMain.vue")
      },
      {
        path:"typesearch",
        name: "TypeSearch",
        component: ()=> import("@/components/Search/TypeSearch.vue")
      },
      {
        path:"peoplesearch",
        name: "PeopleSearch",
        component: ()=> import("@/components/Search/PeopleSearch.vue")
      },
      {
        path: "articlesearch",
        name: "ArticleSearch",
        component: ()=> import("@/components/Search/ArticleSearch.vue")
      }
    ],
  },
  
  {
    path: "/profile",
    // beforeEnter: requireAuth(),
    name: "Profile",
    component: Profile,
    children: [
      {
        path:":userId",
        name:"UserProfile",
        component: ()=> import("@/components/User/UserProfile.vue")
      },
    ],
  },
  {
    path: "/follow",
    // beforeEnter: requireAuth(),
    name: "Follow",
    component: Follow,
    children: [
      {
        path:"follower/:userId",
        name:"UserFollower",
        component: ()=> import("@/components/User/UserFollower.vue")
      },
      {
        path:"following/:userId",
        name:"UserFollowing",
        component: ()=> import("@/components/User/UserFollowing.vue")
      },
      {
        path:"followwait/:userId",
        name:"UserFollowWait",
        component: ()=> import("@/components/User/UserFollowWait.vue")
      },
    ]
  },

  {
      path: '/:pathMatch(.*)*',
      // redirect: "/404",
      component : NotFound
  },
  // FeedWrite_csy
  {
    path: '/write',
    // beforeEnter: requireAuth(),
    name: 'Write',
    component: FeedWrite,
    children: [
      {
        path: "",
        name: "FeedWrite1",
        component: ()=> import("@/components/Feed/FeedWrite1.vue")
      },
      {
        path: "/2",
        name: "FeedWrite2",
        component: ()=> import("@/components/Feed/FeedWrite2.vue")
      },
      {
        path: "/3",
        name: "FeedWrite3",
        component: ()=> import("@/components/Feed/FeedWrite3.vue")
      },
    ],
  },
  {
    path: '/userprofile/:userId',
    name: 'UserProfile2',
    component: UserProfile
  },
  {
    path: '/userprofile/edit/:userId',
    name: 'ProfileEdit',
    component: ProfileEdit
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
