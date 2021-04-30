package com.start.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.start.entities.Teacher;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher,Integer> {

}
