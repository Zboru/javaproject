<template>
  <q-dialog v-model="open">
    <q-card style="min-width: 350px">
      <q-card-section>
        <div class="text-h6">Your address</div>
      </q-card-section>

      <q-card-section class="q-pt-none">
        <q-input dense v-model="firstName" autofocus @keyup.enter="prompt = false"/>
      </q-card-section>

      <q-card-actions align="right" class="text-primary">
        <q-btn flat label="Cancel" v-close-popup/>
        <q-btn flat label="Add address" v-close-popup/>
      </q-card-actions>
    </q-card>
  </q-dialog>
</template>

<script setup>
import {ref, defineProps, computed, defineEmits} from 'vue';
const props = defineProps({
  modelValue: Boolean,
})
const emit = defineEmits(['update:modelValue'])
const open = computed({
  get: () => props.modelValue,
  set: (value) => emit('update:modelValue', value)
})

const firstName = ref("");
</script>
