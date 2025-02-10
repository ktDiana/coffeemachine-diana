package com.practice.coffeemachinediana.service;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TimeService {

    private final RestTemplate restTemplate = new RestTemplate();

    public boolean isMachineAvailable() {
        LocalTime now = LocalTime.now();
        LocalDate today = LocalDate.now();

        // Проверяем рабочие часы (8:00 - 17:00)
        if (now.isBefore(LocalTime.of(8, 0)) || now.isAfter(LocalTime.of(17, 0))) {
            return false;
        }

        // Проверяем выходные
        if (today.getDayOfWeek() == DayOfWeek.SATURDAY || today.getDayOfWeek() == DayOfWeek.SUNDAY) {
            return false;
        }

        // Проверяем праздники через API
        String url = "https://date.nager.at/Api/v2/PublicHolidays/" + today.getYear() + "/RU";
        List<?> holidays = restTemplate.getForObject(url, List.class);
        if (holidays != null && holidays.stream().anyMatch(h -> h.toString().contains(today.toString()))) {
            return false;
        }

        return true;
    }
}