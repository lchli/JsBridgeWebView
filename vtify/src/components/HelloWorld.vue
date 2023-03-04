<template>
  <v-app>
  

    <v-main>
     <v-container v-if="value=='books'"
        >Lorem ipsum dolor sit amet consectetur adipisicing
        elit. Quis blanditiis beatae praesentium repudiandae
        sunt officiis, vero maxime officia obcaecati nisi
        excepturi. Sit laboriosam provident laborum? Rem
        recusandae ea possimus illo.
        </v-container>

        <v-container v-if="value=='photos'"
        >photos
        </v-container>

        <v-container v-if="value=='music'"
        >music
        </v-container>


    </v-main>

    <v-bottom-navigation
      v-model="value"
      app
      color="primary"
      grow
    >
      <v-btn
        value="music"
        height="100%"
      >
        <span>Music</span>
        <v-icon>mdi-music</v-icon>
      </v-btn>

      <v-btn
        value="books"
        height="100%"
      >
        <span>Books</span>
        <v-icon>mdi-book</v-icon>
      </v-btn>

      <v-btn
        value="photos"
        height="100%"
      >
        <span>Photos</span>
        <v-icon>mdi-image</v-icon>
      </v-btn>
    </v-bottom-navigation>
  </v-app>
</template>

<script>
export default {
  name: 'App',
  data: () => ({ value: 'music' }),
};
</script>