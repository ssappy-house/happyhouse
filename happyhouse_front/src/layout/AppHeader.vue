<template>
  <header class="header-global">
    <base-nav class="navbar-main" transparent type="" effect="light" expand>
      <router-link slot="brand" class="navbar-brand mr-lg-5" to="/">
        <img
          src="img/happyhouse/happyhouse_white.png"
          alt="happyhouse_white"
          style="width: 150px; height: 40px"
        />
      </router-link>

      <div class="row" slot="content-header" slot-scope="{ closeMenu }">
        <div class="col-6 collapse-brand">
          <a href="#">
            <img
              src="img/happyhouse/happyhouse_white.png"
              style="width: 150px; height: 40px"
            />
          </a>
        </div>
        <div class="col-6 collapse-close">
          <close-button
            @click="closeMenu"
            style="background-color: white"
          ></close-button>
        </div>
      </div>

      <ul class="navbar-nav navbar-nav-hover align-items-lg-center">
        <div class="nav-item">
          <a href="#" class="nav-link" role="button" style="hover">
            <router-link :to="{ name: 'house' }" class="hov">Apt</router-link>
          </a>
        </div>
        <div class="nav-item">
          <a href="#" class="nav-link" role="button" style="hover">
            <router-link to="/aptstore" class="hov">Store</router-link>
          </a>
        </div>
        <!-- <div class="nav-item">
          <a href="#" class="nav-link" role="button" style="hover">
            <router-link to="/landing" class="hov">Community</router-link>
          </a>
        </div> -->
        <div class="nav-item">
          <li class="navbar-nav align-items-lg-center ml-lg-auto">
            <base-dropdown tag="li" class="nav-item">
              <a
                slot="title"
                href="#"
                class="nav-link nav-link-icon"
                data-toggle="dropdown"
                role="button"
              >
                <span class="hov"> Community </span>
              </a>
              <router-link to="/Freeboard" class="dropdown-item"
                >자유게시판</router-link
              >
              <router-link to="/LocalBoard" class="dropdown-item"
                >지역게시판</router-link
              >
            </base-dropdown>
          </li>
        </div>
        <div class="nav-item">
          <a href="#" class="nav-link" role="button" style="hover">
            <router-link to="/notice" class="hov">Notice</router-link>
          </a>
        </div>
      </ul>

      <ul class="navbar-nav navbar-nav-hover align-items-lg-center ml-lg-auto">
        <div
          v-if="userInfo"
          class="navbar-nav navbar-nav-hover align-items-lg-center ml-lg-auto"
        >
          <ul class="navbar-nav navbar-nav-hover align-items-lg-center">
            <div class="nav-item">
              <a class="nav-link">
                <!-- <a
                href="#"
                class="badge badge-pill badge-primary"
                variant="primary"
                style="color: white"
                v-text="userInfo ? userInfo.userid.charAt(0).toUpperCase() : ''"
              ></a> -->
                <i class="ni ni-circle-08" style="hover;"></i>
                <i> {{ userInfo.username }}({{ userInfo.userid }})님</i>
              </a>
            </div>
            <div class="nav-item">
              <a href="#" class="nav-link" role="button" style="hover">
                <router-link to="/profile" class="hov">MyPage</router-link>
              </a>
            </div>
            <div class="nav-item">
              <a class="nav-link">
                <a
                  href="#"
                  role="button"
                  class="hov"
                  @click.prevent="onClickLogout"
                >
                  Logout
                </a>
              </a>
              <!-- <router-link to="/landing" class="ho">Logout</router-link> -->
            </div>
          </ul>
        </div>

        <div v-else>
          <li class="navbar-nav align-items-lg-center ml-lg-auto">
            <base-dropdown tag="li" class="nav-item">
              <a
                slot="title"
                href="#"
                class="nav-link nav-link-icon"
                data-toggle="dropdown"
                role="button"
              >
                <span class="ho">
                  <i class="ni ni-single-02 ni-2x" style="hover;"></i>
                </span>
              </a>
              <!-- <router-link to="/landing" class="dropdown-item">Login</router-link>
          <router-link to="/profile" class="dropdown-item">Profile</router-link> -->
              <router-link to="/Login" class="dropdown-item">Login</router-link>
              <router-link to="/Register" class="dropdown-item"
                >Sign Up</router-link
              >
            </base-dropdown>
          </li>
        </div>
      </ul>
    </base-nav>
  </header>
</template>
<script>
import BaseNav from "@/components/BaseNav";
import BaseDropdown from "@/components/BaseDropdown";
import CloseButton from "@/components/CloseButton";

import { mapState, mapMutations } from "vuex";

const memberStore = "memberStore";

export default {
  components: {
    BaseNav,
    CloseButton,
    BaseDropdown,
  },
  computed: {
    ...mapState(memberStore, ["isLogin", "userInfo"]),
  },
  methods: {
    ...mapMutations(memberStore, ["SET_IS_LOGIN", "SET_USER_INFO"]),
    onClickLogout() {
      // console.log("memberStore : ", ms);
      this.SET_IS_LOGIN(false);
      this.SET_USER_INFO(null);
      sessionStorage.removeItem("access-token");
      if (this.$route.path != "/") this.$router.push({ name: "home" });
    },
  },
};
</script>
<style>
.hov {
  text-decoration: none;
  color: white;
  font-size: 14.4px;
}
.hov:hover {
  color: lightgray;
}
i {
  color: white;
}
div.collapse.navbar-collapse.show {
  background-color: rgba(25, 30, 75);
}
</style>
