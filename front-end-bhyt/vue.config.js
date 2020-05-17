module.exports = {
  // proxy all webpack dev-server requests starting with /api
  // to our Spring Boot backend (localhost:8088) using http-proxy-middleware
  // see https://cli.vuejs.org/config/#devserver-proxy
  devServer: {
    proxy: {
      '/api': {
        target: process.env.VUE_APP_BASE_API, // this configuration needs to correspond to the Spring Boot backends' application.properties server.port
        ws: true,
        changeOrigin: true
      }
    }
  },
};
