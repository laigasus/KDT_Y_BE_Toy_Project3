package com.example.kdt_y_be_toy_project2.global.util.api.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.assertNotNull;
class GoogleMapUtilsTest {

    private static final Logger logger = LoggerFactory.getLogger(GoogleMapUtilsTest.class);

    @DisplayName("Google API 테스트")
    @Test
    public void testSearchPlaces() {
        String keyword = "역곡 장군집";

        String place = GoogleMapUtils.getAddress(keyword);
        logger.info(place);
        assertNotNull(place);
    }
}