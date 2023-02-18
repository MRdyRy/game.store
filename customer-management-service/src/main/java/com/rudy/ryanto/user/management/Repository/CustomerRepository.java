package com.rudy.ryanto.user.management.Repository;

import com.rudy.ryanto.user.management.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer,Long> {
    @Query(value = "SELECT * FROM CUSTOMER c WHERE UPPER(c.CUSTOMER_NAME) LIKE UPPER(CONCAT('%',:customerName,'%'))", nativeQuery = true)
    List<Customer> findAllByCustomerNameLike(@Param("customerName") String customerName);

    @Query(value = "SELECT * FROM CUSTOMER c WHERE c.CUSTOMER_NAME=?1 and PASSWORD=?2", nativeQuery = true)
    Optional<Customer> findByCustomerNameAndPassword(String name, String password);
}
