package com.englishschool.repo;

import com.englishschool.model.Purchased;
import com.englishschool.model.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PurchasedRepo extends JpaRepository<Purchased, Long> {
    List<Purchased> findAllByStatus(Status status);
}
