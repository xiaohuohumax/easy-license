import { defineStore } from 'pinia'
import localstorage from '@/sctipt/localstorage'

export const useKeysStore = defineStore("keys", {
    state: () => ({
        keys: [],
        _localKeysName: 'keys',
        _keyFormat: {
            privateKey: "",
            publicKey: "",
            describe: "",
            id: 0,
            time: ""
        }
    }),
    actions: {
        loadKeys() {
            const keys = localstorage.get(this._localKeysName);
            this.keys = !!keys ? keys : [];
        },
        saveKeys() {
            localstorage.set(this._localKeysName, this.keys);
        },
        addKeys(keys) {
            this.keys.push({
                ...this._keyFormat,
                ...keys
            });
            this.saveKeys();
        },
        removeKeyById(id) {
            this.keys.splice(this.keys.findIndex(item => item.id === id), 1);
            this.saveKeys();
        }
    }
})