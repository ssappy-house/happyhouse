<template>
  <b-row class="mb-1">
    <b-form @submit="onSubmit" @reset="onReset">
      <div class="card-profile-actions py-4 mt-lg-0">
        <div class="float-left">
          <base-button
            type="submit"
            size="sm"
            @click="moveList"
            class="btn btn-neutral btn-sm"
            >List</base-button
          >
        </div>
        <div class="float-right">
          <b-button type="default" size="sm" v-if="this.type === 'register'"
            >write</b-button
          >
          <b-button type="default" size="sm" v-else>update</b-button>
          <!-- <b-button type="reset" size="sm" @click="moveWrite">clear</b-button> -->
        </div>
      </div>
      <div class="row justify-content-center text-center">
        <div class="col-lg-8">
          <local-search-bar
            v-on:selectDongCode="setDongCode"
          ></local-search-bar>
        </div>
      </div>
      <b-col style="text-align: left">
        <br /><br />
        <b-form-group id="userid-group" label="작성자:" label-for="userid">
          <b-form-input
            id="userid"
            disabled
            v-model="localboard.userid"
            type="text"
            required
          ></b-form-input>
        </b-form-group>

        <!-- <b-form-group id="userid-group" label="작성일:" label-for="regtime">
          <b-form-input
            id="regtime"
            disabled
            v-model="localboard.regtime"
            type="text"
            required
          ></b-form-input>
        </b-form-group> -->

        <b-form-group id="subject-group" label="제목:" label-for="subject">
          <b-form-input
            id="subject"
            v-model="localboard.subject"
            type="text"
            required
            placeholder="제목 입력..."
            v-if="this.type === 'register'"
          ></b-form-input>
          <b-form-input
            id="subject"
            v-model="localboard.subject"
            type="text"
            required
            v-else
            >${localboard.subject}</b-form-input
          >
        </b-form-group>

        <b-form-group id="content-group" label="내용:" label-for="content">
          <b-form-textarea
            id="content"
            v-model="localboard.content"
            placeholder="내용 입력..."
            rows="10"
            max-rows="15"
          ></b-form-textarea>
        </b-form-group>
      </b-col>
    </b-form>
  </b-row>
</template>

<script>
import { writeBoard, getBoard, modifyBoard } from "@/api/board";
import { mapState } from "vuex";
//import BoardBus from "@/bus/board";

import LocalSearchBar from "@/components/localboard/LocalSearchBar";

const memberStore = "memberStore";

export default {
  name: "LocalboardInputItem",
  components: {
    LocalSearchBar,
  },
  data() {
    return {
      localboard: {
        boardno: 0,
        dongCode: "",
        userid: "",
        subject: "",
        content: "",
        regtime: "",
      },
    };
  },
  props: {
    type: { type: String },
  },
  computed: {
    ...mapState(memberStore, ["userInfo"]),
  },
  created() {
    if (this.type === "modify") {
      getBoard(
        this.$route.params.boardno,
        ({ data }) => {
          this.localboard = data;
        },
        (error) => {
          console.log(error);
        },
      );
      this.isUserid = true;
    } else {
      this.localboard.userid = this.userInfo.userid;
    }
    //this.getDongCode();
  },
  methods: {
    onSubmit(event) {
      event.preventDefault();

      let err = true;
      let msg = "";
      !this.localboard.userid &&
        ((msg = "작성자 입력해주세요"),
        (err = false),
        this.$refs.userid.focus());
      err &&
        !this.localboard.subject &&
        ((msg = "제목 입력해주세요"),
        (err = false),
        this.$refs.subject.focus());
      err &&
        !this.localboard.content &&
        ((msg = "내용 입력해주세요"),
        (err = false),
        this.$refs.content.focus());

      if (!err) alert(msg);
      else this.type === "register" ? this.registBoard() : this.modifyBoard();
    },
    onReset(event) {
      event.preventDefault();
      this.localboard.boardno = 0;
      this.localboard.subject = "";
      this.localboard.content = "";
      this.moveList();
    },
    registBoard() {
      //this.getDongCode();
      writeBoard(
        this.localboard,
        // {
        //   userid: this.localboard.userid,
        //   subject: this.localboard.subject,
        //   content: this.localboard.content,
        // },
        ({ data }) => {
          let msg = "등록 처리시 문제가 발생했습니다.";
          if (data === "success") {
            msg = "등록이 완료되었습니다.";
          }
          alert(msg);
          this.moveList();
        },
        (error) => {
          console.log(error);
        },
      );
    },
    modifyBoard() {
      modifyBoard(
        {
          boardno: this.localboard.boardno,
          userid: this.localboard.userid,
          subject: this.localboard.subject,
          content: this.localboard.content,
        },
        ({ data }) => {
          let msg = "수정 처리시 문제가 발생했습니다.";
          if (data === "success") {
            msg = "수정이 완료되었습니다.";
          }
          alert(msg);
          // 현재 route를 /list로 변경.
          this.moveList();
        },
        (error) => {
          console.log(error);
        },
      );
    },
    // getDongCode() {
    //   console.log("BoardBus.on => getDongCode");
    //   BoardBus.$on("selectedDongCode", function (value) {
    //     console.log(value);
    //     this.localboard.dongCode = value;
    //   });
    // },
    moveList() {
      this.$router.push({ name: "localboardList" });
    },
    setDongCode(dongCode) {
      this.localboard.dongCode = dongCode;
      console.log("dongCode :", this.localboard.dongCode);
    },
  },
};
</script>

<style></style>
