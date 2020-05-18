package com.online.exam.service;

import com.online.exam.controller.vo.IndexCarouselVO;
import com.online.exam.controller.vo.IndexNewsVO;
import com.online.exam.entity.Carousel;
import com.online.exam.entity.Goods;
import com.online.exam.entity.New;
import com.online.exam.util.PageQueryUtil;
import com.online.exam.util.PageResult;

import java.util.List;

public interface NewsService {

    /**
     * 返回固定数量的新闻(首页调用)
     *
     * @param number
     * @return
     */
    List<IndexNewsVO> getNewsForIndex(int number);

    /**
     * 根据id返回值
     *
     * @param id
     * @return
     */
    New getNewBeeMallNewsById(int id);
    /**
     * 后台分页
     *
     * @param pageUtil
     * @return
     */
    PageResult getNewBeeMallNewsPage(PageQueryUtil pageUtil);
    /**
     * 添加新闻
     *
     * @param news
     * @return
     */
    String saveNewBeeMallNews(New news);


    /**
     * 修改新闻信息
     *
     * @param news
     * @return
     */
    String updateNewBeeMallNews(New news);

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    Boolean deleteNews(Long[] ids);

}
