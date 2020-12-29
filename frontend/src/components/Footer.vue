<template>
    <v-footer color="primary lighten-1" padless fixed>
        <v-col class="primary py-4 text-center white--text" cols="12">
            <strong> &copy; Все права защищены </strong> {{ year }}
        </v-col>
    </v-footer>
</template>

<script>
export default {
    name: "Footer",
    props: {
        year: {
            type: String,
            required: true,
        },
    },
};
</script>

<style scoped>
</style>