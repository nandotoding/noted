<style scoped>
.no-underline {
  text-decoration: none;
}
</style>

<template>
    <div class="d-flex justify-content-center align-items-center">
        <div class="container card bg-dark m-4">
            <h2 class="mt-4 mb-3 text-center text-white">noted!</h2>
            <form @submit.prevent="handleRegistration">
                <div class="mb-3">
                    <label for="name" class="form-label text-white">Name</label>
                    <input type="text" class="form-control bg-dark text-white" v-model="registrationData.name" required/>
                </div>
                <div class="mb-3">
                    <label for="username" class="form-label text-white">Username</label>
                    <input type="text" class="form-control bg-dark text-white" v-model="registrationData.username" required/>
                </div>
                <div class="mb-3">
                    <label for="password" class="form-label text-white">Password</label>
                    <input type="password" class="form-control bg-dark text-white" v-model="registrationData.password" required/>
                </div>
                <button type="submit" class="btn btn-success w-100">Create Account</button>
            </form>

            <div class="mt-3">
                <p class="text-white">Have an account? <router-link to="/" class="link-primary text-info no-underline">Login</router-link></p>
            </div>

            <div class="modal fade" id="successModal" tabindex="-1" aria-labelledby="successModalLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content bg-dark">
                        <div class="modal-header">
                            <h5 class="modal-title text-white" id="successModalLabel">Success</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="close"></button>
                        </div>
                        <div class="modal-body text-white">
                            Successfully created account!
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-sm btn-success" @click="goToLogin">Login</button>
                            <button type="button" class="btn btn-sm btn-danger" data-bs-dismiss="modal">Close</button>
                        </div>
                    </div>
                </div>
            </div>

            <div class="modal fade" id="failedModal" tabindex="-1" aria-labelledby="failedModalLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content bg-dark">
                        <div class="modal-header">
                            <h5 class="modal-title text-white" id="failedModalLabel">Fail</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="close"></button>
                        </div>
                        <div class="modal-body text-white">
                            Username already used
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
import { accountApi } from '@/api/accountApi';
import * as bootstrap from 'bootstrap/dist/js/bootstrap.bundle';
import { reactive } from 'vue';
import { useRouter } from 'vue-router';

export default {
    setup() {
        const router = useRouter();
        const registrationData = reactive({
            name: null,
            username: null,
            password: null
        });

        const handleRegistration = () => {
            accountApi.register(registrationData);
        };

        const goToLogin = () => {
            const modalElement = document.getElementById("successModal");
            const modal = bootstrap.Modal.getInstance(modalElement);

            if (modal) {
                modal.hide();
            }

            router.push("/");
        };

        return {accountApi, registrationData, handleRegistration, goToLogin};
    }
};
</script>
