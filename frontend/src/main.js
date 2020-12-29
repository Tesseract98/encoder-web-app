import Vue from "vue";
import App from "./App.vue";
import router from "./paths/router.js";
import vuetify from "./plugins/vuetify";
import "@mdi/font/css/materialdesignicons.css";

Vue.config.productionTip = false;

new Vue({
    router, // equivalent router: router
    vuetify,
    el: "#app",
    render: (h) => h(App),
}).$mount("#app");
