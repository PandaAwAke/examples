package org.example.boot;

import org.example.boot.repo.AuthorRepository;
import org.example.boot.repo.BookRepository;
import org.example.entity.TAuthor;
import org.example.entity.TBook;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.r2dbc.convert.R2dbcCustomConversions;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.data.relational.core.query.Query;
import org.springframework.r2dbc.core.DatabaseClient;

import java.io.IOException;
import java.util.Arrays;


@SpringBootTest
public class SpringR2dbcTest {

    @Autowired
    R2dbcEntityTemplate r2dbcEntityTemplate;

    @Autowired
    DatabaseClient databaseClient;  // 数据库客户端

    @Test
    public void testR2dbcEntityTemplate() throws IOException {
        Criteria criteria = Criteria.empty()
                .and("id").is(1L)
                .and("name").is("zhangsan");

        Query query = Query.query(criteria).limit(1);

        r2dbcEntityTemplate.select(query, TAuthor.class)
                .subscribe(tAuthor -> System.out.println("tAuthor = " + tAuthor));

        System.in.read();
    }

    @Test
    public void testDatabaseClient() throws IOException {
        databaseClient.sql("select * from t_author where id=?")
                .bind(0, 2L)
                .fetch()
                .all()
                .map(map -> {
                    System.out.println("map = " + map);
                    String id = map.getOrDefault("id", "").toString();
                    String name = map.getOrDefault("name", "").toString();
                    return new TAuthor(Long.parseLong(id), name);
                })
                .subscribe(tAuthor -> System.out.println("tAuthor = " + tAuthor));

        System.in.read();
    }


    @Autowired
    AuthorRepository authorRepository;

    @Test
    public void testAuthorRepository() throws IOException {
//        authorRepository.findAll()
//                .subscribe(tAuthor -> System.out.println("tAuthor = " + tAuthor));

        authorRepository.findAllByIdInAndNameLike(
                Arrays.asList(1L, 2L),
                "zhang%"
        ).subscribe(tAuthor -> System.out.println("tAuthor = " + tAuthor));

        System.in.read();
    }


    @Autowired
    BookRepository bookRepository;

    @Autowired
    R2dbcCustomConversions r2dbcCustomConversions;

    @Test
    public void testBookRepository() throws IOException {
//        bookRepository.findAll()
//                .subscribe(tBook -> System.out.println("tBook = " + tBook));

//        bookRepository.findBookAndAuthor(1L)
//                        .map(book -> {
//                            Long authorId = book.getAuthorId();
//                            TAuthor tAuthor = authorRepository.findById(authorId).block();
//                            book.setAuthor(tAuthor);
//                            return book;
//                        });

        TBook tBook = bookRepository.findBookAndAuthor(1L)
                        .block();

        System.out.println("tBook = " + tBook);
        System.in.read();
    }

}
