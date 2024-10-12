<template>
    <div>
        <h5 class="text-center">my notes</h5>
        <table class="table mt-3 table-striped border-0">
            <tbody>
                <tr v-for="note in notesApi.notesList" :key="note.id" class="align-middle">
                    <td class="border-0">
                        <button type="button" class="btn btn-secondary btn-sm" @click="view">view</button>
                    </td>
                    <td class="border-0">{{ note.title }}</td>
                    <td class="border-0">{{ note.note }}</td>
                </tr>
                <tr v-if="!notesApi.notesList.length">
                    <td colspan="2" class="text-center border-0">
                        <small><em>you haven't written any notes yet</em></small>
                    </td>
                </tr>
            </tbody>
        </table>
    </div>
</template>

<script>
import { notesApi } from '@/api/notesApi';
import { onMounted } from 'vue';

export default {
    setup() {
        onMounted(() => {
            notesApi.getAll();
        });

        return {notesApi};
    }
}
</script>
