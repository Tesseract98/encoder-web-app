import Vue from 'vue'
import VueRouter from 'vue-router'
import Main from '@/views/Main'

Vue.use(VueRouter)

const paths = [
    {
        path: '/',
        name: 'Main',
        component: Main
    },
    {
        path: '/cipher',
        name: 'Cipher',
        component: () => import('@/views/Cipher.vue')      // lazy loading
    }
]

export default new VueRouter({
    mode: 'history',
    routes: paths
})
