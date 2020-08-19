<template>
    <footer class="container-fluid" id="foot">
    <div class="container">
        <div class="row padding text-center">
            <div class="col-12">
                &copy; Все права защищены {{ year }}
            </div>
        </div>
    </div>
</footer>
</template>

<script>
export default {
    name: 'Footer',
    props: {
        year: {
            type: String,
            required: true
        }
    }
}
</script>

<style scoped>
footer{
    position: fixed;
    bottom: 0;
    background-color: darkblue;
    padding-top:  10px;
    padding-bottom:  10px;
    border-top: 2px solid black;
    color: white;
    margin-top: 20px;
}
</style>