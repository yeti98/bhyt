<template>
  <div id="app">
    <div style="padding: 20%;" v-if="!isLoggedIn">
      <Login></Login>
    </div>
    <DashBoard v-else></DashBoard>
    <div class="footer">
      <p>Copyright © 2020 N08. All rights reserved</p>
    </div>
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
  computed: {
    isLoggedIn: function() {
      return this.$store.getters.isLoggedIn;
    }
  },
  data () {
    return {
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
 .footer {
   position: fixed;
   left: 0;
   bottom: 0;
   width: 100%;
   background-color: gray;
   color: white;
   text-align: center;
 }
</style>
