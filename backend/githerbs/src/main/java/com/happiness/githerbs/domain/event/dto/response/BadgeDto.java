package com.happiness.githerbs.domain.event.dto.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class BadgeDto {
    Integer badgeId;
    String name;
    String details;
}
