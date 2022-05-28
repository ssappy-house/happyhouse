//import { apiInstance, houseInstance } from "./index.js";
import { apiInstance } from "./index.js";

const api = apiInstance();
// const house = houseInstance();

function sidoList(success, fail) {
  api.get(`/map/sido`).then(success).catch(fail);
}

function gugunList(params, success, fail) {
  api.get(`/map/gugun`, { params: params }).then(success).catch(fail);
}

function dongList(params, success, fail) {
  api.get(`/map/dong`, { params: params }).then(success).catch(fail);
}

// function houseList(params, success, fail) {
//   house.get(``, { params: params }).then(success).catch(fail);
// }

function houseList(params, success, fail) {
  api.get(`/map/apt`, { params: params }).then(success).catch(fail);
}

function sortList({ dong, sort, order }, success, fail) {
  api
    .get(`/map/sortApt`, {
      params: { dong, sort, order },
    })
    .then(success)
    .catch(fail);
}

function searchApt(apt, success, fail) {
  api.get(`/map/aptName`, { params: apt }).then(success).catch(fail);
}

export { sidoList, gugunList, houseList, dongList, sortList, searchApt };
