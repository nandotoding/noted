<template>
    <div class="container mt-5">
        <h2>{{ title }}</h2>
        <div class="note-content">
            <p>{{ note }}</p>
        </div>
        <div class="mt-3">
            <button class="btn btn-secondary" @click="goBack">back</button>
        </div>
    </div>
</template>

<script>
export default {
    props: {
        title: {
            type: String,
            required: true,
        },
        note: {
            type: String,
            required: true,
        },
    },
    methods: {
        goBack() {
            this.$emit('back'); // Emit an event to go back to the previous view
        },
    },
};
</script>