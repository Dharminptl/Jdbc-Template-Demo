package com.example.Jdbc.Template.Demo;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class BookRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public int count() {
		return jdbcTemplate.queryForObject("select count(*) from books", Integer.class);
	}

	public int save(Book book) {
		return jdbcTemplate.update("insert into books (name, price) values(?,?)", book.getName(), book.getPrice());
	}

	public int update(Book book) {
		return jdbcTemplate.update("update books set price = ? where id = ?", book.getPrice(), book.getId());
	}
	
	public Optional<Book> findById(Long id) {
		return jdbcTemplate.queryForObject("select * from books where id = ?", new Object[] { id }, (rs,
				rowNum) -> Optional.of(new Book(rs.getLong("id"), rs.getString("name"), rs.getBigDecimal("price"))));
	}
}

