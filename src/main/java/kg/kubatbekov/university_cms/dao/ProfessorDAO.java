package kg.kubatbekov.university_cms.dao;

import kg.kubatbekov.university_cms.model.Professor;
import kg.kubatbekov.university_cms.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ProfessorDAO {
    private final ProfessorRepository professorRepository;

    @Autowired
    public ProfessorDAO(ProfessorRepository professorRepository) {
        this.professorRepository = professorRepository;
    }

    public List<Professor> getAll() {
        return professorRepository.findAll();
    }

    public Optional<Professor> findById(int professorId){
        return professorRepository.findById(professorId);
    }
}
