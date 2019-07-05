package com.spring.security.test.demo.service;

import com.spring.security.test.demo.bean.ResourceModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ResourceService {

    List<ResourceModel> queryResource(Integer roleCode);

    Integer queryRoleInfoByManger(String manager);
}
