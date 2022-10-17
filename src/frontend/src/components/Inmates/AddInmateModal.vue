<template>
  <q-dialog v-model="open">
    <q-card style="min-width: 350px">
      <q-card-section>
        <div class="text-h6">Dane więźnia</div>
      </q-card-section>

      <q-card-section class="q-pt-none">
        <q-input dense v-model="firstName" outlined label="Imię" autofocus class="q-mb-md"/>
        <q-input dense v-model="lastName" outlined label="Nazwisko" class="q-mb-md"/>
        <q-select dense v-model="prisonCell" option-label="cellName" outlined :options="prisonCells" label="Cela" />
      </q-card-section>

      <q-card-actions align="right" class="text-primary">
        <q-btn @click="addInmate" color="primary" label="Dodaj" v-close-popup/>
        <q-btn flat label="Anuluj" v-close-popup/>
      </q-card-actions>
    </q-card>
  </q-dialog>
</template>

<script setup>
import axios from 'axios'
import {ref, defineProps, computed, defineEmits} from 'vue';
import {useQuasar} from "quasar";
const props = defineProps({
  modelValue: Boolean,
  prisonCells: Array
})
const $q = useQuasar()
const emit = defineEmits(['update:modelValue', 'created'])
const open = computed({
  get: () => props.modelValue,
  set: (value) => emit('update:modelValue', value)
})

const firstName = ref("");
const lastName = ref("");
const prisonCell = ref("");

async function addInmate() {
  const payload = {
    firstname: firstName.value,
    lastname: lastName.value,
    prisonCell: prisonCell.value
  }
  await axios.post('/api/inmates', payload);
  $q.notify({
    color: 'positive',
    icon: 'checkmark',
    message: "Pomyślnie dodano więźnia",
    position: 'bottom-right'
  });
  emit('created');
}

</script>
