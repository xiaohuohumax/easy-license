import { defineStore } from 'pinia'

export const useHistoryStore = defineStore("history", {
    state: () => ({
        history: [],
        historyModel: {
            id: 0,
            path: '',
            name: ''
        }
    }),
    getters: {

    },
    actions: {
        addHistory(history) {
            this.history.push({
                ...this.historyModel,
                id: new Date().getTime(),
                ...history
            })
        },
        closeHistory(id) {
            for (let index = this.history.length - 1; index >= 0; index--) {
                if (this.history[index].id == id) {
                    this.history.splice(index, 1)
                }
            }
        }
    }
})