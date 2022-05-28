const { defineConfig } = require("@vue/cli-service");
module.exports = defineConfig({
  transpileDependencies: true,
});
module.exports = {
  pluginOptions: {
    proxy: {
      enabled: true,
      context: "/api",
      options: {
        target: "http:/localhost:9999/vue",
        changeOrigin: true,
      },
    },
  },
};
