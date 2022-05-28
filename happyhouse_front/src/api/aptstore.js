//import { apiInstance, houseInstance } from "./index.js";
import { apiInstance } from "./index.js";

const api = apiInstance();

function allList({ lng, lat }, success, fail) {
  api.get(`/aptstore/all`, { params: { lng, lat } }).then(success).catch(fail);
}

function categoryList({ lng, lat, category }, success, fail) {
  api
    .get(`/aptstore/category`, { params: { lng, lat, category } })
    .then(success)
    .catch(fail);
}

// function gugunList(params, success, fail) {
//   api.get(`/map/gugun`, { params: params }).then(success).catch(fail);
// }

// function houseList(params, success, fail) {
//   house.get(``, { params: params }).then(success).catch(fail);
// }

export { allList, categoryList };
