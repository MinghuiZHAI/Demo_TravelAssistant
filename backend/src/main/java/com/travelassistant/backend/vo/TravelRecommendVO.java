package com.travelassistant.backend.vo;

import lombok.Data;

import java.util.List;

//旅游推荐实体对象
@Data
public class TravelRecommendVO {
    private Boolean success;
    private String city;
    private Integer days;
    private Double totalBudget;
    private List<DailyItinerary> dailyItinerary;
    private List<BudgetBreakdown>  budgetBreakdown;
    private List<String> tips;
    private List<String> warnings;
    private String error;
    private String rawResponse;

    @Data
    public static class DailyItinerary {
        private Integer day;
        private String date;
        private Timeslot morning;
        private Timeslot afternoon;
        private Timeslot evening;

    }

    @Data
    public static class Timeslot {
        private String spot;
        private String duration;
        private String transportation;
        private String description;
        private String ticket;
    }

    @Data
    public static class BudgetBreakdown {
        private Double accommodation;
        private Double food;
        private Integer transportation;
        private Integer ticket;
        private Integer other;
    }

}
