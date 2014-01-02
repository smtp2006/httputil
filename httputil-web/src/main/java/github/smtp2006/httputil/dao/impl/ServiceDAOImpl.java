/**
 * Copyright (c) 2013.
 */
package github.smtp2006.httputil.dao.impl;

import github.smtp2006.httputil.NamespaceRequestHolder;
import github.smtp2006.httputil.dao.ServiceDAO;
import github.smtp2006.httputil.support.RequestLoader;

/**
 * @email hua.wanghuawh@alibaba-inc.com;smtp2006@126.com
 * 
 */
public class ServiceDAOImpl implements ServiceDAO {

    private String configFile;

    private RequestLoader requestLoader;

    /*
     * (non-Javadoc)
     * 
     * @see github.smtp2006.httputil.ServiceDAO#listMenus()
     */
    @Override
    public NamespaceRequestHolder listMenus() {

        NamespaceRequestHolder nrh = requestLoader.load(configFile);
        return nrh;
    }

    
    public void setConfigFile(String configFile) {
    
        this.configFile = configFile;
    }

    
    public void setRequestLoader(RequestLoader requestLoader) {
    
        this.requestLoader = requestLoader;
    }
    
}
