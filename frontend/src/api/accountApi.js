import { auth } from "@/auth/auth";
import axios from "axios";
import { reactive } from "vue";
import * as bootstrap from 'bootstrap/dist/js/bootstrap.bundle';

export const accountApi = reactive({
    accountInfo: {},
    async register(registrationData) {
        const registrationUrl = import.meta.env.VITE_BACKEND_URL + "/account/registration";
        let data = registrationData;

        try {
            const registrationResponse = await axios.post(registrationUrl, data);

            if (registrationResponse.status == 200) {
                const modal = new bootstrap.Modal(document.getElementById("successModal"));
                modal.show();
            } else {
                alert("Registration failed");
            }
        } catch(e) {
            if (e.response.data.code == 401 && e.response.data.message.toLowerCase() == "username already registered") {
                registrationData.password = null;
                const failedModal = new bootstrap.Modal(document.getElementById("failedModal"));
                failedModal.show();
            }

            console.error("Registration failed. ", e);
        }
    },
    async login(loginData, router) {
        const loginUrl = import.meta.env.VITE_BACKEND_URL + "/account/login";
        let data = loginData;

        try {
            const loginResponse = await axios.post(loginUrl, data);

            if (loginResponse.status == 200) {
                const token = loginResponse.headers["authorization"].replace("Bearer ", "");
                const accountId = loginResponse.data.data.id;
                localStorage.setItem("notedToken", token);
                localStorage.setItem("notedAccountId", accountId);
                auth.login();
                router.push("/dashboard");
            }
        } catch(e) {
            if (e.response.data.code == 401 && e.response.data.message.toLowerCase() == "invalid username or password") {
                const invalidUsnPassModal = new bootstrap.Modal(document.getElementById("invalidUsnPassModal"));
                invalidUsnPassModal.show();
            }

            console.error("Login failed. ", e);
        }
    },
    async getAccountInfo() {
        const accInfoUrl = import.meta.env.VITE_BACKEND_URL + "/account/info";
        let data = {id: localStorage.getItem("notedAccountId")};
        let headers = {headers: {
            Authorization: "Bearer " + localStorage.getItem("notedToken")
        }};

        try {
            const response = await axios.post(accInfoUrl, data, headers);
            this.accountInfo = response.data;
        } catch(e) {
            console.error("Failed fetching account/info. ", e);
        }
    },
    async logout(router) {
        const logoutUrl = import.meta.env.VITE_BACKEND_URL + "/account/logout";
        let data = {id: localStorage.getItem("notedAccountId")};
        
        try {
            const logoutResponse = await axios.post(logoutUrl, data);

            if (logoutResponse.status == 200) {
                localStorage.removeItem("notedToken");
                auth.logout();
                router.push("/");
            }
        } catch(e) {
            console.error("Logout failed. ", e);
        }
    }
});
