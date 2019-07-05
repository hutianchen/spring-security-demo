package com.spring.security.test.demo.service;

import com.spring.security.test.demo.bean.ResourceModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.Set;
import java.util.List;

/**
 * @author hutianchen
 * @Desc 后期该方法移入资源服务中心、结合oauth2.0使用
 */
@Component("rbacauthorityservice")
public class RbacAuthorityService {

    @Autowired
    ResourceService resourceService;

    public boolean hasPermission(HttpServletRequest request, Authentication authentication) {

        Object userInfo = authentication.getPrincipal();

        boolean hasPermission  = false;

        if (userInfo instanceof UserDetails) {

            String username = ((UserDetails) userInfo).getUsername();
            ((UserDetails) userInfo).getPassword();
            Integer rolecode = resourceService.queryRoleInfoByManger(username);
            List<ResourceModel> resourceModels = resourceService.queryResource(rolecode);
            AntPathMatcher antPathMatcher = new AntPathMatcher();
            for (ResourceModel model : resourceModels) {
                if (antPathMatcher.match(model.getUri(), request.getRequestURI())) {
                    hasPermission = true;
                    break;
                }
            }

            return hasPermission;
        } else {
            return false;
        }
    }
}
