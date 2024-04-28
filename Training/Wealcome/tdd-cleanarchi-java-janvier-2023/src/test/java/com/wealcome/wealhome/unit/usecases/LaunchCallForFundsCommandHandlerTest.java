package com.wealcome.wealhome.unit.usecases;

import com.wealcome.wealhome.write.adapters.secondary.gateways.repositories.InMemoryFiscalYearRepositoryStub;
import com.wealcome.wealhome.write.businesslogic.models.CallForFunds;
import com.wealcome.wealhome.write.businesslogic.models.DeterministicDateProvider;
import com.wealcome.wealhome.write.businesslogic.models.FiscalYear;
import com.wealcome.wealhome.write.businesslogic.models.IllegalCallForFundsLaunch;
import com.wealcome.wealhome.write.businesslogic.usecases.callForFundsLaunch.LaunchCallForFundsCommand;
import com.wealcome.wealhome.write.businesslogic.usecases.callForFundsLaunch.LaunchCallForFundsCommandHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

import static java.util.UUID.fromString;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public class LaunchCallForFundsCommandHandlerTest {

    private final InMemoryFiscalYearRepositoryStub fiscalYearRepository = new InMemoryFiscalYearRepositoryStub();
    private final UUID fiscalYearId = fromString("8941e162-7b68-43a3-afb8-43608145780d");
    private final LaunchCallForFundsCommand command = new LaunchCallForFundsCommand(fiscalYearId);

    private final DeterministicDateProvider dateProvider = new DeterministicDateProvider();

    @Nested
    class FirstTrimesterOfTheFiscalYear {

        @BeforeEach
        void setup() {
            setCurrentDateToTrimester(1);
        }


        @ParameterizedTest
        @MethodSource("launchCallForFundsTestData")
        void shouldLaunchTheCorrespondingCallForFunds(double budget, double callForFundsAmount) {
            initFiscalYear(budget);
            launchCallForFunds(command);
            assertCallForFunds(budget, new CallForFunds(
                    1,
                    BigDecimal.valueOf(callForFundsAmount)
            ));
        }

        private static Stream<Arguments> launchCallForFundsTestData() {
            return Stream.of(
                    arguments(10400, 2600),
                    arguments(10000, 2500),
                    arguments(10000.5, 2500.13)
            );
        }

        @Test
        void shouldNotBeingAbleToLaunchTheSecondTrimester() {
            initFiscalYear(10000, new CallForFunds(
                    1,
                    BigDecimal.valueOf(2500)
            ));
            try {
                launchCallForFunds(command);
                fail("Should not have launched the call for funds !");
            } catch (Exception e) {
                assertThat(e).isInstanceOf(IllegalCallForFundsLaunch.class).hasMessage("Can't launch the callForFunds of T2");
            }

        }

    }

    @Nested
    class SecondTrimesterOfTheFiscalYear {

        @Test
        void shouldLaunchTheCorrespondingCallForFunds() {
            setCurrentDateToTrimester(2);
            initFiscalYear(10000, new CallForFunds(
                    1,
                    BigDecimal.valueOf(2500)
            ));
            launchCallForFunds(command);
            assertCallForFunds(10000, new CallForFunds(
                    1,
                    BigDecimal.valueOf(2500)
            ), new CallForFunds(
                    2,
                    BigDecimal.valueOf(2500)
            ));
        }
    }

    @Nested
    class ThirdTrimesterOfTheFiscalYear {

        @Test
        void shouldLaunchTheCorrespondingCallForFunds() {
            setCurrentDateToTrimester(3);
            initFiscalYear(10000, new CallForFunds(
                    1,
                    BigDecimal.valueOf(2500)
            ), new CallForFunds(
                    2,
                    BigDecimal.valueOf(2500)
            ));
            launchCallForFunds(command);
            assertCallForFunds(10000, new CallForFunds(
                    1,
                    BigDecimal.valueOf(2500)
            ), new CallForFunds(
                    2,
                    BigDecimal.valueOf(2500)
            ), new CallForFunds(
                    3,
                    BigDecimal.valueOf(2500)
            ));
        }
    }

    @Nested
    class FourthTrimesterOfTheFiscalYear {

        @Test
        void shouldLaunchTheCorrespondingCallForFunds() {
            setCurrentDateToTrimester(4);
            initFiscalYear(10000, new CallForFunds(
                    1,
                    BigDecimal.valueOf(2500)
            ), new CallForFunds(
                    2,
                    BigDecimal.valueOf(2500)
            ),new CallForFunds(
                    3,
                    BigDecimal.valueOf(2500)
            ));
            launchCallForFunds(command);
            assertCallForFunds(10000, new CallForFunds(
                    1,
                    BigDecimal.valueOf(2500)
            ), new CallForFunds(
                    2,
                    BigDecimal.valueOf(2500)
            ), new CallForFunds(
                    3,
                    BigDecimal.valueOf(2500)
            ),new CallForFunds(
                    4,
                    BigDecimal.valueOf(2500)
            ));
        }
    }

    private void launchCallForFunds(LaunchCallForFundsCommand command) {
        new LaunchCallForFundsCommandHandler(fiscalYearRepository, dateProvider).handle(command);
    }

    private void assertCallForFunds(double fiscalYearBudget, CallForFunds... callForFunds) {
        assertThat(fiscalYearRepository.fiscalYears()).containsExactly(
                new FiscalYear(
                        fiscalYearId,
                        BigDecimal.valueOf(fiscalYearBudget),
                        List.of(callForFunds)
                )
        );
    }

    private void initFiscalYear(double fiscalYearBudget, CallForFunds... callsForFunds) {
        List<CallForFunds> existingCallsForFunds = List.of(callsForFunds);
        fiscalYearRepository.feedWith(new FiscalYear(
                fiscalYearId,
                BigDecimal.valueOf(fiscalYearBudget),
                existingCallsForFunds
        ));
    }

    private void setCurrentDateToTrimester(int trimester) {
        List<Integer> someTrimestersValues = List.of(1, 4, 7, 10);
        dateProvider.setDateOfNow(LocalDateTime.of(2021, someTrimestersValues.get(trimester - 1), 3, 0, 0));
    }

}
