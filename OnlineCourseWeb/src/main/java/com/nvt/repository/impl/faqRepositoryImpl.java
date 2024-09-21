/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nvt.repository.impl;

import com.nvt.pojo.Faq;
import com.nvt.repository.faqRepository;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author thang
 */
@Repository
@Transactional
public class faqRepositoryImpl implements faqRepository {

    @Autowired
    private LocalSessionFactoryBean factory;
    
    private static final int PAGE_SIZE = 10;

    @Override
    public List<Faq> getFaqList(Map<String, String> params) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Faq> q = b.createQuery(Faq.class);
        Root<Faq> root = q.from(Faq.class);

        q.select(root);

        // Tạo danh sách các điều kiện lọc
        if (params != null) {
            List<Predicate> predicates = new ArrayList<>();
            String question = params.get("question");

            
            if (question != null && !question.isEmpty()) {
                Predicate p3 = b.like(root.get("question"), String.format("%%%s%%", question));
                predicates.add(p3);
            }

            String fromCreatedDate = params.get("fromCreatedDate");
            String toCreatedDate = params.get("toCreatedDate");
            if (fromCreatedDate != null && !fromCreatedDate.isEmpty()) {
                Date fromDate = java.sql.Date.valueOf(fromCreatedDate);
                Predicate p4 = b.greaterThanOrEqualTo(root.get("createdDate"), fromDate);
                predicates.add(p4);
            }
            if (toCreatedDate != null && !toCreatedDate.isEmpty()) {
                Date toDate = java.sql.Date.valueOf(toCreatedDate);
                Predicate p5 = b.lessThanOrEqualTo(root.get("createdDate"), toDate);
                predicates.add(p5);
            }

            // Áp dụng các điều kiện lọc vào truy vấn
            if (!predicates.isEmpty()) {
                q.where(predicates.toArray(Predicate[]::new));
                q.orderBy(b.desc(root.get("createdDate")));
            }
        }

        Query query = s.createQuery(q);

        if (params != null) {
            String page = params.get("page");
            if (page != null && !page.isEmpty()) {
                int p = Integer.parseInt(page);
                int start = (p - 1) * PAGE_SIZE;

                query.setFirstResult(start);
                query.setMaxResults(PAGE_SIZE);
            }
        }
        return query.getResultList();
    }
}


