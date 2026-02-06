package com.stmc.spring5webfluxrest.repository;

import com.stmc.spring5webfluxrest.model.Category;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

/**
 * Created by: GvN
 * Date: 06-Feb-26
 * Time: 13:52
 * Project Name: spring5-webflux-rest
 */
public interface CategoryRepository extends ReactiveMongoRepository<Category,String> {
}
