<template>
    <div>
        <h5 class="text-center">my notes</h5>
        <div>
            <div v-for="note in notesApi.notesList" :key="note.id" class="card mt-1 mb-1 p-2">
                <div class="d-flex justify-content-between align-items-center card-header">
                    <div>
                        <h6>{{ note.title }}</h6>
                    </div>
                    <div class="dropdown">
                        <button class="btn btn-secondary btn-sm dropdown-toggle" type="button" id="noteOptionsBtn" data-bs-toggle="dropdown" aria-expanded="false"></button>
                        <ul class="dropdown-menu" aria-labelledby="noteOptionsBtn">
                            <li><a class="dropdown-item" href="#" @click.prevent="handleEditNote(note)">edit</a></li>
                            <li><a class="dropdown-item" href="#" @click.prevent="handleDeleteNote(note)">delete</a></li>
                        </ul>
                    </div>
                </div>
                <div class="card-body">
                    <p>{{ note.note }}</p>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import { notesApi } from '@/api/notesApi';
import { onMounted } from 'vue';

export default {
    setup(_, {emit}) {
        onMounted(() => {
            notesApi.getAll();
        });

        const handleDeleteNote = (noteData) => {
            notesApi.delete(noteData, emit);
        };

        return {notesApi, handleDeleteNote};
    }
}
</script>
