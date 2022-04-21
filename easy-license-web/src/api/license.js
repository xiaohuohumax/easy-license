import requests from "@/sctipt/request"

const baseUrl = "/license"

export default {
    // 创建密钥
    createKeys: () => requests.post(`${baseUrl}/create/key`),
    // 创建许可证
    createLicense: (data) => requests.post(`${baseUrl}/encrypt/license/by/key`, data),
}