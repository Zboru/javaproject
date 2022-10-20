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
  prisonCell: yup.object().required("Cela jest wymagana")
});

const initialValues = {}
watch(open, (v) => {
  if (v) {
    initialValues.firstname = props.inmate.firstname;
    initialValues.lastname = props.inmate.lastname;
    initialValues.prisonCell = props.inmate.prisonCell;
  }
})

function onSubmit(values, actions) {
  actions.resetForm();
  updateInmate(values)
}

async function updateInmate(payload) {
  await axios.patch('/api/inmates/' + props.inmate.id, payload);
  $q.notify({
    color: 'positive',
    icon: 'checkmark',
    message: "Pomyślnie edytowano więźnia",
    position: 'bottom-right'
  });
  open.value = false;
  emit('updated');
}
</script>
