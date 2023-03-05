<template>
    <v-container class="bg-surface-variant">
      <v-row no-gutters>
        <v-col
          v-for="n in 3"
          :key="n"
          cols="12"
          sm="4"
        >
          <v-sheet class="ma-2 pa-2">
            One of three columns
          </v-sheet>
        </v-col>
      </v-row>
    </v-container>
  </template>

<script>

export default {
  mounted() {
    const thiz=this
    if(window.$$registerEventListener){
      window.$$registerEventListener("back","id",function(eventName,param){
        console.log(`back`)
        thiz.$router.back()
      })
    }
    console.log(`the component is now mounted.`)
  }
}

</script>
