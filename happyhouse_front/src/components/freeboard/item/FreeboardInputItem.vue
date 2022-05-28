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
      <b-col style="text-align: left">
        <br /><br />
        <b-form-group id="userid-group" label="작성자:" label-for="userid">
          <b-form-input
            id="userid"
            disabled
            v-model="freeboard.userid"
            type="text"
            required
          ></b-form-input>
        </b-form-group>

        <!-- <b-form-group id="userid-group" label="작성일:" label-for="regtime">
          <b-form-input
            id="regtime"
            disabled
            v-model="freeboard.regtime"
            type="text"
            required
          ></b-form-input>
        </b-form-group> -->

        <b-form-group id="subject-group" label="제목:" label-for="subject">
          <b-form-input
            id="subject"
            v-model="freeboard.subject"
            type="text"
            required
            placeholder="제목 입력..."
            v-if="this.type === 'register'"
          ></b-form-input>
          <b-form-input
            id="subject"
            v-model="freeboard.subject"
            type="text"
            required
            v-else
            >${freeboard.subject}</b-form-input
          >
        </b-form-group>

        <b-form-group id="content-group" label="내용:" label-for="content">
          <b-form-textarea
            id="content"
            v-model="freeboard.content"
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

//import { getArticle, deleteArticle } from "@/api/notice";

const memberStore = "memberStore";

export default {
  name: "FreeboardInputItem",
  data() {
    return {
      freeboard: {
        boardno: 0,
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
          this.freeboard = data;
        },
        (error) => {
          console.log(error);
        },
      );
      this.isUserid = true;
    } else {
      this.freeboard.userid = this.userInfo.userid;
    }
  },
  methods: {
    onSubmit(event) {
      event.preventDefault();

      let err = true;
      let msg = "";
      !this.freeboard.userid &&
        ((msg = "작성자 입력해주세요"),
        (err = false),
        this.$refs.userid.focus());
      err &&
        !this.freeboard.subject &&
        ((msg = "제목 입력해주세요"),
        (err = false),
        this.$refs.subject.focus());
      err &&
        !this.freeboard.content &&
        ((msg = "내용 입력해주세요"),
        (err = false),
        this.$refs.content.focus());

      if (!err) alert(msg);
      else this.type === "register" ? this.registBoard() : this.modifyBoard();
    },
    onReset(event) {
      event.preventDefault();
      this.freeboard.boardno = 0;
      this.freeboard.subject = "";
      this.freeboard.content = "";
      this.moveList();
    },
    registBoard() {
      writeBoard(
        this.freeboard,
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
        this.freeboard,
        ({ data }) => {
          let msg = "수정 처리시 문제가 발생했습니다.";
          if (data === "success") {
            msg = "수정이 완료되었습니다.";
          }
          alert(msg);
          this.moveList();
        },
        (error) => {
          console.log(error);
        },
      );
    },
    moveList() {
      this.$router.push({ name: "freeboardList" });
    },
  },
};
</script>

<style></style>
