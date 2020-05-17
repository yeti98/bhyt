package com.devculi.bhyt.calc;

import com.devculi.bhyt.dto.BillDTO;
import com.devculi.bhyt.entities.Bill;
import com.devculi.bhyt.entities.Family;
import com.devculi.bhyt.exceptions.EntityNotFoundException;
import com.devculi.bhyt.repositories.FamilyRepository;
import com.devculi.bhyt.services.SettingService;
import org.springframework.stereotype.Service;

import java.util.Optional;

public class FamilyCalculator implements Calculator {
    final FamilyRepository familyRepository;

    public FamilyCalculator(FamilyRepository familyRepository) {
        this.familyRepository = familyRepository;
    }

    @Override
    public double calculate(BillDTO billDTO) {
        String familyCode = billDTO.getFamilyCode();
        Optional<Family> optionalFamily = familyRepository.findByCode(familyCode);
        Family family = optionalFamily.orElseThrow(() -> new EntityNotFoundException(Family.class, "code", familyCode));
        int memberCount = family.getBills().size();
        if (memberCount == 0) {
            return SettingService.FIRST_PERSON_RATE * billDTO.getBaseSalary();
        }
        Bill firstBill = family.getBills().get(0);
        if (memberCount == 1) {
            return SettingService.SECOND_PERSON_RATE * firstBill.getAmount();
        }
        if (memberCount == 2) {
            return SettingService.THIRD_PERSON_RATE * firstBill.getAmount();
        }
        if (memberCount == 3) {
            return SettingService.FOURTH_PERSON_RATE * firstBill.getAmount();
        }
        return SettingService.FROM_FIFTH_PERSON_RATE * firstBill.getAmount();
    }
}
