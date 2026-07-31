package com.travelassistant.backend.controller;



import com.travelassistant.backend.dto.TravelRequestDTO;
import com.travelassistant.backend.vo.Result;
import com.travelassistant.backend.vo.TravelRecommendVO;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

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

//    明确参数名称 && 校验参数
    @PostMapping("/recommend")
    public Result<TravelRecommendVO> recommend(@Valid @RequestBody TravelRequestDTO travelRequestDTO){

        System.out.println(travelRequestDTO.getCity());
        System.out.println(travelRequestDTO.getDays());
        System.out.println(travelRequestDTO.getBudget());

        return Result.ok(null);

    }

}
