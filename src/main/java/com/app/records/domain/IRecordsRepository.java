package com.app.records.domain;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

@Repository
public interface IRecordsRepository extends JpaRepository<Records, Long> {

    // Get all users (already provided by JpaRepository)
    @Override
    @NonNull
    List<Records> findAll();

    // Search user by ID (already provided by JpaRepository)
    @Override
    @NonNull
    Optional<Records> findById(@NonNull Long id);

    // Save a user (already provided by JpaRepository)
    @Override
    @NonNull
    <S extends Records> S save(@NonNull S records);

    // Check if a user with a specific ID exists (already provided by JpaRepository)
    @Override
    boolean existsById(@NonNull Long id);

    // Delete user by ID (already provided by JpaRepository)
    @Override
    void deleteById(@NonNull Long id);

    // Additional application-specific methods:

    // // Search for a user by their email
    // Optional<Records> findByEmail(String email);

    // // Check if an email is already registered
    // boolean existsByEmail(String email);
}