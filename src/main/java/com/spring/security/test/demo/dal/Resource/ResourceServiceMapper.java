package com.spring.security.test.demo.dal.Resource;


import com.spring.security.test.demo.bean.ManagerRoleModel;
import com.spring.security.test.demo.bean.ResourceModel;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ResourceServiceMapper {
    /**
     * 查询资源节点
     * @param roleCode
     * @return
     */

    List<ResourceModel> getRoleId(Integer roleCode);

    /**
     * 查询角色id
     * @param manager
     * @return
     */
    List<ManagerRoleModel> selectRoleCode(String manager);
}
