<template>
  <q-layout view="hHh lpR fFf">
    <q-header reveal elevated class="bg-primary text-white">
      <q-toolbar>
        <q-btn dense flat round icon="menu" @click="toggleLeftDrawer"/>
        <q-toolbar-title>
          PrisonManager
        </q-toolbar-title>
      </q-toolbar>
    </q-header>

    <q-drawer v-model="leftDrawerOpen" side="left" overlay bordered>
      <DrawerItem @click="toggleLeftDrawer" label="Lista więżniów" icon="list_alt" link="/inmates"/>
      <DrawerItem @click="toggleLeftDrawer" label="Lista cel" icon="domain" link="/prisonCells"/>
    </q-drawer>

    <q-page-container>
      <Suspense>
        <router-view/>
      </Suspense>
    </q-page-container>

  </q-layout>
</template>

<script setup>
import {ref} from "vue";
import DrawerItem from "@/components/Layout/DrawerItem";

const leftDrawerOpen = ref(false)

function toggleLeftDrawer() {
  leftDrawerOpen.value = !leftDrawerOpen.value
}
</script>
