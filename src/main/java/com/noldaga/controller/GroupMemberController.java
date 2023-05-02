package com.noldaga.controller;

import com.noldaga.controller.response.Response;
import com.noldaga.domain.GroupDto;
import com.noldaga.domain.GroupMemberDto;
import com.noldaga.domain.entity.Group;
import com.noldaga.domain.entity.GroupMember;
import com.noldaga.service.GroupMemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class GroupMemberController {
    private final GroupMemberService groupMemberService;

    @PostMapping("/group/member/{group_id}")
    public Response<GroupMemberDto> registerGroup(@PathVariable Long group_id, Authentication authentication) {
        GroupMemberDto registerGroupMember = groupMemberService.registerGroup(group_id, authentication.getName());

        return Response.success(registerGroupMember);
    }

    @GetMapping("/groups/member") // 자신이 가입한 그룹 리스트(로그인한 유저가 가입한 그룹 리스트 보기)
    public Response<List<Group>> getGroupList2(Authentication authentication) {
        List<Group> groupList2 = groupMemberService.getGroupList2(authentication.getName());
        return Response.success(groupList2);
    }

    @DeleteMapping("/group/member/{group_id}")
    public Response<Void> unregisterGroup(@PathVariable Long group_id,  Authentication authentication) {
        groupMemberService.unregisterGroup(group_id, authentication.getName());

        return Response.success();
    }
}
