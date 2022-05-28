import { apiInstance } from "./index.js";

const api = apiInstance();

function listArticle(param, success, fail) {
  api.get(`/notice`, { params: param }).then(success).catch(fail);
}

function writeArticle(notice, success, fail) {
  api.post(`/notice`, JSON.stringify(notice)).then(success).catch(fail);
}

function getArticle(noticeno, success, fail) {
  api.get(`/notice/${noticeno}`).then(success).catch(fail);
}

function modifyArticle(notice, success, fail) {
  api
    .put(`/notice/${notice.noticeno}`, JSON.stringify(notice))
    .then(success)
    .catch(fail);
}

function deleteArticle(noticeno, success, fail) {
  api.delete(`/notice/${noticeno}`).then(success).catch(fail);
}

export { listArticle, writeArticle, getArticle, modifyArticle, deleteArticle };
