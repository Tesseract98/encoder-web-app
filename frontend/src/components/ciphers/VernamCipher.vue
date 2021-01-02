<template>
    <v-container class="fill-height">
        <v-row class="pt-10">
            <v-radio-group v-model="cypherDataType" class="mx-auto" row>
                <v-radio value="text" label="Text" color="indigo"> </v-radio>
                <v-radio value="file" label="Png" color="indigo"> </v-radio>
            </v-radio-group>
        </v-row>
        <v-card
            width="900px"
            class="mx-auto px-10 py-10 mb-10"
            v-if="cypherDataType == 'file'"
            shaped
        >
            <v-row>
                <v-btn class="mr-3" color="primary" @click="encryptFile">
                    Cipher
                </v-btn>
                <v-btn color="secondary" @click="decipherFileText = ''">
                    Clear
                </v-btn>
            </v-row>
            <v-row class="mt-5">
                <v-file-input
                    v-model="decipherFile"
                    label="Before Png file"
                    accept=".png"
                    show-size
                >
                </v-file-input>
            </v-row>
            <v-row class="mb-5">
                <v-textarea
                    background-color="green lighten-5"
                    label="Enter start text"
                    ref="decipherFileText"
                    v-model="decipherFileText"
                ></v-textarea>
            </v-row>
            <hr />
            <v-row class="my-5">
                <v-btn class="mr-3" color="primary" @click="decryptFile">
                    Decipher
                </v-btn>
                <v-btn color="secondary" @click="cipherFileText = ''">
                    Clear
                </v-btn>
            </v-row>
            <v-row>
                <v-file-input
                    v-model="cipherFile"
                    label="Encode Png file"
                    accept=".png"
                    show-size
                >
                </v-file-input>
            </v-row>
            <v-row>
                <v-textarea
                    background-color="green lighten-5"
                    label="Extracted text"
                    ref="cipherFileText"
                    v-model="cipherFileText"
                    readonly
                ></v-textarea>
            </v-row>
        </v-card>

        <v-card
            width="900px"
            class="mx-auto px-10 py-10 mb-11"
            v-if="cypherDataType == 'text'"
            shaped
        >
            <v-row>
                <v-btn class="mr-3" color="primary" @click="encryptText">
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
                <v-btn class="mr-3" color="primary" @click="decryptText">
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
    // beforeCreate: function () {
    //     document.body.className = "VernamCipher";
    // },
    // name: "VernamCipher",
    data() {
        return {
            cypherDataType: "text",

            decipherText: "",
            cipherText: "",

            decipherFileText: "",
            cipherFileText: "",

            decipherFile: null,
            cipherFile: null,
        };
    },
    methods: {
        encryptFile() {
            console.log("encryptFile");
        },
        encryptText() {
            console.log("encryptText");
        },
        decryptFile() {
            console.log("decryptFile");
        },
        decryptText() {
            console.log("decryptText");
        },
    },
};
</script>

<style scoped>
</style>
