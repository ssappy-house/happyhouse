import { apiInstance } from "./index.js";
import { deleteAllCommentByBoardno } from "./comment.js";

const api = apiInstance();

function listBoard(param, success, fail) {
  api.get(`/board/free`, { params: param }).then(success).catch(fail);
}

function listLocalboard(dongCode, success, fail) {
  api.get(`/board/local/${dongCode}`).then(success).catch(fail);
}

function writeBoard(board, success, fail) {
  api.post(`/board`, JSON.stringify(board)).then(success).catch(fail);
}

function getBoard(boardno, success, fail) {
  api.get(`/board/${boardno}`).then(success).catch(fail);
}

function modifyBoard(board, success, fail) {
  api
    .put(`/board/${board.boardno}`, JSON.stringify(board))
    .then(success)
    .catch(fail);
}

function deleteBoard(boardno, success, fail) {
  deleteAllCommentByBoardno(boardno);
  api.delete(`/board/${boardno}`).then(success).catch(fail);
}

export {
  listBoard,
  listLocalboard,
  writeBoard,
  getBoard,
  modifyBoard,
  deleteBoard,
};
