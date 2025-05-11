package com.example.repsy.Repository;

import com.example.repsy.Entity.Package;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface packageRepository extends JpaRepository<Package, Long> {
    Optional<Package> findByNameAndVersion(String name, String version);
}
