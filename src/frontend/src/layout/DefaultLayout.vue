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
      <DrawerItem v-for="(item, index) in navItems" :key="index" @click="toggleLeftDrawer" :label="item.label"
                  :icon="item.icon" :link="item.link"/>
    </q-drawer>

    <q-page-container>
      <Suspense>
        <div class="q-pa-md">
          <div class="row justify-center">
            <div class="col-6">
              <router-view/>
            </div>
          </div>
        </div>
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

const navItems = [
  {label: "Lista więźniów", icon: "list_alt", link: '/inmates'},
  {label: "Lista cel", icon: "domain", link: "/prisonCells"},
  {label: "Przegląd", icon: "domain", link: "/overview"}
];
</script>
