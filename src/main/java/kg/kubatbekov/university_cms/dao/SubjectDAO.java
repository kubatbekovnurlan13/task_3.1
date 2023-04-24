package kg.kubatbekov.university_cms.dao;

import kg.kubatbekov.university_cms.model.Subject;
import kg.kubatbekov.university_cms.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SubjectDAO {
    private final SubjectRepository subjectRepository;

    @Autowired
    public SubjectDAO(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    public List<Subject> getAll() {
        return subjectRepository.findAll();
    }

    public int subjectsProfessorsSize() {
        List<Subject> subjects = subjectRepository.findAll();
        int size = 0;

        for (Subject subject : subjects) {
            size = size + subject.getProfessors().size();
        }
        return size;
    }
}
