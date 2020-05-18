package com.online.exam.controller.admin;

import com.online.exam.common.ServiceResultEnum;
import com.online.exam.entity.Credential;
import com.online.exam.entity.New;
import com.online.exam.service.CredentialsService;
import com.online.exam.service.NewsService;
import com.online.exam.util.PageQueryUtil;
import com.online.exam.util.Result;
import com.online.exam.util.ResultGenerator;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Objects;

/**
 * @author rocky
 */
@Controller
@RequestMapping("/admin")
public class AdminCredentialsController {


    @Resource
    private CredentialsService credentialsService;

    @GetMapping("/credentials")
    public String credentialsPage(HttpServletRequest request) {
        request.setAttribute("path", "credentials");
        return "admin/credentials";
    }

    @GetMapping("/credentials/edit")
    public String edit(HttpServletRequest request) {
        request.setAttribute("path", "credentials-edit");
        return "admin/credentials_edit";
    }

    @GetMapping("/credentials/edit/{credentialsId}")
    public String edit(HttpServletRequest request, @PathVariable("credentialsId") int credentialsId) {
        request.setAttribute("path", "edit");
        Credential credentials = credentialsService.getNewBeeMallCredentialsById(credentialsId);
        if (credentials == null) {
            return "error/error_400";
        }
        request.setAttribute("credentials", credentials);
        request.setAttribute("path", "credentials-edit");
        return "admin/credentials_edit";
    }


    /**
     * 列表
     */
    @RequestMapping(value = "/credentials/list", method = RequestMethod.GET)
    @ResponseBody
    public Result list(@RequestParam Map<String, Object> params) {
        if (StringUtils.isEmpty(params.get("page")) || StringUtils.isEmpty(params.get("limit"))) {
            return ResultGenerator.genFailResult("参数异常！");
        }
        PageQueryUtil pageUtil = new PageQueryUtil(params);
        return ResultGenerator.genSuccessResult(credentialsService.getNewBeeMallCredentialsPage(pageUtil));
    }
    /**
     * 添加
     */
    @RequestMapping(value = "/credentials/save", method = RequestMethod.POST)
    @ResponseBody
    public Result save(@RequestBody Credential credentials) {
        if (StringUtils.isEmpty(credentials.getCredentialsType())
                || StringUtils.isEmpty(credentials.getCredentialsTitle())
                || StringUtils.isEmpty(credentials.getCredentialsAuthor())
                || Objects.isNull(credentials.getCredentialsValue())) {
            return ResultGenerator.genFailResult("参数异常！");
        }
        String result = credentialsService.saveNewBeeMallCredentials(credentials);
        if (ServiceResultEnum.SUCCESS.getResult().equals(result)) {
            return ResultGenerator.genSuccessResult();
        } else {
            return ResultGenerator.genFailResult(result);
        }
    }

    /**
     * 修改
     */
    @RequestMapping(value = "/credentials/update", method = RequestMethod.POST)
    @ResponseBody
    public Result update(@RequestBody Credential  credentials) {
        if (Objects.isNull(credentials.getCredentialsType())
                || StringUtils.isEmpty(credentials.getCredentialsTitle())
                || StringUtils.isEmpty(credentials.getCredentialsAuthor())
                || Objects.isNull(credentials.getCredentialsValue())) {
            return ResultGenerator.genFailResult("参数异常！");
        }
        String result = credentialsService.updateNewBeeMallCredentials(credentials);
        if (ServiceResultEnum.SUCCESS.getResult().equals(result)) {
            return ResultGenerator.genSuccessResult();
        } else {
            return ResultGenerator.genFailResult(result);
        }
    }

    /**
     * 批量删除
     */
    @RequestMapping(value = "/credentials/delete", method = RequestMethod.PUT)
    @ResponseBody
    public Result delete(@RequestBody Long[] ids) {
        if (ids.length < 1) {
            return ResultGenerator.genFailResult("参数异常！");
        }
        if (credentialsService.deleteCredentials(ids)) {
            return ResultGenerator.genSuccessResult();
        } else {
            return ResultGenerator.genFailResult("修改失败");
        }
    }
}