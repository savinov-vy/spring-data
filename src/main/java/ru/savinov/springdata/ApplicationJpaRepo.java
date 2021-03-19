package ru.savinov.springdata;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;
import ru.savinov.springdata.entity.Account;
import ru.savinov.springdata.repository.AccountRepository;

@SpringBootApplication
public class ApplicationJpaRepo implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication.run(ApplicationJpaRepo.class, args);
    }

    @Autowired
    AccountRepository accountRepository;

    /**
     * Переопределенный класс от interface CommandLineRunner
     * метод run выполнится сразу при старте приложения
     * таким обраом он заменит контроллер.
     */
    @Transactional // обязательно необходимо для метода setNameFor, выловить в методе run не получится так как транзакция не завершена
    @Override
    public void run(String... args) throws Exception {
        for (int i = 1; i < 10; i++) {
            accountRepository.save(new Account(null, "Lory" + i, "lory" + i + "@xyz", 200 * i));
        }
        System.out.println(accountRepository.findAccountByName("Lory4"));
        System.out.println(accountRepository.findAccountByNameAndBill("Lory5", 1000));
        System.out.println(accountRepository.findAccountByNAndB("Lory6", 1200));
        accountRepository.setNameFor(6L, "Max");
    }
}