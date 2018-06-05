package com.glarimy.jaxrs;

import java.util.HashMap;
import java.util.Map;

public class LibraryService {
	private Map<Integer, Book> books;

	public LibraryService() {
		books = new HashMap<>();
		books.put(22061972, new Book(22061972, "The Glarimy Story"));
		books.put(22061973, new Book(22061973, "A QuickLook at Java Threads"));
		books.put(22061974, new Book(22061974, "Random Facts"));
	}

	public Book find(int isbn) {
		return books.get(isbn);
	}
}
