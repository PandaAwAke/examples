package org.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;


@Table("t_author")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TAuthor {

    @Id
    private Long id;
    private String name;

}
