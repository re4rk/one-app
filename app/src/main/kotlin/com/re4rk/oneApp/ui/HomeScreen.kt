package com.re4rk.oneApp.ui

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
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.text.style.TextOverflow.Companion.Ellipsis
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.re4rk.oneApp.R
import com.re4rk.oneApp.ui.theme.OneAppTheme

data class ChatRoom(
    val profileImageId: Int,
    val name: String,
    val contents: String,
    val time: String,
)

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Screen(chatRooms: List<ChatRoom>) {
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
        items(chatRooms.size) { index ->
            ChatRoomItem(chatRooms[index])
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
        ChatRoomProfile(profileImageId = chatRoom.profileImageId)

        Spacer(modifier = Modifier.padding(start = 8.dp))

        Column(modifier = Modifier.weight(1f)) {
            ChatRoomName(name = chatRoom.name)
            ChatRoomContents(contents = chatRoom.contents)
        }

        ChatRoomTime(time = chatRoom.time)
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
private fun ChatRoomProfile(profileImageId: Int) {
    Image(
        painter = painterResource(id = profileImageId),
        contentDescription = null,
        modifier = Modifier
            .size(56.dp)
            .clip(MaterialTheme.shapes.small),
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

val previewChatRooms = listOf(
    ChatRoom(
        profileImageId = R.drawable.img_default_image,
        name = "우아한테크코스",
        contents = "contentscontentscontentscontentscontents",
        time = "time",
    ),
    ChatRoom(
        profileImageId = R.drawable.img_default_image,
        name = "거지방",
        contents = "안녕하세요!\n 공지확인해주시고 들낙금지ㅜㅜ...",
        time = "오후 7:06",
    ),
    ChatRoom(
        profileImageId = R.drawable.img_default_image,
        name = "어플(앱)을 연구하는 사람들-안드로이드,IOS,개발자",
        contents = "그냥 안채우는ㅋㅋㅋ",
        time = "오후 6:18",
    ),
    ChatRoom(
        profileImageId = R.drawable.img_default_image,
        name = "BoB 총동문회: 소식방",
        contents = "<쿠팡 로지스틱스 개인정보보호 8년 이상 경력직 채용>\n[CLS] Privacy 담당자 채용...",
        time = "오후 5:56",
    ),
    ChatRoom(
        profileImageId = R.drawable.img_default_image,
        name = "카카오페이",
        contents = "결제가 완료되었어요.\n....",
        time = "오후 5:56",
    ),
).let { list -> (1..10).flatMap { list } }

@Preview(showBackground = true)
@Composable
private fun GreetingPreview() {
    Screen(
        previewChatRooms,
    )
}
