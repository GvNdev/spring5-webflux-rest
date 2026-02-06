package com.stmc.spring5webfluxrest.repository;

import com.stmc.spring5webfluxrest.model.Vendor;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

/**
 * Created by: GvN
 * Date: 06-Feb-26
 * Time: 15:01
 * Project Name: spring5-webflux-rest
 */
public interface VendorRepository extends ReactiveMongoRepository<Vendor,String> {
}
