<template>
    <div>
        <h5 class="text-center">my notes</h5>
        <table class="table mt-3 table-striped border-0">
            <tbody>
                <tr v-for="note in notesData" :key="note.id" class="align-middle">
                    <td class="border-0">
                        <button type="button" class="btn btn-secondary btn-sm" @click="options">options</button>
                    </td>
                    <td class="border-0">{{ note.title }}</td>
                    <td class="border-0">{{ note.note }}</td>
                </tr>
                <tr v-if="!notesData.length">
                    <td colspan="2" class="text-center border-0">
                        <small><em>you haven't written any notes yet</em></small>
                    </td>
                </tr>
            </tbody>
        </table>
    </div>
</template>

<script>
import axios from 'axios';

export default {
    data() {
        return {
            notesData: []
        };
    },
    async created() {
        await this.fetchNotes();
    },
    methods: {
        async fetchNotes() {
            let data = {accountId: localStorage.getItem("notedAccountId")};
            try {
                const response = await axios.post("http://192.168.5.7:8080/notes/all", data);
                this.notesData = response.data.data;
            } catch(e) {
                console.error("Failed fetching notes/all. ", e);
            }
        }
    }
}
</script>
