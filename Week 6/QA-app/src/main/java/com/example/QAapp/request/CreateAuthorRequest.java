package com.example.QAapp.request;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
public class CreateAuthorRequest {

    private String name;

    private String address;

    private Date dob;

    private String username;

    private String password;
}
