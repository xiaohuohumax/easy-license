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
        },
        attrComponentMap: [
            {
                value: "STRING",
                componentName: "LicenseAttrString",
                label: "字符串"
            },
            {
                value: "DATE",
                label: "日期",
                componentName: "LicenseAttrDate"
            },
            {
                value: "DOUBLE",
                label: "浮点数",
                componentName: "LicenseAttrDouble"
            },
            {
                value: "RANGE_DATE",
                componentName: "LicenseAttrRangeDate",
                label: "范围日期"
            },
            {
                value: "RANGE_DOUBLE",
                componentName: "LicenseAttrRangeDouble",
                label: "范围浮点数"
            },
            {
                value: "STRING_LIST",
                componentName: "LicenseAttrStringList",
                label: "字符串列表"
            }
        ],
        attrModel: {
            key: "",
            describe: "",
            attrStr: "",
            attrType: ""
        }
    }),
    actions: {
        getcomponentNameByValue(value) {
            const item = this.attrComponentMap.find((item) => item.value === value)
            return item;
        }
    }
})