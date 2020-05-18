package com.online.exam.controller.admin;

import com.online.exam.common.CategoryLevelEnum;
import com.online.exam.common.Constants;
import com.online.exam.common.IndexConfigTypeEnum;
import com.online.exam.common.ServiceResultEnum;
import com.online.exam.entity.IndexConfig;
import com.online.exam.entity.New;
import com.online.exam.service.IndexConfigService;
import com.online.exam.service.NewsService;
import com.online.exam.util.PageQueryUtil;
import com.online.exam.util.Result;
import com.online.exam.util.ResultGenerator;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author rocky
 */
@Controller
@RequestMapping("/admin")
public class AdminNewsController {


    @Resource
    private NewsService newsService;

    @GetMapping("/news")
    public String newsPage(HttpServletRequest request) {
        request.setAttribute("path", "news");
        return "admin/news";
    }

    @GetMapping("/news/edit")
    public String edit(HttpServletRequest request) {
        request.setAttribute("path", "news-edit");
        return "admin/news_edit";
    }

    @GetMapping("/news/edit/{newsId}")
    public String edit(HttpServletRequest request, @PathVariable("newsId") int newsId) {
        request.setAttribute("path", "edit");
        New news = newsService.getNewBeeMallNewsById(newsId);
        if (news == null) {
            return "error/error_400";
        }
        request.setAttribute("news", news);
        request.setAttribute("path", "news-edit");
        return "admin/news_edit";
    }


    /**
     * 列表
     */
    @RequestMapping(value = "/news/list", method = RequestMethod.GET)
    @ResponseBody
    public Result list(@RequestParam Map<String, Object> params) {
        if (StringUtils.isEmpty(params.get("page")) || StringUtils.isEmpty(params.get("limit"))) {
            return ResultGenerator.genFailResult("参数异常！");
        }
        PageQueryUtil pageUtil = new PageQueryUtil(params);
        return ResultGenerator.genSuccessResult(newsService.getNewBeeMallNewsPage(pageUtil));
    }
    /**
     * 添加
     */
    @RequestMapping(value = "/news/save", method = RequestMethod.POST)
    @ResponseBody
    public Result save(@RequestBody New news) {
        if (StringUtils.isEmpty(news.getNewsType())
                || StringUtils.isEmpty(news.getNewsTitle())
                || StringUtils.isEmpty(news.getNewsAuthor())
                || Objects.isNull(news.getNewsValue())) {
            return ResultGenerator.genFailResult("参数异常！");
        }
        String result = newsService.saveNewBeeMallNews(news);
        if (ServiceResultEnum.SUCCESS.getResult().equals(result)) {
            return ResultGenerator.genSuccessResult();
        } else {
            return ResultGenerator.genFailResult(result);
        }
    }

    /**
     * 修改
     */
    @RequestMapping(value = "/news/update", method = RequestMethod.POST)
    @ResponseBody
    public Result update(@RequestBody New  news) {
        if (Objects.isNull(news.getNewsType())
                || StringUtils.isEmpty(news.getNewsTitle())
                || StringUtils.isEmpty(news.getNewsAuthor())
                || Objects.isNull(news.getNewsValue())) {
            return ResultGenerator.genFailResult("参数异常！");
        }
        String result = newsService.updateNewBeeMallNews(news);
        if (ServiceResultEnum.SUCCESS.getResult().equals(result)) {
            return ResultGenerator.genSuccessResult();
        } else {
            return ResultGenerator.genFailResult(result);
        }
    }

    /**
     * 批量删除
     */
    @RequestMapping(value = "/news/delete", method = RequestMethod.PUT)
    @ResponseBody
    public Result delete(@RequestBody Long[] ids) {
        if (ids.length < 1) {
            return ResultGenerator.genFailResult("参数异常！");
        }
        if (newsService.deleteNews(ids)) {
            return ResultGenerator.genSuccessResult();
        } else {
            return ResultGenerator.genFailResult("修改失败");
        }
    }
}