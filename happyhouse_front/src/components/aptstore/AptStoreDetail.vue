<template>
  <b-container v-if="aptstore" class="bv-example-row">
    <b-row>
      <b-col
        ><br />
        <h2>{{ aptstore.storename }}</h2></b-col
      >
    </b-row>
    <b-row class="mb-2 mt-1">
      <!-- <b-col
        ><b-img :src="require('@/assets/apt.png')" fluid-grow></b-img
      ></b-col> -->
    </b-row>
    <b-row>
      <b-col>
        <br />
        <b-alert show variant="secondary"
          >구분 : {{ aptstore.categoryS }}</b-alert
        >
      </b-col>
    </b-row>
    <b-row>
      <b-col>
        <b-alert show variant="primary" style="color: #41464b"
          >법정동 : {{ aptstore.dongname }}
        </b-alert>
      </b-col>
    </b-row>
    <b-row>
      <b-col>
        <b-alert show variant="warning" style="color: #41464b"
          >주소 : {{ aptstore.address }}
        </b-alert>
      </b-col>
    </b-row>
    <b-row>
      <b-col>
        <b-alert show variant="danger" style="color: #41464b"
          >거리 : {{ aptstore.distance | roundDistance }} m</b-alert
        >
      </b-col>
    </b-row>
  </b-container>
</template>

<script>
import { mapState, mapMutations } from "vuex";

//const houseStore = "houseStore";
const aptstoreStore = "aptstoreStore";

export default {
  name: "AptStoreDetail",
  computed: {
    ...mapState(aptstoreStore, ["aptstore"]),

    // house() {
    //   return this.$store.state.house;
    // },
  },
  filters: {
    roundDistance(value) {
      return Math.round(value * 100) / 100;
    },
  },
  // filters: {
  //   price(value) {
  //     if (!value) return value;
  //     return value.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
  //   },
  // },
  created() {
    // this.$store.dispatch("getSido");
    // this.sidoList();
    this.CLEAR_STORE_DETAIL();
    //this.getSido();
  },
  methods: {
    ...mapMutations(aptstoreStore, ["CLEAR_STORE_DETAIL"]),
  },
};
</script>

<style></style>
