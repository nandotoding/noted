<style scoped>
.no-underline {
  text-decoration: none; /* Remove underline */
}
</style>

<template>
    <div class="container">
        <h2 class="mt-5 text-center">create new account</h2>
        <form @submit.prevent="register">
            <div class="mb-3">
                <label for="name" class="form-label">full name</label>
                <input type="text" class="form-control" v-model="registrationData.name" required/>
            </div>
            <div class="mb-3">
                <label for="username" class="form-label">username</label>
                <input type="text" class="form-control" v-model="registrationData.username" required/>
            </div>
            <div class="mb-3">
                <label for="password" class="form-label">password</label>
                <input type="password" class="form-control" v-model="registrationData.password" required/>
            </div>
            <button type="submit" class="btn btn-primary w-100 fw-bold">create account</button>
        </form>

        <div class="mt-3">
            <p>have an account? <router-link to="/" class="link-primary fw-bold no-underline">login</router-link></p>
        </div>

        <div class="modal fade" id="successModal" tabindex="-1" aria-labelledby="successModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="successModalLabel">success</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="close"></button>
                    </div>
                    <div class="modal-body">
                        successfully created account!
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-primary fw-bold" @click="goToLogin">login</button>
                        <button type="button" class="btn btn-secondary fw-bold" data-bs-dismiss="modal">close</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import axios from 'axios';
import * as bootstrap from 'bootstrap/dist/js/bootstrap.bundle';

export default {
    data() {
        return {
            registrationData: {
                name: null, 
                username: null,
                password: null
            },
            successMessage: null
        };
    },
    methods: {
        async register() {
            let data = {
                name: this.registrationData.name,
                username: this.registrationData.username,
                password: this.registrationData.password
            };

            try {
                const registrationResponse = await axios.post("http://192.168.5.7:8080/account/registration", data);

                if (registrationResponse.status == 200) {
                    this.registrationData.name = null;
                    this.registrationData.username = null;
                    this.registrationData.password = null;
                    const modal = new bootstrap.Modal(document.getElementById("successModal"));
                    modal.show();
                } else {
                    alert("fill all fields");
                }
            } catch(e) {
                console.error("Registration failed. ", e);
            }
        },
        goToLogin() {
            const modalElement = document.getElementById("successModal");
            const modal = bootstrap.Modal.getInstance(modalElement);

            if (modal) {
                modal.hide();
            }

            this.$router.push("/");
        }
    }
};
</script>