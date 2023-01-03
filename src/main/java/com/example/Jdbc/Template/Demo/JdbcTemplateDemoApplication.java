package com.example.Jdbc.Template.Demo;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
public class JdbcTemplateDemoApplication implements CommandLineRunner {

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Autowired
    private BookRepository bookRepository;
	
    private static final Logger log = LogManager.getLogger(JdbcTemplateDemoApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(JdbcTemplateDemoApplication.class, args);
	}

	@Override
	public void run(String... args) {
		jdbcTemplate.execute("DROP TABLE books IF EXISTS");
		jdbcTemplate.execute("CREATE TABLE books(" + "id SERIAL, name VARCHAR(255), price NUMERIC(15, 2))");
		
		List<Book> books = Arrays.asList(
                new Book("Thinking in Java", new BigDecimal("46.32")),
                new Book("Mkyong in Java", new BigDecimal("1.99")),
                new Book("Getting Clojure", new BigDecimal("37.3")),
                new Book("Head First Android Development", new BigDecimal("41.19"))
        );
		
		books.forEach(book -> {
			log.info("Saving...{}", book.getName());
            bookRepository.save(book);
        });
		
        log.info("[COUNT] Total books: {}", bookRepository.count());
        
        Book book = bookRepository.findById(2L).orElseThrow(IllegalArgumentException::new);
        
        log.info("[UPDATE] :2L :99.99");
        book.setPrice(new BigDecimal("99.99"));
        log.info("rows affected: {}", bookRepository.update(book));

	}

}
