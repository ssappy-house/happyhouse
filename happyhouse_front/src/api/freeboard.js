import { apiInstance } from "./index.js";

const api = apiInstance();

function listArticle(param, success, fail) {
  api.get(`/freeboard`, { params: param }).then(success).catch(fail);
}

function writeArticle(freeboard, success, fail) {
  api.post(`/freeboard`, JSON.stringify(freeboard)).then(success).catch(fail);
}

function getArticle(boardno, success, fail) {
  api.get(`/freeboard/${boardno}`).then(success).catch(fail);
}

function modifyArticle(freeboard, success, fail) {
  api
    .put(`/freeboard/${freeboard.boardno}`, JSON.stringify(freeboard))
    .then(success)
    .catch(fail);
}

function deleteArticle(boardno, success, fail) {
  api.delete(`/freeboard/${boardno}`).then(success).catch(fail);
}

export { listArticle, writeArticle, getArticle, modifyArticle, deleteArticle };
