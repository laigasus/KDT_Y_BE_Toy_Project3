//package com.example.kdt_y_be_toy_project2.global.util.api.controller;
//
//import com.example.kdt_y_be_toy_project2.global.util.api.dto.Place;
//import com.example.kdt_y_be_toy_project2.global.util.api.service.GoogleMapUtils;
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import lombok.AllArgsConstructor;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@AllArgsConstructor
//public class GoogleController {
//
//    @GetMapping("/places/search/{keyword}")
//    public ResponseEntity<String> searchPlaces(@PathVariable String keyword) {
//        Place place = GoogleMapUtils.searchPlaces(keyword);
//
//        ObjectMapper objectMapper = new ObjectMapper();
//        String jsonString;
//        try {
//            jsonString = objectMapper.writeValueAsString(place);
//        } catch (JsonProcessingException e) {
//            throw new IllegalStateException("JSON 파싱 실패", e);
//        }
//        return ResponseEntity.ok(jsonString);
//    }
//}
