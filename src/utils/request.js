import axios from "axios";

//二次封装，基于axios创建了一个axios instance
const instance = axios.create({
    baseURL: "http://159.75.169.224:1236",
    timeout: 60000,
    headers: {
        "Content-Type": "application/json",
    }
})

//请求拦截器，如携带token，可放在请求拦截器中
instance.interceptors.request.use(
    config => {
        return config;
    },
    error => {
        return Promise.reject(error);
    }
)

//响应拦截器，可处理后端返回的异常
instance.interceptors.response.use(
    response => {
        return response.data;
    },
    error => {
        console.log('请求错误',error);
        return Promise.reject(error);
    }
)

//??
export const post = (url, data = {}) => {
    return instance.post(url, data);
}

//??
export const get = (url, params = {}) => {
    return instance.get(url, {params});
}

//??
export default instance;