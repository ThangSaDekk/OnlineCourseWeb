/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nvt.repository.impl;

import com.nvt.pojo.User;
import com.nvt.repository.UserRepository;
import java.util.ArrayList;
import java.util.Date;
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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author admin
 */
@Repository
@Transactional
public class UserRepositoryImpl implements UserRepository {

    @Autowired
    private LocalSessionFactoryBean factory;
    @Autowired
    private BCryptPasswordEncoder passEncoder;

    private static final int PAGE_SIZE = 10;

    @Override
    public User getUserByUsername(String username) {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createNamedQuery("User.findByUsername");
        q.setParameter("username", username);

        return (User) q.getSingleResult();

    }

    @Override
    public boolean authUser(String username, String password) {
        User u = this.getUserByUsername(username);

        return this.passEncoder.matches(password, u.getPassword());
    }

    @Override
    public User addUser(User u) {
        Session s = this.factory.getObject().getCurrentSession();

        if (u.getId() != null) {
            s.update(u);

        } else {
            s.save(u);
        }
        return u;
    }

    @Override
    public User getUserByID(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        return s.get(User.class, id);
    }

    @Override
    public void deleteUserInstructor(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        User u = this.getUserByID(id);
        s.delete(u);

    }

    @Override
    public long countUsers(Map<String, String> params) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Long> q = b.createQuery(Long.class);
        Root<User> root = q.from(User.class);

        // Tạo truy vấn đếm
        q.select(b.count(root));

        // Tạo danh sách các điều kiện lọc
        if (params != null) {
            List<Predicate> predicates = new ArrayList<>();
            String role1 = params.get("role1");
            String role2 = params.get("role2");

            if (role1 != null && !role1.isEmpty()) {
                Predicate p1 = b.equal(root.get("userRole"), role1);
                predicates.add(p1);
            }
            if (role2 != null && !role2.isEmpty()) {
                Predicate p2 = b.equal(root.get("userRole"), role2);
                predicates.add(p2);
            }

            // Áp dụng các điều kiện lọc vào truy vấn
            if (!predicates.isEmpty()) {
                q.where(predicates.toArray(Predicate[]::new));
            }
        }

        Query query = s.createQuery(q);
        return (long) query.getSingleResult();
    }

    @Override
    public List<User> getUserList(Map<String, String> params) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<User> q = b.createQuery(User.class);
        Root<User> root = q.from(User.class);

        // Tạo truy vấn đếm
        q.select(root);

        // Tạo danh sách các điều kiện lọc
        if (params != null) {
            List<Predicate> predicates = new ArrayList<>();
            String userRole = params.get("userRole");
            String firstName = params.get("firstName");
            String lastName = params.get("lastName");
            String active = params.get("active");

            if (userRole != null && !userRole.isEmpty()) {
                Predicate p1 = b.equal(root.get("userRole"), userRole);
                predicates.add(p1);
            }
            if (firstName != null && !firstName.isEmpty()) {
                Predicate p2 = b.like(root.get("firstName"), String.format("%%%s%%", firstName));
                predicates.add(p2);
            }
            if (lastName != null && !lastName.isEmpty()) {
                Predicate p3 = b.like(root.get("lastName"), String.format("%%%s%%", lastName));
                predicates.add(p3);
            }
            if (active != null && !active.isEmpty()) {
                Predicate p4 = b.equal(root.get("active"), Boolean.parseBoolean(active));
                predicates.add(p4);
            }

            String fromCreatedDate = params.get("fromCreatedDate");
            String toCreatedDate = params.get("toCreatedDate");
            if (fromCreatedDate != null && !fromCreatedDate.isEmpty()) {
                Date fromDate = java.sql.Date.valueOf(fromCreatedDate);
                Predicate p5 = b.greaterThanOrEqualTo(root.get("createdDate"), fromDate);
                predicates.add(p5);
            }
            if (toCreatedDate != null && !toCreatedDate.isEmpty()) {
                Date toDate = java.sql.Date.valueOf(toCreatedDate);
                Predicate p6 = b.lessThanOrEqualTo(root.get("createdDate"), toDate);
                predicates.add(p6);
            }

            // Lọc theo ngày cập nhật
            String fromUpdatedDate = params.get("fromUpdatedDate");
            String toUpdatedDate = params.get("toUpdatedDate");
            if (fromUpdatedDate != null && !fromUpdatedDate.isEmpty()) {
                Date fromDate = java.sql.Date.valueOf(fromUpdatedDate);
                Predicate p7 = b.greaterThanOrEqualTo(root.get("updatedDate"), fromDate);
                predicates.add(p7);
            }
            if (toUpdatedDate != null && !toUpdatedDate.isEmpty()) {
                Date toDate = java.sql.Date.valueOf(toUpdatedDate);
                Predicate p8 = b.lessThanOrEqualTo(root.get("updatedDate"), toDate);
                predicates.add(p8);
            }

            // Áp dụng các điều kiện lọc vào truy vấn
            if (!predicates.isEmpty()) {
                q.where(predicates.toArray(Predicate[]::new));
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
