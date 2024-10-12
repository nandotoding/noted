<template>
    <p v-bind="accountInfo">{{ accountInfo.name }}</p>
</template>

<script>
import axios from 'axios';

export default {
    data() {
        return {
            accountInfo: {}
        }
    },
    methods: {
        async fetchAccountInfo() {
            let data = {id: localStorage.getItem("notedAccountId")};
            let headers = {headers: {
                Authorization: "Bearer " + localStorage.getItem("notedToken")
            }};

            try {
                const response = await axios.post("http://192.168.5.7:8080/account/info", data, headers);
                this.accountInfo = response.data;
            } catch(e) {
                console.error("Failed fetching account/info. ", e);
            }
        }
    },
    async created() {
        await this.fetchAccountInfo();
    },
}
</script>