package com.biminds.web;

import com.biminds.framework.mvc.BaseController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;


@Controller
@RequestMapping(value = "/web/user")
public class RbUserWebController extends BaseController {

    /**
     * 日志
     */
    protected static final Logger logger = LoggerFactory.getLogger(RbUserWebController.class);


    /**
     * 用户管理
     *
     * @param request
     * @param menuId
     * @return
     */
    @RequestMapping(value = "/list")
    public String goUserList(HttpServletRequest request, String menuId) {
        logger.debug("用户管理");
        request.setAttribute("menuId", menuId);
        return "user/list";
    }


}
