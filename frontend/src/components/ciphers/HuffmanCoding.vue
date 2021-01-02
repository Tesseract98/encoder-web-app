<template>
    <v-container>
        <v-card width="900px" class="mx-auto px-10 py-10 mb-11" shaped>
            <v-row>
                <v-btn class="mr-3" color="primary" @click="squeezeText">
                    Encode
                </v-btn>
                <v-btn color="secondary" @click="decodeText = ''">
                    Clear
                </v-btn>
            </v-row>
            <v-row class="my-5">
                <v-textarea
                    background-color="green lighten-5"
                    label="Enter start text"
                    ref="decodeText"
                    v-model="decodeText"
                ></v-textarea>
            </v-row>
            <hr />
            <v-row class="my-5">
                <v-btn class="mr-3" color="primary" @click="expandText">
                    Decode
                </v-btn>
                <v-btn color="secondary" @click="encodeText = ''">
                    Clear
                </v-btn>
            </v-row>
            <v-row>
                <v-textarea
                    background-color="green lighten-5"
                    label="Enter encoded text"
                    ref="encodeText"
                    v-model="encodeText"
                ></v-textarea>
            </v-row>
        </v-card>
    </v-container>
</template>

<script>
export default {
    data() {
        return {
            decodeText: "",
            encodeText: "",
        };
    },
    methods: {
        squeezeText() {
            console.log("squeezeText");
        },
        expandText() {
            console.log("expandText");
        },
    },
};
</script>

<style scoped>
</style>
