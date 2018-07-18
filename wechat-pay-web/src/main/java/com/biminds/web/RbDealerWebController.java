package com.biminds.web;

import com.biminds.framework.mvc.BaseController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;


@Controller
@RequestMapping(value = "/web/dealer")
public class RbDealerWebController extends BaseController {

    protected static final Logger logger = LoggerFactory.getLogger(RbDealerWebController.class);

    /**
     * 商户管理
     *
     * @param request
     * @param menuId
     * @return
     */
    @RequestMapping(value = "/list")
    public String goDealerList(HttpServletRequest request, String menuId) {
        logger.debug("商户管理");
        request.setAttribute("menuId", menuId);
        return "dealer/list";
    }

}
