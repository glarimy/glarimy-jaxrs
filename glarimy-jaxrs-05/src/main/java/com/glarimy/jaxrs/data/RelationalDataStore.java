package com.glarimy.jaxrs.data;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.glarimy.jaxrs.api.model.Book;

@Repository
public class RelationalDataStore implements DataStore {
	@PersistenceContext(unitName = "library")
	private EntityManager em;

	@Override
	public void create(Book book) {
		em.persist(book);
	}

	@Override
	public Book read(int isbn) {
		Book book = em.find(Book.class, isbn);
		return book;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Book> read() {
		List<Book> books = em.createQuery("select b from Book b").getResultList();
		return books;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Book> read(String title) {
		List<Book> books = em.createQuery("from " + Book.class + " where title=:title").setParameter("title", title)
				.getResultList();
		return books;

	}

	@Override
	public void delete(int isbn) {
		em.remove(isbn);
	}

	@Override
	public void replace(Book book) {
		delete(book.getIsbn());
		create(book);
	}

}
