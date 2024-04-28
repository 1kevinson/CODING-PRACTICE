package com.wealcome.wealhome.e2e;

import com.wealcome.wealhome.write.businesslogic.gateways.repositories.FiscalYearRepository;
import com.wealcome.wealhome.write.businesslogic.models.CallForFunds.CallForFundsSnapshot;
import com.wealcome.wealhome.write.businesslogic.models.DateProvider;
import com.wealcome.wealhome.write.businesslogic.models.DeterministicDateProvider;
import com.wealcome.wealhome.write.businesslogic.models.FiscalYear;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static com.wealcome.wealhome.write.businesslogic.models.FiscalYear.FiscalYearSnapshot;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
public class CallsForFundsControllerIT extends BaseE2E {

    @Autowired
    protected MockMvc mvc;

    @Autowired
    private FiscalYearRepository fiscalYearRepository;

    @Autowired
    private DateProvider dateProvider;

    @Test
    void shouldLaunchTheFirstCallForFundsOfTheFiscalYear() throws Exception {
        ((DeterministicDateProvider) dateProvider).setDateOfNow(LocalDateTime.of(2022, 1, 3, 14, 15, 3));
        UUID fiscalYearId = UUID.fromString("699915cd-056a-41e2-ba3d-cea478d4c0a2");
        fiscalYearRepository.save(new FiscalYear(
                fiscalYearId,
                roundToTwoDecimals(10000),
                List.of()
        ));
        mvc.perform(post("/fiscalyears/{fiscalYearId}/callsforfunds",
                        fiscalYearId
                ))
                .andExpect(status().isCreated());
        FiscalYearSnapshot fiscalYearSnapshot = fiscalYearRepository.byId(fiscalYearId).get().takeSnapshot();
        assertThat(fiscalYearSnapshot.getId()).isEqualTo(fiscalYearId);
        assertThat(fiscalYearSnapshot.getBudget()).isEqualTo(roundToTwoDecimals(10000));
        assertThat(fiscalYearSnapshot.getCallsForFundsSnapShots()).containsExactly(
                new CallForFundsSnapshot(
                        1,
                        roundToTwoDecimals(2500)
                ));
    }

    private BigDecimal roundToTwoDecimals(int value) {
        return BigDecimal.valueOf(value).setScale(2, RoundingMode.HALF_UP);
    }

}
