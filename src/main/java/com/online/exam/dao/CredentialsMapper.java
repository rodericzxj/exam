package com.online.exam.dao;


import com.online.exam.entity.Credential;
import com.online.exam.entity.New;
import com.online.exam.util.PageQueryUtil;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CredentialsMapper {
    Credential selectByPrimaryKey(int credentialsId);
    List<Credential> findCredentialsByNum(@Param("number") int number, @Param("credentialsType") String credentialsType);

    List<Credential> findNewBeeMallCredentialsList(PageQueryUtil pageUtil);

    int getTotalNewBeeMallCredentials(PageQueryUtil pageUtil);

    int insertSelective(Credential record);

    int updateByPrimaryKeySelective(Credential record);

    int deleteCredentials(@Param("orderIds") Long[] orderIds);
}