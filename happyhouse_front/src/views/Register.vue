<template>
  <section class="section section-shaped section-lg my-0">
    <div class="shape shape-style-1 bg-gradient-default">
      <span></span>
      <span></span>
      <span></span>
      <span></span>
      <span></span>
      <span></span>
      <span></span>
      <span></span>
    </div>
    <div class="container pt-lg-md">
      <div class="row justify-content-center">
        <div class="col-lg-5">
          <card
            shadow
            header-classes="bg-white pb-5"
            body-classes="px-lg-5 py-lg-5"
            class="border-0"
          >
            <template>
              <div class="text-muted text-center mb-3">
                <small>Sign in with</small>
              </div>
              <div class="btn-wrapper text-center">
                <base-button type="neutral">
                  <img slot="icon" src="img/icons/common/github.svg" />
                  Github
                </base-button>

                <base-button type="neutral">
                  <img slot="icon" src="img/icons/common/google.svg" />
                  Google
                </base-button>
              </div>
            </template>
            <template>
              <div class="text-center text-muted mb-4">
                <small>Or sign up with credentials</small>
              </div>
              <b-form @submit="onSubmitR" @reset="onResetR">
                <base-input
                  id="userid"
                  v-model="user.userid"
                  class="mb-3"
                  placeholder=" ID"
                  required
                  type="text"
                  addon-left-icon="ni ni-key-25"
                >
                </base-input>
                <base-input
                  id="username"
                  v-model="user.username"
                  type="text"
                  class="mb-3"
                  placeholder=" Name"
                  required
                  addon-left-icon="ni ni-circle-08"
                >
                </base-input>
                <base-input
                  id="userpwd"
                  v-model="user.userpwd"
                  alternative
                  type="password"
                  placeholder=" Password"
                  required
                  addon-left-icon="ni ni-lock-circle-open"
                >
                </base-input>
                <base-input
                  id="email"
                  v-model="user.email"
                  alternative
                  type="text"
                  class="mb-3"
                  placeholder=" Email"
                  required
                  addon-left-icon="ni ni-email-83"
                >
                </base-input>
                <base-input
                  id="address"
                  v-model="user.address"
                  alternative
                  class="mb-3"
                  placeholder=" Address"
                  required
                  addon-left-icon="ni ni-square-pin"
                >
                </base-input>
                <div class="text-muted font-italic">
                  <small
                    >password strength:
                    <span class="text-success font-weight-700">strong</span>
                  </small>
                </div>
                <!-- <base-checkbox>
                  <span
                    >I agree with the
                    <a href="#">Privacy Policy</a>
                  </span>
                </base-checkbox> -->
                <div class="text-center">
                  <b-button type="submit" class="my-4">Create account</b-button>
                </div>
              </b-form>
            </template>
          </card>
        </div>
      </div>
    </div>
  </section>
</template>

<script>
import { registU } from "@/api/member";
//import { mapState } from "vuex";

export default {
  name: "Register",
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
  methods: {
    onSubmitR(event) {
      event.preventDefault();

      console.log(this.$refs.userid);

      let err = true;
      let msg = "";
      !this.user.userid && ((msg = "ID를 입력해주세요"), (err = false));
      //, this.$refs.userid.focus());
      err &&
        !this.user.username &&
        ((msg = "이름을 입력해주세요"), (err = false));
      //.this.$refs.username.focus());
      err &&
        !this.user.userpwd &&
        ((msg = "비밀번호를 입력해주세요"), (err = false));
      //.this.$refs.userpwd.focus());
      err &&
        !this.user.email &&
        ((msg = "이메일을 입력해주세요"), (err = false));

      //.this.$refs.email.focus());
      err &&
        !this.user.address &&
        ((msg = "주소를 입력해주세요"), (err = false));
      //.this.$refs.address.focus());

      if (!err) alert(msg);
      else this.register();
    },
    onResetR(event) {
      event.preventDefault();
      this.user.userid = "";
      this.user.username = "";
      this.user.userpwd = "";
      this.user.email = "";
      this.user.address = "";
    },
    register() {
      registU(
        {
          userid: this.user.userid,
          username: this.user.username,
          userpwd: this.user.userpwd,
          email: this.user.email,
          address: this.user.address,
        },
        ({ data }) => {
          let msg = "등록 처리시 문제가 발생했습니다.";
          if (data === "success") {
            msg = "등록이 완료되었습니다.";
          }
          alert(msg);

          this.$router.push({ name: "login" });
        },
        (error) => {
          let msg = "등록 처리시 문제가 발생했습니다.";
          alert(msg);
          console.log(error);
        },
      );
    },
  },
};
</script>
<style scoped>
card {
  background-color: white;
}
</style>
