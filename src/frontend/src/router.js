import Home from "@/pages/Home";
import Inmates from "@/pages/Inmates";
import { createRouter, createWebHashHistory } from "vue-router";

const routes = [
    { path: '/', component: Home },
    { path: '/inmates', component: Inmates },
]

const router = new createRouter({
    history: createWebHashHistory(),
    routes,
})

export default router