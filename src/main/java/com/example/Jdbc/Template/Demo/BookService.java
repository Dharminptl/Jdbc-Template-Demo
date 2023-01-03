package com.example.Jdbc.Template.Demo;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {
	
	@Autowired
	private BookRepository bookRepository;
	
	public int saveBook(Book book) {
		return bookRepository.save(book);
	}
	
	public int count() {
		return bookRepository.count();
	}

	public int update(Book book) {
		return bookRepository.update(book);
	}
	
	public Optional<Book> findById(Long id) {
		return bookRepository.findById(id);
	}

}
