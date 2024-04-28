package com.wealcome.wealhome.write.businesslogic.usecases.callForFundsLaunch;

import java.util.UUID;

public class LaunchCallForFundsCommand {

    public UUID fiscalYearId;

    public LaunchCallForFundsCommand(UUID fiscalYearId) {
        this.fiscalYearId = fiscalYearId;
    }

}
