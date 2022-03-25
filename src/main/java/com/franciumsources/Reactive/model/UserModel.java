package com.franciumsources.Reactive.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("user")
@Setter
@Getter
public class UserModel {
    @Id
    Long id;
    String name;
    String town;
    String age;
}
