package kg.kubatbekov.university_cms.dao;

import kg.kubatbekov.university_cms.model.Room;
import kg.kubatbekov.university_cms.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RoomDAO {

    private final RoomRepository roomRepository;

    @Autowired
    public RoomDAO(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public List<Room> getAll() {
        return roomRepository.findAll();
    }
}
