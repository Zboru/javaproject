<template>
  <q-dialog v-model="open">
    <q-card style="min-width: 350px">
      <q-card-section>
        <div class="text-h6">Dane więźnia</div>
      </q-card-section>

      <q-card-section class="q-pt-none">
        <p class="q-mb-xs">Imię: {{props.inmate.firstname}}</p>
        <p class="q-mb-xs">Nazwisko: {{props.inmate.lastname}}</p>
        <span>Dane celi:</span>
        <ul class="q-mt-none">
          <li>ID: {{props.inmate.prisonCell.id}}</li>
          <li>Nazwa: {{props.inmate.prisonCell.cellName}}</li>
          <li>Maksymalna pojemność: {{props.inmate.prisonCell.capacity}}</li>
        </ul>
      </q-card-section>

      <q-card-actions align="right" class="text-primary">
        <q-btn flat label="Zamknij" v-close-popup/>
      </q-card-actions>
    </q-card>
  </q-dialog>
</template>

<script setup>
import {defineProps, computed, defineEmits} from 'vue';
const props = defineProps({
  modelValue: Boolean,
  inmate: Object
})
const emit = defineEmits(['update:modelValue'])
const open = computed({
  get: () => props.modelValue,
  set: (value) => emit('update:modelValue', value)
})

</script>
