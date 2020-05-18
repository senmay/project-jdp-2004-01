package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.dto.GroupDto;
import com.kodilla.ecommercee.mapper.GroupMapper;
import com.kodilla.ecommercee.service.DbGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/v1/group")
public class GroupController {

    @Autowired
    private DbGroupService groupService;

    @Autowired
    private GroupMapper groupMapper;

    @GetMapping(value = "getGroups")
    public List<GroupDto> getAllGroups(){
        return groupMapper.mapToGroupDtoList(groupService.getAllGroups());
    }

    @GetMapping(value = "getGroup")
    public GroupDto getGroup(@RequestParam Long id) throws GroupNotFoundException{
        return groupMapper.mapToGroupDto(groupService.getGroup(id).orElseThrow(GroupNotFoundException::new));
    }

    @PostMapping(value = "addGroup", consumes = APPLICATION_JSON_VALUE)
    public void addGroup(@RequestBody GroupDto groupDto) {
        groupService.saveGroup(groupMapper.mapToGroup(groupDto));
        }

    @PutMapping(value = "updateGroup", consumes = APPLICATION_JSON_VALUE)
    public GroupDto updateGroup(@RequestBody GroupDto groupDto){
        return groupMapper.mapToGroupDto(groupService.saveGroup(groupMapper.mapToGroup(groupDto)));
    }
}
