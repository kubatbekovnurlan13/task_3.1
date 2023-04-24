package kg.kubatbekov.university_cms.dao;

import kg.kubatbekov.university_cms.model.Lesson;
import kg.kubatbekov.university_cms.repository.LessonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LessonDAO {
    private final LessonRepository lessonRepository;

    @Autowired
    public LessonDAO(LessonRepository lessonRepository) {
        this.lessonRepository = lessonRepository;
    }

    public void saveAll(List<Lesson> cours) {
        lessonRepository.saveAll(cours);
    }

    public List<Lesson> findAll() {
        return lessonRepository.findAll();
    }

    public void deleteAll() {
        lessonRepository.deleteAll();
    }

    public List<Lesson> findByGroupId(int groupId) {
        return lessonRepository
                .findAll()
                .stream()
                .filter(course -> course.getGroup().getGroupId() == groupId)
                .toList();
    }

    public List<Lesson> findByProfessorId(int professorId) {
        return lessonRepository
                .findAll()
                .stream()
                .filter(course -> course.getProfessor().getProfessorId() == professorId)
                .toList();
    }
}
