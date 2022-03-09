package com.aelsayed.kaizen.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aelsayed.kaizen.domain.model.MatchEvent
import com.aelsayed.kaizen.R
import com.aelsayed.kaizen.util.DateConverter
import kotlinx.coroutines.delay

@Composable
fun MatchCard(
    matchEvent: MatchEvent,
    updateFavouriteMatch: () -> Unit,
    updateCountDown: (time: String) -> Unit
) {

    val opponents = matchEvent.eventName.split("-")

    val timeInMilliseconds = remember {
        mutableStateOf(matchEvent.eventStartTime.toLong())
    }

    val timeForEvent = remember {
        mutableStateOf(DateConverter.untilEvent(matchEvent.eventStartTime))
    }

    LaunchedEffect(key1 = timeInMilliseconds.value) {

        if(timeInMilliseconds.value != 0L){
            delay(1000L)
            timeInMilliseconds.value -= 1000L
            updateCountDown(timeInMilliseconds.value.toString())
            timeForEvent.value = DateConverter.untilEvent(timeInMilliseconds.value.toString())
        }

    }




    Column(
        modifier = Modifier
            .width(100.dp)
            .height(120.dp)
            .background(color = Color.Transparent),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Row(
            modifier = Modifier
                .border(
                    width = 1.dp,
                    shape = RoundedCornerShape(12.dp),
                    color = Color.White
                )
                .padding(start = 8.dp, end = 8.dp, top = 2.dp, bottom = 2.dp)
        ) {

            Text(
                text = timeForEvent.value,
                color = Color.White,
                fontSize = 14.sp,
                fontWeight = FontWeight.Light
            )
        }

        if (matchEvent.isEventFavourite) {
            Image(
                painter = painterResource(id = R.drawable.ic_filled_star),
                contentDescription = stringResource(R.string.fav_icon),
                modifier = Modifier
                    .padding(4.dp)
                    .clickable { updateFavouriteMatch() }
            )
        } else {
            Image(
                painter = painterResource(id = R.drawable.ic_empty_star),
                contentDescription = stringResource(R.string.not_fav_icon),
                modifier = Modifier
                    .padding(4.dp)
                    .clickable { updateFavouriteMatch() }
            )
        }

        Text(
            text = opponents[0],
            overflow = TextOverflow.Ellipsis,
            fontSize = 14.sp,
            textAlign = TextAlign.Center,
            maxLines = 1,
            color = Color.White
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = opponents[1],
            overflow = TextOverflow.Ellipsis,
            fontSize = 14.sp,
            textAlign = TextAlign.Center,
            maxLines = 1,
            color = Color.White
        )


    }

}

@Preview
@Composable
private fun PreviewComp() {
    MatchCard(
        matchEvent = MatchEvent(
            sportId = "",
            eventId = "",
            eventName = "Test Test",
            eventStartTime = "12:00:00",
            isEventFavourite = true
        ), {}, {}
    )
}