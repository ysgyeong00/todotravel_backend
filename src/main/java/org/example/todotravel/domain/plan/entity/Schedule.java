package org.example.todotravel.domain.plan.entity;

import lombok.*;

import jakarta.persistence.*;

import java.time.LocalTime;

@Entity
@Table(name = "schedules")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "schedule_id", nullable = false)
    private Long scheduleId;

    @Column(name = "status", nullable = false)
    private Boolean status;

    @Column(name = "travel_day_count", nullable = false)
    private Integer travelDayCount;

    @Column(name = "description")
    private String description;

    @Column(name = "travel_time")
    private LocalTime travelTime;

    @ManyToOne
    @JoinColumn(name = "plan_id", nullable = false)
    private Plan plan;

    @ManyToOne
    @JoinColumn(name = "location_id", nullable = false)
    private Location location;

    @ManyToOne
    @JoinColumn(name = "vehicle_id")
    private Vehicle vehicle;

    @ManyToOne
    @JoinColumn(name = "budget_id")
    private Budget budget;
}
