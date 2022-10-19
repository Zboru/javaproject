<template>
  <q-dialog v-model="open">
    <q-card style="min-width: 350px">
      <q-card-section>
        <div class="text-h6">Edytuj więźnia</div>
      </q-card-section>

      <q-card-section class="q-pt-none">
        <q-input dense v-model="firstName" outlined label="Imię" autofocus class="q-mb-md"/>
        <q-input dense v-model="lastName" outlined label="Nazwisko" class="q-mb-md"/>
        <q-select dense v-model="prisonCell" option-label="cellName" outlined :options="prisonCells" label="Cela" />
      </q-card-section>

      <q-card-actions align="right" class="text-primary">
        <q-btn @click="updateInmate" color="primary" label="Edytuj" v-close-popup/>
        <q-btn flat label="Anuluj" v-close-popup/>
      </q-card-actions>
    </q-card>
  </q-dialog>
</template>

<script setup>
import {ref, defineProps, computed, defineEmits, watch} from 'vue';
import axios from "axios";
import {useQuasar} from "quasar";
const props = defineProps({
  modelValue: Boolean,
  prisonCells: Array,
  inmate: Object
})
const $q = useQuasar()
const emit = defineEmits(['update:modelValue', 'updated'])
const open = computed({
  get: () => props.modelValue,
  set: (value) => emit('update:modelValue', value)
})

const firstName = ref("");
const lastName = ref("");
const prisonCell = ref("");

watch(open, (value) => {
  if (value) {
    firstName.value = props.inmate.firstname;
    lastName.value = props.inmate.lastname;
    prisonCell.value = props.inmate.prisonCell;
  }
})

async function updateInmate() {
  const payload = {
    firstname: firstName.value,
    lastname: lastName.value,
    prisonCell: prisonCell.value
  }
  await axios.patch('/api/inmates/' + props.inmate.id, payload);
  $q.notify({
    color: 'positive',
    icon: 'checkmark',
    message: "Pomyślnie edytowano więźnia",
    position: 'bottom-right'
  });
  emit('updated');
}
</script>
