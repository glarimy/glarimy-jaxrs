package com.glarimy.jaxrs.data;

import java.util.List;

import com.glarimy.jaxrs.api.model.Book;

public interface DataStore {
	public void create(Book book);

	public Book read(int isbn);

	public List<Book> read();

	public void replace(Book book);

	public List<Book> read(String title);

	public void delete(int isbn);
}
