package com.travelassistant.backend.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;


import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class LLMutils {
    private String apiKey;
    private String baseUrl;
    private String model;
    private OkHttpClient client;
    private ObjectMapper objectMapper = new ObjectMapper();

//    创建网络连接
    public LLMutils(String apiKey, String baseUrl, String model) {
        this.apiKey = apiKey;
        this.baseUrl = baseUrl;
        this.model = model;
        this.client = new OkHttpClient.Builder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(120, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .build();
    }

//    旅游推荐接口
    public String chat(String systemPrompt, String userPrompt) {

        String requestBody = buildRequestBody(systemPrompt, userPrompt, false);
//        创建一个post请求
        Request request =  new Request.Builder()
                .url(baseUrl + "/chat/completions")
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", "Bearer " + apiKey)
//                固定写法
                .post(RequestBody.create(requestBody, MediaType.parse("application/json;  charset=utf-8")))
                .build();
        try(Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("LLM调用异常 " + response.code());
            }

//            返回一个JSON字符串
            String responseBody =  response.body().string();
            return extractContent(responseBody);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

//    第三个参数决定是否为流式接口响应
public String buildRequestBody(String systemPrompt, String userPrompt, Boolean stream) {
    StringBuilder str = new StringBuilder();
//    拼接参数，注意不同AI平台调用对话API格式不一样！！！
    str.append("{");
    // model
    str.append("\"model\":\"").append(model).append("\",");
    // stream
    str.append("\"stream\":").append(stream).append(",");
    // messages
    str.append("\"messages\":[");
    boolean hasMessage = false;
    if (systemPrompt != null && !systemPrompt.isEmpty()) {
        str.append("{\"role\":\"system\",\"content\":\"").append(escapeJson(systemPrompt)).append("\"}");
        hasMessage = true;
    }
    if (userPrompt != null && !userPrompt.isEmpty()) {
        if (hasMessage) {
            str.append(",");
        }
        str.append("{\"role\":\"user\",\"content\":\"").append(escapeJson(userPrompt)).append("\"}");
        hasMessage = true;
    }
    str.append("],");
    // thinking 和 reasoning_effort（按文档示例固定值）
    str.append("\"thinking\":{\"type\":\"enabled\"},");
    str.append("\"reasoning_effort\":\"high\"");
    // 可选的 temperature（按需要取消注释）
    // str.append(",\"temperature\":0.7");
    str.append("}");
    return str.toString();
}

    // 模型返回数据处理
    private String extractContent(String responseBody) throws IOException {
        JsonNode root = objectMapper.readTree(responseBody);
        JsonNode choices = root.path("choices");
        if (choices.isArray() && choices.size() > 0) {
            return choices.get(0).path("message").path("content").asText();
        }
        return "";
    }

    // JSON字符串转义方法
    private String escapeJson(String text) {
        if (text == null) {
            return "";
        }
        return text.replace("\\", "\\\\")      // 转义反斜杠
                .replace("\"", "\\\"")      // 转义双引号
                .replace("\n", "\\n")       // 转义换行符
                .replace("\r", "\\r")       // 转义回车符
                .replace("\t", "\\t");      // 转义制表符
    }
}
