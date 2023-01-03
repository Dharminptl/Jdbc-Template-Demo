package com.example.Jdbc.Template.Demo;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookController {

	@Autowired
	private BookService bookService;

	@RequestMapping("/getBookCount")
	public int getBookCount(@RequestParam Long id) {
		return bookService.count();
	}

	@RequestMapping("/saveBook")
	public int saveBook(@RequestBody Book book) {
		return bookService.saveBook(book);
	}

	@RequestMapping("/updateBook")
	public int updateBook(@RequestBody Book book) {
		return bookService.update(book);
	}
	
	@RequestMapping("/findBookById")
	public Optional<Book> findById(@RequestParam Long id) {
		return bookService.findById(id);
	}

}
