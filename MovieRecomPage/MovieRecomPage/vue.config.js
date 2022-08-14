module.exports = {
  lintOnSave: false,
  devServer: {
    host: "localhost",
    port: 80,
    //转发
    proxy: {
      "/api": {
        target: "http://localhost:8080",
      }
    }
  }
};