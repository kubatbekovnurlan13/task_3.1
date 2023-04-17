package kg.kubatbekov.university_cms.dao;

import kg.kubatbekov.university_cms.model.Timeslot;
import kg.kubatbekov.university_cms.repository.TimeslotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class TimeslotDAO {
    private final TimeslotRepository timeslotRepository;

    @Autowired
    public TimeslotDAO(TimeslotRepository timeslotRepository) {
        this.timeslotRepository = timeslotRepository;
    }

    public Optional<Timeslot> find(int id) {
        return timeslotRepository.findById(id);
    }

    public List<Timeslot> findAll() {
        return timeslotRepository.findAll();
    }
}
