package com.online.exam.service.impl;


import com.online.exam.common.ServiceResultEnum;
import com.online.exam.controller.vo.IndexCarouselVO;
import com.online.exam.controller.vo.IndexNewsVO;
import com.online.exam.dao.CarouselMapper;
import com.online.exam.dao.NewsMapper;
import com.online.exam.entity.Carousel;
import com.online.exam.entity.Goods;
import com.online.exam.entity.New;
import com.online.exam.service.CarouselService;
import com.online.exam.service.NewsService;
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
public class NewsServiceImpl implements NewsService {

    @Autowired
    private NewsMapper newsMapper;


    @Override
    public New getNewBeeMallNewsById(int id) {
        return newsMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<IndexNewsVO> getNewsForIndex(int number) {
        List<IndexNewsVO> indexNewsVOS = new ArrayList<>(number);
        List<New> news = newsMapper.findNewsByNum(number);
        if (!CollectionUtils.isEmpty(news)) {
            indexNewsVOS = BeanUtil.copyList(news, IndexNewsVO.class);
        }
        return indexNewsVOS;
    }

    @Override
    public PageResult getNewBeeMallNewsPage(PageQueryUtil pageUtil) {
        List<New> newsList = newsMapper.findNewBeeMallNewsList(pageUtil);
        int total = newsMapper.getTotalNewBeeMallNews(pageUtil);
        PageResult pageResult = new PageResult(newsList, total, pageUtil.getLimit(), pageUtil.getPage());
        return pageResult;
    }

    @Override
    public String saveNewBeeMallNews(New news) {
        if (newsMapper.insertSelective(news) > 0) {
            return ServiceResultEnum.SUCCESS.getResult();
        }
        return ServiceResultEnum.DB_ERROR.getResult();
    }

    @Override
    public String updateNewBeeMallNews(New news) {
        New temp = newsMapper.selectByPrimaryKey(news.getNewsId());
        if (temp == null) {
            return ServiceResultEnum.DATA_NOT_EXIST.getResult();
        }
        news.setNewsTime(new Date());
        if (newsMapper.updateByPrimaryKeySelective(news) > 0) {
            return ServiceResultEnum.SUCCESS.getResult();
        }
        return ServiceResultEnum.DB_ERROR.getResult();
    }

    @Override
    public Boolean deleteNews(Long[] ids) {
        return newsMapper.deleteNews(ids) > 0;
    }
}
