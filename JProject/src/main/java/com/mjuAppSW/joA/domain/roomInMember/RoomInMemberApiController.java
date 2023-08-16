package com.mjuAppSW.joA.domain.roomInMember;

import com.mjuAppSW.joA.domain.roomInMember.dto.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
public class RoomInMemberApiController {

    private RoomInMemberService roomInMemberService;

    @Autowired
    public RoomInMemberApiController(RoomInMemberService roomInMemberService){
        this.roomInMemberService = roomInMemberService;
    }

    @GetMapping("/load/roomList")
    public ResponseEntity<List<RoomDTO>> getRoomList(
            @RequestParam("memberId") Long memberId,
            @RequestParam("expired") String expired) {
        log.info("getRoomList");
        log.info("memberId : " + memberId);
        log.info("expired : " + expired);
        RoomList response = roomInMemberService.getRoomList(memberId, expired);
        if (response.getStatus().equals("0") || response.getStatus().equals("1")) { // 1 = no room;
            return ResponseEntity.ok(response.getRoomDTOList());
        }
        return ResponseEntity.badRequest().build(); // memberId or expired is wrong
    }


    @PostMapping("/save/voteResult") // 투표 저장하기
    public ResponseEntity<VoteDTO> saveVoteResult(@RequestBody VoteRequest request){
        log.info("saveVoteResult");
        log.info("roomId : " + request.getRoomId());
        log.info("memberId : " + request.getMemberId());
        log.info("result : " + request.getResult());
        boolean checkRoomIdAndMemberId = roomInMemberService.checkRoomIdAndMemberId(request.getRoomId(), request.getMemberId());
        if(!checkRoomIdAndMemberId){
            return ResponseEntity.badRequest().build(); // requestBody의 정보가 잘못되었을 때
        }
        VoteDTO voteDTO = roomInMemberService.saveVoteResult(request.getRoomId(), request.getMemberId(), request.getResult());
        if(voteDTO != null){
            return ResponseEntity.ok(voteDTO); // 성공
        }
        return ResponseEntity.notFound().build(); // 실패
    }

    @GetMapping("/check/voteResult") // 두 회원의 투표 결과 확인하기
    public ResponseEntity<List<CheckVoteDTO>> checkVoteResult(
            @RequestParam("roomId") Long roomId){
        log.info("checkVoteResult");
        log.info("roomId : " + roomId);
        boolean checkRoomIdAndMemberId = roomInMemberService.checkRoomId(roomId);
        if(!checkRoomIdAndMemberId){
            return ResponseEntity.badRequest().build();
        }
        List<CheckVoteDTO> list = roomInMemberService.checkVoteResult(roomId);
        if(list.isEmpty() || list == null){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(list);
    }

    @PostMapping("/update/expired") // 나간 회원의 expired 1 -> 0으로 업데이트하기
    public HttpStatus updateExpired(@RequestBody updateExpiredRequest request){
        log.info("checkVoteResult");
        log.info("roomId : " + request.getRoomId());
        log.info("memberId : " + request.getMemberId());
        log.info("expired : " + request.getExpired());
        boolean save = roomInMemberService.updateExpired(request.getRoomId(), request.getMemberId(), request.getExpired());
        if(!save){
            return HttpStatus.BAD_REQUEST;
        }
        return HttpStatus.OK;
    }
}