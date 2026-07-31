package com.travelassistant.backend.controller;



import com.travelassistant.backend.vo.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/travel")
public class TravelController {
    @GetMapping("/hello")
    public Result<Object> hello(){

        /*Result<String> result = new Result<>();
        result.setSuccess(true);
        System.out.println(result.getSuccess());*/

        return Result.ok("hello world");

    }
}
