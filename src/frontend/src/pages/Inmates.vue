<template>
  <div class="q-pa-md">
    <div class="row justify-center">
      <div class="col-6">
        <div class="row q-mb-lg">
          <div class="col">
            <h5 class="q-ma-none">Lista więźniów</h5>
          </div>
          <div class="col flex">
            <q-space/>
            <q-btn color="primary" @click="openModal = true" label="Dodaj więźnia"/>
          </div>
        </div>
        <q-table
            title="Więźniowie"
            :rows="inmates"
            :columns="columns"
            row-key="name"
        >
          <template v-slot:body-cell-actions="props">
            <q-td :props="props">
              <q-btn flat round size="sm" icon="zoom_in" color="black"/>
              <q-btn flat round size="sm" icon="edit" color="primary"/>
              <q-btn flat round size="sm" icon="delete" color="red"/>
            </q-td>
          </template>
        </q-table>
      </div>
    </div>
    <AddInmateModal v-model="openModal"/>
  </div>
</template>

<script setup>
import axios from 'axios'
import AddInmateModal from "@/components/Inmates/AddInmateModal";
import {ref} from "vue";

const {data: inmates} = await axios.get('/api/inmates');

const openModal = ref(false);

const columns = [
  {
    name: 'id',
    label: 'ID',
    align: 'left',
    field: row => row.id,
    sortable: true
  },
  {
    name: 'firstname',
    label: 'Imię',
    align: 'left',
    field: row => row.firstname,
    sortable: true
  },
  {
    name: 'lastname',
    label: 'Nazwisko',
    align: 'left',
    field: row => row.lastname,
    sortable: true
  },
  {
    name: 'prisonCell',
    label: 'Przypisana cela',
    align: 'left',
    field: row => row.prisonCell.cellName,
    sortable: true
  },
  {
    name: 'actions',
    label: 'Akcje',
    align: 'left',
    sortable: false
  }
]

</script>
