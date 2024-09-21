/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nvt.pojo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author thang
 */
@Entity
@Table(name = "grading")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Grading.findAll", query = "SELECT g FROM Grading g"),
    @NamedQuery(name = "Grading.findById", query = "SELECT g FROM Grading g WHERE g.id = :id"),
    @NamedQuery(name = "Grading.findByGrade", query = "SELECT g FROM Grading g WHERE g.grade = :grade"),
    @NamedQuery(name = "Grading.findByGradedDate", query = "SELECT g FROM Grading g WHERE g.gradedDate = :gradedDate")})
public class Grading implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "grade")
    private double grade;
    @Lob
    @Size(max = 65535)
    @Column(name = "feedback")
    private String feedback;
    @Basic(optional = false)
    @NotNull
    @Column(name = "graded_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date gradedDate;
    @JoinColumn(name = "instructor_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Instructor instructorId;
    @JoinColumn(name = "submission_id", referencedColumnName = "id")
    @OneToOne(optional = false)
    private Submission submissionId;

    public Grading() {
    }

    public Grading(Integer id) {
        this.id = id;
    }

    public Grading(Integer id, double grade, Date gradedDate) {
        this.id = id;
        this.grade = grade;
        this.gradedDate = gradedDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public Date getGradedDate() {
        return gradedDate;
    }

    public void setGradedDate(Date gradedDate) {
        this.gradedDate = gradedDate;
    }

    public Instructor getInstructorId() {
        return instructorId;
    }

    public void setInstructorId(Instructor instructorId) {
        this.instructorId = instructorId;
    }

    public Submission getSubmissionId() {
        return submissionId;
    }

    public void setSubmissionId(Submission submissionId) {
        this.submissionId = submissionId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Grading)) {
            return false;
        }
        Grading other = (Grading) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.group8.pojo.Grading[ id=" + id + " ]";
    }
    
}
