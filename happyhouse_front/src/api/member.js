import { apiInstance } from "./index.js";

const api = apiInstance();

async function login(user, success, fail) {
  await api.post(`/user/login`, JSON.stringify(user)).then(success).catch(fail);
}

async function findById(userid, success, fail) {
  api.defaults.headers["access-token"] = sessionStorage.getItem("access-token");
  await api.get(`/user/info/${userid}`).then(success).catch(fail);
}

function registU(user, success, fail) {
  api.post(`/user`, JSON.stringify(user)).then(success).catch(fail);
}

function modifyU(user, success, fail) {
  api
    .put(`/user/${user.userid}`, JSON.stringify(user))
    .then(success)
    .catch(fail);
}

function deleteU(userid, success, fail) {
  api.delete(`/user/${userid}`).then(success).catch(fail);
}

// function logout(success, fail)

export { login, findById, registU, modifyU, deleteU };
