package uk.gov.justice.laa.crime.equinity.historicaldata.controller;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("equinity/historical-data")
public class WelcomeController {

    @ApiResponse(responseCode="200")
    @GetMapping(value="/welcome")
    public String welcome() {
        return "Hello World";
    }
}
