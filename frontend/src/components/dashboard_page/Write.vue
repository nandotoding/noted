<style scoped>
    .textarea-placeholder::placeholder {
        color: #ccc; /* Light gray placeholder */
        opacity: 1; /* Ensures visibility */
    }
</style>

<template>
    <div class="container p-3 bg-dark rounded">
        <div class="mb-3">
            <textarea class="form-control bg-dark text-white textarea-placeholder" v-model="noteData.title" rows="1" placeholder="title..." required></textarea>
        </div>
        <div class="mb-3">
            <textarea class="form-control bg-dark text-white textarea-placeholder" v-model="noteData.note" rows="5" placeholder="write your note here..." required></textarea>
        </div>
        <div class="text-end">
            <button class="btn btn-success btn-sm w-100" @click="handleSubmitNote">Write Note</button>
        </div>
    </div>
</template>

<script>
import { notesApi } from '@/api/notesApi';
import { reactive } from 'vue';

export default {
    setup(_, {emit}) {
        const noteData = reactive({
            title: null,
            note: null
        });

        const handleSubmitNote = () => {
            if (noteData.title === null) noteData.title = "Untitled";
            notesApi.write(noteData, emit);
            noteData.title = null;
            noteData.note = null;
        };

        return {noteData, notesApi, handleSubmitNote};
    }
}
</script>