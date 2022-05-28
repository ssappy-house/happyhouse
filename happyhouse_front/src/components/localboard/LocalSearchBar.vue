<template>
  <div class="mt-4 mb-4 text-center">
    <!-- <b-col class="sm-3">
      <b-form-input
        v-model.trim="dongCode"
        placeholder="동코드 입력...(예 : 11110)"
        @keypress.enter="sendKeyword"
      ></b-form-input>
    </b-col>
    <b-col class="sm-3" align="left">
      <b-button variant="outline-primary" @click="sendKeyword">검색</b-button>
    </b-col> -->
    <div class="row justify-content-center text-center">
      <tabs fill class="flex-column flex-md-row">
        <br />
        <card shadow>
          <!-- <tab-pane title="지역 검색"> -->
          <p class="description">
            <b-form inline style="margin: 15px">
              <b-form-select
                v-model="sidoCode"
                :options="sidos"
                @change="gugunList"
                style="width: 160px; margin: auto"
              ></b-form-select
              ><b-form-select
                v-model="gugunCode"
                :options="guguns"
                @change="dongList"
                style="width: 160px; margin: auto"
              ></b-form-select
              ><b-form-select
                v-model="dongCode"
                :options="dongs"
                @change="searchBoard"
                style="width: 160px; margin: auto"
              ></b-form-select>
              <br /><br />
            </b-form>
          </p>
        </card>
      </tabs>
      <!-- <div class="row"> -->

      <div class="row justify-content-center text-center"></div>
      <!-- </div> -->
    </div>
  </div>
</template>

<script>
import { mapState, mapActions, mapMutations } from "vuex";

import Tabs from "@/components/Tabs/Tabs.vue";
//import TabPane from "@/components/Tabs/TabPane.vue";

/*
  namespaced: true를 사용했기 때문에 선언해줍니다.
  index.js 에서 modules 객체의 '키' 이름입니다.

  modules: {
    키: 값
    memberStore: memberStore,
    houseStore: houseStore
  }
*/
const houseStore = "houseStore";

export default {
  name: "LocalSearchBar",
  components: {
    Tabs,
    // TabPane,
  },
  data() {
    return {
      sidoCode: null,
      gugunCode: null,
      dongCode: null,
      // sort: null,
      // sortOptions: [
      //   { value: null, text: "정렬기준" },
      //   { value: "aptName", text: "이름" },
      //   { value: "buildYear", text: "건축연도" },
      //   { value: "recentPrice", text: "거래금액" },
      // ],
      // order: null,
      // orderOptions: [
      //   { value: null, text: "정렬순서" },
      //   { value: "asc", text: "오름차순" },
      //   { value: "desc", text: "내림차순" },
      // ],
      // aptName: null,
    };
  },
  computed: {
    ...mapState(houseStore, ["sidos", "guguns", "dongs", "houses"]),
    // sidos() {
    //   return this.$store.state.sidos;
    // },
  },
  created() {
    // this.$store.dispatch("getSido");
    // this.sidoList();
    this.CLEAR_SIDO_LIST();
    this.getSido();
  },
  methods: {
    ...mapActions(houseStore, [
      "getSido",
      "getGugun",
      "getDong",
      "getHouseList",
      "sortHouseList",
      "getListByName",
    ]),
    ...mapMutations(houseStore, [
      "CLEAR_SIDO_LIST",
      "CLEAR_GUGUN_LIST",
      "CLEAR_DONG_LIST",
      "CLEAR_HOUSE_LIST",
      "CLEAR_HOUSE_DETAIL",
    ]),
    // sidoList() {
    //   this.getSido();
    // },
    gugunList() {
      console.log(this.sidoCode);
      this.CLEAR_GUGUN_LIST();
      this.gugunCode = null;
      if (this.sidoCode) this.getGugun(this.sidoCode);
    },
    dongList() {
      console.log(this.gugunCode);
      this.CLEAR_DONG_LIST();
      this.dongCode = null;
      if (this.gugunCode) this.getDong(this.gugunCode);
    },
    searchBoard() {
      console.log(this.dongCode);
      // this.$emit;
      // this.CLEAR_HOUSE_DETAIL();
      if (this.dongCode) {
        this.$emit("selectDongCode", this.dongCode);
      }
      // this.getHouseList(this.dongCode);
    },
    // sortAptList() {
    //   this.CLEAR_HOUSE_LIST();
    //   this.CLEAR_HOUSE_DETAIL();
    //   if (this.dongCode && this.sort && this.order)
    //     this.sortHouseList({
    //       dong: this.dongCode,
    //       sort: this.sort,
    //       order: this.order,
    //     });
    // },
    // searchAptList() {
    //   this.CLEAR_HOUSE_LIST();
    //   this.CLEAR_HOUSE_DETAIL();
    //   if (this.aptName) this.getListByName(this.aptName);
    // },
  },
};
</script>

<style></style>
