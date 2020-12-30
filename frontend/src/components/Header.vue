<template>
    <!-- <v-app-bar app color="primary"> -->

    <div>
        <!-- <v-navigation-drawer app v-model="sidebar" class="hidden-xl-only">
            <v-list>
                <v-list-item-group>
                    <v-list-item
                        v-for="item in menuItems"
                        :key="item.title"
                        :to="item.path"
                    >
                        <v-list-item-action>
                            <v-icon> {{ item.icon }} </v-icon>
                        </v-list-item-action>
                        <v-list-item-content>
                            {{ item.title }}
                        </v-list-item-content>
                    </v-list-item>
                </v-list-item-group>
            </v-list>
        </v-navigation-drawer> -->

        <v-toolbar app color="primary">
            <span class="hidden-sm-and-up">
                <v-app-bar-nav-icon
                    @click="sidebar = !sidebar"
                ></v-app-bar-nav-icon>
            </span>
            <v-toolbar-title>
                <router-link to="/cipher" class="navbar-brand">
                    <v-img
                        src="@/assets/lock_secure.png"
                        alt="logo"
                        width="40"
                    />
                </router-link>
            </v-toolbar-title>
            <v-spacer> </v-spacer>
            <v-toolbar-items class="hidden-xs-only">
                <v-btn
                    color="primary"
                    dence
                    v-for="item in menuItems"
                    :key="item.title"
                    :to="item.path"
                >
                    <v-icon left dark>
                        {{ item.icon }}
                    </v-icon>
                    {{ item.title }}
                </v-btn>
            </v-toolbar-items>
        </v-toolbar>
    </div>
</template>

<script>
export default {
    name: "Header",

    props: {},

    data() {
        return {
            sidebar: false,

            menuItems: [
                { title: "Main page", path: "/", icon: "home" },
                {
                    title: "Cipher",
                    path: "/cipher",
                    icon: "enhanced_encryption",
                },
                { title: "logout", path: "/logout", icon: "lock_open" },
            ],
        };
    },
};
</script>

<style scoped>
</style>
