package com.wealcome.wealhome.write.businesslogic.usecases.callForFundsLaunch;

import com.wealcome.wealhome.write.businesslogic.gateways.repositories.FiscalYearRepository;
import com.wealcome.wealhome.write.businesslogic.models.DateProvider;

import javax.transaction.Transactional;

@Transactional
public class LaunchCallForFundsCommandHandler {

    private final FiscalYearRepository fiscalYearRepository;
    private final DateProvider dateProvider;

    public LaunchCallForFundsCommandHandler(FiscalYearRepository fiscalYearRepository,
                                            DateProvider dateProvider) {
        this.fiscalYearRepository = fiscalYearRepository;
        this.dateProvider = dateProvider;
    }

    public void handle(LaunchCallForFundsCommand command) {
        fiscalYearRepository.byId(command.fiscalYearId).ifPresent(fiscalYear -> {
            fiscalYear.launchCallForFunds(dateProvider);
            fiscalYearRepository.save(fiscalYear);
        });
    }

}
