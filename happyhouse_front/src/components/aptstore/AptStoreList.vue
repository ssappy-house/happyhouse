<template>
  <b-container
    v-if="aptstores && aptstores.length != 0"
    class="bv-example-row mt-3"
  >
    <div class="overflow-auto">
      <b-table
        id="my-table"
        :items="aptstores"
        :per-page="perPage"
        :current-page="currentPage"
        :fields="visibleFields"
        @row-clicked="selectStore"
        hover
        borderless
        style="margin: auto"
      ></b-table>

      <b-pagination
        v-model="currentPage"
        :total-rows="rows"
        :per-page="perPage"
        aria-controls="my-table"
        align="center"
      ></b-pagination>
    </div>
  </b-container>

  <b-container v-else class="bv-example-row mt-3">
    <div class="alert alert-secondary" role="alert">상가 목록이 없습니다.</div>
  </b-container>

  <!-- <b-container
    v-if="aptstores && aptstores.length != 0"
    class="bv-example-row mt-3"
  >
    <apt-store-list-item
      v-for="(aptstore, index) in aptstores"
      :key="index"
      :aptstore="aptstore"
    />
  </b-container>
  <b-container v-else class="bv-example-row mt-3">
    <div class="alert alert-secondary" role="alert">상가 목록이 없습니다.</div>
  </b-container> -->
</template>

<script>
import AptStoreListItem from "@/components/aptstore/AptStoreListItem.vue";
import { mapState, mapActions, mapMutations } from "vuex";

//const houseStore = "houseStore";
const aptstoreStore = "aptstoreStore";

export default {
  name: "AptStoreList",
  components: {
    AptStoreListItem,
  },
  data() {
    return {
      perPage: 8,
      currentPage: 1,
      fields: [
        { key: "storeno", label: "storeno", visible: false },
        { key: "storename", label: "", visible: true },
        { key: "categoryL", label: "categoryL", visible: false },
        { key: "categoryS", label: "분류", visible: false },
        { key: "dongcode", label: "dongcode", visible: false },
        { key: "dongname", label: "법정동", visible: false },
        { key: "address", label: "주소", visible: false },
        { key: "lng", label: "lng", visible: false },
        { key: "lat", label: "lat", visible: false },
        { key: "distance", label: "거리", visible: false },
      ],
    };
  },
  computed: {
    //...mapState(houseStore, ["houses"]),
    ...mapState(aptstoreStore, ["aptstores"]),
    rows() {
      return this.aptstores.length;
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
    this.CLEAR_STORE_LIST();
    //this.getSido();
  },
  methods: {
    ...mapActions(aptstoreStore, ["detailStore"]),
    ...mapMutations(aptstoreStore, ["CLEAR_STORE_LIST"]),
    selectStore(store) {
      //console.log("house loc : ", this.house.lat, this.house.lng);
      // this.$store.dispatch("getHouse", this.house);
      this.detailStore(store);
    },
  },
};
</script>

<style></style>
