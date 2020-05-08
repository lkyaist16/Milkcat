package com.part6.jdbc;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class User {

    private Integer id;

    private String loginName;

    private String password;

    private Integer age;

    private String email;

}
