package com.re4rk.oneapp.presentation.repository

import com.re4rk.oneapp.domain.model.ChatRoom
import com.re4rk.oneapp.domain.repository.ChatRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Thread.sleep

class FakeChatRepository : ChatRepository {
    override fun getChatRooms(): Flow<List<ChatRoom>> {
        sleep(1000)
        return flow {
            emit(
                listOf(
                    ChatRoom(
                        id = 1,
                        title = "title",
                        profile = "https://i.pravatar.cc/150?img=0",
                        lastMessage = "lastMessage",
                        lastMessageTime = "lastMessageTime",
                        messageCount = 1,
                        status = "status",
                    ),
                    ChatRoom(
                        id = 2,
                        title = "거지방",
                        profile = "https://i.pravatar.cc/150?img=0",
                        lastMessage = "안녕하세요!\n공지확인해주시고 들낙금지ㅜㅜ...",
                        lastMessageTime = "오후 7:06",
                        messageCount = 2,
                        status = "status",
                    ),
                    ChatRoom(
                        id = 3,
                        title = "어플(앱)을 연구하는 사람들-안드로이드,IOS,개발자",
                        profile = "https://i.pravatar.cc/150?img=0",
                        lastMessage = "그냥 안채우는ㅋㅋㅋ",
                        lastMessageTime = "오후 6:18",
                        messageCount = 3,
                        status = "status",
                    ),
                    ChatRoom(
                        id = 4,
                        title = "BoB 총동문회: 소식방",
                        profile = "https://i.pravatar.cc/150?img=0",
                        lastMessage = "<쿠팡 로지스틱스 개인정보보호 8년 이상 경력직 채용>\n[CLS] Privacy 담당자 채용...",
                        lastMessageTime = "오후 5:56",
                        messageCount = 4,
                        status = "status",
                    ),
                    ChatRoom(
                        id = 5,
                        title = "카카오페이",
                        profile = "https://i.pravatar.cc/150?img=0",
                        lastMessage = "결제가 완료되었어요.\n....",
                        lastMessageTime = "오후 5:56",
                        messageCount = 5,
                        status = "status",
                    ),
                ).let { list -> (1..10).flatMap { list } },
            )
        }
    }
}
