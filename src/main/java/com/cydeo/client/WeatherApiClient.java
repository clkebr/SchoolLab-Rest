package com.cydeo.client;

import com.cydeo.dto.weather.WeatherDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(url = "http://api.weatherstack.com", name = "weather-client")
public interface WeatherApiClient {
//http://api.weatherstack.com/current?access_key=98ab4af7996448a0d8403a657ef3bb32&query=New York

    @GetMapping("/current")
    WeatherDTO getCurrentStatus(@RequestParam(value = "access_key") String key,
                                @RequestParam (value = "query") String city );
}
