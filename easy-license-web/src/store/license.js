import { defineStore } from 'pinia'

export const useLicenseStore = defineStore("license", {
    state: () => ({
        license: {
            // 秘钥
            keys: {
                privateKey: "",
                publicKey: ""
            },
            licenseData: {
                // 可见参数
                licenseVisible: {
                    describe: "",
                    version: "",
                    name: ""
                },
                // 自定义参数
                attrMap: {},
                // 注册码
                authRegistrationCodeList: [],
                // 过期时间
                expiredTime: null
            }
        }
    }),
    actions: {
    }
})