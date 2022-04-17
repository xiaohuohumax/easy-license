import { defineStore } from 'pinia'

export const useDefineStore = defineStore("define", {
    state: () => ({
        theme: null
    }),
    getters: {

    },
    actions: {
        setTheme(theme) {
            this.theme = theme
        }
    }
})