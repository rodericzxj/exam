package com.online.exam.service.impl;


import com.online.exam.common.ServiceResultEnum;
import com.online.exam.controller.vo.SearchGoodsVO;
import com.online.exam.dao.GoodsMapper;
import com.online.exam.entity.Goods;
import com.online.exam.service.GoodsService;
import com.online.exam.util.BeanUtil;
import com.online.exam.util.PageQueryUtil;
import com.online.exam.util.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private GoodsMapper goodsMapper;

    @Override
    public PageResult getNewBeeMallGoodsPage(PageQueryUtil pageUtil) {
        List<Goods> goodsList = goodsMapper.findNewBeeMallGoodsList(pageUtil);
        int total = goodsMapper.getTotalNewBeeMallGoods(pageUtil);
        PageResult pageResult = new PageResult(goodsList, total, pageUtil.getLimit(), pageUtil.getPage());
        return pageResult;
    }

    @Override
    public String saveNewBeeMallGoods(Goods goods) {
        if (goodsMapper.insertSelective(goods) > 0) {
            return ServiceResultEnum.SUCCESS.getResult();
        }
        return ServiceResultEnum.DB_ERROR.getResult();
    }

    @Override
    public void batchSaveNewBeeMallGoods(List<Goods> newBeeMallGoodsList) {
        if (!CollectionUtils.isEmpty(newBeeMallGoodsList)) {
            goodsMapper.batchInsert(newBeeMallGoodsList);
        }
    }

    @Override
    public String updateNewBeeMallGoods(Goods goods) {
        Goods temp = goodsMapper.selectByPrimaryKey(goods.getGoodsId());
        if (temp == null) {
            return ServiceResultEnum.DATA_NOT_EXIST.getResult();
        }
        goods.setUpdateTime(new Date());
        if (goodsMapper.updateByPrimaryKeySelective(goods) > 0) {
            return ServiceResultEnum.SUCCESS.getResult();
        }
        return ServiceResultEnum.DB_ERROR.getResult();
    }

    @Override
    public Goods getNewBeeMallGoodsById(Long id) {
        return goodsMapper.selectByPrimaryKey(id);
    }
    
    @Override
    public Boolean batchUpdateSellStatus(Long[] ids, int sellStatus) {
        return goodsMapper.batchUpdateSellStatus(ids, sellStatus) > 0;
    }

    @Override
    public PageResult searchNewBeeMallGoods(PageQueryUtil pageUtil) {
        List<Goods> goodsList = goodsMapper.findNewBeeMallGoodsListBySearch(pageUtil);
        int total = goodsMapper.getTotalNewBeeMallGoodsBySearch(pageUtil);
        List<SearchGoodsVO> searchGoodsVOS = new ArrayList<>();
        if (!CollectionUtils.isEmpty(goodsList)) {
            searchGoodsVOS = BeanUtil.copyList(goodsList, SearchGoodsVO.class);
            for (SearchGoodsVO newBeeMallSearchGoodsVO : searchGoodsVOS) {
                String goodsName = newBeeMallSearchGoodsVO.getGoodsName();
                String goodsIntro = newBeeMallSearchGoodsVO.getGoodsIntro();
                // 字符串过长导致文字超出的问题
                if (goodsName.length() > 28) {
                    goodsName = goodsName.substring(0, 28) + "...";
                    newBeeMallSearchGoodsVO.setGoodsName(goodsName);
                }
                if (goodsIntro.length() > 30) {
                    goodsIntro = goodsIntro.substring(0, 30) + "...";
                    newBeeMallSearchGoodsVO.setGoodsIntro(goodsIntro);
                }
            }
        }
        PageResult pageResult = new PageResult(searchGoodsVOS, total, pageUtil.getLimit(), pageUtil.getPage());
        return pageResult;
    }
}
