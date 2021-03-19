package ru.savinov.springdata.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.savinov.springdata.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Account findAccountByName(String name);

    Account findAccountByNameAndBill(String name, Integer bill);

    @Query(nativeQuery = true, value = "select * from Account where name = :name and bill = :bill")
    Account findAccountByNAndB(@Param("name") String name, @Param("bill") Integer bill);

    @Modifying
    @Query(nativeQuery = true, value = "update Account set name = ?2 where id = ?1")
    int setNameFor(Long id, String name);
}
