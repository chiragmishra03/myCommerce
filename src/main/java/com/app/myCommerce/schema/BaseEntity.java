package com.app.myCommerce.schema;

import jakarta.persistence.*;

@MappedSuperclass
public class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long Id;

}
