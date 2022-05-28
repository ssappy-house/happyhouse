import Vue from "vue";
import Router from "vue-router";
import AppHeader from "./layout/AppHeader";
import AppFooter from "./layout/AppFooter";
import Components from "./views/Components.vue";
import Landing from "./views/Landing.vue";

import Login from "./views/Login.vue";
import Register from "./views/Register.vue";
import Profile from "./views/Profile.vue";
import ProfileModify from "./views/ProfileModify.vue";

import House from "./views/HouseView.vue";
import Aptstore from "./views/AptStoreView.vue";
import Freeboard from "./views/FreeboardView.vue";
import Localboard from "./views/LocalboardView.vue";
import Notice from "./views/NoticeView.vue";

import store from "@/store/index.js";

Vue.use(Router);

// https://router.vuejs.org/kr/guide/advanced/navigation-guards.html
const onlyAuthUser = async (to, from, next) => {
  // console.log(store);
  const checkUserInfo = store.getters["memberStore/checkUserInfo"];
  const getUserInfo = store._actions["memberStore/getUserInfo"];
  let token = sessionStorage.getItem("access-token");
  if (checkUserInfo == null && token) {
    await getUserInfo(token);
  }
  if (checkUserInfo === null) {
    alert("로그인이 필요한 페이지입니다..");
    next({ name: "login" });
    // router.push({ name: "signIn" });
  } else {
    // console.log("로그인 했다.");
    next();
  }
};

// const noAptSelect = async (to, from, next) => {
//   // console.log(store);
//   const checkUserInfo = store.getters["houseStore/checkAptSelect"];
//   const getUserInfo = store._actions["memberStore/getUserInfo"];
//   let token = sessionStorage.getItem("access-token");
//   if (checkUserInfo == null && token) {
//     await getUserInfo(token);
//   }
//   if (checkUserInfo === null) {
//     alert("로그인이 필요한 페이지입니다..");
//     next({ name: "login" });
//     // router.push({ name: "signIn" });
//   } else {
//     // console.log("로그인 했다.");
//     next();
//   }
// };

export default new Router({
  linkExactActiveClass: "active",
  mode: "hash",
  routes: [
    {
      path: "/",
      name: "home",
      components: {
        header: AppHeader,
        default: Components,
        footer: AppFooter,
      },
    },
    {
      path: "/landing",
      name: "landing",
      components: {
        header: AppHeader,
        default: Landing,
        footer: AppFooter,
      },
    },

    /*----------------------------User------------------------------*/
    /*----------------------------User------------------------------*/
    /*----------------------------User------------------------------*/
    {
      path: "/login",
      name: "login",
      components: {
        header: AppHeader,
        default: Login,
        footer: AppFooter,
      },
    },
    {
      path: "/register",
      name: "register",
      components: {
        header: AppHeader,
        default: Register,
        footer: AppFooter,
      },
    },
    {
      path: "/profile",
      name: "profile",
      components: {
        header: AppHeader,
        default: Profile,
        footer: AppFooter,
      },
    },
    {
      path: "/profileModify",
      name: "profileModify",
      components: {
        header: AppHeader,
        default: ProfileModify,
        footer: AppFooter,
      },
    },

    /*----------------------------APT-House-----------------------------*/
    /*----------------------------APT-House-----------------------------*/
    /*----------------------------APT-House-----------------------------*/
    {
      path: "/house",
      name: "house",
      beforeEnter: onlyAuthUser,
      components: {
        header: AppHeader,
        default: House,
        footer: AppFooter,
      },
    },
    {
      path: "/aptstore",
      name: "aptstore",
      beforeEnter: onlyAuthUser,
      components: {
        header: AppHeader,
        default: Aptstore,
        footer: AppFooter,
      },
      props: true,
    },

    /*----------------------------Board------------------------------*/
    /*----------------------------Board------------------------------*/
    /*----------------------------Board------------------------------*/
    {
      path: "/freeboard",
      name: "freeboard",
      beforeEnter: onlyAuthUser,
      components: {
        header: AppHeader,
        default: Freeboard,
        footer: AppFooter,
      },
      component: () => import("@/views/FreeboardView.vue"),
      redirect: "/freeboard/list",
      children: [
        {
          path: "list",
          name: "freeboardList",
          component: () => import("@/components/freeboard/FreeboardList.vue"),
        },
        {
          path: "write",
          name: "freeboardRegister",
          beforeEnter: onlyAuthUser,
          component: () =>
            import("@/components/freeboard/FreeboardRegister.vue"),
        },
        {
          path: "detail/:boardno",
          name: "freeboardDetail",
          beforeEnter: onlyAuthUser,
          component: () => import("@/components/freeboard/FreeboardDetail.vue"),
        },
        {
          path: "modify/:boardno",
          name: "freeboardModify",
          beforeEnter: onlyAuthUser,
          component: () => import("@/components/freeboard/FreeboardModify.vue"),
        },
      ],
    },
    {
      path: "/localboard",
      name: "localboard",
      beforeEnter: onlyAuthUser,
      components: {
        header: AppHeader,
        default: Localboard,
        footer: AppFooter,
      },
      component: () => import("@/views/LocalboardView.vue"),
      redirect: "/localboard/list",
      children: [
        {
          path: "list",
          name: "localboardList",
          component: () => import("@/components/localboard/LocalboardList.vue"),
        },
        {
          path: "write",
          name: "localboardRegister",
          beforeEnter: onlyAuthUser,
          component: () =>
            import("@/components/localboard/LocalboardRegister.vue"),
        },
        {
          path: "detail/:boardno",
          name: "localboardDetail",
          beforeEnter: onlyAuthUser,
          component: () =>
            import("@/components/localboard/LocalboardDetail.vue"),
        },
        {
          path: "modify/:boardno",
          name: "localboardModify",
          beforeEnter: onlyAuthUser,
          component: () =>
            import("@/components/localboard/LocalboardModify.vue"),
        },
      ],
    },

    /*----------------------------Notice------------------------------*/
    /*----------------------------Notice------------------------------*/
    /*----------------------------Notice------------------------------*/
    {
      path: "/notice",
      name: "notice",
      beforeEnter: onlyAuthUser,
      components: {
        header: AppHeader,
        default: Notice,
        footer: AppFooter,
      },
      component: () => import("@/views/NoticeView.vue"),
      redirect: "/notice/list",
      children: [
        {
          path: "list",
          name: "noticeList",
          component: () => import("@/components/notice/NoticeList.vue"),
        },
        {
          path: "write",
          name: "noticeRegister",
          beforeEnter: onlyAuthUser,
          component: () => import("@/components/notice/NoticeRegister.vue"),
        },
        {
          path: "detail/:noticeno",
          name: "noticeDetail",
          beforeEnter: onlyAuthUser,
          component: () => import("@/components/notice/NoticeDetail.vue"),
        },
        {
          path: "modify/:noticeno",
          name: "noticeModify",
          beforeEnter: onlyAuthUser,
          component: () => import("@/components/notice/NoticeModify.vue"),
        },
      ],
    },
  ],

  scrollBehavior: (to) => {
    if (to.hash) {
      return { selector: to.hash };
    } else {
      return { x: 0, y: 0 };
    }
  },
});
