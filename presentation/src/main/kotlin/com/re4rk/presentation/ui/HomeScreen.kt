package com.re4rk.presentation.ui

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.text.style.TextOverflow.Companion.Ellipsis
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.re4rk.domain.model.ChatRoom
import com.re4rk.presentation.R
import com.re4rk.presentation.ui.theme.OneAppTheme
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun HomeRoot(homeViewModel: HomeViewModel) {
    val homeState: HomeState by homeViewModel.homeState.collectAsStateWithLifecycle()
    when (val state = homeState) {
        is HomeState.Loading -> Text(text = "Loading...")
        is HomeState.Error -> Text(text = "Error")
        is HomeState.Success -> Screen(state.chatRooms)
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun Screen(chatRooms: List<ChatRoom>) {
    OneAppTheme {
        Scaffold(
            modifier = Modifier
                .width(360.dp)
                .fillMaxHeight(),
            content = {
                ChatRoomList(chatRooms)
            },
            bottomBar = {
                BottomAppBar {
                    Image(
                        painter = painterResource(id = R.drawable.ic_launcher_background),
                        contentDescription = "Chat",
                        modifier = Modifier
                            .padding(8.dp)
                            .size(24.dp),
                    )
                    Image(
                        painter = painterResource(id = R.drawable.ic_launcher_background),
                        contentDescription = "Search",
                        modifier = Modifier
                            .padding(8.dp)
                            .size(24.dp)
                            .clickable {
                                Log.d("OneApp", "Search")
                            },
                    )
                    Image(
                        painter = painterResource(id = R.drawable.ic_launcher_background),
                        contentDescription = "More",
                        modifier = Modifier
                            .padding(8.dp)
                            .size(24.dp),
                    )
                }
            },
        )
    }
}

// recycler View
@Composable
private fun ChatRoomList(chatRooms: List<ChatRoom>) {
    LazyColumn(content = {
        item {
            ChatRoomHeader()
        }
        items(chatRooms) { chatRoom ->
            ChatRoomItem(chatRoom)
        }
    })
}

@Composable
private fun ChatRoomHeader() {
    Text(
        text = "Chats",
        style = TextStyle(
            fontWeight = Bold,
            fontSize = 20.sp,
        ),
        modifier = Modifier.padding(
            horizontal = 16.dp,
            vertical = 8.dp,
        ),
    )
}

@Composable
private fun ChatRoomItem(
    chatRoom: ChatRoom,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = 16.dp,
                vertical = 8.dp,
            ),
    ) {
        ChatRoomProfile(profileUrl = chatRoom.profile)

        Spacer(modifier = Modifier.padding(start = 8.dp))

        Column(modifier = Modifier.weight(1f)) {
            ChatRoomName(name = chatRoom.title)
            ChatRoomContents(contents = chatRoom.lastMessage)
        }

        ChatRoomTime(time = chatRoom.lastMessageTime)
    }
}

@Composable
private fun ChatRoomTime(time: String) {
    Text(
        text = time,
        fontSize = 10.sp,
    )
}

@Composable
private fun ChatRoomProfile(profileUrl: String) {
    // Glide
    GlideImage(
        imageModel = profileUrl,
        contentDescription = null,
        modifier = Modifier
            .size(56.dp)
            .clip(MaterialTheme.shapes.small),
        previewPlaceholder = R.drawable.ic_launcher_background,
    )
}

@Composable
private fun ChatRoomName(name: String) {
    Text(
        text = name,
        style = TextStyle(
            fontWeight = Bold,
        ),
        maxLines = 1,
        fontSize = 14.sp,
        overflow = Ellipsis,
    )
}

@Composable
private fun ChatRoomContents(contents: String) {
    Text(
        text = contents,
        fontSize = 12.sp,
        style = TextStyle(
            lineHeight = 16.sp,
        ),
        maxLines = 2,
        overflow = Ellipsis,
    )
}

@Preview(showBackground = true)
@Composable
private fun GreetingPreview() {
    Screen(previewChatRooms)
}

val previewChatRooms = listOf(
    ChatRoom(
        id = 1,
        title = "title",
        profile = "https://i.pravatar.cc/150?img=1",
        lastMessage = "lastMessage",
        lastMessageTime = "lastMessageTime",
        messageCount = 1,
        status = "status",
    ),
    ChatRoom(
        id = 2,
        title = "거지방",
        profile = "https://i.pravatar.cc/150?img=2",
        lastMessage = "안녕하세요!\n공지확인해주시고 들낙금지ㅜㅜ...",
        lastMessageTime = "오후 7:06",
        messageCount = 2,
        status = "status",
    ),
    ChatRoom(
        id = 3,
        title = "어플(앱)을 연구하는 사람들-안드로이드,IOS,개발자",
        profile = "https://i.pravatar.cc/150?img=3",
        lastMessage = "그냥 안채우는ㅋㅋㅋ",
        lastMessageTime = "오후 6:18",
        messageCount = 3,
        status = "status",
    ),
    ChatRoom(
        id = 4,
        title = "BoB 총동문회: 소식방",
        profile = "https://i.pravatar.cc/150?img=4",
        lastMessage = "<쿠팡 로지스틱스 개인정보보호 8년 이상 경력직 채용>\n[CLS] Privacy 담당자 채용...",
        lastMessageTime = "오후 5:56",
        messageCount = 4,
        status = "status",
    ),
    ChatRoom(
        id = 5,
        title = "카카오페이",
        profile = "https://i.pravatar.cc/150?img=5",
        lastMessage = "결제가 완료되었어요.\n....",
        lastMessageTime = "오후 5:56",
        messageCount = 5,
        status = "status",
    ),
).let { list -> (1..10).flatMap { list } }
