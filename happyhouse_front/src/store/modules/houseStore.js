import {
  sidoList,
  gugunList,
  dongList,
  houseList,
  sortList,
  searchApt,
} from "@/api/house.js";
//import jwt_decode from "jwt-decode";

const houseStore = {
  namespaced: true,
  state: {
    sidos: [{ value: null, text: "시/도" }],
    guguns: [{ value: null, text: "구/군" }],
    dongs: [{ value: null, text: "동" }],
    houses: [],
    house: null,
  },

  getters: {
    checkAptSelect: function (state) {
      return state.userInfo;
    },
  },

  mutations: {
    SET_SIDO_LIST: (state, sidos) => {
      sidos.forEach((sido) => {
        state.sidos.push({ value: sido.sidoCode, text: sido.sidoName });
      });
    },
    SET_GUGUN_LIST: (state, guguns) => {
      guguns.forEach((gugun) => {
        state.guguns.push({ value: gugun.gugunCode, text: gugun.gugunName });
      });
    },
    SET_DONG_LIST: (state, dongs) => {
      dongs.forEach((dong) => {
        state.dongs.push({ value: dong.dongCode, text: dong.dongName });
      });
    },
    CLEAR_SIDO_LIST: (state) => {
      state.sidos = [{ value: null, text: "시/도" }];
    },
    CLEAR_GUGUN_LIST: (state) => {
      state.guguns = [{ value: null, text: "구/군" }];
    },
    CLEAR_DONG_LIST: (state) => {
      state.dongs = [{ value: null, text: "동" }];
    },
    SET_HOUSE_LIST: (state, houses) => {
      //   console.log(houses);
      state.houses = houses;
    },
    SET_DETAIL_HOUSE: (state, house) => {
      state.house = house;
    },
    MARKER_HOUSE_IN_MAP: (state, house) => {
      state.house = house;
    },
    CLEAR_HOUSE_LIST: (state) => {
      state.houses = [];
    },
    CLEAR_HOUSE_DETAIL: (state) => {
      state.house = null;
    },
  },

  actions: {
    //getStoreInfo({ commit }, token) {
    //let decode_token = jwt_decode(token);
    //   findByHouse(
    //     decode_token.house,
    //     (response) => {
    //       if (response.data.message === "success") {
    //         commit("SET_DETAIL_HOUSE", response.data.house);
    //       } else {
    //         console.log("Apt 정보 없음!!");
    //       }
    //     },
    //     (error) => {
    //       console.log(error);
    //     },
    //   );
    // },
    getSido: ({ commit }) => {
      sidoList(
        ({ data }) => {
          // console.log(data);
          commit("SET_SIDO_LIST", data);
        },
        (error) => {
          console.log(error);
        },
      );
    },
    getGugun: ({ commit }, sidoCode) => {
      const params = {
        sido: sidoCode,
      };
      gugunList(
        params,
        ({ data }) => {
          // console.log(commit, response);
          commit("SET_GUGUN_LIST", data);
        },
        (error) => {
          console.log(error);
        },
      );
    },
    getDong: ({ commit }, gugunCode) => {
      const params = {
        gugun: gugunCode,
      };
      dongList(
        params,
        ({ data }) => {
          // console.log(commit, response);
          commit("SET_DONG_LIST", data);
        },
        (error) => {
          console.log(error);
        },
      );
    },
    getHouseList: ({ commit }, dongCode) => {
      const params = {
        dong: dongCode,
      };
      houseList(
        params,
        ({ data }) => {
          // console.log(commit, response);
          commit("SET_HOUSE_LIST", data);
        },
        (error) => {
          console.log(error);
        },
      );
    },
    sortHouseList: ({ commit }, { dong, sort, order }) => {
      console.log(dong, sort, order);
      const params = {
        dong,
        sort,
        order,
      };
      sortList(
        params,
        ({ data }) => {
          // console.log(commit, response);
          commit("SET_HOUSE_LIST", data);
        },
        (error) => {
          console.log(error);
        },
      );
    },
    getListByName: ({ commit }, aptName) => {
      const params = {
        apt: aptName,
      };
      searchApt(
        params,
        ({ data }) => {
          // console.log(commit, response);
          commit("SET_HOUSE_LIST", data);
        },
        (error) => {
          console.log(error);
        },
      );
    },
    // getHouseList: ({ commit }, gugunCode) => {
    //   // vue cli enviroment variables 검색
    //   //.env.local file 생성.
    //   // 반드시 VUE_APP으로 시작해야 한다.
    //   //console.log(process.env);
    //   const SERVICE_KEY = process.env.VUE_APP_APT_DEAL_API_KEY;
    //   //   const SERVICE_KEY =
    //   //     "9Xo0vlglWcOBGUDxH8PPbuKnlBwbWU6aO7%2Bk3FV4baF9GXok1yxIEF%2BIwr2%2B%2F%2F4oVLT8bekKU%2Bk9ztkJO0wsBw%3D%3D";

    //   const params = {
    //     LAWD_CD: gugunCode,
    //     DEAL_YMD: "202110",
    //     serviceKey: decodeURIComponent(SERVICE_KEY),
    //   };
    //   houseList(
    //     params,
    //     (response) => {
    //       console.log(response);
    //       commit("SET_HOUSE_LIST", response.data.response.body.items.item);
    //     },
    //     (error) => {
    //       console.log(error);
    //     },
    //   );
    // },
    detailHouse: ({ commit }, house) => {
      // 나중에 house.일련번호를 이용하여 API 호출
      commit("SET_DETAIL_HOUSE", house);
    },
  },
};

export default houseStore;
