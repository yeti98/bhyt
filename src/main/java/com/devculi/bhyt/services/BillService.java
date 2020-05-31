package com.devculi.bhyt.services;

import com.devculi.bhyt.calc.ByPercentCalculator;
import com.devculi.bhyt.calc.Calculator;
import com.devculi.bhyt.calc.FamilyCalculator;
import com.devculi.bhyt.calc.SupportedCalculator;
import com.devculi.bhyt.dto.BillDTO;
import com.devculi.bhyt.entities.Bill;
import com.devculi.bhyt.enums.CalculatorType;
import com.devculi.bhyt.repositories.BillRepository;
import com.devculi.bhyt.repositories.FamilyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class BillService {
    @Autowired
    FamilyRepository familyRepository;
    @Autowired
    BillRepository billRepository;

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

    public List buildStatisticalMap(LocalDateTime startDateTime, LocalDateTime endDateTime) {
        List<Bill> bills = billRepository.getAllBetweenDates(startDateTime, endDateTime);
        if (bills.size() > 0) {
            Double totalMoney = bills.stream().mapToDouble(Bill::getAmount).sum();
            Map khcn = statisticByCode(bills, "CN_", totalMoney);
            Map khdn = statisticByCode(bills, "DN_", totalMoney);
            Map khhgd = statisticByCode(bills, "HGĐ_", totalMoney);
            khcn.put("type", "Cá Nhân");
            khdn.put("type", "Doanh Nghiệp");
            khhgd.put("type", "Hộ Gia Đình");
            List result = new ArrayList();
            result.add(khcn);
            result.add(khdn);
            result.add(khhgd);
            return result;
        }
        return new ArrayList();
    }

    private Map statisticByCode(List<Bill> bills, String code, Double totalMoney) {
        Map data = new HashMap<String, Number>();
        List<Bill> validList = bills.stream().filter(bill -> bill.getCode().startsWith(code)).collect(Collectors.toList());
        Double validMoney = validList.stream().mapToDouble(Bill::getAmount).sum();
        data.put("size", validList.size());
        data.put("money", validMoney);
        data.put("moneyPercentage", toPercent((validMoney / totalMoney)));
        Double sizePercent = Double.valueOf(validList.size()) / Double.valueOf(bills.size());
        data.put("sizePercentage", toPercent(sizePercent));
        return data;
    }
    private Double toPercent(Double num){
        return Double.valueOf(Math.round((num + 1E-5) * 100));
    }
}
