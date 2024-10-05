import * as bootstrap from 'bootstrap/dist/js/bootstrap.bundle';
import './assets/main.css';

import { createApp } from 'vue';
import App from './App.vue';
import { createRouter, createWebHistory } from 'vue-router';
import Login from './components/login_page/Login.vue';
import Dashboard from './components/dashboard_page/Dashboard.vue';
import { auth } from './auth/auth';
import Registration from './components/registration_page/Registration.vue';

const router = createRouter({
    history: createWebHistory(),
    routes: [
        {
            path: "/",
            component: Login
        },
        {
            path: "/registration",
            component: Registration
        },
        {
            path: "/dashboard",
            component: Dashboard,
            meta: {requiresAuth: true}
        }
    ]
});

router.beforeEach((to, from, next) => {
    if (to.matched.some(r => r.meta.requiresAuth) && !auth.isAuthenticated) {
        next("/");
    } else {
        next();
    }
});

const app = createApp(App);
app.provide('bootstrap', bootstrap);
app.use(router);
app.mount('#app');
