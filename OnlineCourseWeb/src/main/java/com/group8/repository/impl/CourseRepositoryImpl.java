/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.group8.repository.impl;

import com.group8.pojo.Course;
import com.group8.repository.CourseRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
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
public class CourseRepositoryImpl implements CourseRepository {

    private static final int PAGE_SIZE = 8;

    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public List<Course> getCourse(Map<String, String> params) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Course> q = b.createQuery(Course.class);
        Root root = q.from(Course.class);
        q.select(root);
        if (params != null) {
            List<Predicate> predicates = new ArrayList<>();
            String kw = params.get("kw");
            String fromPrice = params.get("fromPrice");
            String toPrice = params.get("toPrice");
            String cateId = params.get("cateId");
            if (kw != null && !kw.isEmpty()) {
                Predicate p1 = b.like(root.get("title"), String.format("%%%s%%", kw));
                predicates.add(p1);
            }
            if (fromPrice != null && !fromPrice.isEmpty()) {
                Predicate p2 = b.greaterThanOrEqualTo(root.get("price"), Double.parseDouble(fromPrice));
                predicates.add(p2);
            }

            if (toPrice != null && !toPrice.isEmpty()) {
                Predicate p3 = b.lessThanOrEqualTo(root.get("price"), Double.parseDouble(toPrice));
                predicates.add(p3);
            }
            if (cateId != null && !cateId.isEmpty()) {
                Predicate p4 = b.equal(root.get("categoryId"), Integer.parseInt(cateId));
                predicates.add(p4);
            }
            q.where(predicates.toArray(new Predicate[0]));
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

    @Override
    public void addOrUpCourse(Course course) {
        Session s = this.factory.getObject().getCurrentSession();
        if (course.getId() != null) {
            s.update(course);
        } else {
            s.save(course);
        }
    }

   

}
