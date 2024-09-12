package org.example.boot.entity;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "用户实体")
public class UserEntity {

    private Long id;
    private String name;
    private String nickName;

}
