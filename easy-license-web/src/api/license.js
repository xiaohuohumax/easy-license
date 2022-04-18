import requests from "@/sctipt/request"

const baseUrl = "/license"

export default {
    createKeys: () => requests.post(`${baseUrl}/create/key`),
}