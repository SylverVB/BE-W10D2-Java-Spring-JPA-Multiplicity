package com.app.Service;

import com.app.Model.Classroom;
import com.app.Model.Student;
// import com.app.Repository.ClassroomRepository;
import com.app.Repository.StudentRepository;
// import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * This service provides functionality to retrieve and manipulate entities related to the Student entity.
 * It complements the ClassroomService in managing relationships between classrooms and students.
 */
@Service
public class StudentService {
    private final StudentRepository studentRepository;
    // private final ClassroomRepository classroomRepository;

   /**
     * Constructs a StudentService with the necessary repository dependencies.
     * The ClassroomRepository is provided but not required for the current operations.
     *
     * @param studentRepository the repository used for student persistence operations
     * @param classroomRepository the repository used for classroom-related operations (optional)
     */
    // @Autowired is not required if there's only one constructor in the class
    // public StudentService(StudentRepository studentRepository, ClassroomRepository classroomRepository){
        public StudentService(StudentRepository studentRepository){
        this.studentRepository = studentRepository;
        // this.classroomRepository = classroomRepository;
    }

    /**
     * Persist a new student entity.
     * @param student a transient student entity.
     * @return the persisted student entity.
     */
    public Student addStudent(Student student){
        return studentRepository.save(student);
    }

    /**
     * @return all student entities.
     */
    public List<Student> getAllStudent(){
        return studentRepository.findAll();
    }

    /**
     * Assigns a classroom to a student by updating the classroom field of the student entity.
     * The updated student is then persisted.
     *
     * @param studentId the ID of an existing student entity
     * @param classroom a persisted classroom entity to assign
     */
    public void assignClassroomToStudent(long studentId, Classroom classroom){
        Optional<Student> studentOptional = studentRepository.findById(studentId);

        if (studentOptional.isPresent()) {
            Student student = studentOptional.get();
            student.setClassroom(classroom);
            studentRepository.save(student);
        } 
        // else {
        //     throw new RuntimeException("Student with ID " + studentId + " not found.");
        // }
    }

    /**
     * Retrieves the classroom assigned to a given student.
     *
     * @param studentId the ID of a persisted student entity
     * @return the classroom assigned to the student, or null if the student is not found
     */
    public Classroom getClassroomOfStudent(long studentId){
        Optional<Student> studentOptional = studentRepository.findById(studentId);
        if (studentOptional.isPresent()) {
            return studentOptional.get().getClassroom();
        } 
        // else {
        //     throw new RuntimeException("Student with ID " + studentId + " not found.");
        // }
        return null;
    }

    /**
     * Unassigns a classroom from a student by setting the classroom field to null.
     * The updated student is then persisted.
     *
     * @param studentId the ID of a persisted student entity
     */
    public void unassignClassroomOfStudent(long studentId){
        Optional<Student> studentOptional = studentRepository.findById(studentId);
        if (studentOptional.isPresent()) {
            Student student = studentOptional.get();
            student.setClassroom(null);
            studentRepository.save(student);
        }
        // else {
        //     throw new RuntimeException("Student with ID " + studentId + " not found.");
        // }
    }
}