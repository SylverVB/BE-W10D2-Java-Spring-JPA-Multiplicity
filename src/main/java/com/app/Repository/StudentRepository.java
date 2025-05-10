package com.app.Repository;

import com.app.Model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * JPA Repository interface for the Student entity
 */
public interface StudentRepository extends JpaRepository<Student, Long> {
}