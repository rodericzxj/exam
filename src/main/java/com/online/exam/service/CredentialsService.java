package com.online.exam.service;

import com.online.exam.controller.vo.IndexCredentialsVO;
import com.online.exam.controller.vo.IndexNewsVO;
import com.online.exam.entity.Credential;
import com.online.exam.entity.New;
import com.online.exam.util.PageQueryUtil;
import com.online.exam.util.PageResult;

import java.util.List;

public interface CredentialsService {

    /**
     * 返回固定数量的新闻(首页调用)
     *
     * @param number
     * @return
     */
    List<Credential> getCredentialsForIndex(int number, String credentialsType);
    /**
     * 根据id返回值
     *
     * @param id
     * @return
     */
    Credential getNewBeeMallCredentialsById(int id);

    /**
     * 后台分页
     *
     * @param pageUtil
     * @return
     */
    PageResult getNewBeeMallCredentialsPage(PageQueryUtil pageUtil);
    /**
     * 添加新闻
     *
     * @param credentials
     * @return
     */
    String saveNewBeeMallCredentials(Credential credentials);


    /**
     * 修改新闻信息
     *
     * @param credentials
     * @return
     */
    String updateNewBeeMallCredentials(Credential credentials);

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    Boolean deleteCredentials(Long[] ids);

}
