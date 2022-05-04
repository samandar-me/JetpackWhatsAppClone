@file:OptIn(ExperimentalPagerApi::class)

package com.samsdk.whatsupclone

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.pager.*
import com.samsdk.whatsupclone.ui.theme.WhatsAppFloatIconColor
import com.samsdk.whatsupclone.ui.theme.WhatsAppThemeColor
import com.samsdk.whatsupclone.ui.theme.WhatsUpCloneTheme
import com.samsdk.whatsupclone.utils.Constants._tabCurrentStatus
import com.samsdk.whatsupclone.utils.Constants.tabCurrentStatus
import com.samsdk.whatsupclone.view.WhatsAppCalls
import com.samsdk.whatsupclone.view.WhatsAppChats
import com.samsdk.whatsupclone.view.WhatsAppStatus
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WhatsUpCloneTheme {
                WhatsApp()
            }
        }
    }
}

@Composable
fun WhatsApp() {
    val context = LocalContext.current
    val menuExpanded = remember { mutableStateOf(false) }

    val tabStatus = tabCurrentStatus.observeAsState()

    val topBar: @Composable () -> Unit = {
        TopAppBar(
            title = {
                Text(
                    text = "Whats App",
                    color = Color.White,
                    fontSize = 20.sp
                )
            },
            actions = {
                IconButton(onClick = {
                    Toast.makeText(context, "Clicked Search", Toast.LENGTH_SHORT).show()
                }) {
                    Icon(
                        imageVector = Icons.Filled.Search,
                        contentDescription = "Search",
                        tint = Color.White
                    )
                }

                IconButton(onClick = {
                    menuExpanded.value = !menuExpanded.value
                }) {
                    Icon(
                        imageVector = Icons.Filled.MoreVert,
                        contentDescription = "Menu",
                        tint = Color.White
                    )
                }
                Column(
                    modifier = Modifier
                        .wrapContentSize(Alignment.TopStart)
                ) {
                    DropdownMenu(
                        modifier = Modifier
                            .width(200.dp)
                            .wrapContentSize(Alignment.TopStart),
                        expanded = menuExpanded.value,
                        onDismissRequest = {
                            menuExpanded.value = !menuExpanded.value
                        }
                    ) {
                        when(tabStatus.value) {
                            0 -> {
                                DropdownMenuItem(onClick = { /*Handle New group*/ }) {
                                    Text(text = "New group")
                                }
                                DropdownMenuItem(onClick = { /*Handle New broadcast*/ }) {
                                    Text(text = "New broadcast")
                                }
                                DropdownMenuItem(onClick = { /*Handle Linked devices*/ }) {
                                    Text(text = "Linked devices")
                                }
                                DropdownMenuItem(onClick = { /*Handle Starred messages*/ }) {
                                    Text(text = "Starred messages")
                                }
                                DropdownMenuItem(onClick = { /*Handle Payments*/ }) {
                                    Text(text = "Payments")
                                }
                                DropdownMenuItem(onClick = { /*Handle Settings*/ }) {
                                    Text(text = "Settings")
                                }
                            }
                            1 -> {
                                DropdownMenuItem(onClick = { /*Handle Status privacy*/ }) {
                                    Text(text = "Status privacy")
                                }
                                DropdownMenuItem(onClick = { /*Handle Settings*/ }) {
                                    Text(text = "Settings")
                                }
                            }
                            2 -> {
                                DropdownMenuItem(onClick = { /*Handle Clear call log*/ }) {
                                    Text(text = "Clear call log")
                                }
                                DropdownMenuItem(onClick = { /*Handle Settings*/ }) {
                                    Text(text = "Settings")
                                }
                            }
                        }
                    }
                }
            },
            backgroundColor = WhatsAppThemeColor,
            elevation = AppBarDefaults.TopAppBarElevation
        )
    }
    Scaffold(
        topBar = { topBar() },
        content = {
            WhatsAppTab()
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                Toast.makeText(context, "Message clicked", Toast.LENGTH_SHORT).show()
            },
                backgroundColor = WhatsAppFloatIconColor,
                elevation = FloatingActionButtonDefaults.elevation(),
                modifier = Modifier.padding(10.dp)
            ) {
                when(tabStatus.value) {
                    0 -> {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_baseline_chat_24),
                            contentDescription = "Message",
                            tint = Color.White,
                            modifier = Modifier.padding(10.dp)
                        )
                    }
                    1 -> {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_baseline_camera_alt_24),
                            contentDescription = "Camera",
                            tint = Color.White,
                            modifier = Modifier.padding(10.dp)
                        )
                    }
                    2 -> {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_baseline_add_ic_call_24),
                            contentDescription = "Call",
                            tint = Color.White,
                            modifier = Modifier.padding(10.dp)
                        )
                    }
                }
            }
        }
    )
}

@Composable
fun WhatsAppTab() {
    val pagerState = rememberPagerState(pageCount = 3)
    Column {
        Tabs(pagerState)
        TabsContent(pagerState)
    }
}

@Composable
fun Tabs(pagerState: PagerState) {
    val list = listOf("CHATS", "STATUS", "CALLS")
    val scope = rememberCoroutineScope()
    _tabCurrentStatus.value = pagerState.currentPage

    TabRow(
        selectedTabIndex = pagerState.currentPage,
        backgroundColor = WhatsAppThemeColor,
        contentColor = Color.Gray,
        indicator = { tabPositions ->
            TabRowDefaults.Indicator(
                modifier = Modifier.pagerTabIndicatorOffset(pagerState, tabPositions),
                height = 3.dp,
                color = Color.White
            )
        }
    ) {
        list.forEachIndexed { index, _ ->
            Tab(
                text = {
                    Text(text = list[index], color = Color.White)
                },
                selected = pagerState.currentPage == index,
                onClick = {
                    scope.launch {
                        pagerState.animateScrollToPage(index)
                    }
                }
            )
        }
    }
}

@Composable
fun TabsContent(pagerState: PagerState) {
    HorizontalPager(state = pagerState) { page ->
        when (page) {
            0 -> WhatsAppChats()
            1 -> WhatsAppStatus()
            2 -> WhatsAppCalls()
        }
    }
}

