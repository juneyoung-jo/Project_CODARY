<template>
  <section id="blog">
    <v-container class='ma-10 pa-16'>
      <v-row>
        <v-col
          v-for="({ src }, i) in articles"
          :key="i"
          cols="12"
          md="4"
        >
        <v-avatar
          size="300"
        >
          <v-img
            :src="src"
            class="mb-4"
          ></v-img>
        </v-avatar>
          
          <v-btn
            class="font-weight-black"
            text
          >
            blog >
          </v-btn>
        </v-col>
      </v-row>
    </v-container>
    <div class="py-12"></div>
  </section>
</template>

<script>
export default {
    name:"PopularTag",
    data () {
      return {
        articles: [
          {
            src: 'https://lh3.googleusercontent.com/proxy/_zhfHEleF-X_wu-34WVkYZEoUG9E588J1iNDfEZ2m6TVttu7BbANYbZdnD6woI5qtv5fMwl4AeIQ3QPLngdW-G1YunrEYiHoEulpJo9yUBIahQfRR_FdH1OsLboC27-wmTjpAw1IBjv4YmAjsRJmKYgg-IYNk6Bo5SjkTrz8I7sY97Dv-zzce-I9gotYBN1D8g-WQkoVTgtT5vOAlmWNKpSbMkPhoZVaXt58RxXA5ZbNENwjpdGOW1kx-qgglEnjdZmcomz22GrTwssuykZ_xqPSZZaMcYYteUi8y3JUvEEH1TTkQDzqYdKhhjRIKxg',
            title: 'Mobile first & Responsive',
            text: 'Phasellus lorem enim, luctus ut velit eget, convallis egestas eros. Sed ornare ligula eget tortor tempor, quis porta tellus dictum.',
          },
          {
            src: 'https://images.unsplash.com/photo-1475938476802-32a7e851dad1?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1950&q=80',
            title: 'Think outside the box',
            text: 'Nam ut leo ipsum. Maecenas pretium aliquam feugiat. Aenean vel tempor est, vitae tincidunt risus. Sed sodales vestibulum nibh.',
          },
          {
            src: 'https://lh3.googleusercontent.com/proxy/EyF8AqIXDUPDQSzH_jlWXCfwZDsK6u6Iyq1Bd2GkRBg96O0mMsPYQE9BB2hoDKl0IgJz2X1juPh3MiaBdJCkdUD1--gLYXabLrfmQuSi1y40HFA9u5tjUa10bnxMacPkAqHItVEP6BVZclMwBQ',
            title: 'Small changes, big difference',
            text: 'Vestibulum in dictum velit, in rhoncus nibh. Maecenas neque libero, interdum a dignissim in, aliquet vitae lectus. Phasellus lorem enim, luctus ut velit eget.',
          },
        ],
      }
    }
}
</script>

<style>

</style>