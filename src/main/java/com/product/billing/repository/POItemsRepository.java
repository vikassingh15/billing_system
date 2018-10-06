package com.product.billing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.product.billing.model.POItems;

public interface POItemsRepository extends JpaRepository<POItems, Long>{

}
