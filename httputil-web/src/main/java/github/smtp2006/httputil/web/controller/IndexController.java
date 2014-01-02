/**
 * Copyright (c) 2013.
 */
package github.smtp2006.httputil.web.controller;

import github.smtp2006.httputil.manager.ServiceManager;
import github.smtp2006.httputil.web.view.JsonView;
import github.smtp2006.httputil.web.view.MenuJsonView;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

/**
 * @email hua.wanghuawh@alibaba-inc.com;smtp2006@126.com
 * 
 */
@Controller
@RequestMapping(value = "/index")
public class IndexController extends AbstractController {

    @Autowired
    private ServiceManager serviceManager;

    @RequestMapping("/menu.json")
    public MappingJackson2JsonView menu() {

        JsonView jsonView = new MenuJsonView(serviceManager.listMenus().getAll());
        return toJson(jsonView);
    }
}
