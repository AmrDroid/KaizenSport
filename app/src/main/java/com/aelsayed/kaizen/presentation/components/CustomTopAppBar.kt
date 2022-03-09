package com.aelsayed.kaizen.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aelsayed.kaizen.R


@Composable
fun CustomTopAppBar(
    title: String
) {


    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .background(color = MaterialTheme.colors.primary)
    ) {

        Text(
            text = title,
            fontFamily = FontFamily.Cursive,
            fontSize = 24.sp,
            modifier = Modifier
                .padding(12.dp)
                .align(
                    Alignment.CenterStart
                ),
            color = Color.White
        )

        Row(modifier = Modifier
            .padding(12.dp)
            .align(Alignment.CenterEnd)) {

            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = stringResource(R.string.profile_icon),
                tint = Color.White
            )

            Spacer(modifier = Modifier.width(12.dp))

            Icon(
                imageVector = Icons.Default.Settings,
                contentDescription = stringResource(R.string.setting_icon),
                tint = Color.White
            )

        }

    }

}

@Preview
@Composable
private fun PreviewComp() {
    CustomTopAppBar(stringResource(R.string.app_name))
}