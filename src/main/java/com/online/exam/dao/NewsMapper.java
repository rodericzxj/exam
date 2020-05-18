package com.online.exam.dao;


import com.online.exam.entity.Carousel;
import com.online.exam.entity.Goods;
import com.online.exam.entity.New;
import com.online.exam.util.PageQueryUtil;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface NewsMapper {
    New selectByPrimaryKey(int newsId);

    List<New> findNewsByNum(@Param("number") int number);

    List<New> findNewBeeMallNewsList(PageQueryUtil pageUtil);

    int getTotalNewBeeMallNews(PageQueryUtil pageUtil);

    int insertSelective(New record);

    int updateByPrimaryKeySelective(New record);

    int deleteNews(@Param("orderIds") Long[] orderIds);



}