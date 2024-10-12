<template>
    <div class="container mt-4">
        <h5 class="mt-3 text-center">write note</h5>
        <div class="mb-3">
            <textarea class="form-control" v-model="noteData.title" rows="1" placeholder="title..."></textarea>
        </div>
        <div class="mb-3">
            <textarea class="form-control" v-model="noteData.note" rows="5" placeholder="write your note here..."></textarea>
        </div>
        <button class="btn btn-primary" @click="submitNote">submit</button>
    </div>
</template>

<script>
import axios from 'axios';

export default {
    data() {
        return {
            noteData: {
                title: null,
                note: null
            }
        }
    },
    methods: {
        async submitNote() {
            let writeData = {
                accountId: localStorage.getItem("notedAccountId"),
                title: this.noteData.title,
                note: this.noteData.note
            };
            let headers = {headers: {
                Authorization: "Bearer " + localStorage.getItem("notedToken")
            }};

            try {
                const writeResp = await axios.post("http://192.168.5.7:8080/notes/write", writeData, headers);

                if (writeResp.status == 200) {
                    this.$emit("noteSubmitted");
                    this.noteData.title = null;
                    this.noteData.note = null;
                }
            } catch(e) {
                console.error("Submit error: ", e);
            }
        }
    }
}
</script>