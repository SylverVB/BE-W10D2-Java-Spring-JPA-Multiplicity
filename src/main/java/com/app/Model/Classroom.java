package com.app.Model;

import lombok.*;

import jakarta.persistence.*;
import java.util.List;

/**
 * JPA entity representing a classroom. This entity is associated with the Student entity 
 * using a @OneToMany relationship. The corresponding database table will include a 
 * foreign key in the Student table that references the Classroom table.
 *
 * The mapping includes (fetch = FetchType.EAGER), which indicates that related Student 
 * entities are retrieved immediately along with the Classroom entity. Eager fetching 
 * is generally suited for scenarios where related entities are frequently accessed together, 
 * while lazy fetching retrieves related entities only when explicitly requested. 
 * Selecting an appropriate fetch strategy is essential for optimizing application performance.
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Classroom {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String room;
    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "classroomFK")
    private List<Student> students;

    public Classroom(String room) {
        this.room = room;
    }

    @Override
    public String toString() {
        return "Classroom{" +
                "id=" + id +
                ", room='" + room + '\'' +
                '}';
    }
}