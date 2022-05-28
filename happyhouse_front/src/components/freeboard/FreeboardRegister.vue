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
              <!-- <div class="card-profile-actions py-4 mt-lg-0">
                <div class="float-left">
                  <base-button type="default" size="sm" @click="moveList" class="btn btn-neutral btn-sm"
                    >List</base-button
                  >
                </div>
                <div class="float-right">
                  <base-button type="default" size="sm" @click="moveWrite"
                    >수정</base-button
                  >
                  <base-button type="default" size="sm" @click="moveWrite"
                    >초기화</base-button
                  >
                </div>
              </div> -->

              <!-- <div class="col-lg-4 order-lg-1">
                <div
                  class="card-profile-stats d-flex justify-content-center"
                ></div>
              </div>
            </div>
            <div class="text-center mt-5"> -->
              <freeboard-input-item type="register" />
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
import FreeboardInputItem from "@/components/freeboard/item/FreeboardInputItem.vue";

export default {
  name: "FreeboardWrite",
  components: {
    FreeboardInputItem,
  },
};
</script>

<style></style>
