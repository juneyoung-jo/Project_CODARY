import Vue from 'vue';
import Vuetify from 'vuetify/lib';
import { library } from '@fortawesome/fontawesome-svg-core'
import { FontAwesomeIcon } from '@fortawesome/vue-fontawesome'
import { fas } from '@fortawesome/free-solid-svg-icons'
import colors from 'vuetify/lib/util/colors'


Vue.component('font-awesome-icon', FontAwesomeIcon) // Register component globally
library.add(fas);


Vue.use(Vuetify);

export default new Vuetify({
    icons: {
        iconfont:  'faSvg',
      },
      theme: {
        options: {
          customProperties: true,
        },
        themes: {
          light: {
            background: colors.grey.lighten5,
            primary: colors.indigo.darken3,
            secondary: colors.indigo.lighten3,
            accent:colors.grey.darken4,
            error: colors.red.accent3,
          },
          dark: {
            background: colors.grey.darken4,
            primary: colors.blue.lighten3,
            accent: colors.grey.lighten5,
          },
        },
      },
});
