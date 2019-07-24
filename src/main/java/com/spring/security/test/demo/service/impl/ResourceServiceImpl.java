package com.spring.security.test.demo.service.impl;


import com.spring.security.test.demo.bean.ManagerRoleModel;
import com.spring.security.test.demo.bean.ResourceModel;
import com.spring.security.test.demo.dal.Resource.ResourceServiceMapper;
import com.spring.security.test.demo.service.ResourceService;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class ResourceServiceImpl implements ResourceService {
//    @Autowired
//    RedissonClient redissonClient;
//    @Autowired
//    ResourceServiceMapper serviceMapper;
    static final String REDISKEY = "REDISKEY";
    @Override
    public List<ResourceModel> queryResource(Integer roleCode) {
//        RBucket<List<ResourceModel>> url = redissonClient.getBucket(REDISKEY + roleCode);
////        //缓存中获取信息
////        if (url.isExists()) {
////            return url.get();
////        } else {
////            //TODO 查询数据库
////            List<ResourceModel> roleId = serviceMapper.getRoleId(roleCode);
////            RBucket<List<ResourceModel>> rBucket = redissonClient.getBucket(REDISKEY + roleCode);
////            rBucket.set(roleId, 60 * 60 * 2, TimeUnit.SECONDS);
////            return roleId;
////        }
        return null;
    }
    @Override
    public Integer queryRoleInfoByManger(String manager) {
        //查询role_code
//        Integer roleCode=null;
//        List<ManagerRoleModel> list = serviceMapper.selectRoleCode(manager);
//        if(!CollectionUtils.isEmpty(list)){
//             roleCode = list.get(0).getRoleCode();
//        }
//        return roleCode;
        return 0;
    }
}
