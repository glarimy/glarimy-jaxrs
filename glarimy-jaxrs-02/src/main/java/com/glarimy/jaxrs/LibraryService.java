package com.glarimy.jaxrs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LibraryService {
	private Map<Integer, Book> books;

	public LibraryService() {
		books = new HashMap<>();
		books.put(22061972, new Book(22061972, "The Glarimy Story"));
		books.put(22061973, new Book(22061973, "A QuickLook at Java Threads"));
		books.put(22061974, new Book(22061974, "Random Facts"));
		Book book = new Book(22061975, "Java Design Patterns");
		book.setPublisher(new Publisher("GTS", 9731423166L));
		book.getAuthors().add(new Author("Krishna", "krishna@glarimy.com"));
		book.getAuthors().add(new Author("Mohan", "mohan@glarimy.com"));
		books.put(book.getIsbn(), book);
	}

	public Book find(int isbn) {
		return books.get(isbn);
	}

	public void add(Book book) {
		books.put(book.getIsbn(), book);
	}

	public void remove(int isbn) {
		books.remove(isbn);
	}

	public void replace(Book book) {
		books.put(book.getIsbn(), book);
	}

	public List<Book> list() {
		return new ArrayList<>(books.values());
	}

	public List<Book> listByTitle(String title) {
		List<Book> list = new ArrayList<>();
		for (Book book : books.values()) {
			if (book.getTitle().contains(title))
				list.add(book);
		}
		return list;
	}
}
