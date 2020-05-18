package com.online.exam.controller.credential;


import com.online.exam.common.Constants;
import com.online.exam.common.IndexConfigTypeEnum;
import com.online.exam.controller.vo.*;
import com.online.exam.entity.Credential;
import com.online.exam.entity.Goods;
import com.online.exam.entity.New;
import com.online.exam.service.*;
import com.sun.org.apache.bcel.internal.generic.NEW;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class CredentialIndexController {

    @Resource
    private CarouselService carouselService;
    @Resource
    private NewsService newsService;
    @Resource
    private CredentialsService credentialsService;

    @GetMapping({"credential/index","credential/","credential/index.html"})
    public String indexPage(HttpServletRequest request) {

        List<IndexCarouselVO> carousels = carouselService.getCarouselsForIndex(Constants.INDEX_CAROUSEL_NUMBER);
        List<IndexNewsVO> news = newsService.getNewsForIndex(Constants.INDEX_NEWS_NUMBER);
        List<Credential> credentials1 = credentialsService.getCredentialsForIndex(Constants.INDEX_CAROUSEL_NUMBER,"工程证书");
        List<Credential> credentials2 = credentialsService.getCredentialsForIndex(Constants.INDEX_CAROUSEL_NUMBER,"财经证书");
        List<Credential> credentials3 = credentialsService.getCredentialsForIndex(Constants.INDEX_CAROUSEL_NUMBER,"外语证书");
        request.setAttribute("carousels", carousels);//轮播图
        request.setAttribute("news", news);
        request.setAttribute("credential1", credentials1);
        request.setAttribute("credential2", credentials2);
        request.setAttribute("credential3", credentials3);
        return "credential/index";
    }
    @GetMapping({"/credential/news/{newsId}","/news/{newsId}"})
    public String searchnews(HttpServletRequest request, @PathVariable("newsId") int newsId) {
        New news= newsService.getNewBeeMallNewsById(newsId);
        request.setAttribute("news", news);
        return "credential/news";
    }
    @GetMapping({"/credential/credentials/{credentialsId}","/credentials/{credentialsId}"})
    public String searchcredentials(HttpServletRequest request, @PathVariable("credentialsId") int credentialsId) {
        Credential credential= credentialsService.getNewBeeMallCredentialsById(credentialsId);
        request.setAttribute("credentials", credential);
        return "credential/credential";
    }
}
