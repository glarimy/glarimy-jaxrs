package com.glarimy.jaxrs.data;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.glarimy.jaxrs.api.model.Book;

public class RelationalDataStore implements DataStore {
	EntityManagerFactory factory = Persistence.createEntityManagerFactory("library");

	@Override
	public void create(Book book) {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		em.persist(book);
		em.getTransaction().commit();
		em.close();
	}

	@Override
	public Book read(int isbn) {
		EntityManager em = factory.createEntityManager();
		Book book = em.find(Book.class, isbn);
		em.close();
		return book;

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Book> read() {
		EntityManager em = factory.createEntityManager();
		List<Book> books = em.createQuery("select b from Book b").getResultList();
		em.close();
		return books;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Book> read(String title) {
		EntityManager em = factory.createEntityManager();
		List<Book> books = em.createQuery("from " + Book.class + " where title=:title").setParameter("title", title)
				.getResultList();
		em.close();
		return books;

	}

	@Override
	public void delete(int isbn) {
		EntityManager em = factory.createEntityManager();
		em.remove(isbn);
		em.close();
	}

	@Override
	public void replace(Book book) {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		delete(book.getIsbn());
		create(book);
		em.getTransaction().commit();
		em.close();
	}

}
