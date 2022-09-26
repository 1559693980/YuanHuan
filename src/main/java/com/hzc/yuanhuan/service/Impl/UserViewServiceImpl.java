package com.hzc.yuanhuan.service.Impl;

import com.hzc.yuanhuan.entity.UserView;
import com.hzc.yuanhuan.repository.UserViewRepository;
import com.hzc.yuanhuan.service.UserViewService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.*;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.*;

@Service
public class UserViewServiceImpl implements UserViewService {

    @Resource
    private UserViewRepository userViewRepository;

    /**
     * 实体管理对象
     */
    @PersistenceContext
    EntityManager entityManager;

    @Override
    public Page<UserView> findAllPage(Integer page, Integer size, UserView userView, Date start, Date end) {
        String countSelectSql = "select count(*) from user_view po where 1=1 ";

        String selectSql = "from user_view po where 1=1 ";

        Map<String, Object> params = new HashMap<>();
        StringBuilder whereSql = new StringBuilder();

        if (StringUtils.isNotBlank(userView.getName())){
            whereSql.append(" and po.name like :name ");
            params.put("name",userView.getName());
        }
        if (StringUtils.isNotBlank(userView.getUsername())){
            whereSql.append(" and po.username like :username ");
            params.put("username",userView.getUsername());
        }
        if (StringUtils.isNotBlank(userView.getUsername())){
            whereSql.append(" and po.create_time between :start and :end");
            params.put("start",start);
            params.put("end",end);
        }
        if (StringUtils.isNotBlank(userView.getPositionName())){
            whereSql.append(" and po.position_name like :positionName ");
            params.put("positionName",userView.getPositionName());
        }
        if (StringUtils.isNotBlank(userView.getDepName())){
            whereSql.append(" and po.dep_name like :depName ");
            params.put("depName",userView.getDepName());
        }
        if (StringUtils.isNotBlank(userView.getTel())){
            whereSql.append(" and po.tel like :tel ");
            params.put("tel",userView.getTel());
        }
        String countSql = countSelectSql + whereSql;
        Query countQuery = this.entityManager.createQuery(countSql, Long.class);
        this.setParameters(countQuery, params);
        Long count = (Long) countQuery.getSingleResult();

        String querySql = selectSql + whereSql;
        Query query = this.entityManager.createQuery(querySql, UserView.class);
        this.setParameters(query, params);
        query.setFirstResult(page - 1);
        query.setMaxResults(size);
        List<UserView> incomeDailyList = query.getResultList();
        Pageable pageable = PageRequest.of(page -1, size);
        return new PageImpl<>(incomeDailyList, pageable, count);
    }

    /**
     * 给hql参数设置值
     *
     * @param query  查询
     * @param params 参数
     */
    private void setParameters(Query query, Map<String, Object> params) {
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            query.setParameter(entry.getKey(), entry.getValue());
        }
    }
}
