package com.start.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.start.entities.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student,Integer>{

}
