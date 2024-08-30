/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.group8.repository.impl;

import com.group8.pojo.Invoice;
import com.group8.repository.InvoiceRepository;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
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
public class InvoiceRepositoryImpl implements InvoiceRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public void addUpInvoice(Invoice invoice) {
        Session s = this.factory.getObject().getCurrentSession();
        if (invoice.getId() != null) {
            s.update(invoice);
        } else {
            s.save(invoice);
        }
    }

    @Override
    public Invoice checkrequestId(String RequestId) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder cb = s.getCriteriaBuilder();
        CriteriaQuery<Invoice> cq = cb.createQuery(Invoice.class);
        Root<Invoice> invoice = cq.from(Invoice.class);

        // Đếm số lượng Invoice có transactionId tương ứng
        cq.where(cb.equal(invoice.get("referenceCode"), RequestId));

        Query query = s.createQuery(cq);
        return (Invoice) query.getSingleResult(); 
    }

}
