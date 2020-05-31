<template>
  <div id="app">
    <div style="padding: 20%;" v-if="showLogin">
      <Login @LoggedIn="showLogin=!showLogin"></Login>
    </div>
    <DashBoard v-else></DashBoard>
  </div>
</template>

<script>
import Login from "./pages/Login";
import DashBoard from "./pages/DashBoard";

export default {
  name: 'App',
  components: {
    Login, DashBoard
  },
  data () {
    return {
        showLogin: true
    }
  },
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}
</style>
