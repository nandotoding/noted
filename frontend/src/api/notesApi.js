import axios from "axios";
import { reactive } from "vue";
import { accountApi } from "./accountApi";

export const notesApi = reactive({
    notesList: [],
    async getAll() {
        let data = {accountId: localStorage.getItem("notedAccountId")};
        let headers = {headers: {
            Authorization: "Bearer " + localStorage.getItem("notedToken")
        }};

        try {
            const response = await axios.post("http://192.168.5.7:8080/notes/all", data, headers);
            this.notesList = response.data.data;
        } catch(e) {
            console.error("Failed fetching notes/all. ", e);
        }
    },
    async write(noteData, emitter) {
        let writeData = {
            accountId: localStorage.getItem("notedAccountId"),
            title: noteData.title,
            note: noteData.note
        };
        let headers = {headers: {
            Authorization: "Bearer " + localStorage.getItem("notedToken")
        }};

        try {
            const writeResp = await axios.post("http://192.168.5.7:8080/notes/write", writeData, headers);

            if (writeResp.status == 200) {
                emitter("noteSubmitted");
            }
        } catch(e) {
            console.error("Submit error: ", e);
        }
    },
    async delete(noteData, emitter) {
        let deleteData = {
            id: noteData.id,
            accountId: localStorage.getItem("notedAccountId")
        };
        let headers = {headers: {
            Authorization: "Bearer " + localStorage.getItem("notedToken")
        }};

        try {
            const deleteResp = await axios.post("http://192.168.5.7:8080/notes/delete", deleteData, headers);

            if (deleteResp.status == 200) {
                emitter("noteUpdated");
            }
        } catch(e) {
            console.error("Deleting error. ", e);
        }
    }
});
