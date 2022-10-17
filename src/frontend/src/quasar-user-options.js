import 'quasar/dist/quasar.css'
import lang from 'quasar/lang/pl.js'
import '@quasar/extras/roboto-font/roboto-font.css'
import '@quasar/extras/material-icons/material-icons.css'
import '@quasar/extras/roboto-font-latin-ext/roboto-font-latin-ext.css'
import {Notify} from "quasar";

// To be used on app.use(Quasar, { ... })
export default {
    config: {notify: {}},
    plugins: {
        Notify
    },
    lang: lang
}