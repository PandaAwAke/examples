package org.example.mybatis.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {

    private Integer id;
    private String username;
    private String password;
    private String gender;
    private String addr;
    private String companyName;

}
