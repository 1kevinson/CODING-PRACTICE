package com.wealcome.wealhome.write.businesslogic.models;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.wealcome.wealhome.write.businesslogic.models.CallForFunds.*;

public class FiscalYear {
    private final UUID id;
    private final BigDecimal budget;

    private final List<CallForFunds> callsForFunds;

    public FiscalYear(UUID id, BigDecimal budget, List<CallForFunds> callsForFunds) {
        this.id = id;
        this.budget = budget;
        this.callsForFunds = callsForFunds;
    }

    public void launchCallForFunds(DateProvider dateProvider) {
        int currentTrimester = currentTrimester(dateProvider);
        ensureCurrentTrimesterCanBeLaunched(currentTrimester);
        this.callsForFunds.add(new CallForFunds(currentTrimester, quarterOfTheBudget()));
    }

    private BigDecimal quarterOfTheBudget() {
        return BigDecimal.valueOf(budget.doubleValue() / 4.0);
    }

    private int currentTrimester(DateProvider dateProvider) {
        int currentTrimester = 0;
        for (int i = 0; i < 12; i = i + 3)
            if (dateProvider.dateNow().getMonthValue() > i)
                currentTrimester++;
        return currentTrimester;
    }

    private void ensureCurrentTrimesterCanBeLaunched(int currentTrimester) {
        if (currentTrimester < nextTrimester())
            throw new IllegalCallForFundsLaunch("Can't launch the callForFunds of T" + nextTrimester());
    }

    private int nextTrimester() {
        return callsForFunds.size() + 1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FiscalYear that = (FiscalYear) o;
        return Objects.equals(id, that.id) && Objects.equals(budget, that.budget) && Objects.equals(callsForFunds, that.callsForFunds);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, budget, callsForFunds);
    }

    public UUID getId() {
        return id;
    }

    public static FiscalYear fromSnapshot(FiscalYearSnapshot fiscalYearSnapshot) {
        return new FiscalYear(
                fiscalYearSnapshot.id,
                fiscalYearSnapshot.budget,
                fiscalYearSnapshot.callsForFundsSnapShots.stream().map(CallForFunds::restore).collect(Collectors.toList())
        );
    }

    public FiscalYearSnapshot takeSnapshot() {
        return new FiscalYearSnapshot(
                this.id,
                this.budget,
                this.callsForFunds.stream().map(CallForFunds::takeSnapshot).collect(Collectors.toList())
        );
    }

    @Override
    public String toString() {
        return "FiscalYear{" +
                "id=" + id +
                ", budget=" + budget +
                ", callForFunds=" + callsForFunds +
                '}';
    }

    public static class FiscalYearSnapshot {
        private final UUID id;
        private final BigDecimal budget;

        private final List<CallForFundsSnapshot> callsForFundsSnapShots;

        public FiscalYearSnapshot(UUID id, BigDecimal budget, List<CallForFundsSnapshot> callsForFundsSnapShots) {
            this.id = id;
            this.budget = budget;
            this.callsForFundsSnapShots = callsForFundsSnapShots;
        }

        public UUID getId() {
            return id;
        }

        public BigDecimal getBudget() {
            return budget;
        }

        public List<CallForFundsSnapshot> getCallsForFundsSnapShots() {
            return callsForFundsSnapShots;
        }
    }
}
