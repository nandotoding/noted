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
    </div>
</template>

<script>
import axios from "axios";
import { auth } from "./../../auth/auth"

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
                    console.log(loginResponse.headers);
                    const token = loginResponse.headers["authorization"].replace("Bearer ", "");
                    const accountId = loginResponse.data.data.id;
                    localStorage.setItem("notedToken", token);
                    localStorage.setItem("notedAccountId", accountId);
                    auth.login();
                    this.$router.push("/dashboard");
                }
            } catch(e) {
                console.error("Login failed. ", e);
            }
        }
    }
}
</script>
