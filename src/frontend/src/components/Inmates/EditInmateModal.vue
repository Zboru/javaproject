<template>
  <q-dialog v-model="open">
    <q-card style="min-width: 350px">
      <Form
          :initial-values="initialValues"
          :validation-schema="schema"
          @submit="onSubmit"
      >
        <q-card-section>
          <div class="text-h6">Dane więźnia</div>
        </q-card-section>

        <q-card-section class="q-pt-none">
          <QInputValidation dense name="firstname" outlined label="Imię" autofocus class="q-mb-md"/>
          <QInputValidation dense name="lastname" outlined label="Nazwisko" class="q-mb-md"/>
          <QSelectValidation dense name="dangerState" outlined label="Stan zagrożenia" class="q-mb-md"
                             :options="dangerStates" option-label="name"/>
          <QSelectValidation dense name="prisonCell" outlined label="Cela" option-label="cellName"
                             :options="prisonCells"/>
        </q-card-section>

        <q-card-actions align="right" class="text-primary">
          <q-btn type="submit" color="primary" label="Edytuj"/>
          <q-btn flat label="Anuluj" v-close-popup/>
        </q-card-actions>
      </Form>
    </q-card>
  </q-dialog>
</template>

<script setup>
import {defineProps, computed, defineEmits, watch} from 'vue';
import axios from "axios";
import {Form} from 'vee-validate';
import {useQuasar} from "quasar";
import QInputValidation from "@/components/General/QInputValidation";
import QSelectValidation from "@/components/General/QSelectValidation";
import * as yup from "yup";

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

const schema = yup.object({
  firstname: yup.string().required("Imię jest wymagane"),
  lastname: yup.string().required("Nazwisko jest wymagane"),
  dangerState: yup.object().required("Stan jest wymagany"),
  prisonCell: yup.object().required("Cela jest wymagana")
});

const initialValues = {}
watch(open, (v) => {
  if (v) {
    initialValues.firstname = props.inmate.firstname;
    initialValues.lastname = props.inmate.lastname;
    initialValues.dangerState = dangerStates[props.inmate.dangerState - 1];
    initialValues.prisonCell = props.inmate.prisonCell;
  }
})

async function onSubmit(values, actions) {
  await updateInmate(values)
  actions.resetForm();
}

const dangerStates = [
  {name: 'Niski', value: 1},
  {name: 'Średni', value: 2},
  {name: 'Wysoki', value: 3}
];

async function updateInmate(payload) {
  payload.dangerState = payload.dangerState.value;
  try {
    await axios.patch('/api/inmates/' + props.inmate.id, payload);
    $q.notify({
      color: 'positive',
      icon: 'checkmark',
      message: "Pomyślnie edytowano więźnia",
      position: 'bottom-right'
    });
    open.value = false;
    emit('updated');
  } catch (e) {
    $q.notify({
      color: 'negative',
      icon: 'warning',
      message: e.response.data.message,
      position: 'bottom-right'
    });
    open.value = false;
  }
}
</script>
