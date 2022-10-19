<template>
  <div>
    <div class="row q-mb-lg">
      <div class="col">
        <h5 class="q-ma-none">Lista cel więziennych</h5>
      </div>
      <div class="col flex">
        <q-space/>
        <q-btn color="primary" label="Dodaj celę"/>
      </div>
    </div>
    <q-table
        title="Cele"
        :rows="prisonCells"
        :columns="columns"
        row-key="name"
    >
      <template v-slot:body-cell-actions="props">
        <q-td :props="props">
          <q-btn flat round size="sm" icon="edit" color="primary"/>
          <q-btn flat round size="sm" icon="delete" color="red"/>
        </q-td>
      </template>
    </q-table>
  </div>
</template>

<script setup>
import axios from 'axios'

const {data: prisonCells} = await axios.get('/api/prisonCells');

const columns = [
  {
    name: 'id',
    label: 'ID',
    align: 'left',
    field: row => row.id,
    sortable: true
  },
  {
    name: 'cellName',
    label: 'Nazwa celi',
    align: 'left',
    field: row => row.cellName,
    sortable: true
  },
  {
    name: 'capacity',
    label: 'Pojemność',
    align: 'left',
    field: row => row.capacity,
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
