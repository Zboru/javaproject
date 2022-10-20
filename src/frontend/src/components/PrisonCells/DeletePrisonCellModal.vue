<template>
  <q-dialog v-model="open">
    <q-card style="min-width: 350px">
      <q-card-section>
        <div class="text-h6">Usuń celę</div>
      </q-card-section>

      <q-card-section class="q-py-none">
        <p>Czy na pewno chcesz usunąć celę? Ta operacja jest nieodwracalna i dane zostaną unicestwione</p>
      </q-card-section>

      <q-card-actions align="right" class="text-primary">
        <q-btn @click="deletePrisonCell" color="red-7" label="Usuń" v-close-popup/>
        <q-btn flat label="Anuluj" v-close-popup/>
      </q-card-actions>
    </q-card>
  </q-dialog>
</template>

<script setup>
import {defineProps, computed, defineEmits} from 'vue';
import axios from "axios";
import {useQuasar} from "quasar";
const $q = useQuasar()
const props = defineProps({
  modelValue: Boolean,
  prisonCell: Object
})
const emit = defineEmits(['update:modelValue', 'deleted'])
const open = computed({
  get: () => props.modelValue,
  set: (value) => emit('update:modelValue', value)
})

async function deletePrisonCell() {
  await axios.delete('/api/prisonCells/' + props.prisonCell.id);
  $q.notify({
    color: 'positive',
    icon: 'checkmark',
    message: "Pomyślnie usunięto celę",
    position: 'bottom-right'
  });
  emit('deleted');
}
</script>
