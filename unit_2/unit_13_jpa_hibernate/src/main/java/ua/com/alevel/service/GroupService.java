package ua.com.alevel.service;

import ua.com.alevel.entity.Group;
import ua.com.alevel.repository.GroupRepository;

import java.util.List;

public class GroupService {

    private final GroupRepository groupRepository;

    public GroupService() {
        this.groupRepository = new GroupRepository();
    }

    public Group getGroupById(Long id) {
        if (!isValidId(id)) {
            return null;
        }
        return groupRepository.getGroupById(id);
    }

    public List<Group> getAllGroups() {
        return groupRepository.getAllGroups();
    }

    public void addGroup(Group group) {
        groupRepository.addGroup(group);
    }

    public void updateGroup(Group group) {
        groupRepository.updateGroup(group);
    }

    public void deleteGroupById(Long id) {
        if (!isValidId(id)) {
            return;
        }
        groupRepository.deleteGroupById(id);
    }

    private boolean isValidId(Long id) {
        return id > 0 && id <= getAllGroups().size();
    }
}
