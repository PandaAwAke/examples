package org.example.boot.config.converter;

import io.r2dbc.spi.Row;
import org.example.entity.TAuthor;
import org.example.entity.TBook;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;

import java.time.Instant;

@ReadingConverter
public class BookConverter implements Converter<Row, TBook> {
    @Override
    public TBook convert(Row source) {
        TBook tBook = new TBook();

        Long authorId = source.get("author_id", Long.class);
        tBook.setId(source.get("id", Long.class));
        tBook.setTitle(source.get("title", String.class));
        tBook.setAuthorId(authorId);
        tBook.setPublishTime(source.get("publish_time", Instant.class));

        TAuthor tAuthor = new TAuthor(authorId, source.get("name", String.class));
        tBook.setAuthor(tAuthor);

        return tBook;
    }

}
