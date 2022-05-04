package com.samsdk.whatsupclone.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.samsdk.whatsupclone.R
import com.samsdk.whatsupclone.model.SampleData
import com.samsdk.whatsupclone.ui.theme.liteGrayColor
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun WhatsAppCalls() {
    val date = SimpleDateFormat("hh:mm a", Locale.getDefault())
    val strDate: String = date.format(Date())

    val listOfStatusData = listOf(
        SampleData("Status 1", "Android Jetpack Compose 1", "Sample Url", strDate),
        SampleData("Status 2", "Android Jetpack Compose 2", "Sample Url", strDate),
        SampleData("Status 3", "Android Jetpack Compose 3", "Sample Url", strDate),
        SampleData("Status 4", "Android Jetpack Compose 4", "Sample Url", strDate),
        SampleData("Status 5", "Android Jetpack Compose 5", "Sample Url", strDate),
        SampleData("Status 6", "Android Jetpack Compose 6", "Sample Url", strDate),
        SampleData("Status 7", "Android Jetpack Compose 7", "Sample Url", strDate),
    )
    LazyColumn(
        modifier = Modifier
            .background(Color.White)
    ) {
        item {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(5.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.img_what),
                        contentDescription = "Image",
                        modifier = Modifier
                            .width(70.dp)
                            .height(70.dp)
                            .padding(5.dp)
                    )
                    Column(
                        modifier = Modifier
                            .padding(10.dp)
                            .fillMaxWidth()
                    ) {
                        Text(
                            text = "My Status",
                            fontSize = 15.sp,
                            color = Color.Black,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.padding(2.dp))
                        Text(
                            text = "Click",
                            fontSize = 14.sp,
                            color = Color.Gray,
                            fontWeight = FontWeight.Normal
                        )
                        Spacer(modifier = Modifier.padding(4.dp))
                    }
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(liteGrayColor)
                    ) {
                        Text(
                            text = "Recent Updates",
                            modifier = Modifier
                                .padding(15.dp, 5.dp, 10.dp, 5.dp),
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Normal,
                            color = Color.Black
                        )
                    }
                }
            }
        }
        items(listOfStatusData.size) { index ->
            SampleStatusListItem(listOfStatusData[index])
        }
        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(liteGrayColor)
            ) {
                Text(
                    text = "Viewed Updates",
                    modifier = Modifier
                        .padding(15.dp, 5.dp, 10.dp, 5.dp),
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color.Black
                )
            }
        }
    }
}

