<template>
  <q-dialog v-model="open">
    <q-card style="min-width: 350px">
      <Form
          :initial-values="initialValues"
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
import * as yup from "yup";

const props = defineProps({
  modelValue: Boolean,
  prisonCell: Object
})

const $q = useQuasar()

const emit = defineEmits(['update:modelValue', 'updated'])
const open = computed({
  get: () => props.modelValue,
  set: (value) => emit('update:modelValue', value)
})

const schema = yup.object({
  cellName: yup.string().required("Nazwa jest wymagana"),
  maxCapacity: yup.number().typeError("Pole musi być liczbą").required("Pojemność jest wymagana"),
});

const initialValues = {}
watch(open, (v) => {
  if (v) {
    initialValues.cellName = props.prisonCell.cellName;
    initialValues.maxCapacity = props.prisonCell.maxCapacity;
  }
})

function onSubmit(values, actions) {
  actions.resetForm();
  updatePrisonCell(values)
}

async function updatePrisonCell(payload) {
  await axios.patch('/api/prisonCells/' + props.prisonCell.id, payload);
  $q.notify({
    color: 'positive',
    icon: 'checkmark',
    message: "Pomyślnie edytowano celę",
    position: 'bottom-right'
  });
  open.value = false;
  emit('updated');
}
</script>
