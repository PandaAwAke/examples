package org.example.boot.repo;


import org.example.entity.TAuthor;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import java.util.Collection;

@Repository
public interface AuthorRepository extends R2dbcRepository<TAuthor, Long> {

    Flux<TAuthor> findAllByIdInAndNameLike(Collection<Long> id, String name);

    //多表复杂查询

    @Query("select * from t_author") //自定义query注解，指定sql语句
    Flux<TAuthor> findHaha();

}
