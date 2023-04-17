package kg.kubatbekov.university_cms.dao;

import kg.kubatbekov.university_cms.model.Course;
import kg.kubatbekov.university_cms.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CourseDAO {
    private final CourseRepository courseRepository;

    @Autowired
    public CourseDAO(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public void saveAll(List<Course> courses) {
        courseRepository.saveAll(courses);
    }

    public List<Course> findAll() {
        return courseRepository.findAll();
    }

    public void deleteAll() {
        courseRepository.deleteAll();
    }

    public List<Course> findByGroupId(int groupId) {
        return courseRepository
                .findAll()
                .stream()
                .filter(course -> course.getGroup().getGroupId() == groupId)
                .toList();
    }

    public List<Course> findByProfessorId(int professorId) {
        return courseRepository
                .findAll()
                .stream()
                .filter(course -> course.getProfessor().getProfessorId() == professorId)
                .toList();
    }
}
