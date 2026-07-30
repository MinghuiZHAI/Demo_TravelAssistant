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


export const post = (url, data = {}) => {
    return instance.post(url, data);
}


export const get = (url, params = {}) => {
    return instance.get(url, {params});
}

//处理流式接口，流式对话的参数就是用户输入的内容
//添加 async 与 await 改成同步的写法
export async function fetchStream(url, data, onChunk, onComplete, onError) {
    // 终止的请求控制器
    const controller = new AbortController();
    //通过await就能直接获得接口返回的数据
    const response = await fetch(`http://159.75.169.224:1236/api/travel/${url}`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        //将传入的JSON对象转化为字符串，以字符串的格式传给后端
        body: JSON.stringify(data),
        signal: controller.signal
    })
    /*如果用.then，相当于把逻辑写到了回调函数里，通过添加async改成同步的写法*/

    //创建响应体的可读流的读取器
    const reader = response.body.getReader()
    // 将二进制数据解码为字符串
    const decoder = new TextDecoder()

    // 流式接口的处理
    while (true) {
        const { done, value } = await reader.read();
        if (done) break;
        // 返回二进制流数据的真实结果，即字符串结果
        const chunk = decoder.decode(value, { stream: true })
        console.log(chunk)
        //根据空行对字符串进行切割
        const lines = chunk.split('\n').filter(line => line.trim())

        for (const line of lines) {
            if( line.startsWith('data:') ) {
                //截掉前面5个字符
                const jsonStr = line.substring(5)
                if (jsonStr) {
                    //通过JSON.parse()方法转为JSON对象
                    const  jsonData = JSON.parse(jsonStr)

                    if (jsonData.type === 'chunk') {
                        //分片的数据
                        onChunk(jsonData.content)
                    } else if (jsonData.done){
                        //完成的数据
                        onComplete()
                    } else if (jsonData.error){
                        //错误的数据
                        onError(jsonData.error)
                    }
                }

            }

        }
    } // 流式接口处理完成

    // 读取完成后，终止
    return controller.abort();

}

export default instance;