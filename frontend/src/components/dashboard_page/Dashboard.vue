<template>
    <div class="container">
        <h2 class="mt-5 text-center">DASHBOARD</h2>
        <div @click="logout">
            <button type="button" class="btn btn-danger">logout</button>
        </div>
        <notes-list/>
    </div>
</template>

<script>
import { auth } from '@/auth/auth';
import axios from 'axios';
import NotesList from "./NotesList.vue";

export default {
    data() {
        return {
            logoutData: {
                accountId: localStorage.getItem("notedAccountId")
            }
        }
    },
    methods: {
        async logout() {
            let data = this.logoutData;
            
            try {
                const logoutResponse = await axios.post("http://192.168.5.7:8080/account/logout", data);

                if (logoutResponse.status == 200) {
                    localStorage.removeItem("notedToken");
                    auth.logout();
                    this.$router.push("/");
                }
            } catch(e) {
                console.error("Logout failed. ", e);
            }
        }
    },
    components: {
        "notes-list": NotesList
    }
}
</script>

