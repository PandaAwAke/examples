package org.example.boot.repo;


import org.example.entity.TBook;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface BookRepository extends R2dbcRepository<TBook, Long> {

    @Query("SELECT b.*, t.name as name FROM t_book b" +
            " LEFT JOIN t_author ON b.author_id = t.id WHERE b.id = $1")
    Mono<TBook> findBookAndAuthor(Long bookId);

}
