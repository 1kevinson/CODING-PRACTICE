package com.wealcome.wealhome.write.businesslogic.models;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

public class CallForFunds {

    private final int id;
    private final BigDecimal amount;

    public CallForFunds(int id, BigDecimal amount) {
        this.id = id;
        this.amount = amount.setScale(2, RoundingMode.HALF_UP);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CallForFunds that = (CallForFunds) o;
        return id == that.id && Objects.equals(amount, that.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, amount);
    }

    @Override
    public String toString() {
        return "CallForFunds{" +
                "id=" + id +
                ", amount=" + amount +
                '}';
    }

    public static CallForFunds restore(CallForFundsSnapshot callForFundsSnapshot) {
        return new CallForFunds(
                callForFundsSnapshot.id,
                callForFundsSnapshot.amount
        );
    }

    public CallForFundsSnapshot takeSnapshot() {
        return new CallForFundsSnapshot(
                this.id,
                this.amount
        );
    }

    public static class CallForFundsSnapshot {
        private final int id;
        private final BigDecimal amount;

        public CallForFundsSnapshot(int id, BigDecimal amount) {
            this.id = id;
            this.amount = amount;
        }

        public int getId() {
            return id;
        }

        public BigDecimal getAmount() {
            return amount;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            CallForFundsSnapshot that = (CallForFundsSnapshot) o;
            return id == that.id && Objects.equals(amount, that.amount);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, amount);
        }
    }
}
