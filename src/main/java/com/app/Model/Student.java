package com.app.Model;

import lombok.*;
import jakarta.persistence.*;

/**
 * JPA entity representing a student. This entity is associated with the Classroom entity 
 * using a @ManyToOne relationship. The corresponding database table will include 
 * a foreign key in the Student table that references the Classroom table.
 *
 * The mapping includes (fetch = FetchType.EAGER), which specifies that the related Classroom 
 * entity is loaded immediately when the Student entity is retrieved. Eager fetching is 
 * typically used when related entities are accessed together often, whereas lazy fetching 
 * is better suited for accessing a single entity without its related data. The choice of fetch 
 * strategy should align with the application's data access patterns for optimal efficiency.
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private double grade;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "classroomFK")
    private Classroom classroom;

    public Student(String name, double grade) {
        this.name = name;
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", grade=" + grade +
                ", classroom=" + classroom +
                '}';
    }
}