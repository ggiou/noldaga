package com.noldaga.domain.entity.Chat;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@EntityListeners(AuditingEntityListener.class) //자동으로 시간을 매핑하여 테이블에 넣어주는 jpa auditing 사용
@Getter //dto 생성시 사용
@ToString
@Entity
public class ChatRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //채팅방 번호

    @Column(updatable = false)
    private String roomName; //생성될때 채팅방 이름

    @Setter
    @Column
    private String viewRoomName; //채팅방 이름 (변경가능한..)

    protected ChatRoom(){}

    public ChatRoom(Long id, String name, String uname){
        this.id = id;
        this.roomName = name;
        this.viewRoomName = uname;
    }

    public static ChatRoom of(String name, String viewName){
        return new ChatRoom(null, name, viewName);
    }
}

/* 다대다 연결 관계를 없애기 위해서 아래와 같이 설정
 메시지 - 채팅방 정보 - 채팅방에 참가한 사람들 정보
 chat  - chatRoom  - JoinRoom

*/
