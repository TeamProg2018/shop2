package com.shop.shop.Entity;

import javax.persistence.*;

@Entity
@Table(name = "goods", catalog = "db07")
public class Goods {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long good_id;
    private String goodName;
    private Integer goodPrice;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "shop_id")
    private Shops shops;

    public Goods() {
    }

    public Goods(String goodName, Integer goodPrice, Shops shops) {
        this.goodName = goodName;
        this.goodPrice = goodPrice;
        this.shops = shops;
    }

    public Long getGood_id() {
        return good_id;
    }

    public void setGood_id(Long good_id) {
        this.good_id = good_id;
    }

    public String getGoodName() {
        return goodName;
    }

    public void setGoodName(String goodName) {
        this.goodName = goodName;
    }

    public Integer getGoodPrice() {
        return goodPrice;
    }

    public void setGoodPrice(Integer goodPrice) {
        this.goodPrice = goodPrice;
    }

    public Shops getShops() {
        return shops;
    }

    public void setShops(Shops shops) {
        this.shops = shops;
    }


}
