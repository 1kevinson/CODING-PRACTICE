package com.wealcome.wealhome.integration;

import com.wealcome.wealhome.write.adapters.secondary.gateways.repositories.jpa.SpringFiscalYearRepository;
import com.wealcome.wealhome.write.adapters.secondary.gateways.repositories.jpa.entities.CallForFundsJPAEntity;
import com.wealcome.wealhome.write.adapters.secondary.gateways.repositories.jpa.entities.FiscalYearJPAEntity;
import com.wealcome.wealhome.write.businesslogic.gateways.repositories.FiscalYearRepository;
import com.wealcome.wealhome.write.businesslogic.models.CallForFunds;
import com.wealcome.wealhome.write.businesslogic.models.FiscalYear;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static java.util.UUID.fromString;
import static org.assertj.core.api.Assertions.assertThat;

public class JPAFiscalYearRepositoryIT extends BaseIntegration {

    @Autowired
    private SpringFiscalYearRepository springFiscalYearRepository;

    @Autowired
    private FiscalYearRepository fiscalYearRepository;

    private final UUID fiscalYearId = fromString("caa920a9-c190-4287-972f-d0588efb35b6");

    @Test
    void withoutCallsForFunds_canSaveAFiscalYear() {
        fiscalYearRepository.save(new FiscalYear(
                fiscalYearId,
                BigDecimal.valueOf(10000),
                new ArrayList<>()
        ));
        FiscalYearJPAEntity fiscalYearJPAEntity = springFiscalYearRepository.findById(fiscalYearId).get();
        assertThat(fiscalYearJPAEntity.getId()).isEqualTo(fiscalYearId);
        assertThat(fiscalYearJPAEntity.getBudget()).isEqualTo(BigDecimal.valueOf(10000));
        assertThat(fiscalYearJPAEntity.getCallForFundsJPAEntities()).isEmpty();
    }

    @Test
    void withCallsForFunds_creation_canSaveAFiscalYear() {
        CallForFunds callForFunds = new CallForFunds(1, BigDecimal.valueOf(2500));
        CallForFunds callForFunds2 = new CallForFunds(2, BigDecimal.valueOf(2500));
        List<CallForFunds> callsForFunds = new ArrayList<>();
        callsForFunds.add(callForFunds);
        callsForFunds.add(callForFunds2);

        fiscalYearRepository.save(new FiscalYear(
                fiscalYearId,
                BigDecimal.valueOf(10000),
                callsForFunds
        ));

        FiscalYearJPAEntity fiscalYearJPAEntity = springFiscalYearRepository.findById(fiscalYearId).get();
        assertThat(fiscalYearJPAEntity.getId()).isEqualTo(fiscalYearId);
        assertThat(fiscalYearJPAEntity.getBudget()).isEqualTo(BigDecimal.valueOf(10000));
        assertThat(fiscalYearJPAEntity.getCallForFundsJPAEntities()).containsExactly(
                new CallForFundsJPAEntity(
                        1,
                        roundToTwoDecimals(2500),
                        fiscalYearJPAEntity
                ),
                new CallForFundsJPAEntity(
                        2,
                        roundToTwoDecimals(2500),
                        fiscalYearJPAEntity
                ));
    }

    @Test
    void withCallsForFunds_update_canSaveAFiscalYear() {
        springFiscalYearRepository.save(new FiscalYearJPAEntity(
                fiscalYearId,
                roundToTwoDecimals(10000)
        ));

        FiscalYear fiscalYear = fiscalYearRepository.byId(fiscalYearId).get();

        CallForFunds callForFunds = new CallForFunds(1, roundToTwoDecimals(2500));
        CallForFunds callForFunds2 = new CallForFunds(2, roundToTwoDecimals(2500));
        List<CallForFunds> callsForFunds = new ArrayList<>();
        callsForFunds.add(callForFunds);
        callsForFunds.add(callForFunds2);

        FiscalYear fiscalYearUpdatedWithCallsForFunds = new FiscalYear(
                fiscalYearId,
                fiscalYear.takeSnapshot().getBudget(),
                callsForFunds
        );

        fiscalYearRepository.save(fiscalYearUpdatedWithCallsForFunds);

        FiscalYearJPAEntity fiscalYearJPAEntity = springFiscalYearRepository.findById(fiscalYearId).get();
        assertThat(fiscalYearJPAEntity.getId()).isEqualTo(fiscalYearId);
        assertThat(fiscalYearJPAEntity.getBudget()).isEqualTo(roundToTwoDecimals(10000));
        assertThat(fiscalYearJPAEntity.getCallForFundsJPAEntities()).containsExactly(
                new CallForFundsJPAEntity(
                        1,
                        roundToTwoDecimals(2500),
                        fiscalYearJPAEntity
                ),
                new CallForFundsJPAEntity(
                        2,
                        roundToTwoDecimals(2500),
                        fiscalYearJPAEntity
                ));
    }

    private BigDecimal roundToTwoDecimals(int value) {
        return BigDecimal.valueOf(value).setScale(2, RoundingMode.HALF_UP);
    }

    @Test
    void withoutCallsForFunds_canFindAFiscalYearById() {
        springFiscalYearRepository.save(new FiscalYearJPAEntity(
                fiscalYearId,
                roundToTwoDecimals(10000)
        ));
        assertThat(fiscalYearRepository.byId(fiscalYearId)).contains(new FiscalYear(
                fiscalYearId,
                roundToTwoDecimals(10000),
                List.of()
        ));
    }

    @Test
    void withCallsForFunds_canFindAFiscalYearById() {
        FiscalYearJPAEntity fiscalYearJPAEntity = new FiscalYearJPAEntity(fiscalYearId, roundToTwoDecimals(10000));
        List<CallForFundsJPAEntity> callForFundsJPAEntities = new ArrayList<>();
        callForFundsJPAEntities.add(new CallForFundsJPAEntity(1, roundToTwoDecimals(2500)));
        callForFundsJPAEntities.add(new CallForFundsJPAEntity(2, roundToTwoDecimals(2500)));
        fiscalYearJPAEntity.addCallForFunds(callForFundsJPAEntities);
        springFiscalYearRepository.save(fiscalYearJPAEntity);
        assertThat(fiscalYearRepository.byId(fiscalYearId)).contains(new FiscalYear(
                fiscalYearId,
                roundToTwoDecimals(10000),
                List.of(
                   new CallForFunds(1, roundToTwoDecimals(2500)),
                   new CallForFunds(2, roundToTwoDecimals(2500))
                )
        ));
    }


}
