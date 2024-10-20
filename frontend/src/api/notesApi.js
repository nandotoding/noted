import axios from "axios";
import { reactive } from "vue";
import { accountApi } from "./accountApi";

export const notesApi = reactive({
    notesList: [],
    async getAll() {
        const notesAllUrl = import.meta.env.VITE_BACKEND_URL + "/notes/all";
        let data = {accountId: localStorage.getItem("notedAccountId")};
        let headers = {headers: {
            Authorization: "Bearer " + localStorage.getItem("notedToken")
        }};

        try {
            const response = await axios.post(notesAllUrl, data, headers);
            this.notesList = response.data.data;
        } catch(e) {
            console.error("Failed fetching notes/all. ", e);
        }
    },
    async write(noteData, emitter) {
        const writeUrl = import.meta.env.VITE_BACKEND_URL + "/notes/write";
        let writeData = {
            accountId: localStorage.getItem("notedAccountId"),
            title: noteData.title,
            note: noteData.note
        };
        let headers = {headers: {
            Authorization: "Bearer " + localStorage.getItem("notedToken")
        }};

        try {
            const writeResp = await axios.post(writeUrl, writeData, headers);

            if (writeResp.status == 200) {
                emitter("noteSubmitted");
            }
        } catch(e) {
            console.error("Submit error: ", e);
        }
    },
    async delete(noteData, emitter) {
        const deleteUrl = import.meta.env.VITE_BACKEND_URL + "/notes/delete";
        let deleteData = {
            id: noteData.id,
            accountId: localStorage.getItem("notedAccountId")
        };
        let headers = {headers: {
            Authorization: "Bearer " + localStorage.getItem("notedToken")
        }};

        try {
            const deleteResp = await axios.post(deleteUrl, deleteData, headers);

            if (deleteResp.status == 200) {
                emitter("noteUpdated");
            }
        } catch(e) {
            console.error("Deleting error. ", e);
        }
    }
});
