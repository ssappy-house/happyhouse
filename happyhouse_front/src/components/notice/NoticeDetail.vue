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
              <h1 class="display-3 text-white">Notice</h1>
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
                    @click="listArticle"
                    class="btn btn-neutral btn-sm"
                    >List</base-button
                  >
                </div>

                <div v-if="userCheck()">
                  <div class="float-right">
                    <b-button
                      type="default"
                      size="sm"
                      @click="moveModifyArticle"
                      >update</b-button
                    >
                    <b-button size="sm" @click="deleteArticle">delete</b-button>
                  </div>
                </div>
              </div>

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
                    v-model="notice.userid"
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
                    v-model="notice.regtime"
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
                    v-model="notice.subject"
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
                    v-model="notice.content"
                    placeholder="내용 입력..."
                    rows="10"
                    max-rows="15"
                  ></b-form-textarea>
                </b-form-group>
              </b-col>
              <!-- <notice-input-item type="update" /> -->
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
// import moment from "moment";
import { getArticle, deleteArticle } from "@/api/notice";

//import NoticeInputItem from "@/components/notice/item/NoticeInputItem.vue";
import { mapState } from "vuex";
const memberStore = "memberStore";

export default {
  name: "NoticeDetail",
  components: {
    // NoticeInputItem,
  },
  data() {
    return {
      notice: {},
    };
  },
  computed: {
    ...mapState(memberStore, ["userInfo"]),
    message() {
      if (this.notice.content)
        return this.notice.content.split("\n").join("<br>");
      return "";
    },
  },
  created() {
    getArticle(
      this.$route.params.noticeno,
      (response) => {
        this.notice = response.data;
      },
      (error) => {
        console.log("삭제 실패", error);
      },
    );
  },
  methods: {
    listArticle() {
      this.$router.push({ name: "noticeList" });
    },
    moveModifyArticle() {
      this.$router.replace({
        name: "noticeModify",
        params: { noticeno: this.notice.noticeno },
      });
      //   this.$router.push({ path: `/notice/modify/${this.article.articleno}` });
    },
    deleteArticle() {
      if (confirm("삭제하시겠습니까?")) {
        deleteArticle(this.notice.noticeno, () => {
          this.$router.push({ name: "noticeList" });
        });
      }
    },
    userCheck() {
      return this.notice.userid === this.userInfo.userid;
    },
  },
  // filters: {
  //   dateFormat(regtime) {
  //     return moment(new Date(regtime)).format("YY.MM.DD hh:mm:ss");
  //   },
  // },
};
</script>

<style></style>
