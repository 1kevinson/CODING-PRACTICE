package com.wealcome.wealhome.write.businesslogic.gateways.repositories;

import com.wealcome.wealhome.write.businesslogic.models.FiscalYear;

import java.util.Optional;
import java.util.UUID;

public interface FiscalYearRepository {

    Optional<FiscalYear> byId(UUID fiscalYearId);
    void save(FiscalYear fiscalYear);

}
