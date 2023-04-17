package kg.kubatbekov.university_cms.repository;


import kg.kubatbekov.university_cms.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Integer> {
}
