package com.wealcome.wealhome.write.adapters.secondary.gateways.repositories.jpa.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "calls_for_funds")
public class CallForFundsJPAEntity {
    @Id
    private long id;

    private BigDecimal amount;

    @ManyToOne
    @JoinColumn(name = "fiscal_year_id", nullable = false)
    private FiscalYearJPAEntity fiscalYearJPAEntity;

    public CallForFundsJPAEntity() {
    }

    public CallForFundsJPAEntity(long id, BigDecimal amount) {
        this.id = id;
        this.amount = amount;
    }

    public CallForFundsJPAEntity(long id, BigDecimal amount, FiscalYearJPAEntity fiscalYearJPAEntity) {
        this.id = id;
        this.amount = amount;
        this.fiscalYearJPAEntity = fiscalYearJPAEntity;
    }

    public void setFiscalYearJPAEntity(FiscalYearJPAEntity fiscalYearJPAEntity) {
        this.fiscalYearJPAEntity = fiscalYearJPAEntity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CallForFundsJPAEntity that = (CallForFundsJPAEntity) o;
        return id == that.id && Objects.equals(amount, that.amount) && Objects.equals(fiscalYearJPAEntity, that.fiscalYearJPAEntity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, amount, fiscalYearJPAEntity);
    }

    public long getId() {
        return id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public FiscalYearJPAEntity getFiscalYearJPAEntity() {
        return fiscalYearJPAEntity;
    }

    @Override
    public String toString() {
        return "CallForFundsJPAEntity{" +
                "id=" + id +
                ", amount=" + amount +
                '}';
    }
}
