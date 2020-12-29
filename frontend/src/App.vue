<template>
    <v-app>
        <Header />
        <v-main>
            <router-view />
        </v-main>
        <Footer v-bind:year="current_year" />
    </v-app>
</template>

<script>
import Header from "./components/Header.vue";
import Footer from "./components/Footer.vue";

export default {
    name: "App",
    data() {
        return {
            current_year: new Date().getFullYear().toString(),
        };
    },
    components: {
        Header,
        Footer,
    },
};
</script>

<style>
#app {
    font-family: Avenir, Helvetica, Arial, sans-serif;
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
    text-align: center;
    color: #2c3e50;
}

@import url("https://fonts.googleapis.com/css?family=Poppins:400,500,700&display=swap");
</style>
