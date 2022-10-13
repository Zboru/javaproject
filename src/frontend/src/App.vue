<template>
  <DefaultLayout>

  </DefaultLayout>
</template>

<script>
import { ref } from 'vue'
import DefaultLayout from "@/layout/DefaultLayout";

export default {
  name: 'LayoutDefault',

  components: {
    DefaultLayout,
  },

  setup () {
    return {
      leftDrawerOpen: ref(false)
    }
  }
}
</script>
