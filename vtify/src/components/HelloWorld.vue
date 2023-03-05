<template>
  <v-app>


    <v-main>
      <v-container v-if="value == 'books'">Lorem ipsum dolor sit amet consectetur adipisicing
        elit. Quis blanditiis beatae praesentium repudiandae
        sunt officiis, vero maxime officia obcaecati nisi
        excepturi. Sit laboriosam provident laborum? Rem
        recusandae ea possimus illo.
      </v-container>

      <v-container v-if="value == 'photos'" v-on:click="gotoDetail">photos
      </v-container>

      <Tools v-if="value == 'music'">
      </Tools>



    </v-main>

    <v-bottom-navigation v-model="value" app color="primary" grow>


      <v-btn stacked prepend-icon="mdi-music" variant="text" value="music" height="100%" v-on:click="onTabClick('music')">
        工具
      </v-btn>

      <v-btn stacked prepend-icon="mdi-book" variant="text" value="books" height="100%" v-on:click="onTabClick('books')">
        资讯

      </v-btn>

      <v-btn stacked prepend-icon="mdi-image" variant="text" value="photos" height="100%"
        v-on:click="onTabClick('photos')">
        设置

      </v-btn>
    </v-bottom-navigation>
  </v-app>
</template>

<script>
export default {
  name: 'App',
  data: () => ({ value: 'music' }),
  methods: {
    gotoDetail() {
      this.$router.push('/login')
    },
    onTabClick(tab) {
      if (this.value != tab) {
        this.value = tab
      }

    }
  },
  mounted() {
    console.log(`home the component is now mounted.`)

  },
  unmounted() {
    console.log(`unmounted home the component is now mounted.`)

  },

};
</script>

<script setup>
import Tools from '@/views/Tools.vue'
</script>
