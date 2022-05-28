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
            v-model="notice.userid"
            type="text"
            required
          ></b-form-input>
        </b-form-group>

        <!-- <b-form-group id="userid-group" label="작성일:" label-for="regtime">
          <b-form-input
            id="regtime"
            disabled
            v-model="notice.regtime"
            type="text"
            required
          ></b-form-input>
        </b-form-group> -->

        <b-form-group id="subject-group" label="제목:" label-for="subject">
          <b-form-input
            id="subject"
            v-model="notice.subject"
            type="text"
            required
            placeholder="제목 입력..."
            v-if="this.type === 'register'"
          ></b-form-input>
          <b-form-input
            id="subject"
            v-model="notice.subject"
            type="text"
            required
            v-else
            >${notice.subject}</b-form-input
          >
        </b-form-group>

        <b-form-group id="content-group" label="내용:" label-for="content">
          <b-form-textarea
            id="content"
            v-model="notice.content"
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
import { writeArticle, getArticle, modifyArticle } from "@/api/notice";
import { mapState } from "vuex";

//import { getArticle, deleteArticle } from "@/api/notice";

const memberStore = "memberStore";

export default {
  name: "NoticeInputItem",
  data() {
    return {
      notice: {
        noticeno: 0,
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
      getArticle(
        this.$route.params.noticeno,
        ({ data }) => {
          // this.article.articleno = data.article.articleno;
          // this.article.userid = data.article.userid;
          // this.article.subject = data.article.subject;
          // this.article.content = data.article.content;
          this.notice = data;
        },
        (error) => {
          console.log(error);
        },
      );
      this.isUserid = true;
    } else {
      this.notice.userid = this.userInfo.userid;
    }
  },
  methods: {
    onSubmit(event) {
      event.preventDefault();

      let err = true;
      let msg = "";
      !this.notice.userid &&
        ((msg = "작성자 입력해주세요"),
        (err = false),
        this.$refs.userid.focus());
      err &&
        !this.notice.subject &&
        ((msg = "제목 입력해주세요"),
        (err = false),
        this.$refs.subject.focus());
      err &&
        !this.notice.content &&
        ((msg = "내용 입력해주세요"),
        (err = false),
        this.$refs.content.focus());

      if (!err) alert(msg);
      else
        this.type === "register" ? this.registArticle() : this.modifyArticle();
    },
    onReset(event) {
      event.preventDefault();
      this.notice.noticeno = 0;
      this.notice.subject = "";
      this.notice.content = "";
      this.$router.push({ name: "noticeList" });
    },
    registArticle() {
      writeArticle(
        {
          userid: this.notice.userid,
          subject: this.notice.subject,
          content: this.notice.content,
        },
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
    modifyArticle() {
      modifyArticle(
        {
          noticeno: this.notice.noticeno,
          userid: this.notice.userid,
          subject: this.notice.subject,
          content: this.notice.content,
        },
        ({ data }) => {
          let msg = "수정 처리시 문제가 발생했습니다.";
          if (data === "success") {
            msg = "수정이 완료되었습니다.";
          }
          alert(msg);
          // 현재 route를 /list로 변경.
          this.$router.push({ name: "noticeList" });
        },
        (error) => {
          console.log(error);
        },
      );
    },
    moveList() {
      this.$router.push({ name: "noticeList" });
    },
  },
};
</script>

<style></style>
