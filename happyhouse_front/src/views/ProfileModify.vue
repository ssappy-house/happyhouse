<template>
  <div class="profile-page">
    <section class="section-profile-cover section-shaped my-0">
      <div class="shape shape-style-1 shape-primary shape-skew alpha-4">
        <span></span>
        <span></span>
        <span></span>
        <span></span>
        <span></span>
        <span></span>
        <span></span>
      </div>
    </section>
    <section class="section section-skew">
      <div class="container">
        <card shadow class="card-profile mt--300" no-body>
          <div class="px-4">
            <div class="row justify-content-center">
              <div class="col-lg-3 order-lg-2">
                <div class="card-profile-image">
                  <a href="#">
                    <img
                      v-lazy="'img/happyhouse/profile.png'"
                      class="rounded-circle"
                    />
                  </a>
                </div>
              </div>
              <div
                class="col-lg-4 order-lg-3 text-lg-right align-self-lg-center"
              >
                <div class="card-profile-actions py-4 mt-lg-0">
                  <base-button
                    type="info"
                    size="sm"
                    class="float-right"
                    @click="updateSubmit"
                    >Correct</base-button
                  >
                  <!-- <base-button type="default" size="sm" class="float-right"
                    >Drop out</base-button
                  > -->
                </div>
              </div>
              <div class="col-lg-4 order-lg-1">
                <div class="card-profile-stats d-flex justify-content-center">
                  <!-- <div>
                    <span class="heading">22</span>
                    <span class="description">Friends</span>
                  </div>
                  <div>
                    <span class="heading">10</span>
                    <span class="description">Photos</span>
                  </div>
                  <div>
                    <span class="heading">89</span>
                    <span class="description">Comments</span>
                  </div> -->
                </div>
              </div>
            </div>
            <div class="text-center mt-5">
              <b-col style="text-align: left">
                <br /><br />
                <b-form-group id="userid-group" label="ID" label-for="userid">
                  <b-form-input
                    id="userid"
                    disabled
                    type="text"
                    required
                    v-model="user.userid"
                  ></b-form-input>
                </b-form-group>

                <b-form-group
                  id="username-group"
                  label="Name"
                  label-for="username"
                >
                  <b-form-input
                    id="username"
                    type="text"
                    required
                    v-model="user.username"
                  ></b-form-input>
                </b-form-group>

                <b-form-group
                  id="userpwd-group"
                  label="Password"
                  label-for="userpwd"
                >
                  <b-form-input
                    id="userpwd"
                    type="password"
                    required
                    v-model="user.userpwd"
                  ></b-form-input>
                </b-form-group>

                <b-form-group id="email-group" label="Email" label-for="email">
                  <b-form-input
                    id="email"
                    type="email"
                    required
                    v-model="user.email"
                  ></b-form-input>
                </b-form-group>

                <b-form-group
                  id="address-group"
                  label="Address"
                  label-for="address"
                >
                  <b-form-input
                    id="address"
                    type="text"
                    required
                    v-model="user.address"
                  ></b-form-input>
                </b-form-group>
              </b-col>

              <!-- <div class="h6 font-weight-300">
                <i class="ni location_pin mr-2"></i>{{ userInfo.email }}
              </div>
              <div class="h6 mt-4">
                <i class="ni business_briefcase-24 mr-2"></i
                >{{ userInfo.address }}
              </div> -->

              <!-- <div>
                <i class="ni education_hat mr-2"></i>University of Computer
                Science
              </div> -->
            </div>
            <div class="mt-5 py-5 border-top text-center">
              <!-- <div class="row justify-content-center">
                <div class="col-lg-9">
                  <p>
                    An artist of considerable range, Ryan — the name taken by
                    Melbourne-raised, Brooklyn-based Nick Murphy — writes,
                    performs and records all of his own music, giving it a warm,
                    intimate feel with a solid groove structure. An artist of
                    considerable range.
                  </p>
                  <a href="#">Show more</a>
                </div>
              </div> -->
            </div>
          </div>
        </card>
      </div>
    </section>
  </div>
</template>

<script>
import { mapState, mapActions } from "vuex";

import { modifyU } from "@/api/member";

const memberStore = "memberStore";

export default {
  name: "MemberMyPageModify",
  data() {
    return {
      user: {
        userid: "",
        username: "",
        userpwd: "",
        email: "",
        address: "",
      },
    };
  },
  created() {
    this.user.userid = this.userInfo.userid;
    this.user.username = this.userInfo.username;
    this.user.email = this.userInfo.email;
    this.user.address = this.userInfo.address;
  },
  components: {},
  computed: {
    ...mapState(memberStore, ["userInfo"]),
  },
  methods: {
    ...mapActions(memberStore, ["updateUserInfo"]),
    updateSubmit() {
      let err = true;
      let msg = "";

      !this.user.username && ((msg = "이름을 입력하세요"), (err = false));
      //this.$refs.username.focus());
      err &&
        !this.user.userpwd &&
        ((msg = "비밀번호를 입력하세요"), (err = false));
      //this.$refs.userpwd.focus());
      err && !this.user.email && ((msg = "이메일을 입력하세요"), (err = false));
      //, this.$refs.email.focus());
      err && !this.user.address && ((msg = "주소를 입력하세요"), (err = false));
      //this.$refs.address.focus());

      if (!err) alert(msg);
      else this.modifyUser();
    },
    modifyUser() {
      console.log(this.user);
      modifyU(
        {
          userid: this.user.userid,
          username: this.user.username,
          userpwd: this.user.userpwd,
          email: this.user.email,
          address: this.user.address,
        },
        ({ data }) => {
          let msg = "수정에 실패하였습니다.";
          if (data === "success") {
            msg = "수정 완료!";
            this.updateUserInfo(this.user);
          }
          alert(msg);
          this.$router.push({ name: "profile" });
        },
        (error) => {
          let msg = "수정에 실패하였습니다.";
          alert(msg);
          console.log(error);
        },
      );
    },
  },
};
</script>

<style>
.font-weight-light {
  font-size: 20px;
}
</style>
