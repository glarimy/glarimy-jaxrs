package com.glarimy.jaxrs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.glarimy.jaxrs.api.LibraryService;
import com.glarimy.jaxrs.api.exceptions.BookNotFoundException;
import com.glarimy.jaxrs.api.exceptions.DuplicateBookException;
import com.glarimy.jaxrs.api.exceptions.InvalidBookException;
import com.glarimy.jaxrs.api.model.Book;
import com.glarimy.jaxrs.data.DataStore;

@EnableTransactionManagement
@Service
public class LibraryServiceImpl implements LibraryService {
	@Autowired
	private DataStore store;

	@Override
	public Book find(int isbn) {
		Book book = store.read(isbn);
		if (book == null)
			throw new BookNotFoundException();
		return book;
	}

	@Override
	@Transactional
	public void add(Book book) {
		if (book == null || book.getIsbn() < 0)
			throw new InvalidBookException();
		if (store.read(book.getIsbn()) != null)
			throw new DuplicateBookException();
		store.create(book);
	}

	@Override
	@Transactional
	public void remove(int isbn) {
		Book book = store.read(isbn);
		if (book == null)
			throw new BookNotFoundException();
		store.delete(isbn);
	}

	@Override
	@Transactional
	public void replace(Book book) {
		store.replace(book);
	}

	@Override
	public List<Book> list() {
		return store.read();
	}

	@Override
	public List<Book> listByTitle(String title) {
		return store.read(title);
	}
}
