package com.glarimy.jaxrs.service;

import java.util.List;

import com.glarimy.jaxrs.api.LibraryService;
import com.glarimy.jaxrs.api.exceptions.BookNotFoundException;
import com.glarimy.jaxrs.api.exceptions.DuplicateBookException;
import com.glarimy.jaxrs.api.exceptions.InvalidBookException;
import com.glarimy.jaxrs.api.model.Book;
import com.glarimy.jaxrs.data.DataStore;
import com.glarimy.jaxrs.data.RelationalDataStore;

public class LibraryServiceImpl implements LibraryService {
	private DataStore store;

	public LibraryServiceImpl() {
		store = new RelationalDataStore();
	}

	@Override
	public Book find(int isbn) {
		Book book = store.read(isbn);
		if (book == null)
			throw new BookNotFoundException();
		return book;
	}

	@Override
	public void add(Book book) {
		if (book == null || book.getIsbn() < 0)
			throw new InvalidBookException();
		if (store.read(book.getIsbn()) != null)
			throw new DuplicateBookException();
		store.create(book);
	}

	@Override
	public void remove(int isbn) {
		Book book = store.read(isbn);
		if (book == null)
			throw new BookNotFoundException();
		store.delete(isbn);
	}

	@Override
	public void replace(Book book) {
		// TODO
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
