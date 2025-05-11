package com.app;

import com.app.Model.Classroom;
import com.app.Model.Student;
import com.app.Repository.ClassroomRepository;
import com.app.Repository.StudentRepository;
import com.app.Service.StudentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class) // Initializes mocks automatically
public class StudentServiceTest {

    @InjectMocks // / Automatically injects mocks into studentService
    private StudentService studentService;

    @Mock
    private StudentRepository studentRepository;

    @Mock
    private ClassroomRepository classroomRepository;

    @BeforeEach
    public void setup() {
 
    }

    @Test
    public void testAddStudent() {
        Student student = new Student();
        when(studentRepository.save(student)).thenReturn(student);

        Student result = studentService.addStudent(student);

        assertEquals(student, result);
        verify(studentRepository, times(1)).save(student);
    }

    @Test
    public void testAssignClassroomToStudent() {
        long studentId = 1L;
        Classroom classroom = new Classroom();

        Student student = new Student();
        student.setId(studentId);

        when(studentRepository.findById(studentId)).thenReturn(Optional.of(student));
        when(studentRepository.save(student)).thenReturn(student);

        studentService.assignClassroomToStudent(studentId, classroom);

        assertEquals(classroom, student.getClassroom());
        verify(studentRepository, times(1)).findById(studentId);
        verify(studentRepository, times(1)).save(student);
    }

    @Test
    public void testGetClassroomOfStudent() {
        long studentId = 1L;
        Classroom classroom = new Classroom();

        Student student = new Student();
        student.setId(studentId);
        student.setClassroom(classroom);

        when(studentRepository.findById(studentId)).thenReturn(Optional.of(student));

        Classroom result = studentService.getClassroomOfStudent(studentId);

        assertEquals(classroom, result);
        verify(studentRepository, times(1)).findById(studentId);
    }

    @Test
    public void testUnassignClassroomOfStudent() {
        long studentId = 1L;

        Student student = new Student();
        student.setId(studentId);
        student.setClassroom(new Classroom());

        when(studentRepository.findById(studentId)).thenReturn(Optional.of(student));
        when(studentRepository.save(student)).thenReturn(student);

        studentService.unassignClassroomOfStudent(studentId);

        assertEquals(null, student.getClassroom());
        verify(studentRepository, times(1)).findById(studentId);
        verify(studentRepository, times(1)).save(student);
    }
}