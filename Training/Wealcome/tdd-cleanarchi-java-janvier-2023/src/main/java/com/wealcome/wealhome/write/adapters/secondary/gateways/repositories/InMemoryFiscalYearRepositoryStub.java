package com.wealcome.wealhome.write.adapters.secondary.gateways.repositories;

import com.wealcome.wealhome.write.businesslogic.gateways.repositories.FiscalYearRepository;
import com.wealcome.wealhome.write.businesslogic.models.FiscalYear;

import java.util.*;

public class InMemoryFiscalYearRepositoryStub implements FiscalYearRepository {

    private final Map<UUID, FiscalYear> fiscalYearsById = new HashMap<>();

    public Optional<FiscalYear> byId(UUID fiscalYearId) {
        return Optional.of(FiscalYear.fromSnapshot(fiscalYearsById.get(fiscalYearId).takeSnapshot()));
    }

    public void save(FiscalYear fiscalYear) {
        fiscalYearsById.put(fiscalYear.getId(), fiscalYear);
    }

    public void feedWith(FiscalYear fiscalYear) {
        save(fiscalYear);
    }

    public List<FiscalYear> fiscalYears() {
        return fiscalYearsById.values().stream().toList();
    }
}
