package com.portal.repository;

import com.portal.entities.Complaint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//@Repository annotation is used to indicate that the class provides the mechanism for storage, retrieval, search, update and delete operation on objects

public interface IComplaintRepository extends JpaRepository<Complaint, Integer> {


}
