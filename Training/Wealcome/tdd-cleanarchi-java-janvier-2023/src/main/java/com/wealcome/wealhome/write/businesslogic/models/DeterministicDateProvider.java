package com.wealcome.wealhome.write.businesslogic.models;

import java.time.LocalDateTime;

public class DeterministicDateProvider implements DateProvider {

    private LocalDateTime dateOfNow;

    @Override
    public LocalDateTime dateNow() {
        return dateOfNow;
    }

    public void setDateOfNow(LocalDateTime dateOfNow) {
        this.dateOfNow = dateOfNow;
    }
}
