<template>
    <v-container class="fill-height">
        <v-card width="900px" class="mx-auto px-10 py-10 mb-5" shaped>
            <v-row>
                <v-btn class="mr-3" color="primary" @click="onSubmit">
                    Cipher
                </v-btn>
                <v-btn color="secondary" @click="decipherText = ''">
                    Clear
                </v-btn>
            </v-row>
            <v-row class="my-5">
                <v-textarea
                    background-color="green lighten-5"
                    label="Enter start text"
                    ref="decipherText"
                    v-model="decipherText"
                ></v-textarea>
            </v-row>
            <hr />
            <v-row class="my-5">
                <v-btn class="mr-3" color="primary" @click="onSubmit">
                    Decipher
                </v-btn>
                <v-btn color="secondary" @click="cipherText = ''">
                    Clear
                </v-btn>
            </v-row>
            <v-row>
                <v-textarea
                    background-color="green lighten-5"
                    label="Enter ciphered text"
                    ref="cipherText"
                    v-model="cipherText"
                ></v-textarea>
            </v-row>
        </v-card>
    </v-container>
</template>

<script>
export default {
    beforeCreate: function () {
        document.body.className = "cipher";
    },
    name: "Cipher",
    data() {
        return {
            decipherText: "",
            cipherText: "",
        };
    },
    methods: {
        onSubmit() {
            console.log("submit the form");
            console.log(this.decipherText);
            console.log(this.cipherText);
        },
    },
};
</script>

<style scoped>
</style>
