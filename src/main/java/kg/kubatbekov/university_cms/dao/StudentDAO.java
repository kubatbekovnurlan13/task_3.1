package kg.kubatbekov.university_cms.dao;

import kg.kubatbekov.university_cms.model.Student;
import kg.kubatbekov.university_cms.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class StudentDAO {
    private final StudentRepository studentRepository;

    @Autowired
    public StudentDAO(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Optional<Student> findById(int studentId){
        return studentRepository.findById(studentId);
    }
}
