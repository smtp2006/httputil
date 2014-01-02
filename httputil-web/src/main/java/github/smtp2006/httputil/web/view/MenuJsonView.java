/**
 * Copyright (c) 2013.
 */
package github.smtp2006.httputil.web.view;

import github.smtp2006.httputil.Request;
import github.smtp2006.httputil.RequestHolder;
import github.smtp2006.httputil.utils.MapUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * @email hua.wanghuawh@alibaba-inc.com;smtp2006@126.com
 * 
 */
public class MenuJsonView extends AbstractJsonView {

    private Map<String, RequestHolder> holder;

    /**
     * @param holder
     */
    public MenuJsonView(Map<String, RequestHolder> holder) {

        super();
        this.holder = holder;
    }

    /*
     * (non-Javadoc)
     * 
     * @see github.smtp2006.httputil.web.view.JsonView#getData()
     */
    @Override
    public Object getData() {

        List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();
        for (Map.Entry<String, RequestHolder> entry : holder.entrySet()) {
            Map<String, Object> __rh = new TreeMap<String, Object>();
            MapUtils.putAsNotNull(__rh, "description", entry.getValue().getDescription());
            MapUtils.putAsNotNull(__rh, "host", entry.getValue().getHost());
            MapUtils.putAsNotNull(__rh, "port", entry.getValue().getPort());
            MapUtils.putAsNotNull(__rh, "scheme", entry.getValue().getScheme());
            List<Map<String, Object>> __requests = new ArrayList<Map<String, Object>>();
            for (Request req : entry.getValue().getAll()) {
                Map<String, Object> __req = new TreeMap<String, Object>();
                MapUtils.putAsNotNull(__req, "description", req.getDescription());
                MapUtils.putAsNotNull(__req, "uri", req.getUri());
                MapUtils.putAsNotNull(__req, "method", req.getMethod());
                __requests.add(__req);
            }
            MapUtils.putAsNotNull(__rh, "list", __requests);
            data.add(__rh);
        }
        return data;
    }
}
