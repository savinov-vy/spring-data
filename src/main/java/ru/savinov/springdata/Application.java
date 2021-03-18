package ru.savinov.springdata;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;
import ru.savinov.springdata.entity.Account;

import java.util.Map;

@SpringBootApplication
public class Application implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Autowired
    JdbcTemplate jdbcTemplate;

    /**
     * Переопределенный класс от interface CommandLineRunner
     * метод run выполнится сразу при старте приложения
     * таким обраом он заменит контроллер.
     */
    @Override
    public void run(String... args) throws Exception {

        //!! строчные значения в запросах обязательно в одинарных ковычках!!
        jdbcTemplate.execute("INSERT INTO Account (id, name, email, bill) VALUES (1, 'Lory', 'lory@email', 1000)");
        System.out.println(findAccountById(1L));
    }
    private Account findAccountById(Long accountId) {
        String query = "SELECT * FROM Account WHERE id = %s";
        Map<String, Object> resultSet = jdbcTemplate.queryForMap(String.format(query, accountId));
        Account account = new Account();
        account.setId(accountId);
        account.setName((String) resultSet.get("name"));
        account.setEmail((String) resultSet.get("email"));
        account.setBill((Integer) resultSet.get("bill"));
        return account;
    }
}
