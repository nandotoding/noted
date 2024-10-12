<style scoped>
.no-underline {
  text-decoration: none;
}
</style>

<template>
    <div class="container">
        <h2 class="mt-5 text-center">login</h2>
        <form @submit.prevent="handleLogin">
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
