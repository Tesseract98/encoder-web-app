<template> 
<div id="welcome_div">
    <img alt="Vue logo" src="@/assets/logo.png">
    <h1>Encoder project</h1>
    <p>web pages</p>
    <router-link to='/register'>
        <button type="button" class="btn btn-outline-info">На страницу регистрации</button>
    </router-link>
    <button type="button" class="btn btn-outline-success" @click="login()">На страницу авторизации</button>
</div>
</template>

<script>
export default {
    beforeCreate: function() {
        document.body.className = 'main';
    },
    name: 'Main',
    methods: {
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