<template>
    <div class="container mb-5">
        <div class="d-flex justify-content-between align-items-center mt-3 mb-5">
            <div class="align-items-center">
                <h5 class="text-white">{{ accountApi.accountInfo.name }}'s notes</h5>
            </div>
            <div @click="handleLogout" style="cursor: pointer;">
                <button type="button" class="btn btn-outline-light btn-sm">Logout</button>

            </div>
        </div>
        <Write @noteSubmitted="notesApi.getAll()"/>
         <NotesList @noteUpdated="notesApi.getAll()"/>
    </div>
</template>

<script>
import NotesList from "./NotesList.vue";
import Write from './Write.vue';
import { onMounted } from 'vue';
import { accountApi } from '@/api/accountApi';
import { notesApi } from '@/api/notesApi';
import { useRouter } from 'vue-router';

export default {
    setup() {
        const router = useRouter();

        onMounted(() => {
            accountApi.getAccountInfo();
        });

        const handleLogout = () => {
            accountApi.logout(router);
        }

        return {accountApi, notesApi, handleLogout};
    },
    components: {
        NotesList,
        Write
    }
}
</script>

