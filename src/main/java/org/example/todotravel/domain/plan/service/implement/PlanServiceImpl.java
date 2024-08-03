package org.example.todotravel.domain.plan.service.implement;

import lombok.RequiredArgsConstructor;
import org.example.todotravel.domain.plan.dto.request.PlanRequestDto;
import org.example.todotravel.domain.plan.entity.Plan;
import org.example.todotravel.domain.plan.entity.PlanUser;
import org.example.todotravel.domain.plan.repository.PlanRepository;
import org.example.todotravel.domain.plan.service.PlanService;
import org.example.todotravel.domain.user.entity.User;
import org.example.todotravel.domain.user.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class PlanServiceImpl implements PlanService {
    private final PlanRepository planRepository;
    private final UserRepository userRepository;//push 전 제거 (테스트용)

    @Override
    @Transactional
    public Plan createPlan(PlanRequestDto planRequestDto) {
        //플랜 생성 시 일정과 메모가 빈 플랜이 db에 생성

        Plan plan = planRequestDto.toEntity();
        //현재 로그인 중인 사용자 user
        User user = new User();
        plan.setPlanUser(user);
        //planUsers에 플랜 생성자 추가
        PlanUser planUser = PlanUser.builder()
                .status(PlanUser.StatusType.PENDING)
                .user(user)
                .plan(plan)
                .build();
        plan.setPlanUsers(Collections.singleton(planUser));
        return planRepository.save(plan);
    }

    @Override
    @Transactional(readOnly = true)
    public Plan getPlan(Long planId) {
        return planRepository.findByPlanId(planId).orElseThrow(() -> new RuntimeException("여행 플랜을 찾을 수 없습니다."));
    }

    @Override
    @Transactional
    public Plan updatePlan(Long planId, PlanRequestDto dto) {
        Plan plan = planRepository.findByPlanId(planId).orElseThrow(() -> new RuntimeException("여행 플랜을 찾을 수 없습니다."));
        plan.setTitle(dto.getTitle());
        plan.setLocation(dto.getLocation());
        plan.setStartDate(dto.getStartDate());
        plan.setEndDate(dto.getEndDate());
        plan.setIsPublic(dto.getIsPublic());
        plan.setTotalBudget(dto.getTotalBudget());

        //수정을 위해 toBuilder 사용
//        plan.toBuilder()
//                .title(dto.getTitle())
//                .location(dto.getLocation())
//                .startDate(dto.getStartDate())
//                .endDate(dto.getEndDate())
//                .isPublic(dto.getIsPublic())
//                .totalBudget(dto.getTotalBudget())
//                .build();

        return planRepository.save(plan);
    }

    @Override
    @Transactional
    public void deletePlan(Long planId) {
        planRepository.deleteByPlanId(planId);
    }
}
