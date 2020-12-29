<template>
    <v-app-bar app color="primary" dark>
        <div class="d-flex align-center">
            <router-link to="/cipher" class="navbar-brand">
                <v-img src="@/assets/lock_secure.png" alt="logo" width="40" />
            </router-link>
        </div>

        <v-spacer></v-spacer>

        <div class="container-fluid">
            <button
                class="navbar-toggler"
                type="button"
                data-toggle="collapse"
                data-target="#navbarResponse"
            >
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarResponse">
                <ul class="navbar nav ml-auto">
                    <li class="nav-item active">
                        <router-link to="/" class="nav-link"
                            >Main page</router-link
                        >
                    </li>
                    <li class="nav-item">
                        <router-link to="/cipher" class="nav-link"
                            >Cipher</router-link
                        >
                    </li>
                    <li class="nav-item">
                        <form action="/logout" method="post">
                            <input
                                type="submit"
                                class="btn btn-outline-info"
                                value="Sign Out"
                                id="formBtn"
                            />
                        </form>
                    </li>
                </ul>
            </div>
        </div>
    </v-app-bar>
</template>

<script>
export default {
    name: "Header",
    props: {},
};
</script>

<style scoped>
nav {
    margin-bottom: 20px;
}

.navbar-brand img {
    width: 50px;
}

.navbar-nav li {
    padding-right: 20px;
}

.nav-link {
    font-size: 1.1em !important;
    color: white;
}

.col-md-6 {
    color: Crimson;
}

#formBtn {
    color: FloralWhite;
}
</style>