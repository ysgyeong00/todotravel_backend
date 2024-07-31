package org.example.todotravel.domain.plan.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.todotravel.domain.plan.dto.request.LocationRequestDto;
import org.example.todotravel.domain.plan.dto.request.ScheduleRequestDto;
import org.example.todotravel.domain.plan.dto.response.ScheduleResponseDto;
import org.example.todotravel.domain.plan.entity.Location;
import org.example.todotravel.domain.plan.entity.Schedule;
import org.example.todotravel.domain.plan.implement.ScheduleServiceImpl;
import org.example.todotravel.global.controller.ApiResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/plan")
public class ScheduleController {
    private final ScheduleServiceImpl scheduleService;

    @PostMapping("/{plan_id}/course")
    public ApiResponse<ScheduleResponseDto> insertSchedule(@PathVariable("plan_id") Long planId,
                                                @Valid @RequestBody ScheduleRequestDto dto) {
        Schedule schedule = scheduleService.createSchedule(planId, dto);
        ScheduleResponseDto responseDto = ScheduleResponseDto.fromEntity(schedule);
        return new ApiResponse<>(true, "장소 저장 성공", responseDto);
    }
}
