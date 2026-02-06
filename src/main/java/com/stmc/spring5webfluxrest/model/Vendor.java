package com.stmc.spring5webfluxrest.model;

import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by: GvN
 * Date: 06-Feb-26
 * Time: 15:00
 * Project Name: spring5-webflux-rest
 */
@Data
@Document
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Vendor {
    @Id
    private String id;

    private String fistName;
    private String lastName;
}
