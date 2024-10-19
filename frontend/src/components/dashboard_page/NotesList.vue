<template>
    <div class="mt-5 mb-3">
        <div>
            <div v-for="note in notesApi.notesList" :key="note.id" class="card mt-1 mb-1 p-2 bg-dark">
                <div class="d-flex justify-content-between align-items-center card-header">
                    <div>
                        <h6 class="text-white">{{ note.title }}</h6>
                    </div>
                    <div class="dropdown">
                        <button class="btn btn-dark btn-sm dropdown-toggle" type="button" id="noteOptionsBtn" data-bs-toggle="dropdown" aria-expanded="false"></button>
                        <ul class="dropdown-menu" aria-labelledby="noteOptionsBtn" style="background-color: black;">
                            <!-- <li><a class="dropdown-item text-white" href="#" @click.prevent="handleEditNote(note)">Edit</a></li> -->
                            <li><a class="dropdown-item text-white" href="#" @click.prevent="handleDeleteNote(note)">Delete</a></li>
                        </ul>
                    </div>
                </div>
                <div class="card-body">
                    <p class="text-white">{{ note.note }}</p>
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
