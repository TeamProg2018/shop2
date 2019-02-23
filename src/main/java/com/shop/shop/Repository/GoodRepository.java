package com.shop.shop.Repository;

import com.shop.shop.Entity.Goods;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GoodRepository extends JpaRepository<Goods,Long> {
    List<Goods> findAllByGoodName(String name);
    List<Goods> findAllByGoodPrice(Integer price);
    List<Goods> findAllByShops(String nane);



}
