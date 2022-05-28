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
                  Look Up The Store
                  <span>Around Your Fantastic Apt</span>
                </h1>
                <p class="lead text-white">
                  Join us! You can get store informations around the apartment
                  :)
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
                  <AptStoreKakaoMap></AptStoreKakaoMap>
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
                    Store List
                  </h6>
                  <div cols="6" style="align: left">
                    <apt-store-list />
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
                    Store Detail
                  </h6>
                  <div cols="6" style="align: right">
                    <apt-store-detail />
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
    </section> 

    <section class="section section-lg pt-lg-0 section-contact-us">
      <div class="container">
        <div class="row justify-content-center mt--300">
          <div class="col-lg-8">
            <card gradient="secondary" shadow body-classes="p-lg-5">
              <h4 class="mb-1">Want to work with us?</h4>
              <p class="mt-0">Your project is very important to us.</p>
              <base-input
                class="mt-5"
                alternative
                placeholder="Your name"
                addon-left-icon="ni ni-user-run"
              >
              </base-input>
              <base-input
                alternative
                placeholder="Email address"
                addon-left-icon="ni ni-email-83"
              >
              </base-input>
              <base-input class="mb-4">
                <textarea
                  class="form-control form-control-alternative"
                  name="name"
                  rows="4"
                  cols="80"
                  placeholder="Type a message..."
                ></textarea>
              </base-input>
              <base-button type="default" round block size="lg">
                Send Message
              </base-button>
            </card>
          </div>
        </div>
      </div>
    </section>
    <section class="section section-lg">
      <div class="container"></div>
    </section> -->
  </div>
</template>

<script>
import { mapState } from "vuex";

import AptStoreList from "@/components/aptstore/AptStoreList.vue";
import AptStoreDetail from "@/components/aptstore/AptStoreDetail.vue";
import AptStoreKakaoMap from "@/components/aptstore/AptStoreKakaoMap";

const houseStore = "houseStore";
const aptstoreStore = "aptstoreStore";

export default {
  name: "AptStoreView",
  computed: {
    ...mapState(houseStore, ["house"]),
    ...mapState(aptstoreStore, ["aptstore", "aptstores"]),
  },
  created() {},
  components: {
    AptStoreList,
    AptStoreDetail,
    AptStoreKakaoMap,
  },
  props: {
    //house: Object,
  },
};
</script>
