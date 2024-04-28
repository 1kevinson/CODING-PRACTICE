package com.wealcome.wealhome.write.adapters.secondary.gateways.repositories.jpa;

import com.wealcome.wealhome.write.adapters.secondary.gateways.repositories.jpa.entities.FiscalYearJPAEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SpringFiscalYearRepository extends JpaRepository<FiscalYearJPAEntity, UUID> {
}
