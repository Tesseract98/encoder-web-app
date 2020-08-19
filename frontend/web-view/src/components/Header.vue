<template>
    <nav class="navbar navbar-expand-md navbar-dark bg-dark sticky-top">
        <div class="container-fluid">
            <router-link to="/cipher" class="navbar-brand"><img src="@/assets/lock_secure.png" alt="logo"></router-link>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponse">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarResponse">
                <ul class="navbar nav ml-auto">
                    <li class="nav-item active">
                        <a href="/" class="nav-link">Main page</a>
                    </li>
                    <li class="nav-item">
                        <a href="/cipher" class="nav-link">Cipher</a>
                    </li>
                    <li class="nav-item">
                        <form action="/logout" method="post">
                            <input type="submit" class="btn btn-outline-info" value="Sign Out" id="formBtn"/>
                        </form>
                    </li>
                </ul>
            </div>
        </div>
    </nav>
</template>

<script>
export default {
    name: 'Header',
    props: {

    }
}
</script>

<style scoped>
nav {
    margin-bottom: 20px;
}

.navbar-brand img{
    width: 50px;
}

.navbar-nav li {
    padding-right: 20px;
}

.nav-link {
    font-size: 1.1em !important;
    color: white;
}

.col-md-6{
    color: Crimson;
}

#formBtn{
    color: FloralWhite;
}
</style>