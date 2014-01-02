/**
 * Copyright (c) 2013.
 */
package github.smtp2006.httputil.web.controller;

import github.smtp2006.httputil.web.view.JsonView;

import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

/**
 * @email hua.wanghuawh@alibaba-inc.com;smtp2006@126.com
 * 
 */
public abstract class AbstractController {

    public MappingJackson2JsonView toJson(JsonView jsonView) {

        MappingJackson2JsonView resultView = new MappingJackson2JsonView();
        resultView.addStaticAttribute("data", jsonView.getData());
        return resultView;
    }
}
