<style scoped>
.no-underline {
  text-decoration: none;
}
</style>

<template>
    <div class="d-flex justify-content-center align-items-center">
        <div class="container card m-4 bg-dark">
            <h2 class="mt-4 mb-3 text-center text-white">noted!</h2>
            <form @submit.prevent="handleLogin">
                <div class="mb-3">
                    <label for="username" class="form-label text-white">Username</label>
                    <input type="text" class="form-control bg-dark text-white" v-model="loginData.username" required/>
                </div>
                <div class="mb-3">
                    <label for="password" class="form-label text-white">Password</label>
                    <input type="password" class="form-control bg-dark text-white" v-model="loginData.password" required/>
                </div>
                <button type="submit" class="btn btn-success w-100">Login</button>
            </form>
            <div class="mt-3">
                <p class="text-white">Don't have an account? 
                    <router-link to="/registration" class="link-primary text-info no-underline">Create new account</router-link>
                </p>
            </div>

            <div class="modal fade" id="invalidUsnPassModal" tabindex="-1" aria-labelledby="invalidUsnPassModalLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content bg-dark">
                        <div class="modal-header">
                            <h5 class="modal-title text-white" id="invalidUsnPassModalLabel">Fail</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="close"></button>
                        </div>
                        <div class="modal-body text-white">
                            Invalid username or password
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-sm btn-danger" data-bs-dismiss="modal">Close</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import { reactive } from "vue";
import { accountApi } from "@/api/accountApi";
import { useRouter } from "vue-router";

export default {
    setup() {
        const router = useRouter();
        const loginData = reactive({
            username: null,
            password: null
        });

        const handleLogin = () => {
            accountApi.login(loginData, router);
        }

        return {accountApi, loginData, handleLogin};
    }
}
</script>
