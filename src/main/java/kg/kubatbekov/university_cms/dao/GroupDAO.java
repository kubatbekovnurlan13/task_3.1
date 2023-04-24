package kg.kubatbekov.university_cms.dao;

import kg.kubatbekov.university_cms.model.Group;
import kg.kubatbekov.university_cms.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class GroupDAO {
    private final GroupRepository groupRepository;

    @Autowired
    public GroupDAO(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    public List<Group> getAll() {
        return groupRepository.findAll();
    }

    public int groupsSubjectsSize(){
        List<Group> groups = groupRepository.findAll();
        int size = 0;

        for (Group group : groups) {
            size = size + group.getSubjects().size();
        }
        return size;
    }
}
