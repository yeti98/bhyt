package com.devculi.bhyt.services;

import com.devculi.bhyt.entities.Bill;
import com.devculi.bhyt.repositories.BillRepository;
import com.devculi.bhyt.repositories.FamilyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class BillService {
    @Autowired
    FamilyRepository familyRepository;
    @Autowired
    BillRepository billRepository;

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

    private Double toPercent(Double num) {
        return Double.valueOf(Math.round((num + 1E-5) * 100));
    }

    public List buildStatisticalForYears(LocalDateTime startDateTime, LocalDateTime endDateTime) {
        List barChartData = new ArrayList();
        List<Bill> allBetweenDates = billRepository.getAllBetweenDates(startDateTime, endDateTime);
        for (int year = startDateTime.getYear(); year <= endDateTime.getYear(); year++) {
            int finalYear = year;
            Supplier<Stream<Bill>> sameYearStream = () -> allBetweenDates.stream().filter(bill -> bill.getTimeStamp().getYear() == finalYear);
            long totalNumberOfBill = sameYearStream.get().count();
            Double totalIncome = sameYearStream.get().map(bill -> bill.getAmount()).reduce(0.0, Double::sum);
            Map customerMap = new HashMap();
            customerMap.put("value", totalNumberOfBill);
            customerMap.put("name", "Số khách hàng");
            Map incomeMap = new HashMap();
            incomeMap.put("value", totalIncome / 1000000);
            incomeMap.put("name", "Số tiền");
            Map yearData = new HashMap();
            yearData.put("year", year);
            yearData.put("values", Arrays.asList(customerMap, incomeMap));
            barChartData.add(yearData);
        }
        return barChartData;
    }
}
