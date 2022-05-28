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
              <div class="col-lg-3 order-lg-2">
                <div class="card-profile-image">
                  <!-- <a href="#">
                    <img
                      v-lazy="'img/happyhouse/profile.png'"
                      class="rounded-circle"
                  </a> -->
                </div>
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
              <b-col v-if="freeboards.length">
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
                    <freeboard-list-item
                      v-for="(freeboard, idx) in freeboards"
                      :key="idx"
                      v-bind:freeboard="freeboard"
                      v-bind:idx="freeboards.length - idx"
                    />
                  </tbody>
                </b-table-simple>
              </b-col>
            </div>
            <div class="mt-5 py-5 border-top text-center"></div>
          </div>
        </card>
      </div>
    </section>
  </div>
</template>

<script>
import { listBoard } from "@/api/board";
import FreeboardListItem from "@/components/freeboard/item/FreeboardListItem";

export default {
  name: "FreeboardList",
  components: {
    FreeboardListItem,
  },
  data() {
    return {
      freeboards: [],
    };
  },
  created() {
    let param = {
      pg: 1,
      spp: 20,
      key: null,
      word: null,
    };
    listBoard(
      param,
      (response) => {
        this.freeboards = response.data;
      },
      (error) => {
        console.log(error);
      },
    );
  },
  methods: {
    moveWrite() {
      this.$router.push({ name: "freeboardRegister" });
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
