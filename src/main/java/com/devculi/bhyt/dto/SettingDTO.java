package com.devculi.bhyt.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SettingDTO {
    private double baseByPercent;
    private double minPoorerSupportInPercent;
    private double minStudentSupportInPercent;
    private double minHaveAverageIncomeSupportInPercent;
    private double secondPersonRate;
    private double thirdPersonRate;
    private double fourthPersonRate;
    private double fromFifthPersonRate;
}
