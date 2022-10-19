<template>
  <q-dialog v-model="open">
    <q-card style="min-width: 350px">
      <q-card-section>
        <div class="text-h6">Usuń więźnia</div>
      </q-card-section>

      <q-card-section class="q-py-none">
        <p>Czy na pewno chcesz usunąć więźnia? Ta operacja jest nieodwracalna i dane zostaną unicestwione</p>
      </q-card-section>

      <q-card-actions align="right" class="text-primary">
        <q-btn @click="deleteInmate" color="red-7" label="Usuń" v-close-popup/>
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
  inmate: Object
})
const emit = defineEmits(['update:modelValue', 'deleted'])
const open = computed({
  get: () => props.modelValue,
  set: (value) => emit('update:modelValue', value)
})

async function deleteInmate() {
  await axios.delete('/api/inmates/' + props.inmate.id);
  $q.notify({
    color: 'positive',
    icon: 'checkmark',
    message: "Pomyślnie usunięto więźnia",
    position: 'bottom-right'
  });
  emit('deleted');
}
</script>
