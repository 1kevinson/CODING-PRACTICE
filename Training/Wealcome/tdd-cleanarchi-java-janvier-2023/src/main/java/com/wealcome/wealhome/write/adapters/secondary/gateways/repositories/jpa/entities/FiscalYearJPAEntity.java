package com.wealcome.wealhome.write.adapters.secondary.gateways.repositories.jpa.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "fiscal_years")
public class FiscalYearJPAEntity {
    @Id
    private UUID id;

    private BigDecimal budget;

    @OneToMany(cascade = {CascadeType.ALL}, mappedBy = "fiscalYearJPAEntity", fetch = FetchType.EAGER)
    private List<CallForFundsJPAEntity> callForFundsJPAEntities = new ArrayList<>();

    public FiscalYearJPAEntity() {
    }

    public FiscalYearJPAEntity(UUID id, BigDecimal budget) {
        this.id = id;
        this.budget = budget;
    }

    public FiscalYearJPAEntity(UUID id, BigDecimal budget, List<CallForFundsJPAEntity> callForFundsJPAEntities) {
        this.id = id;
        this.budget = budget;
        this.callForFundsJPAEntities = callForFundsJPAEntities;
    }

    public void addCallForFunds(List<CallForFundsJPAEntity> callForFundsJPAEntities) {
        this.callForFundsJPAEntities.addAll(callForFundsJPAEntities);
        this.callForFundsJPAEntities.forEach(callForFundsJPAEntity -> callForFundsJPAEntity.setFiscalYearJPAEntity(this));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FiscalYearJPAEntity that = (FiscalYearJPAEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(budget, that.budget) && Objects.equals(callForFundsJPAEntities, that.callForFundsJPAEntities);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, budget, callForFundsJPAEntities);
    }

    public List<CallForFundsJPAEntity> getCallForFundsJPAEntities() {
        return callForFundsJPAEntities;
    }

    public UUID getId() {
        return id;
    }

    public BigDecimal getBudget() {
        return budget;
    }

    @Override
    public String toString() {
        return "FiscalYearJPAEntity{" +
                "id=" + id +
                ", budget=" + budget +
                ", callForFundsJPAEntities=" + callForFundsJPAEntities +
                '}';
    }
}
