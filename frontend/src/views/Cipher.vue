<template>
    <v-container>
        <v-tabs v-model="currentTab" centered show-arrows fixed-tabs>
            <v-tab
                v-for="item in tabs"
                :key="item.value"
                :href="`#${item.value}`"
            >
                {{ item.name }}
            </v-tab>
        </v-tabs>

        <v-tabs-items v-model="currentTab">
            <v-tab-item value="VernamCipher">
                <VernamCipher />
            </v-tab-item>
        </v-tabs-items>
    </v-container>
</template>

<script>
import VernamCipher from "@/components/ciphers/VernamCipher";

export default {
    components: {
        VernamCipher,
    },

    data() {
        return {
            tabs: [
                { name: "Vernam Cipher", value: "VernamCipher" },
                { name: "Steganography", value: "Steganography" },
                { name: "Huffman Coding", value: "HuffmanCoding" },
            ],
            currentTab: "VernamCipher",
        };
    },
};
</script>

<style scoped>
</style>
