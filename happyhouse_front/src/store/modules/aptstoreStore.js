import { allList, categoryList } from "@/api/aptstore.js";

const aptstoreStore = {
  namespaced: true,
  state: {
    // sidos: [{ value: null, text: "선택하세요" }],
    // guguns: [{ value: null, text: "선택하세요" }],
    // dongs: [{ value: null, text: "선택하세요" }],
    aptstoresloc: [],
    aptstores: [],
    aptstore: null,
  },

  getters: {},

  mutations: {
    SET_STORE_LIST: (state, aptstores) => {
      state.aptstores = [];
      state.aptstores = aptstores;
    },
    SET_STORE_LOC: (state, aptstores) => {
      state.aptstoresloc = [];
      for (var i = 0; i < aptstores.length; i++) {
        state.aptstoresloc.push([aptstores[i].lat, aptstores[i].lng]);
      }
    },
    SET_DETAIL_STORE: (state, aptstore) => {
      state.aptstore = aptstore;
    },
    // MARKER_HOUSE_IN_MAP: (state, house) => {
    //   state.house = house;
    // },
    CLEAR_STORE_LIST: (state) => {
      state.aptstores = [];
    },
    CLEAR_STORE_LOC: (state) => {
      state.aptstoresloc = [];
    },
    CLEAR_STORE_DETAIL: (state) => {
      state.aptstore = null;
    },
  },

  actions: {
    getAllList: ({ commit }, { lng, lat }) => {
      const params = {
        lng,
        lat,
      };
      allList(
        params,
        ({ data }) => {
          // console.log(commit, response);
          commit("SET_STORE_LIST", data);
          console.log(data);
        },
        (error) => {
          console.log(error);
        },
      );
    },
    getCategoryList: ({ commit }, { lng, lat, category }) => {
      const params = {
        lng,
        lat,
        category,
      };
      categoryList(
        params,
        ({ data }) => {
          // console.log(commit, response);
          commit("SET_STORE_LIST", data);
          commit("SET_STORE_LOC", data);
          console.log(data);
        },
        (error) => {
          console.log(error);
        },
      );
    },

    detailStore: ({ commit }, aptstore) => {
      // 나중에 house.일련번호를 이용하여 API 호출
      commit("SET_DETAIL_STORE", aptstore);
    },
  },
};

export default aptstoreStore;
