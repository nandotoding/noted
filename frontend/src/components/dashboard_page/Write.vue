<template>
    <div class="container mt-4">
        <h5 class="mt-3 text-center">write note</h5>
        <div class="mb-3">
            <textarea class="form-control" v-model="noteData.title" rows="1" placeholder="title..."></textarea>
        </div>
        <div class="mb-3">
            <textarea class="form-control" v-model="noteData.note" rows="5" placeholder="write your note here..."></textarea>
        </div>
        <button class="btn btn-primary" @click="handleSubmitNote">submit</button>
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
            notesApi.write(noteData, emit);
            noteData.title = null;
            noteData.note = null;
        };

        return {noteData, notesApi, handleSubmitNote};
    }
}
</script>