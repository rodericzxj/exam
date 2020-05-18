package com.online.exam.service.impl;


import com.online.exam.common.ServiceResultEnum;
import com.online.exam.controller.vo.IndexCredentialsVO;
import com.online.exam.controller.vo.IndexNewsVO;
import com.online.exam.dao.CredentialsMapper;
import com.online.exam.dao.NewsMapper;
import com.online.exam.entity.Credential;
import com.online.exam.entity.New;
import com.online.exam.service.CredentialsService;
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
public class CredentialsServiceImpl implements CredentialsService {

    @Autowired
    private CredentialsMapper credentialsMapper;


    @Override
    public Credential getNewBeeMallCredentialsById(int id) {
        return credentialsMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Credential> getCredentialsForIndex(int number,String credentialsType) {
        List<Credential> indexCredentialsVOS = new ArrayList<>(number);
        List<Credential> credentials= credentialsMapper.findCredentialsByNum(number,credentialsType);
        if (!CollectionUtils.isEmpty(credentials)) {
            indexCredentialsVOS = BeanUtil.copyList(credentials, Credential.class);
        }
        return indexCredentialsVOS;
    }
    @Override
    public PageResult getNewBeeMallCredentialsPage(PageQueryUtil pageUtil) {
        List<Credential> credentialsList = credentialsMapper.findNewBeeMallCredentialsList(pageUtil);
        int total = credentialsMapper.getTotalNewBeeMallCredentials(pageUtil);
        PageResult pageResult = new PageResult(credentialsList, total, pageUtil.getLimit(), pageUtil.getPage());
        return pageResult;
    }

    @Override
    public String saveNewBeeMallCredentials(Credential credentials) {
        if (credentialsMapper.insertSelective(credentials) > 0) {
            return ServiceResultEnum.SUCCESS.getResult();
        }
        return ServiceResultEnum.DB_ERROR.getResult();
    }

    @Override
    public String updateNewBeeMallCredentials(Credential credentials) {
        Credential temp = credentialsMapper.selectByPrimaryKey(credentials.getCredentialsId());
        if (temp == null) {
            return ServiceResultEnum.DATA_NOT_EXIST.getResult();
        }
        credentials.setCredentialsTime(new Date());
        if (credentialsMapper.updateByPrimaryKeySelective(credentials) > 0) {
            return ServiceResultEnum.SUCCESS.getResult();
        }
        return ServiceResultEnum.DB_ERROR.getResult();
    }

    @Override
    public Boolean deleteCredentials(Long[] ids) {
        return credentialsMapper.deleteCredentials(ids) > 0;
    }
}
