package com.shop.shop.Entity;

import org.springframework.web.bind.annotation.GetMapping;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "shops", catalog = "db07")
public class Shops {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long shop_id;
    private String shopName;

    @OneToMany(mappedBy = "shops", cascade = CascadeType.ALL)
    Set<Goods>goodsSet=new HashSet<>();

    public Shops() {
    }

    public Shops(String shopName) {
        this.shopName = shopName;
    }

    public Long getShop_id() {
        return shop_id;
    }

    public void setShop_id(Long shop_id) {
        this.shop_id = shop_id;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public Set<Goods> getGoodsSet() {
        return goodsSet;
    }

    public void setGoodsSet(Set<Goods> goodsSet) {
        this.goodsSet = goodsSet;
    }


}
