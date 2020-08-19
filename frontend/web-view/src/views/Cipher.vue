<template>
    <form v-on:submit.prevent="onSubmit" action="/cipher" method="POST" class="cipherForm">
        <br>
        <div class="button_position">
            <button class="btn btn-primary" type="submit" name="submit" value="0">Cipher</button>
            <button class="btn btn-secondary" type="submit" name="reset" value="0">Clear</button>
        </div>
        <div class="form-group">
            <label for="StartText">Enter start text</label>
            <textarea id="StartText" class="form-control" rows="3" name="decipherText" v-model="decipherText"></textarea>
        </div>
        <hr>
        <div class="button_position">
            <button class="btn btn-primary" type="submit" name="submit" value="1">Decipher</button>
            <button class="btn btn-secondary" type="submit" name="reset"  value="1">Clear</button>
        </div>
        <div class="form-group">
            <label for="CipherTxt">Enter cipher text</label>
            <textarea id = "CipherTxt" class="form-control" rows="3" name="cipherText" v-model="cipherText"></textarea>
        </div>
    </form>
</template>

<script>
export default {
    beforeCreate: function() {
        document.body.className = 'cipher';
    },
    name: 'Cipher',
    data() {
      return {
          decipherText: '',
          cipherText: ''
      }  
    },
    methods: {
        onSubmit () {
            console.log("submit the form")
            console.log(this.decipherText)
            console.log(this.cipherText)
        }
    }
}
</script>

<style scoped>
body{
    background-color: LightBlue;
}

@media only screen and (min-device-width : 300px) and (max-device-width : 450px){
    .cipherForm{
        margin: 50% 15% 70px 10%;
    }
}

@media only screen and (min-device-width : 450px) and (max-device-width : 600px){
    .cipherForm{
        margin: 25% 15% 70px 10%;
    }
}

@media only screen and (min-device-width : 600px) and (max-device-width : 760px){
    .cipherForm{
        margin: 13% 15% 70px 10%;
    }
}

@media only screen and (min-device-width : 760px){
    .cipherForm{
        margin: 1% 15% 70px 10%;
    }
}

.form-control{
    padding: 5px 10px 150px 10px;
    resize: none;
    background-color: #98FB98;
}

.button_position{
    float: right;
    margin: 0px 5px 5px 0px;
}
</style>