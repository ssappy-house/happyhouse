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
              <div class="col-lg-3 order-lg-2">
                <div class="card-profile-image"></div>
              </div>
              <div
                class="col-lg-4 order-lg-3 text-lg-right align-self-lg-center"
              >
                <div v-if="adminCheck()">
                  <div class="card-profile-actions py-4 mt-lg-0">
                    <base-button
                      type="default"
                      size="sm"
                      class="btn btn-neutral float-right"
                      @click="moveWrite"
                      >Write</base-button
                    >
                  </div>
                </div>
              </div>
              <div class="col-lg-4 order-lg-1">
                <div
                  class="card-profile-stats d-flex justify-content-center"
                ></div>
              </div>
            </div>
            <div class="text-center mt-5">
              <b-col v-if="notices.length">
                <b-table-simple hover responsive>
                  <b-thead head-variant="lignt">
                    <b-tr
                      style="
                        font-size: 15px;
                        color: white;
                        background-color: rgba(150, 159, 237, 0.7);
                      "
                    >
                      <b-th>No</b-th>
                      <b-th>Title</b-th>
                      <b-th>Name</b-th>
                      <b-th>Date</b-th>
                    </b-tr>
                  </b-thead>
                  <tbody>
                    <!-- 하위 component인 ListRow에 데이터 전달(props) -->
                    <notice-list-item
                      v-for="(notice, idx) in notices"
                      :key="idx"
                      v-bind:notice="notice"
                      v-bind:idx="notices.length - idx"
                    />
                  </tbody>
                </b-table-simple>
              </b-col>
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
import { listArticle } from "@/api/notice.js";
import NoticeListItem from "@/components/notice/item/NoticeListItem";

import { mapState } from "vuex";

const memberStore = "memberStore";

export default {
  name: "NoticeList",
  components: {
    NoticeListItem,
  },
  data() {
    return {
      notices: [],
    };
  },
  computed: {
    ...mapState(memberStore, ["userInfo"]),
  },
  created() {
    let param = {
      pg: 1,
      spp: 20,
      key: null,
      word: null,
    };
    listArticle(
      param,
      (response) => {
        this.notices = response.data;
      },
      (error) => {
        console.log(error);
      },
    );
  },
  methods: {
    moveWrite() {
      this.$router.push({ name: "noticeRegister" });
    },
    adminCheck() {
      return (
        this.userInfo.userid == "admin0" ||
        this.userInfo.userid == "admin1" ||
        this.userInfo.userid == "admin3" ||
        this.userInfo.userid == "admin4"
      );
    },
  },
};
</script>

<style scope>
.tdClass {
  width: 50px;
  text-align: center;
}
.tdSubject {
  width: 300px;
  text-align: left;
}
</style>
