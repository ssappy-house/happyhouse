<template>
  <div>
    <div class="position-relative">
      <!-- shape Hero -->
      <section class="section-shaped my-0">
        <div class="shape shape-style-1 shape-default shape-skew">
          <span></span>
          <span></span>
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
                <h1 class="display-3 text-white">
                  Find Your Fantastic Apt
                  <span>with 'HappyHouse'</span>
                </h1>
                <p class="lead text-white">
                  Join us! You will be able to find apartment information
                </p>
              </div>
            </div>
          </div>
        </div>
      </section>
      <!-- 1st Hero Variation -->
    </div>

    <section class="section section pt-lg-0 mt--200">
      <div class="container">
        <div class="row justify-content-center">
          <div class="col-lg-0">
            <div class="row row-grid">
              <div class="col-lg-0">
                <card class="border-0" hover shadow body-classes="py-5">
                  <KakaoMap />
                </card>
              </div>
            </div>
          </div>
        </div>
      </div>
    </section>

    <section class="section section" style="padding-top: 0">
      <div class="container">
        <div class="row justify-content-center">
          <!-- <div class="row justify-content-center text-center mb-lg"> -->
          <div class="row justify-content-center text-center">
            <div class="col-lg-8">
              <h2 class="display-3">Choose the area you want!</h2>
              <!-- <p class="lead text-muted">Choose the area you want!</p> -->
            </div>
          </div>

          <!-- <div class="row justify-content-center text-center mb-lg"> -->
          <div class="row justify-content-center text-center">
            <div class="col-lg-8">
              <house-search-bar></house-search-bar>
            </div>
          </div>
          <div class="col-lg-12">
            <div class="row row-grid">
              <div class="col-lg-6">
                <card
                  class="border-0"
                  shadow
                  body-classes="py-5"
                  style="height: 600px"
                >
                  <icon
                    name="ni ni-istanbul"
                    type="success"
                    rounded
                    class="mb-4"
                  >
                  </icon>
                  <h6
                    class="text-success text-uppercase"
                    style="display: inline-block; margin: 10px"
                  >
                    Apart List
                  </h6>
                  <div cols="6" style="align: left">
                    <house-list />
                  </div>
                </card>
              </div>
              <div class="col-lg-6">
                <card
                  class="border-0"
                  shadow
                  body-classes="py-5"
                  style="height: 600px"
                >
                  <icon name="ni ni-planet" type="warning" rounded class="mb-4">
                  </icon>
                  <h6
                    class="text-warning text-uppercase"
                    style="display: inline-block; margin: 10px"
                  >
                    Apart Detail
                  </h6>
                  <div cols="6" style="align: right">
                    <house-detail />
                  </div>
                </card>
              </div>
            </div>
          </div>
        </div>
      </div>
    </section>

    <!-- <section class="section section-shaped my-0 overflow-hidden">
      <div class="shape shape-style-3 bg-gradient-default shape-skew">
        <span></span>
        <span></span>
        <span></span>
        <span></span>
      </div>
      <div class="container pt-lg pb-300">
        <div class="row text-center justify-content-center">
          <div class="col-lg-10">
            <h2 class="display-3 text-white">Build something</h2>
            <p class="lead text-white">
              According to the National Oceanic and Atmospheric Administration,
              Ted, Scambos, NSIDClead scentist, puts the potentially record low
              maximum sea ice extent tihs year down to low ice.
            </p>
          </div>
        </div>
      </div>
    </section> -->
  </div>
</template>

<script>
import HouseSearchBar from "@/components/house/HouseSearchBar.vue";
import HouseList from "@/components/house/HouseList.vue";
import HouseDetail from "@/components/house/HouseDetail.vue";
import KakaoMap from "@/components/house/KakaoMap";

export default {
  name: "HouseView",
  components: {
    HouseSearchBar,
    HouseList,
    HouseDetail,
    KakaoMap,
  },
};
</script>
