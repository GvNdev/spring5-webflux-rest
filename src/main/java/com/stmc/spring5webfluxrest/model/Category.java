package com.stmc.spring5webfluxrest.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by: GvN
 * Date: 06-Feb-26
 * Time: 13:51
 * Project Name: spring5-webflux-rest
 */
@Data
@Document
public class Category {
    @Id
    private String id;

    private String description;
}
