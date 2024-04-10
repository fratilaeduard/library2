package com.example.library2.service;

import com.example.library2.entity.Book;
import com.example.library2.repository.BookRepository;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class BookService {
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
    public Book addBook(Book book){
        return bookRepository.save(book);
    }
    public List<Book> getAllBooks(){
        return bookRepository.findAll();
    }
    public Book getBookById(Long id) throws Exception{
        Optional<Book> book = bookRepository.findById(id);
        if(book.isPresent()){
            return book.get();
        } else{
            throw new Exception("Book not found with id: " + id);
        }
    }
    public Book updateBook(Long id, Book bookDetails) throws Exception {
     Book book = getBookById(id);
     book.setTitle(bookDetails.getTitle());
     book.setIsbn(bookDetails.getIsbn());
     book.setAuthors(bookDetails.getAuthors());
     return bookRepository.save(book);
    }
    public void deleteBook(Long id) throws Exception {
        Book book = getBookById(id);
        bookRepository.delete(book);
    }
}
