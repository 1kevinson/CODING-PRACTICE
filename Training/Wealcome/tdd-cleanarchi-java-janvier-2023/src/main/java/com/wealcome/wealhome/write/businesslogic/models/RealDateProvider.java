package com.wealcome.wealhome.write.businesslogic.models;

import java.time.LocalDateTime;

public class RealDateProvider implements DateProvider {

    @Override
    public LocalDateTime dateNow() {
        return LocalDateTime.now();
    }

}
