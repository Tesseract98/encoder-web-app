<template>
    <v-container>
        <v-card width="900px" class="my-10 mx-auto px-10 py-10 mb-11" shaped>
            <v-row>
                <v-btn class="mr-3" color="primary" @click="hideInfo">
                    Hide data
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
                <v-btn class="mr-3" color="primary" @click="extractInfo">
                    Extract data
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
    </v-container>
</template>

<script>
export default {
    data() {
        return {
            decipherFile: null,
            cipherFile: null,

            decipherFileText: "",
            cipherFileText: "",
        };
    },
    methods: {
        hideInfo() {
            console.log("hideInfo");
        },
        extractInfo() {
            console.log("extractInfo");
        },
    },
};
</script>

<style scoped>
</style>
