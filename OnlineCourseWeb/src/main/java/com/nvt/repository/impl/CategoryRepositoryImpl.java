/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nvt.repository.impl;

import com.nvt.pojo.Category;
import com.nvt.repository.CategoryRepository;
import java.util.List;
import javax.persistence.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class CategoryRepositoryImpl implements CategoryRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public List<Category> getCates() {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createQuery("From Category");
        return q.getResultList();
    }

    @Override
    public void deleteCate(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        Category c = s.get(Category.class, id);
        s.delete(c);
    }

    @Override
    public void addOrUpCate(Category cate) {
        Session s = this.factory.getObject().getCurrentSession();
        if (cate.getId() != null) {
            s.update(cate);
        } else {
            s.save(cate);
        }
    }

    @Override
    public Category getCateById(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        Category c = s.get(Category.class, id);
        return c;
    }

}
