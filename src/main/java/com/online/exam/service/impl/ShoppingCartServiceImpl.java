package com.online.exam.service.impl;

import com.online.exam.common.Constants;
import com.online.exam.common.ServiceResultEnum;
import com.online.exam.controller.vo.ShoppingCartItemVO;
import com.online.exam.dao.GoodsMapper;
import com.online.exam.dao.ShoppingCartItemMapper;
import com.online.exam.entity.Goods;
import com.online.exam.entity.ShoppingCartItem;
import com.online.exam.service.ShoppingCartService;

import com.online.exam.util.BeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    @Autowired
    private ShoppingCartItemMapper shoppingCartItemMapper;

    @Autowired
    private GoodsMapper goodsMapper;

    //todo 修改session中购物项数量

    @Override
    public String saveNewBeeMallCartItem(ShoppingCartItem shoppingCartItem) {
        ShoppingCartItem temp = shoppingCartItemMapper.selectByUserIdAndGoodsId(shoppingCartItem.getUserId(), shoppingCartItem.getGoodsId());
        if (temp != null) {
            //已存在则修改该记录
            //todo count = tempCount + 1
            temp.setGoodsCount(shoppingCartItem.getGoodsCount());
            return updateNewBeeMallCartItem(temp);
        }
        Goods goods = goodsMapper.selectByPrimaryKey(shoppingCartItem.getGoodsId());
        //商品为空
        if (goods == null) {
            return ServiceResultEnum.GOODS_NOT_EXIST.getResult();
        }
        int totalItem = shoppingCartItemMapper.selectCountByUserId(shoppingCartItem.getUserId()) + 1;
        //超出单个商品的最大数量
        if (shoppingCartItem.getGoodsCount() > Constants.SHOPPING_CART_ITEM_LIMIT_NUMBER) {
            return ServiceResultEnum.SHOPPING_CART_ITEM_LIMIT_NUMBER_ERROR.getResult();
        }
        //超出最大数量
        if (totalItem > Constants.SHOPPING_CART_ITEM_TOTAL_NUMBER) {
            return ServiceResultEnum.SHOPPING_CART_ITEM_TOTAL_NUMBER_ERROR.getResult();
        }
        //保存记录
        if (shoppingCartItemMapper.insertSelective(shoppingCartItem) > 0) {
            return ServiceResultEnum.SUCCESS.getResult();
        }
        return ServiceResultEnum.DB_ERROR.getResult();
    }

    @Override
    public String updateNewBeeMallCartItem(ShoppingCartItem shoppingCartItem) {
        ShoppingCartItem newBeeMallShoppingCartItemUpdate = shoppingCartItemMapper.selectByPrimaryKey(shoppingCartItem.getCartItemId());
        if (newBeeMallShoppingCartItemUpdate == null) {
            return ServiceResultEnum.DATA_NOT_EXIST.getResult();
        }
        //超出单个商品的最大数量
        if (shoppingCartItem.getGoodsCount() > Constants.SHOPPING_CART_ITEM_LIMIT_NUMBER) {
            return ServiceResultEnum.SHOPPING_CART_ITEM_LIMIT_NUMBER_ERROR.getResult();
        }
        //todo 数量相同不会进行修改
        //todo userId不同不能修改
        newBeeMallShoppingCartItemUpdate.setGoodsCount(shoppingCartItem.getGoodsCount());
        newBeeMallShoppingCartItemUpdate.setUpdateTime(new Date());
        //修改记录
        if (shoppingCartItemMapper.updateByPrimaryKeySelective(newBeeMallShoppingCartItemUpdate) > 0) {
            return ServiceResultEnum.SUCCESS.getResult();
        }
        return ServiceResultEnum.DB_ERROR.getResult();
    }

    @Override
    public ShoppingCartItem getNewBeeMallCartItemById(Long newBeeMallShoppingCartItemId) {
        return shoppingCartItemMapper.selectByPrimaryKey(newBeeMallShoppingCartItemId);
    }

    @Override
    public Boolean deleteById(Long newBeeMallShoppingCartItemId) {
        //todo userId不同不能删除
        return shoppingCartItemMapper.deleteByPrimaryKey(newBeeMallShoppingCartItemId) > 0;
    }

    @Override
    public List<ShoppingCartItemVO> getMyShoppingCartItems(Long newBeeMallUserId) {
        List<ShoppingCartItemVO> shoppingCartItemVOS = new ArrayList<>();
        List<ShoppingCartItem> shoppingCartItems = shoppingCartItemMapper.selectByUserId(newBeeMallUserId, Constants.SHOPPING_CART_ITEM_TOTAL_NUMBER);
        if (!CollectionUtils.isEmpty(shoppingCartItems)) {
            //查询商品信息并做数据转换
            List<Long> newBeeMallGoodsIds = shoppingCartItems.stream().map(ShoppingCartItem::getGoodsId).collect(Collectors.toList());
            List<Goods> goods = goodsMapper.selectByPrimaryKeys(newBeeMallGoodsIds);
            Map<Long, Goods> goodsMap = new HashMap<>();
            if (!CollectionUtils.isEmpty(goods)) {
                goodsMap = goods.stream().collect(Collectors.toMap(Goods::getGoodsId, Function.identity(), (entity1, entity2) -> entity1));
            }
            for (ShoppingCartItem shoppingCartItem : shoppingCartItems) {
                ShoppingCartItemVO newBeeMallShoppingCartItemVO = new ShoppingCartItemVO();
                BeanUtil.copyProperties(shoppingCartItem, newBeeMallShoppingCartItemVO);
                if (goodsMap.containsKey(shoppingCartItem.getGoodsId())) {
                    Goods newBeeMallGoodsTemp = goodsMap.get(shoppingCartItem.getGoodsId());
                    newBeeMallShoppingCartItemVO.setGoodsCoverImg(newBeeMallGoodsTemp.getGoodsCoverImg());
                    String goodsName = newBeeMallGoodsTemp.getGoodsName();
                    // 字符串过长导致文字超出的问题
                    if (goodsName.length() > 28) {
                        goodsName = goodsName.substring(0, 28) + "...";
                    }
                    newBeeMallShoppingCartItemVO.setGoodsName(goodsName);
                    newBeeMallShoppingCartItemVO.setSellingPrice(newBeeMallGoodsTemp.getSellingPrice());
                    shoppingCartItemVOS.add(newBeeMallShoppingCartItemVO);
                }
            }
        }
        return shoppingCartItemVOS;
    }
}
