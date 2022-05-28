<template style="align: left">
  <b-container v-if="houses && houses.length != 0" class="bv-example-row mt-3">
    <house-list-item
      v-for="(house, index) in houses"
      :key="index"
      :house="house"
    />
  </b-container>
  <b-container v-else class="bv-example-row mt-3">
    <div class="alert alert-secondary" role="alert">
      아파트 목록이 없습니다.
    </div>
  </b-container>
</template>

<script>
import HouseListItem from "@/components/house/HouseListItem.vue";
import { mapState, mapMutations } from "vuex";

const houseStore = "houseStore";

export default {
  name: "HouseList",
  components: {
    HouseListItem,
  },
  data() {
    return {};
  },
  computed: {
    ...mapState(houseStore, ["houses"]),

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
    ...mapMutations(houseStore, ["CLEAR_HOUSE_LIST"]),
    selectHouse() {
      console.log("house loc : ", this.house.lat, this.house.lng);
      // this.$store.dispatch("getHouse", this.house);
      this.detailHouse(this.house);
    },
  },
};
</script>

<style></style>
