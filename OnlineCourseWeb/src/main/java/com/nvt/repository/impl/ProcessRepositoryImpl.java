/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nvt.repository.impl;

import com.nvt.pojo.Process;
import com.nvt.repository.ProcessRepository;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.persistence.NoResultException;
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
public class ProcessRepositoryImpl implements ProcessRepository {

    private static final int PAGE_SIZE = 10;

    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public double checkProcess(int userId, int contentId) {
        Session session = this.factory.getObject().getCurrentSession();

        if (userId == 0) {
            return -1;
        }

        // Sử dụng CriteriaBuilder để tạo truy vấn
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Process> cq = cb.createQuery(Process.class);
        Root<Process> root = cq.from(Process.class);

        // Xác định điều kiện WHERE
        cq.select(root)
                .where(
                        cb.equal(root.get("contentId"), contentId),
                        cb.equal(root.get("userId"), userId)
                );

        // Thực thi truy vấn và lấy kết quả
        try {
            Process process = session.createQuery(cq).getSingleResult();
            // Nếu tìm thấy Process, trả về giá trị của point
            return process.getPoint();
        } catch (NoResultException e) {
            // Nếu không tìm thấy Process, trả về -1
            return -1;
        }
    }

    @Override
    public void addUpProcess(Process process) {
        Session s = this.factory.getObject().getCurrentSession();
        if (process.getId() != null) {
            s.update(process);
        } else {
            s.save(process);
        }
    }

    @Override
    public List<Process> getProcessList(Map<String, String> params) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Process> q = b.createQuery(Process.class);
        Root<Process> root = q.from(Process.class);

        // Tạo truy vấn đếm
        q.select(root);

        // Tạo danh sách các điều kiện lọc
        if (params != null) {
            List<Predicate> predicates = new ArrayList<>();
            String point = params.get("point");
            String lastName = params.get("lastName");
            String firstName = params.get("firstName");
            String courseTitle = params.get("courseTitle");
            String contentTitle = params.get("contentTitle");

            if (point != null && !point.isEmpty()) {
                Predicate p1 = b.equal(root.get("point"), point);
                predicates.add(p1);
            }
            if (lastName != null && !lastName.isEmpty()) {
                Predicate p2 = b.like(root.get("userId").get("lastName"), String.format("%%%s%%", lastName));
                predicates.add(p2);
            }

            if (firstName != null && !firstName.isEmpty()) {
                Predicate p8 = b.like(root.get("userId").get("firstName"), String.format("%%%s%%", firstName));
                predicates.add(p8);
            }
            if (courseTitle != null && !courseTitle.isEmpty()) {
                Predicate p3 = b.like(root.get("contentId").get("courseId").get("title"), String.format("%%%s%%", courseTitle));
                predicates.add(p3);
            }
            if (contentTitle != null && !contentTitle.isEmpty()) {
                Predicate p9 = b.like(root.get("contentId").get("title"), String.format("%%%s%%", contentTitle));
                predicates.add(p9);
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
