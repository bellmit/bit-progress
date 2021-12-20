package com.wpx.model;

import java.util.List;

/**
 * @author wpx
 * Created on 2021/1/27 14:48
 * 资源对象，由方法、url和权限列表组成
 */
public class RouteRouse {

    /**
     * 资源方法
     */
    private String method;

    /**
     * 资源url
     */
    private String url;

    /**
     * 角色列表，默认为OR，即只要匹配其中一个角色就可以通过
     */
    private List<String> role;

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<String> getRole() {
        return role;
    }

    public void setRole(List<String> role) {
        this.role = role;
    }
}
