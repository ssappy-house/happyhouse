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
      <div class="container shape-container d-flex">
        <div class="col px-0">
          <div class="row">
            <div class="col-lg-6">
              <h1 class="display-3 text-white">자유게시판</h1>
            </div>
          </div>
        </div>
      </div>
    </section>
    <section class="section section-skew">
      <div class="container">
        <card shadow class="card-profile mt--300" no-body>
          <div class="px-4">
            <div class="row justify-content-center">
              <div class="card-profile-actions py-4 mt-lg-0">
                <div class="float-left">
                  <base-button
                    type="submit"
                    size="sm"
                    @click="listBoard"
                    class="btn btn-neutral btn-sm"
                    >List</base-button
                  >
                </div>

                <div v-if="userCheck()">
                  <div class="float-right">
                    <base-button
                      type="default"
                      size="sm"
                      @click="moveModifyBoard"
                      class="btn btn btn-info"
                      >update</base-button
                    >
                    <base-button
                      size="sm"
                      @click="deleteBoard"
                      class="btn btn btn-info"
                      >delete</base-button
                    >
                  </div>
                </div>
              </div>

              <!-- <div class="p-5 mb-4 bg-light rounded-3">
                <div class="container-fluid py-5">
                  <div class="row g-5">
                    <div class="col-md-12">
                      <h4 style="display: inline-block; margin-right: 20px">
                        Subject
                      </h4>
                      <p style="display: inline-block">
                        {{ freeboard.subject }}
                      </p>

                      <h4
                        style="
                          display: inline-block;
                          margin-left: 100px;
                          margin-right: 20px;
                        "
                      >
                        ID
                      </h4>
                      <p style="display: inline-block">
                        {{ freeboard.userid }}
                      </p>

                      <h4
                        style="
                          display: inline-block;
                          margin-left: 100px;
                          margin-right: 20px;
                        "
                      >
                        Date
                      </h4>
                      {{ freeboard.regtime }}
                    </div>

                    <div class="col-md-12">
                      <h4 style="display: inline-block; margin-right: 20px">
                        Content
                      </h4>

                      {{ freeboard.content }}
                    </div>
                  </div>
                </div>
              </div> -->
              <b-col style="text-align: left">
                <br /><br />
                <b-form-group
                  id="userid-group"
                  label="작성자:"
                  label-for="userid"
                >
                  <b-form-input
                    id="userid"
                    disabled
                    v-model="freeboard.userid"
                    type="text"
                    required
                  ></b-form-input>
                </b-form-group>

                <b-form-group
                  id="userid-group"
                  label="작성일:"
                  label-for="regtime"
                >
                  <b-form-input
                    id="regtime"
                    disabled
                    v-model="freeboard.regtime"
                    type="text"
                    required
                  ></b-form-input>
                </b-form-group>

                <b-form-group
                  id="subject-group"
                  label="제목:"
                  label-for="subject"
                >
                  <b-form-input
                    id="subject"
                    disabled
                    v-model="freeboard.subject"
                    type="text"
                    required
                    readonly="readonly"
                  ></b-form-input>
                </b-form-group>

                <b-form-group
                  id="content-group"
                  label="내용:"
                  label-for="content"
                >
                  <b-form-textarea
                    id="content"
                    disabled
                    v-model="freeboard.content"
                    placeholder="내용 입력..."
                    rows="10"
                    max-rows="15"
                  ></b-form-textarea>
                </b-form-group>
              </b-col>
              <!-- <notice-input-item type="update" /> -->
            </div>
            <div class="mt-5 py-5 border-top text-center">
              <div class="row justify-content-center">
                <div class="col-lg-9">
                  <div>
                    <!-- <h2>댓글</h2> -->
                    <div class="input-group mb-3">
                      <div
                        class="input-comment"
                        align-items="flex-start"
                        style="margin-right: auto; width: 100%"
                      >
                        <b-form-group id="comment-group" label-for="comment">
                          <b-form-input
                            class="form-control"
                            id="comment"
                            v-model="comment.content"
                            type="text"
                            required
                            placeholder="내용 입력..."
                          ></b-form-input>
                        </b-form-group>
                      </div>
                      <div
                        class="input-group-append"
                        style="position: absolute; right: 0px"
                      >
                        <b-button
                          type="default"
                          size="sm"
                          @click="registComment"
                          style="height: 45px"
                          >write</b-button
                        >
                      </div>
                    </div>
                    <comment-item
                      v-for="item in comments"
                      :key="item.commentno"
                      v-bind:comment="item"
                      v-on:deleteComment="deleteComment"
                      v-on:updateComment="updateComment"
                    />
                  </div>
                </div>
              </div>
            </div>
          </div>
        </card>
      </div>
    </section>
  </div>
</template>

<script>
// import moment from "moment";
import { getBoard, deleteBoard } from "@/api/board";
import {
  listComment,
  writeComment,
  modifyComment,
  deleteComment,
} from "@/api/comment.js";
import CommentItem from "@/components/comment/CommentItem";

import { mapState } from "vuex";
const memberStore = "memberStore";

export default {
  name: "FreeboardDetail",
  components: {
    CommentItem,
  },
  data() {
    return {
      freeboard: {},
      comments: [],
      //boarduser: "",
      comment: {
        commentno: 0,
        boardno: 0,
        userid: "",
        content: "",
        regtime: "",
      },
    };
  },
  computed: {
    ...mapState(memberStore, ["userInfo"]),
    message() {
      if (this.freeboard.content)
        return this.freeboard.content.split("\n").join("<br>");
      return "";
    },
  },
  created() {
    //this.boarduser = this.freeboard.userid;
    this.comment.userid = this.userInfo.userid;
    //console.log(this.boarduser);

    getBoard(
      this.$route.params.boardno,
      (response) => {
        this.freeboard = response.data;
        this.comment.boardno = this.freeboard.boardno;
      },
      (error) => {
        console.log("삭제 실패", error);
      },
    );
    this.getcommentList();
  },
  methods: {
    listBoard() {
      this.$router.push({ name: "freeboardList" });
    },
    moveModifyBoard() {
      this.$router.replace({
        name: "freeboardModify",
        params: { boardno: this.freeboard.boardno },
      });
      //   this.$router.push({ path: `/notice/modify/${this.article.articleno}` });
    },
    deleteBoard() {
      if (confirm("삭제하시겠습니까?")) {
        deleteBoard(this.freeboard.boardno, () => {
          this.$router.push({ name: "freeboardList" });
        });
      }
    },
    getcommentList() {
      listComment(
        this.$route.params.boardno,
        (response) => {
          this.comments = response.data;
        },
        (error) => {
          console.log("삭제 실패", error);
        },
      );
    },
    registComment() {
      if (this.comment.content == "") {
        alert("작성한 댓글이 없습니다");
      } else {
        writeComment(
          this.comment,
          ({ data }) => {
            let msg = "등록 처리시 문제가 발생했습니다.";
            if (data === "success") {
              msg = "등록이 완료되었습니다.";
            }
            alert(msg);
            this.comment.content = "";
            this.getcommentList();
          },
          (error) => {
            console.log(error);
          },
        );
      }
    },
    updateComment(updateComment) {
      console.log(updateComment);
      modifyComment(
        updateComment,
        ({ data }) => {
          let msg = "수정 처리시 문제가 발생했습니다.";
          if (data === "success") {
            msg = "수정이 완료되었습니다.";
          }
          alert(msg);
          this.getcommentList();
        },
        (error) => {
          console.log(error);
        },
      );
    },
    deleteComment(commentno) {
      console.log("delete" + commentno);
      deleteComment(
        commentno,
        ({ data }) => {
          let msg = "삭제 처리시 문제가 발생했습니다.";
          if (data === "success") {
            msg = "삭제가 완료되었습니다.";
          }
          alert(msg);
          this.getcommentList();
        },
        (error) => {
          console.log(error);
        },
      );
    },
    userCheck() {
      return this.freeboard.userid === this.userInfo.userid;
    },
  },
};
</script>

<style></style>
