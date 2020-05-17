package com.devculi.bhyt.calc;

import com.devculi.bhyt.dto.BillDTO;
import com.devculi.bhyt.services.SettingService;

public class SupportedCalculator implements Calculator {
    @Override
    public double calculate(BillDTO billDTO) {
        double rate = billDTO.getCategory().getValue();
        return rate* SettingService.BASE_BY_PERCENT;
    }
}
