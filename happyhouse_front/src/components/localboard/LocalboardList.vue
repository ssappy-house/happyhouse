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
      </div>
      <div class="container shape-container d-flex">
        <div class="col px-0">
          <div class="row">
            <div class="col-lg-6">
              <h1 class="display-3 text-white">지역게시판</h1>
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
              <div class="row justify-content-center text-center">
                <div class="col-lg-8">
                  <local-search-bar
                    v-on:selectDongCode="getLocalBoardList"
                  ></local-search-bar>
                </div>
              </div>
              <div class="col-lg-3 order-lg-2">
                <div class="card-profile-image"></div>
              </div>
              <div
                class="col-lg-4 order-lg-3 text-lg-right align-self-lg-center"
              >
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
              <div class="col-lg-4 order-lg-1">
                <div
                  class="card-profile-stats d-flex justify-content-center"
                ></div>
              </div>
            </div>
            <div class="text-center mt-5" v-if="localboards.length == 0">
              등록된 게시글이 없습니다
            </div>
            <div class="text-center mt-5" v-else>
              <b-col>
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
                    <localboard-list-item
                      v-for="(localboard, idx) in localboards"
                      :key="idx"
                      v-bind:localboard="localboard"
                      v-bind:idx="localboards.length - idx"
                    />
                  </tbody>
                </b-table-simple>
              </b-col>
            </div>
            <div class="mt-5 py-5 border-top text-center">
              <div class="row justify-content-center">
                <div class="col-lg-9"></div>
              </div>
            </div>
          </div>
        </card>
      </div>
    </section>
  </div>
</template>

<script>
import { listLocalboard } from "@/api/board";
import LocalboardListItem from "@/components/localboard/item/LocalboardListItem";
import LocalSearchBar from "@/components/localboard/LocalSearchBar";
import BoardBus from "@/bus/board";

export default {
  name: "LocalboardList",
  components: {
    LocalboardListItem,
    LocalSearchBar,
  },
  data() {
    return {
      localboards: [],
    };
  },
  created() {
    // let param = {
    //   pg: 1,
    //   spp: 20,
    //   key: null,
    //   word: null,
    // };
    // listBoard(
    //   param,
    //   (response) => {
    //     this.localboards = response.data;
    //   },
    //   (error) => {
    //     console.log(error);
    //   },
    // );
  },
  methods: {
    moveWrite() {
      this.$router.push({ name: "localboardRegister" });
    },
    getLocalBoardList(dongCode) {
      console.log("localboardlist " + dongCode);
      BoardBus.$emit("selectedDongCode", dongCode);
      listLocalboard(
        dongCode,
        (response) => {
          this.localboards = response.data;
        },
        (error) => {
          console.log(error);
        },
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
