import { auth } from "@/auth/auth";
import axios from "axios";
import { reactive } from "vue";
import * as bootstrap from 'bootstrap/dist/js/bootstrap.bundle';

export const accountApi = reactive({
    accountInfo: {},
    async register(registrationData) {
        let data = registrationData;

        try {
            const registrationResponse = await axios.post("http://192.168.5.7:8080/account/registration", data);

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
        let data = loginData;

        try {
            const loginResponse = await axios.post("http://192.168.5.7:8080/account/login", data);

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
        let data = {id: localStorage.getItem("notedAccountId")};
        let headers = {headers: {
            Authorization: "Bearer " + localStorage.getItem("notedToken")
        }};

        try {
            const response = await axios.post("http://192.168.5.7:8080/account/info", data, headers);
            this.accountInfo = response.data;
        } catch(e) {
            console.error("Failed fetching account/info. ", e);
        }
    },
    async logout(router) {
        let data = {id: localStorage.getItem("notedAccountId")};
        
        try {
            const logoutResponse = await axios.post("http://192.168.5.7:8080/account/logout", data);

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
