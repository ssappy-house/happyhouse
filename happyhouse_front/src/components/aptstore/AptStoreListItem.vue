<template>
  <b-row
    class="m-2"
    @click="selectStore"
    @mouseover="colorChange(true)"
    @mouseout="colorChange(false)"
    :class="{ 'mouse-over-bgcolor': isColor }"
  >
    <b-col cols="2" class="text-center align-self-center">
      <!-- <b-img
        thumbnail
        src="https://picsum.photos/250/250/?image=58"
        alt="Image 1"
      ></b-img> -->
    </b-col>
    <b-col cols="10" class="align-self-center">
      <li>[{{ aptstore.categoryS }}] {{ aptstore.storename }}</li>
    </b-col>
  </b-row>
</template>

<script>
import { mapActions } from "vuex";

//const houseStore = "houseStore";
const aptstoreStore = "aptstoreStore";

export default {
  name: "AptStoreListItem",
  data() {
    return {
      isColor: false,
    };
  },
  props: {
    aptstore: Object,
  },
  methods: {
    ...mapActions(aptstoreStore, ["detailStore"]),
    selectStore() {
      //console.log("house loc : ", this.house.lat, this.house.lng);
      // this.$store.dispatch("getHouse", this.house);
      this.detailStore(this.aptstore);
    },
    colorChange(flag) {
      this.isColor = flag;
    },
  },
};
</script>

<style scoped>
.apt {
  width: 50px;
}
.mouse-over-bgcolor {
  background-color: lightblue;
}
</style>
