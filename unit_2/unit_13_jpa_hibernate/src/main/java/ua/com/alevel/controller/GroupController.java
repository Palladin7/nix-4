package ua.com.alevel.controller;

import ua.com.alevel.entity.Group;
import ua.com.alevel.service.GroupService;

import java.util.List;

public class GroupController {

    private final GroupService groupService;

    public GroupController() {
        this.groupService = new GroupService();
    }

    public Group getGroupById(Long id) {
        return groupService.getGroupById(id);
    }

    public List<Group> getAllGroups() {
        return groupService.getAllGroups();
    }

    public void addGroup(Group group) {
        groupService.addGroup(group);
    }

    public void updateGroup(Group group) {
        groupService.updateGroup(group);
    }

    public void deleteGroupById(Long id) {
        groupService.deleteGroupById(id);
    }
}
