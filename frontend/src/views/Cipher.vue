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
            <v-tab-item value="Steganography">
                <LsbSteganography />
            </v-tab-item>
            <v-tab-item value="HuffmanCoding">
                <HuffmanCoding />
            </v-tab-item>
        </v-tabs-items>
    </v-container>
</template>

<script>
import VernamCipher from "@/components/ciphers/VernamCipher";
import LsbSteganography from "@/components/ciphers/LsbSteganography"
import HuffmanCoding from "@/components/ciphers/HuffmanCoding"

export default {
    components: {
        VernamCipher,
        LsbSteganography,
        HuffmanCoding,
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
