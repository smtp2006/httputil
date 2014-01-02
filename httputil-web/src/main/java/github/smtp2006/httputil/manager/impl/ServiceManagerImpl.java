/**
 * Copyright (c) 2013.
 */
package github.smtp2006.httputil.manager.impl;

import github.smtp2006.httputil.NamespaceRequestHolder;
import github.smtp2006.httputil.dao.ServiceDAO;
import github.smtp2006.httputil.manager.ServiceManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @email hua.wanghuawh@alibaba-inc.com;smtp2006@126.com
 *
 */
@Service
public class ServiceManagerImpl implements ServiceManager {
    @Autowired
    private ServiceDAO serviceDAO;
    @Override
    public NamespaceRequestHolder listMenus() {

        return serviceDAO.listMenus();
    }

}
