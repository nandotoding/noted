<template>
    <div class="container">
        <div class="d-flex justify-content-between align-items-center mt-3 mb-5">
            <div class="align-items-center">
                <h5 :accountInfo>hi, {{ accountInfo.name }}!</h5>
            </div>
            <div @click="logout">
                <button type="button" class="btn btn-danger">logout</button>
            </div>
        </div>
        <Write @noteSubmitted="fetchNotes"/>
        <NotesList :notesData="notesData"/>
    </div>
</template>

<script>
import { auth } from '@/auth/auth';
import axios from 'axios';
import NotesList from "./NotesList.vue";
import AccountInfo from './AccountInfo.vue';
import Write from './Write.vue';

export default {
    data() {
        return {
            logoutData: {
                id: localStorage.getItem("notedAccountId")
            },
            accountInfo: {},
            notesData: []
        }
    },
    async created() {
        await this.fetchNotes();
        await this.fetchAccountInfo();
    },
    methods: {
        async fetchNotes() {
            let data = {accountId: localStorage.getItem("notedAccountId")};
            let headers = {headers: {
                Authorization: "Bearer " + localStorage.getItem("notedToken")
            }};

            try {
                const response = await axios.post("http://192.168.5.7:8080/notes/all", data, headers);
                this.notesData = response.data.data;
            } catch(e) {
                console.error("Failed fetching notes/all. ", e);
            }
        },
        async fetchAccountInfo() {
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
        NotesList,
        AccountInfo,
        Write
    }
}
</script>

