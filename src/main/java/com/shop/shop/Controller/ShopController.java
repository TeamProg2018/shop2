package com.shop.shop.Controller;

import com.shop.shop.Entity.Goods;
import com.shop.shop.Entity.Shops;
import com.shop.shop.Repository.GoodRepository;
import com.shop.shop.Repository.ShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;

@Controller
public class ShopController {
    @Autowired
    private ShopRepository shopRepository;
    @Autowired
    private GoodRepository goodRepository;

    public String curentShop=null;

    @GetMapping("/shop")
    public String getShops(){
        return "shop";
    }

    @PostMapping("/addShop")
    private String addShop(@RequestParam String shopName){
        boolean add=true;
        List<Shops>shopsList=shopRepository.findAll();
        for (int i = 0; i < shopsList.size(); i++) {
            String str=shopsList.get(i).getShopName();
            System.out.println(str);
            if(str.equals(shopName)){
                System.out.println("Exist");
                add=false;
            }
        }
        if(add){
        Shops shops=new Shops();
        shops.setShopName(shopName);
        shopRepository.save(shops);
            System.out.println(add);
            add=false;
            System.out.println(add);
        }
        return "shop";
    }
    @PostMapping("/showAll")
    private String showAll(Map<String,Object>model){
        List<Shops>shopsList=shopRepository.findAll();
        model.put("keyAddShop",shopsList);
        return "shop";
    }

    @PostMapping("/renameShop")
    public String renameShop(@RequestParam String oldName,
                             @RequestParam String newName){
        List<Shops>shopsList=shopRepository.findByShopName(oldName);
        for (Shops shops : shopsList) {
            shops.setShopName(newName);
            shopRepository.save(shops);
        }
        return "shop";
    }

    @PostMapping("/goToShop")
    public String goToShop(@RequestParam String shopNameByGoToShop,
                           Map<String,Object>model){
        model.put("keyShopNameByGoToShop", shopNameByGoToShop);
        curentShop=shopNameByGoToShop;
        return "goods";
    }

    @PostMapping("/deleteShop")
    private String delete(@RequestParam String shopId){
        shopRepository.deleteById(Long.parseLong(shopId));
        return "shop";
    }


    ////////////////////////////////  GOOGS    //////////////////


    @PostMapping("/addGood")
    public String addGood(@RequestParam String goodName,
                          @RequestParam String goodPrice,
                          Map<String,Object>model){
        List<Shops>shopsList=shopRepository.findByShopName(curentShop);
        for (Shops shops : shopsList) {
            if(shops.getShopName().equals(curentShop)){
                Long tempId=shops.getShop_id();
                System.out.println(tempId);
            }
            Goods goods=new Goods();
            shops.setShopName(curentShop);
            goods.setGoodName(goodName);
            goods.setGoodPrice(Integer.parseInt(goodPrice));
            goods.setShops(shops);
            goodRepository.save(goods);
        }
        List<Goods>goodsListSelected=new ArrayList<>();
        List<Goods>goodsList=goodRepository.findAll();
        for (Goods goods : goodsList) {
            if (goods.getShops().getShopName().equals(curentShop)){
                goodsListSelected.add(goods);
            }
        }
        model.put("keyAddGood",goodsListSelected);
        return "goods";
    }
    @PostMapping("showAllGoods")
    public String showAllGoods(Map<String,Object>model){
        String titleName=curentShop;
        model.put("keyShopNameByGoToShop", titleName);

        List<Goods>goodsListSelected=new ArrayList<>();

        List<Goods>goodsList=goodRepository.findAll();
        for (Goods goods : goodsList) {
            if (goods.getShops().getShopName().equals(curentShop)){
                goodsListSelected.add(goods);
            }
        }
        model.put("keyShowAllGoods", goodsListSelected);
        return "goods";
    }
    @PostMapping("/findAllGoodsBy")
    public String findAllGoodsBy(@RequestParam String name1,
                                 @RequestParam String name2,
                                 Map<String,Object>model){
        String titleName=curentShop;
        model.put("keyShopNameByGoToShop", titleName);
        List<Goods>goodsListSeleced1=new ArrayList<>();
        switch (name1){
            case "byPrice":{
                List<Goods>goodsList=goodRepository.findAllByGoodPrice(Integer.parseInt(name2));
                for (Goods goods : goodsList) {
                    goods.getShops().getShopName();
                    System.out.println(goods.getShops().getShopName());
                    goodsListSeleced1.add(goods);
                }
                model.put("keyFindAllGoodsBy",goodsListSeleced1);
                break;
            }
            case "byName":{
                List<Goods>goodsList=goodRepository.findAllByGoodName(name2);
                for (Goods goods : goodsList) {
                    goods.getShops().getShopName();
                    System.out.println(goods.getShops().getShopName());
                    goodsListSeleced1.add(goods);
                }
                model.put("keyFindAllGoodsBy",goodsListSeleced1);
                break;
            }
        }
        return "goods";
    }
}



/////////////////////////////// archive /////////////////////////////////


//    @PostMapping("/findAllGoodsBy")
//    public String findAllGoodsBy(@RequestParam String name1,
//                                 @RequestParam String name2,
//                                 Map<String,Object>model){
//        String titleName=curentShop;
//        model.put("keyShopNameByGoToShop", titleName);

//        switch (name1){
//            case "byPrice":{
//                List<Goods>goodsList=goodRepository.findAllByGoodPrice(Integer.parseInt(name2));
//                model.put("keyFindAllGoodsBy",goodsList);
//                break;
//            }
//            case "byName":{
//                List<Goods>goodsList=goodRepository.findAllByGoodName(name2);
//                model.put("keyFindAllGoodsBy",goodsList);
//                break;
//            }
//        }
//
//        return "goods";
//    }