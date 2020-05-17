package com.devculi.bhyt.repositories;

import com.devculi.bhyt.entities.Family;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FamilyRepository extends JpaRepository<Family, Long> {
    Optional<Family> findByCode(String familyCode);
}
