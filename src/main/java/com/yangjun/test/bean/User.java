package com.yangjun.test.bean;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class User {

    private int id;

    private String name;

    private String address;

    private Date birthDay;

}
