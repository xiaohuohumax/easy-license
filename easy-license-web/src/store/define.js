import { defineStore } from 'pinia'
import { darkTheme } from 'naive-ui'

export const useDefineStore = defineStore("define", {
    state: () => ({
        theme: darkTheme
    }),
    getters: {

    },
    actions: {
        setTheme(theme) {
            this.theme = theme
        }
    }
})