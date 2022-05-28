<template style="align: left">
  <b-container v-if="houses && houses.length != 0" class="bv-example-row mt-3">
    <div class="overflow-auto">
      <b-table
        id="my-table"
        :items="houses"
        :per-page="perPage"
        :current-page="currentPage"
        :fields="visibleFields"
        @row-clicked="selectHouse"
        hover
        borderless
        style="margin: auto"
      ></b-table>

      <p class="description">
        <b-pagination
          v-model="currentPage"
          :total-rows="rows"
          :per-page="perPage"
          aria-controls="my-table"
          align="center"
        ></b-pagination>
      </p>
    </div>

    <!-- <house-list-item
      v-for="(house, index) in houses"
      :key="index"
      :house="house"
    /> -->
  </b-container>
  <b-container v-else class="bv-example-row mt-3">
    <div class="alert alert-secondary" role="alert">
      아파트 목록이 없습니다.
    </div>
  </b-container>
</template>

<script>
import HouseListItem from "@/components/house/HouseListItem.vue";
import { mapState, mapActions, mapMutations } from "vuex";

const houseStore = "houseStore";

export default {
  name: "HouseList",
  components: {
    HouseListItem,
  },
  data() {
    return {
      perPage: 8,
      currentPage: 1,
      fields: [
        { key: "aptCode", label: "aptCode", visible: false },
        { key: "aptName", label: "", visible: true },
        { key: "dongCode", label: "dongCode", visible: false },
        { key: "dongName", label: "법정동", visible: false },
        { key: "sidoName", label: "sidoName", visible: false },
        { key: "gugunName", label: "gugunName", visible: false },
        { key: "buildYear", label: "건축연도", visible: false },
        { key: "jibun", label: "주소", visible: false },
        { key: "lat", label: "lat", visible: false },
        { key: "lng", label: "lng", visible: false },
        { key: "img", label: "img", visible: false },
        { key: "recentPrice", label: "실거래가", visible: false },
      ],
    };
  },
  computed: {
    ...mapState(houseStore, ["houses"]),
    rows() {
      return this.houses.length;
    },
    visibleFields() {
      return this.fields.filter((field) => field.visible);
    },
    // houses() {
    //   return this.$store.state.houses;
    // },
  },
  created() {
    // this.$store.dispatch("getSido");
    // this.sidoList();
    this.CLEAR_HOUSE_LIST();
    //this.getSido();
  },
  methods: {
    ...mapActions(houseStore, ["detailHouse"]),
    ...mapMutations(houseStore, ["CLEAR_HOUSE_LIST"]),
    selectHouse(house) {
      console.log(house);
      //console.log("house loc : ", this.house.lat, this.house.lng);
      // this.$store.dispatch("getHouse", this.house);
      this.detailHouse(house);
    },
  },
};
</script>

<style></style>
