package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository
            , PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Author eric = new Author("Eric", "Evans");
        Book ddd = new Book("Domain Driven Development", "12122");

        Publisher testPublisher = new Publisher();
        testPublisher.setName("Waleed");
        testPublisher.setAddressLine("Test Address");
        testPublisher.setState("California");
        publisherRepository.save(testPublisher);

        eric.getBook().add(ddd);
        ddd.getAuthors().add(eric);
        ddd.setPublisher(testPublisher);
        testPublisher.getBooks().add(ddd);

        // saving the objects into the H2 Database
        authorRepository.save(eric);
        bookRepository.save(ddd);


        System.out.println("Started In Bootstrap");
        System.out.println("Number Of Book Saved: " + bookRepository.count());
        System.out.println("Number Of Publishers: "+ publisherRepository.count());
        System.out.println("Number of Books Belonging To The Publisher: " + testPublisher.getBooks().size());
    }
}
