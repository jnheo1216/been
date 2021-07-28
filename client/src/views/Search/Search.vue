<template>
  <div class="Search">
    <button @keypress="toTypeSearch">
    <TypeSearch />
  </div>
</template>

<script>
import TypeSearch from '@/components/Search/TypeSearch.vue'

export default {
  name: 'Search',
  components: {
    TypeSearch,
  },
  methods: {
    toTypeSearch: function () {
      
    }
  }
}
</script>

<style>

</style>