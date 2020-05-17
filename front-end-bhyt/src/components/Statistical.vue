<template>
    <div>
        {{bill}}
        <input v-model="bill.name"></input>
        <p @click="$emit('saveBill', {value: 'Test'} )">A- Statistical</p>
    </div>
</template>

<script>
  export default {
    name: "Statistical",
    props: {
      bill: {
        type: Object,
        default: {}
      }
    }
  }
</script>

<style scoped>

</style>
