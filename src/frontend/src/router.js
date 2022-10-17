import { createRouter, createWebHashHistory } from "vue-router";
import Home from "@/pages/Home";
import Inmates from "@/pages/Inmates";
import PrisonCells from "@/pages/PrisonCells";
import Overview from "@/pages/Overview";

const routes = [
    { path: '/', component: Home },
    { path: '/inmates', component: Inmates },
    { path: '/prisonCells', component: PrisonCells },
    { path: '/overview', component: Overview },
]

const router = new createRouter({
    history: createWebHashHistory(),
    routes,
})

export default router