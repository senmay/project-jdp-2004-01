package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.GroupDto;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/group")
public class GroupController {

    @RequestMapping(method = RequestMethod.GET, value = "getGroups")
    public List<GroupDto> getAllGroups(GroupDto groupDto){
        return new ArrayList<GroupDto>();
    }

    @RequestMapping(method = RequestMethod.GET, value = "getGroup")
    public GroupDto getGroup(Long id){
        return new GroupDto(1L, "test");
    }

    @RequestMapping(method = RequestMethod.POST, value = "addGroup")
    public void addGroup(GroupDto groupDto){
    }

    @RequestMapping(method = RequestMethod.PUT, value = "updateGroup")
    public GroupDto updateGroup(GroupDto groupDto){
        return new GroupDto(1L, "afterEdit");
    }
}
