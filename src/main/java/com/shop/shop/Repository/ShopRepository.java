package com.shop.shop.Repository;

import com.shop.shop.Entity.Shops;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface ShopRepository extends JpaRepository<Shops,Long> {
    List<Shops>findAllByShopName(String name);
    List<Shops>findByShopName(String name);


}