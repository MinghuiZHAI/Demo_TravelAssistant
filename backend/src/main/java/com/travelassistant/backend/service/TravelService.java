package com.travelassistant.backend.service;


//service层 用来处理业务 以及 和model层与数据库的交互

import com.travelassistant.backend.vo.TravelRecommendVO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class TravelService {
    @Value("${llm.api-key}")
    private String apiKey;
    @Value("${llm.base-url}")
    private String baseUrl;
    @Value("${llm.model}")
    private String model;

    public TravelRecommendVO recommend (String city, Integer days, Double budget) {

    }
}
