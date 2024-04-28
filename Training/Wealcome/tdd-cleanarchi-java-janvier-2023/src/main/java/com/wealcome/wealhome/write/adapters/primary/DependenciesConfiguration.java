package com.wealcome.wealhome.write.adapters.primary;

import com.wealcome.wealhome.write.adapters.secondary.gateways.repositories.jpa.SpringFiscalYearRepository;
import com.wealcome.wealhome.write.adapters.secondary.gateways.repositories.jpa.entities.JPAFiscalYearRepository;
import com.wealcome.wealhome.write.businesslogic.gateways.repositories.FiscalYearRepository;
import com.wealcome.wealhome.write.businesslogic.models.DateProvider;
import com.wealcome.wealhome.write.businesslogic.models.DeterministicDateProvider;
import com.wealcome.wealhome.write.businesslogic.usecases.callForFundsLaunch.LaunchCallForFundsCommandHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;

@Configuration
public class DependenciesConfiguration {

    @Bean
    public LaunchCallForFundsCommandHandler launchCallForFundsCommandHandler(FiscalYearRepository fiscalYearRepository,
                                                                             DateProvider dateProvider) {
        return new LaunchCallForFundsCommandHandler(fiscalYearRepository, dateProvider);
    }

    @Bean
    public FiscalYearRepository fiscalYearRepository(SpringFiscalYearRepository springFiscalYearRepository) {
        return new JPAFiscalYearRepository(springFiscalYearRepository);
    }

    @Bean
    public DateProvider dateProvider() {
        DeterministicDateProvider deterministicDateProvider = new DeterministicDateProvider();
        deterministicDateProvider.setDateOfNow(LocalDateTime.of(2022, 8, 12, 0, 0));
        return deterministicDateProvider;

    }

}
