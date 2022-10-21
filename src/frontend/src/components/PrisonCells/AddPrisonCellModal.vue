<template>
  <q-dialog v-model="open">
    <q-card style="min-width: 350px">
      <Form
          :validation-schema="schema"
          @submit="onSubmit"
      >
        <q-card-section>
          <div class="text-h6">Dane celi</div>
        </q-card-section>

        <q-card-section class="q-pt-none">
          <QInputValidation dense name="cellName" outlined label="Nazwa" autofocus class="q-mb-md"/>
          <QInputValidation dense name="maxCapacity" outlined label="Pojemność" class="q-mb-md"/>
        </q-card-section>

        <q-card-actions align="right" class="text-primary">
          <q-btn type="submit" color="primary" label="Dodaj"/>
          <q-btn flat label="Anuluj" v-close-popup/>
        </q-card-actions>
      </Form>
    </q-card>
  </q-dialog>
</template>

<script setup>
import axios from 'axios'
import * as yup from 'yup';
import {defineProps, computed, defineEmits} from 'vue';
import {useQuasar} from "quasar";
import {Form} from 'vee-validate';
import QInputValidation from "@/components/General/QInputValidation";

const props = defineProps({
  modelValue: Boolean,
})

const $q = useQuasar()
const emit = defineEmits(['update:modelValue', 'created'])
const open = computed({
  get: () => props.modelValue,
  set: (value) => emit('update:modelValue', value)
})

const schema = yup.object({
  cellName: yup.string().required("Nazwa jest wymagana"),
  maxCapacity: yup.number().typeError("Pole musi być liczbą").max(9, "Pojemność nie może przekraczać 9").required("Pojemność jest wymagana"),
});

function onSubmit(values, actions) {
  actions.resetForm();
  addInmate(values)
}

async function addInmate(payload) {
  await axios.post('/api/prisonCells', payload);
  $q.notify({
    color: 'positive',
    icon: 'checkmark',
    message: "Pomyślnie dodano celę",
    position: 'bottom-right'
  });
  open.value = false;
  emit('created');
}

</script>
