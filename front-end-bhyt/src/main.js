import Vue from 'vue'
import App from './App.vue'
import store from './store'
import ViewUI from 'view-design';
import 'view-design/dist/styles/iview.css';
import locale from 'view-design/dist/locale/vi-VN';

Vue.use(ViewUI, {locale: locale});
Vue.config.productionTip = false

new Vue({
  store,
  render: function (h) { return h(App) }
}).$mount('#app')
