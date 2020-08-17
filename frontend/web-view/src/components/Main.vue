<template> 
<div id="welcome_div">
    <img alt="Vue logo" src="../assets/logo.png">
    <h1>Encoder project</h1>
    <p>web pages</p>
    <button type="button" class="btn btn-outline-info" @click="register()">На страницу регистрации</button>
    <button type="button" class="btn btn-outline-success" @click="login()">На страницу авторизации</button>
</div>
</template>

<script>
export default {
    name: 'Main',
    methods: {
        register: function() {
            location.href = '/register'
        },
        login: function() {
            location.href = '/login'
        }
    }
}
</script>

<style scoped>
#welcome_div{
    margin: 5% 20% 10% 15%;
    width: 500;
}
</style>