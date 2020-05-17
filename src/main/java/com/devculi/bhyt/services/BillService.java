package com.devculi.bhyt.services;

import com.devculi.bhyt.calc.ByPercentCalculator;
import com.devculi.bhyt.calc.Calculator;
import com.devculi.bhyt.calc.FamilyCalculator;
import com.devculi.bhyt.calc.SupportedCalculator;
import com.devculi.bhyt.dto.BillDTO;
import com.devculi.bhyt.enums.CalculatorType;
import com.devculi.bhyt.repositories.FamilyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BillService {
    @Autowired
    FamilyRepository familyRepository;

    public Calculator getCalculator(CalculatorType calculatorType) {
        switch (calculatorType) {
            case BY_PERCENT:
                return new ByPercentCalculator();
            case SUPPORTED:
                return new SupportedCalculator();
            case FAMILY:
                return new FamilyCalculator(familyRepository);
        }
        return null;
    }

    public double calcMoney(BillDTO billDTO) {
        CalculatorType type = billDTO.getType();
        Calculator calculator = getCalculator(type);
        return calculator.calculate(billDTO);
    }
}
