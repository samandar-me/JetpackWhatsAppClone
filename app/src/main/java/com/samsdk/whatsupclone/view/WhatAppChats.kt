package com.samsdk.whatsupclone.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.samsdk.whatsupclone.R
import com.samsdk.whatsupclone.model.SampleData
import com.samsdk.whatsupclone.ui.theme.liteGrayColor
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun WhatsAppChats() {
    val date = SimpleDateFormat("hh:mm a", Locale.getDefault())
    val strDate: String = date.format(Date())

    val listOfData = listOf(
        SampleData("Name 1", "Android Jetpack Compose 1", "Sample Url", strDate),
        SampleData("Name 2", "Android Jetpack Compose 2", "Sample Url", strDate),
        SampleData("Name 3", "Android Jetpack Compose 3", "Sample Url", strDate),
        SampleData("Name 4", "Android Jetpack Compose 4", "Sample Url", strDate),
        SampleData("Name 5", "Android Jetpack Compose 5", "Sample Url", strDate),
        SampleData("Name 6", "Android Jetpack Compose 6", "Sample Url", strDate),
        SampleData("Name 7", "Android Jetpack Compose 7", "Sample Url", strDate),
        SampleData("Name 8", "Android Jetpack Compose 8", "Sample Url", strDate),
        SampleData("Name 9", "Android Jetpack Compose 9", "Sample Url", strDate),
        SampleData("Name 10", "Android Jetpack Compose 10", "Sample Url", strDate)
    )
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .wrapContentSize(Alignment.Center)
    ) {
        LazyColumn(
            modifier = Modifier.padding(10.dp)
        ) {
            items(listOfData.size) { index ->
                SampleDataListItem(listOfData[index])
            }
        }
    }
}

@Composable
fun SampleDataListItem(data: SampleData) {
    Column(
        modifier = Modifier.padding(5.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Image(
                painter = painterResource(id = R.drawable.img_what),
                contentDescription = "Image",
                modifier = Modifier
                    .width(70.dp)
                    .height(70.dp)
                    .padding(5.dp)
                    .clip(CircleShape)
            )
            Column(
                modifier = Modifier.padding(10.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = data.name,
                        modifier = Modifier
                            .weight(1.0f),
                        fontSize = 15.sp,
                        color = Color.Black,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = data.createdDate,
                        modifier = Modifier
                            .weight(0.5f),
                        fontSize = 12.sp,
                        color = Color.Gray,
                        fontWeight = FontWeight.Normal,
                        textAlign = TextAlign.End
                    )
                }
                Spacer(modifier = Modifier.padding(5.dp))
                
                Text(
                    text = data.desc,
                    fontSize = 15.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.Normal
                )
                Spacer(modifier = Modifier.padding(5.dp))
                
                Divider(color = liteGrayColor, thickness = 1.dp)
            }
        }
    }
}

