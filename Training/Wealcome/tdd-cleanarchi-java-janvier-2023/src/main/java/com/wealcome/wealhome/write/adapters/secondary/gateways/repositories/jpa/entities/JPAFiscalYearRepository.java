package com.wealcome.wealhome.write.adapters.secondary.gateways.repositories.jpa.entities;

import com.wealcome.wealhome.write.adapters.secondary.gateways.repositories.jpa.SpringFiscalYearRepository;
import com.wealcome.wealhome.write.businesslogic.gateways.repositories.FiscalYearRepository;
import com.wealcome.wealhome.write.businesslogic.models.CallForFunds;
import com.wealcome.wealhome.write.businesslogic.models.FiscalYear;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.wealcome.wealhome.write.businesslogic.models.FiscalYear.*;

public class JPAFiscalYearRepository implements FiscalYearRepository {

    private final SpringFiscalYearRepository springFiscalYearRepository;

    public JPAFiscalYearRepository(SpringFiscalYearRepository springFiscalYearRepository) {
        this.springFiscalYearRepository = springFiscalYearRepository;
    }

    @Override
    public Optional<FiscalYear> byId(UUID fiscalYearId) {
        return springFiscalYearRepository.findById(fiscalYearId).map(fiscalYearJPAEntity -> {
            FiscalYear fiscalYear = new FiscalYear(
                    fiscalYearJPAEntity.getId(),
                    fiscalYearJPAEntity.getBudget(),
                    fiscalYearJPAEntity.getCallForFundsJPAEntities().stream().map(callForFundsJPAEntity -> new CallForFunds(
                            (int) callForFundsJPAEntity.getId(),
                            callForFundsJPAEntity.getAmount()
                    )).collect(Collectors.toList()));
            return fiscalYear;
        });
    }

    @Override
    public void save(FiscalYear fiscalYear) {
        FiscalYearSnapshot fiscalYearSnapshot = fiscalYear.takeSnapshot();
        FiscalYearJPAEntity fiscalYearJPAEntity = new FiscalYearJPAEntity(fiscalYearSnapshot.getId(), fiscalYearSnapshot.getBudget());
        List<CallForFundsJPAEntity> expectedCallForFundsJPAEntities = fiscalYearSnapshot.getCallsForFundsSnapShots().stream().map(callForFundsSnapshot -> new CallForFundsJPAEntity(callForFundsSnapshot.getId(), callForFundsSnapshot.getAmount())).collect(Collectors.toList());
        fiscalYearJPAEntity.addCallForFunds(expectedCallForFundsJPAEntities);
        springFiscalYearRepository.save(fiscalYearJPAEntity);
    }
}
