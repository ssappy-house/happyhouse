import { apiInstance } from "./index.js";

const api = apiInstance();

// function listArticle(param, success, fail) {
//   api.get(`/freeboard`, { params: param }).then(success).catch(fail);
// }

function listComment(boardno, success, fail) {
  api.get(`/comment/${boardno}`).then(success).catch(fail);
}

function writeComment(comment, success, fail) {
  api.post(`/comment`, JSON.stringify(comment)).then(success).catch(fail);
}

function modifyComment(comment, success, fail) {
  api
    .put(`/comment/${comment.commentno}`, JSON.stringify(comment))
    .then(success)
    .catch(fail);
}

function deleteComment(commentno, success, fail) {
  api.delete(`/comment/${commentno}`).then(success).catch(fail);
}

function deleteAllCommentByBoardno(boardno, success, fail) {
  api.delete(`/comment/${boardno}/all`).then(success).catch(fail);
}

export {
  listComment,
  writeComment,
  modifyComment,
  deleteComment,
  deleteAllCommentByBoardno,
};
