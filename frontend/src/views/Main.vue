<template>
    <v-container class="fill-height">
        <v-card class="mx-auto my-15 px-10 py-10" height="300" width="700px" shaped>
            <v-row class="mb-5">
                <h1 class="mx-auto">Encoder project</h1>
            </v-row>
            <v-row class="mb-5">
                <p class="mx-auto">web pages:</p>
            </v-row>
            <hr />
            <v-row class="mt-5">
                <v-col>
                    <v-btn color="info" @click="$router.push('register')">
                        На страницу регистрации
                    </v-btn>
                </v-col>
                <v-col>
                    <v-btn color="primary" @click="$router.push('/login')">
                        На страницу авторизации
                    </v-btn>
                </v-col>
            </v-row>
        </v-card>
    </v-container>
</template>

<script>
export default {
    beforeCreate: function () {
        document.body.className = "main";
    },
    name: "Main",
    methods: {
        // login: function () {
        //     location.href = "/login";
        // },
    },
};
</script>

<style scoped>
</style>
