package com.tyss.spring_lms.dao;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.tyss.spring_lms.beans.BookBean;
import com.tyss.spring_lms.beans.BorrowBook;
import com.tyss.spring_lms.beans.IssueBook;
import com.tyss.spring_lms.beans.RequestBook;
import com.tyss.spring_lms.beans.UserBean;
import com.tyss.spring_lms.exception.LMSException;

@Repository
public class AdminDAOImplementation implements AdminDAO {

	@PersistenceUnit
	private EntityManagerFactory factory;

	@Override
	public boolean deleteBook(int bookId) {
		EntityManager manager = null;
		EntityTransaction transaction = null;
		try {
			manager = factory.createEntityManager();
			transaction = manager.getTransaction();
			transaction.begin();
			BookBean info = manager.find(BookBean.class, bookId);
			if(info.getNumberOfIssuedBooks() == 0) {
				manager.remove(info);
				transaction.commit();
				return true;
			} else {
				throw new LMSException("Unable to delete the book, since it is issued to the student\n "
						+ "Once it is returned, can delete the book");
			}
		} catch (Exception e) {
			transaction.rollback();
			return false;
		} finally {
			manager.close();
		}

	}

	@Override
	public boolean issueBook(int userId, int bookId) {
		EntityManager manager = null;
		EntityTransaction transaction = null;
		BorrowBook borrowBook = new BorrowBook();
		RequestBook requestBook = new RequestBook();
		IssueBook issueBook = new IssueBook();

		try {
			manager = factory.createEntityManager();
			transaction = manager.getTransaction();
			transaction.begin();

			//Get the number of books borrowed
			String jpql = "select count(b.noOfBooksBorrowed) from BorrowBook b where b.userId = :userId";
			Query query = manager.createQuery(jpql); 
			query.setParameter("userId", userId);
			Long countOfnoOfBooksBorrowed = (Long) query.getSingleResult();
			System.out.println("CountOfnoOfBooksBorrowed = "+countOfnoOfBooksBorrowed);

			UserBean userBean = manager.find(UserBean.class, userId);
			BookBean bookBean = manager.find(BookBean.class, bookId);

			//To check whether borrowbook table is empty or not
			try {
				String jpql1 = "select b from BorrowBook b where b.userId = :userId";
				Query query1 = manager.createQuery(jpql1); 
				query1.setParameter("userId", userId);
				borrowBook = (BorrowBook) query1.getSingleResult();

				//To check whether user is requested book or not
				String jpql2 = "select b from RequestBook b where b.userId = :userId and b.bookId = :bookId";
				Query query2 = manager.createQuery(jpql2); 
				query2.setParameter("userId", userId);
				query2.setParameter("bookId", bookId);
				requestBook = (RequestBook) query2.getSingleResult();

			} catch (Exception e) {}

			if(requestBook != null) {
				if(userBean != null) {
					if(bookBean != null) {
						if(borrowBook == null || (countOfnoOfBooksBorrowed < 3 && countOfnoOfBooksBorrowed >= 0)) {
							if(borrowBook == null || borrowBook.getFees() == 0) {
								bookBean.setNumberOfAvailableBooks(bookBean.getNumberOfAvailableBooks() - 1);
								bookBean.setNumberOfIssuedBooks(bookBean.getNumberOfIssuedBooks() + 1);

								LocalDate currentDate = LocalDate.now();
								issueBook.setIssueDate(currentDate);
								issueBook.setBookId(bookId);
								issueBook.setUserId(userId);
								LocalDate returnDate = LocalDate.now().plusDays(10);
								issueBook.setReturnDate(returnDate);

								manager.persist(issueBook);
								manager.persist(bookBean);

								BorrowBook borrowBook1 = new BorrowBook();
								borrowBook1.setBookId(bookId);
								borrowBook1.setDateOfBorrowed(currentDate);
								borrowBook1.setDateOfReturn(returnDate);
								borrowBook1.setUserId(userId);
								borrowBook1.setFees(borrowBook1.getFees() + 0.00);
								borrowBook1.setNoOfBooksBorrowed(borrowBook1.getNoOfBooksBorrowed() + 1);

								manager.persist(borrowBook1);

								if(requestBook != null) {

									String jpql3 = "select r from RequestBook r where r.userId = :userId and r.bookId = :bookId";
									Query query3 = manager.createQuery(jpql3); 
									query3.setParameter("userId", userId);
									query3.setParameter("bookId", bookId);
									RequestBook requestBook3 = (RequestBook) query3.getSingleResult();
									manager.remove(requestBook3);
								}
								transaction.commit();

							} else {
								throw new LMSException("Fees is unclear");
							}
						} else {
							throw new LMSException("Number of books borrowed limit exceeded");
						}
					} else {
						throw new LMSException("Book does not exist");
					}
				} else {
					throw new LMSException("User does not exist");
				}
			} else {
				throw new LMSException("Book is not requested from the user");
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
			return false;
		} finally {
			manager.close();
		}
	}

	@Override
	public List<RequestBook> showRequest() {
		EntityManager manager = null;
		try {
			manager = factory.createEntityManager();
			String jpql = "select b from RequestBook b";
			TypedQuery<RequestBook> query = manager.createQuery(jpql, RequestBook.class);
			List<RequestBook> beans = query.getResultList();
			return beans;
		} catch (Exception e) {
			return null;
		} finally {
			manager.close();
		}
	}

	@Override
	public List<UserBean> showUsers() {
		EntityManager manager = null;
		try {
			manager = factory.createEntityManager();
			String jpql = "select b from UserBean b where b.role = :role";
			TypedQuery<UserBean> query = manager.createQuery(jpql, UserBean.class);
			query.setParameter("role", "student");
			List<UserBean> beans = query.getResultList();
			return beans;
		} catch (Exception e) {
			return null;
		} finally {
			manager.close();
		}
	}

	@Override
	public boolean addBook(BookBean bean) {
		EntityManager manager = null;
		EntityTransaction transaction = null;
		try {
			manager = factory.createEntityManager();
			transaction = manager.getTransaction();
			transaction.begin();
			manager.persist(bean);
			transaction.commit();
			return true;
		} catch (Exception e) {
			transaction.rollback();
			return false;
		} finally {
			manager.close();
		}
	}

	@Override
	public boolean updateBook(String bookTitle, int numberOfBooks) {
		EntityManager manager = null;
		EntityTransaction transaction = null;
		try {
			manager = factory.createEntityManager();
			transaction = manager.getTransaction();
			transaction.begin();
			String jpql = "select b from BookBean b where bookTitle = :bookTitle";
			TypedQuery<BookBean> query = manager.createQuery(jpql, BookBean.class);
			query.setParameter("bookTitle", bookTitle);
			BookBean info1 = query.getSingleResult();
			if(info1 != null) {
				info1.setNumberOfBooks(info1.getNumberOfBooks() + numberOfBooks);
				info1.setNumberOfAvailableBooks(info1.getNumberOfAvailableBooks() + numberOfBooks);
				manager.persist(info1);
				transaction.commit();
			}
			return true;
		} catch (Exception e) {
			transaction.rollback();
			return false;
		} finally {
			manager.close();
		}
	}


	@Override
	public List<IssueBook> issuedBooks() {
		EntityManager manager = null;
		try {
			manager = factory.createEntityManager();
			String jpql = "select b from IssueBook b";
			TypedQuery<IssueBook> query = manager.createQuery(jpql, IssueBook.class);
			List<IssueBook> beans = query.getResultList();
			return beans;
		} catch (Exception e) {
			return null;
		} finally {
			manager.close();
		}
	}

	@Override
	public boolean isBookReceived(int userId, int bookId) {
		EntityManager manager = null;
		BorrowBook borrowBook = new BorrowBook();
		try {
			manager = factory.createEntityManager();

			String jpql= "select e from BorrowBook e where e.userId = :userId and e.bookId = :bookId";
			Query query = manager.createQuery(jpql); 
			query.setParameter("userId", userId);
			query.setParameter("bookId", bookId);
			try {
				borrowBook = (BorrowBook) query.getSingleResult();
			}catch (NoResultException  e) {
			}

			if(borrowBook != null && borrowBook.getFees() != 0) {
				System.out.println("Student should pay "+borrowBook.getFees()+" Rupees");
				throw new LMSException("Book is not received due to Student should pay the fine for delaying to return the book");
			} 
			return true;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			manager.close();
		}
	}
}
