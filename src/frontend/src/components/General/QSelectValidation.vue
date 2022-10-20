<template>
  <q-select
      :options="options"
      :label="label"
      v-model="value"
      :error-message="errorMessage"
      :error="!!errorMessage"
      :option-label="optionLabel"
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
  label: {
    type: String,
    required: true,
  },
  options: {
    type: Array,
    required: true,
  },
  optionLabel: {
    type: String,
    required: false,
  }
});

const { errorMessage, value } = useField(toRef(props, 'name'));
</script>
