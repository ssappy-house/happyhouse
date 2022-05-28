<template>
  <b-tr>
    <b-td style="font-size: 14px">{{ idx }}</b-td>
    <b-th style="font-size: 14px">
      <router-link
        class="notice-title"
        :to="{
          name: 'localboardDetail',
          params: { boardno: localboard.boardno },
        }"
        >{{ localboard.subject }}</router-link
      >
    </b-th>
    <b-td style="font-size: 14px">{{ localboard.userid }}</b-td>
    <b-td style="font-size: 14px">{{ localboard.regtime.substr(0, 10) }}</b-td>
  </b-tr>
</template>

<script>
// import moment from "moment";

export default {
  name: "LocalboardListItem",
  props: {
    localboard: Object,
    idx: Number,
  },
  filters: {
    // dateFormat(regtime) {
    //   return moment(new Date(regtime)).format("YY.MM.DD");
    // },
  },
};
</script>

<style scope>
a.notice-title {
  text-decoration: none;
  font-style: oblique;
  color: #212529;
}
</style>
