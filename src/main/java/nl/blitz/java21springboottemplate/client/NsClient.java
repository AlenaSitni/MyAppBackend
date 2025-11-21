package nl.blitz.java21springboottemplate.client;

import nl.blitz.java21springboottemplate.dto.ArrivalDto;
import nl.blitz.java21springboottemplate.dto.ArrivalsResponseDto;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class NsClient {

    private final RestTemplate restTemplate = new RestTemplate();

    private static final String API_KEY = "acc7fb0e0e354010b5e607cdb3decb07";

    private static final String URL =
            "https://gateway.apiportal.ns.nl/reisinformatie-api/api/v2/arrivals?station=UT&lang=nl&maxJourneys=20";

    public List<ArrivalDto> fetchArrivals() {

        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", "application/json");
        headers.set("Ocp-Apim-Subscription-Key", API_KEY);

        HttpEntity<Void> request = new HttpEntity<>(headers);

        ResponseEntity<ArrivalsResponseDto> response = restTemplate.exchange(
                URL,
                HttpMethod.GET,
                request,
                ArrivalsResponseDto.class
        );

        // Now DTOs exist → getPayload() is VALID
        if (response.getBody() == null ||
                response.getBody().getPayload() == null ||
                response.getBody().getPayload().getArrivals() == null) {
            return List.of();
        }

        return response.getBody().getPayload().getArrivals();
    }
}
