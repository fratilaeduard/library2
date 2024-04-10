package com.example.library2.service;

import com.example.library2.entity.Author;
import com.example.library2.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {
    private final AuthorRepository authorRepository;
    @Autowired
    public AuthorService(AuthorRepository authorRepository){
        this.authorRepository = authorRepository;
    }
    public Author addAuthor (Author author){
        return authorRepository.save(author);
    }
    public List<Author> getAllAuthors(){
        return authorRepository.findAll();
    }
    public Author getAuthorById(Long id) throws Exception {
        Optional<Author> author = authorRepository.findById(id);
        if(author.isPresent()){
            return author.get();
        } else {
            throw new Exception("Author not found with id: " + id);
        }
    }
    public Author updateAuthor(Long id, Author authorDetails) throws Exception{
        Author author = getAuthorById(id);
        author.setName(authorDetails.getName());
        return authorRepository.save(author);
    }
    public void deleteAuthor(Long id) throws Exception{
        Author author = getAuthorById(id);
        authorRepository.delete(author);
    }

}
