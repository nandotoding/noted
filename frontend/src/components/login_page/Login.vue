<style scoped>
.no-underline {
  text-decoration: none;
}
</style>

<template>
    <div class="container">
        <h2 class="mt-5 text-center">login</h2>
        <form @submit.prevent="login">
            <div class="mb-3">
                <label for="username" class="form-label">username</label>
                <input type="text" class="form-control" v-model="loginData.username" required/>
            </div>
            <div class="mb-3">
                <label for="password" class="form-label">password</label>
                <input type="password" class="form-control" v-model="loginData.password" required/>
            </div>
            <button type="submit" class="btn btn-primary w-100 fw-bold">login</button>
        </form>
        <div class="mt-3">
            <p>don't have an account? 
                <router-link to="/registration" class="link-primary fw-bold no-underline">create new account</router-link>
            </p>
        </div>

        <div class="modal fade" id="invalidUsnPassModal" tabindex="-1" aria-labelledby="invalidUsnPassModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="invalidUsnPassModalLabel">failed</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="close"></button>
                    </div>
                    <div class="modal-body">
                        invalid username or password
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary fw-bold" data-bs-dismiss="modal">close</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import axios from "axios";
import { auth } from "./../../auth/auth";
import * as bootstrap from 'bootstrap/dist/js/bootstrap.bundle';

export default {
    data() {
        return {
            loginData: {
                username: null,
                password: null
            }
        }
    },
    methods: {
        async login() {
            let data = {
                username: this.loginData.username,
                password: this.loginData.password
            }

            try {
                const loginResponse = await axios.post("http://192.168.5.7:8080/account/login", data);

                if (loginResponse.status == 200) {
                    const token = loginResponse.headers["authorization"].replace("Bearer ", "");
                    const accountId = loginResponse.data.data.id;
                    localStorage.setItem("notedToken", token);
                    localStorage.setItem("notedAccountId", accountId);
                    auth.login();
                    this.$router.push("/dashboard");
                }
            } catch(e) {
                if (e.response.data.code == 401 && e.response.data.message.toLowerCase() == "invalid username or password") {
                    const invalidUsnPassModal = new bootstrap.Modal(document.getElementById("invalidUsnPassModal"));
                    invalidUsnPassModal.show();
                }

                console.error("Login failed. ", e);
            }
        }
    }
}
</script>
