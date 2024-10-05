import { reactive } from 'vue'

export const auth = reactive({
    isAuthenticated: localStorage.getItem("notedToken") !== null,
    login() {
        this.isAuthenticated = true;
    },
    logout() {
        this.isAuthenticated = false;
    }
});
