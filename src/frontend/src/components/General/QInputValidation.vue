<template>
  <q-input
      v-model="value"
      :error-message="errorMessage"
      :error="!!errorMessage"
  />
</template>

<script setup>
import { toRef, defineProps } from 'vue';
import { useField } from 'vee-validate';

const props = defineProps({
  name: {
    type: String,
    required: true,
  },
});

const { errorMessage, value } = useField(toRef(props, 'name'));
</script>
