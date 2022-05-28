// javascript

// const express = require("express");
// const { createProxyMiddleware } = require("http-proxy-middleware");

// const app = express();

// app.use(
//   "/api",
//   createProxyMiddleware({
//     target: "http://www.example.org",
//     changeOrigin: true,
//   }),
// );
// app.listen(9999);

// http://localhost:3000/api/foo/bar -> http://www.example.org/api/foo/bar
// src/setupProxy.js
const { createProxyMiddleware } = require("http-proxy-middleware");

module.exports = function (app) {
  app.use(
    createProxyMiddleware("/api", {
      target: "https://localhost:9999/vue",
      changeOrigin: true,
      pathRewrite: {
        "^/api": "", // URL ^/api -> 공백 변경
      },
    }),
  );
};
