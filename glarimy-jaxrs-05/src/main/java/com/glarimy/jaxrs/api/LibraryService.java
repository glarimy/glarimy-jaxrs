package com.glarimy.jaxrs.api;

import java.util.List;

import com.glarimy.jaxrs.api.exceptions.BookNotFoundException;
import com.glarimy.jaxrs.api.exceptions.DuplicateBookException;
import com.glarimy.jaxrs.api.exceptions.InvalidBookException;
import com.glarimy.jaxrs.api.exceptions.LibraryServiceException;
import com.glarimy.jaxrs.api.model.Book;

public interface LibraryService {

	Book find(int isbn) throws BookNotFoundException, LibraryServiceException;

	void add(Book book) throws InvalidBookException, DuplicateBookException, LibraryServiceException;

	void remove(int isbn) throws BookNotFoundException, LibraryServiceException;

	void replace(Book book) throws BookNotFoundException, InvalidBookException, LibraryServiceException;

	List<Book> list() throws LibraryServiceException;

	List<Book> listByTitle(String title) throws LibraryServiceException;

}