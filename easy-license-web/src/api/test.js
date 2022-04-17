import requests from "@/sctipt/request"

const baseUrl = "/test"

export default {
    testHttp: () => requests.post(`${baseUrl}/test`),
}