package com.tyss.spring_lms.dao;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.tyss.spring_lms.beans.BookBean;
import com.tyss.spring_lms.beans.BorrowBook;
import com.tyss.spring_lms.beans.RequestBook;
import com.tyss.spring_lms.beans.UserBean;
import com.tyss.spring_lms.exception.LMSException;

@Repository
public class StudentDAOImplementation implements StudentDAO {

	@PersistenceUnit
	private EntityManagerFactory factory;

	@Override
	public List<BorrowBook> borrowedBook(int userId) {
		EntityManager manager = null;
		try {
			manager = factory.createEntityManager();
			String jpql = "select b from BorrowBook b where b.userId = :userId";
			TypedQuery<BorrowBook> query = 	manager.createQuery(jpql, BorrowBook.class);
			query.setParameter("userId", userId);
			List<BorrowBook> books = query.getResultList();
			return books;
		} catch (Exception e) {
			return null;
		} finally {
			manager.close();
		}
	}

	@Override
	public BookBean searchBookById(int bookId) {
		EntityManager manager = null;
		try {
			manager = factory.createEntityManager();
			BookBean info = manager.find(BookBean.class, bookId);
			return info;
		} catch (Exception e) {
			return null;
		} finally {
			manager.close();
		}
	}

	@Override
	public BookBean searchBookByTitle(String bookTitle) {
		EntityManager manager = null;
		try {
			manager = factory.createEntityManager();
			String jpql = "select e from BookBean e where e.bookTitle = :bookTitle";
			TypedQuery<BookBean> query = manager.createQuery(jpql, BookBean.class);
			query.setParameter("bookTitle", bookTitle);
			BookBean bean = query.getSingleResult();
			return bean;
		} catch (Exception e) {
			return null;
		} finally {
			manager.close();
		}
	}

	@Override
	public BookBean searchBookByAuthor(String author) {
		EntityManager manager = null;
		try {
			manager = factory.createEntityManager();
			String jpql = "select e from BookBean e where e.author = :author";
			TypedQuery<BookBean> query = manager.createQuery(jpql, BookBean.class);
			query.setParameter("author", author);
			BookBean bean = query.getSingleResult();
			return bean;
		} catch (Exception e) {
			return null;
		} finally {
			manager.close();
		}
	}

	@Override
	public List<BookBean> getBooksInfo() {
		EntityManager manager = null;
		try {
			manager = factory.createEntityManager();
			String jpql = "select b from BookBean b";
			TypedQuery<BookBean> query = manager.createQuery(jpql, BookBean.class);
			List<BookBean> beans = query.getResultList();
			return beans;
		} catch (Exception e) {
			return null;
		} finally {
			manager.close();
		}
	}

	@Override
	public boolean bookRequest(int userId, int bookId) {
		EntityManager manager = null;
		EntityTransaction transaction = null;
		try {
			manager = factory.createEntityManager();
			transaction = manager.getTransaction();
			transaction.begin();

			//To check number of books requested
			String jpql = "select count(b.bookTitle) from RequestBook b where b.userId = :userId";
			Query query = manager.createQuery(jpql); 
			query.setParameter("userId", userId);
			Long countOfRequestBook = (Long) query.getSingleResult();
			System.out.println("countOfRequestBook = "+countOfRequestBook);

			//To check number of books borrowed
			String jpql1 = "select count(b.noOfBooksBorrowed) from BorrowBook b where b.userId = :userId";
			Query query1 = manager.createQuery(jpql1); 
			query1.setParameter("userId", userId);
			Long countOfBooksBorrowed = (Long) query1.getSingleResult();
			System.out.println("CountOfnoOfBooksBorrowed = "+countOfBooksBorrowed);


			//To check whether the same book is requested
			String jpql2 = "select count(b.bookId) from RequestBook b where b.userId = :userId and b.bookId = :bookId";
			Query query2 = manager.createQuery(jpql2); 
			query2.setParameter("userId", userId);
			query2.setParameter("bookId", bookId);
			Long countOfBook = (Long) query2.getSingleResult();
			System.out.println("countOfBook = "+countOfBook);

			//To check whether the book requested is already issued to the same user
			String jpql3 = "select count(b.bookId) from BorrowBook b where b.userId = :userId and b.bookId = :bookId";
			Query query3 = manager.createQuery(jpql3); 
			query3.setParameter("userId", userId);
			query3.setParameter("bookId", bookId);
			Long countOfBook1 = (Long) query3.getSingleResult();
			System.out.println("countOfBook1 = "+countOfBook1);


			BookBean bookBean = manager.find(BookBean.class, bookId);
			BorrowBook borrowBook = manager.find(BorrowBook.class, userId);
			UserBean userBean = manager.find(UserBean.class, userId);

			if(bookBean != null) {
				if(userBean != null) {
					if(countOfBook == 0){
						if(countOfBook1 == 0) {
							if(countOfRequestBook >= 0 && countOfRequestBook < 2){
								if(borrowBook == null || (countOfBooksBorrowed >= 0 && countOfBooksBorrowed < 2)) {
									if(bookBean.getNumberOfAvailableBooks() != 0) {
										RequestBook requestBook = new RequestBook();
										requestBook.setBookId(bookId);
										requestBook.setBookTitle(bookBean.getBookTitle());
										requestBook.setFirstName(userBean.getFirstName());
										requestBook.setLastName(userBean.getLastName());
										requestBook.setUserId(userId);
										manager.persist(requestBook);
										transaction.commit();
										return true;
									} else {
										throw new LMSException("Book is not in stock");
									}
								} else {
									throw new LMSException("Exceeded limit to borrow the book");
								}
							}else {
								throw new LMSException("Exceeded limit to request the book");
							}
						} else {
							throw new LMSException("Book is already borrowed, cannot be issued again");
						}
					} else {
						throw new LMSException("Book is already requested, cannot request again");
					} 
				} else {
					throw new LMSException("User does not exist");
				}
			} else {
				throw new LMSException("Book does not exist");
			}
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
			return false;
		} finally {
			manager.close();
		}
	}

	@Override
	public boolean bookReturn(int userId, int bookId) {
		EntityManager manager = null;
		EntityTransaction transaction = null;
		BorrowBook borrowBook = new BorrowBook();
		try {
			manager = factory.createEntityManager();
			transaction = manager.getTransaction();
			transaction.begin();

			String jpql= "select e from BorrowBook e where e.userId = :userId and e.bookId = :bookId";
			Query query = manager.createQuery(jpql); 
			query.setParameter("userId", userId);
			query.setParameter("bookId", bookId);
			borrowBook = (BorrowBook) query.getSingleResult();
			
			Date returnDate = (Date) borrowBook.getDateOfReturn();

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Calendar calendar =  Calendar.getInstance();
			String date = sdf.format(calendar.getTime());
			Date currentDate = java.sql.Date.valueOf(date);

			long noOfDaysBetween = currentDate.getTime() - returnDate.getTime();
			float daysBetween = (noOfDaysBetween / (1000 * 60 * 60 * 24));
			if(daysBetween > 0) {
				borrowBook.setFees(daysBetween * 5);
				transaction.commit();
				System.out.println("Student should pay "+borrowBook.getFees()+" Rupees");
				throw new LMSException("Student should pay the fine for delaying to return the book");
			} else {
				String jpql1 = "delete from BorrowBook e where e.bookId = :bookId and e.userId = :userId";
				Query query1 = manager.createQuery(jpql1); 
				query1.setParameter("bookId", bookId);
				query1.setParameter("userId", userId);
				int count = query1.executeUpdate();
				if(count != 0) {
					String jpql2 = "delete from IssueBook e where e.bookId = :bookId and e.userId = :userId";
					Query query2 = manager.createQuery(jpql2); 
					query2.setParameter("bookId", bookId);
					query2.setParameter("userId", userId);
					int count1 = query2.executeUpdate();
					if(count1 != 0) {
						BookBean bookBean = manager.find(BookBean.class, borrowBook.getBookId());
						bookBean.setNumberOfIssuedBooks(bookBean.getNumberOfIssuedBooks() - 1);
						bookBean.setNumberOfAvailableBooks(bookBean.getNumberOfAvailableBooks() + 1);
						transaction.commit();
					}
				}
			}
			return true;
		} catch (Exception e) {
			transaction.rollback();
			return false;
		} finally {
			manager.close();
		}
	}

}
