package com.aelsayed.kaizen.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aelsayed.kaizen.domain.model.Category
import com.aelsayed.kaizen.R

@Composable
fun CategoryBar(
    category: Category,
    isExpanded: Boolean,
    updateExpand: () -> Unit
) {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .background(color = MaterialTheme.colors.surface).clickable { updateExpand() }
    ) {

        Row(
            modifier = Modifier.align(Alignment.CenterStart),
            verticalAlignment = Alignment.CenterVertically
        ) {
            CategoryIcon(category.id)
            Spacer(modifier = Modifier.width(3.dp))
            Text(text = category.name.uppercase(), fontSize = 14.sp, color = Color.White)
        }

        Row(modifier = Modifier.align(Alignment.CenterEnd)) {

            if (!isExpanded) {
                Image(
                    painter = painterResource(id = R.drawable.ic_expand_more),
                    contentDescription = stringResource(R.string.expand)
                )
            } else {
                Image(
                    painter = painterResource(id = R.drawable.ic_expand_less),
                    contentDescription = stringResource(R.string.expand_less)
                )
            }

        }

    }

}

@Composable
private fun CategoryIcon(
    category: String
) {

    when (category.uppercase()) {

        "FOOT" -> {
            Image(
                painter = painterResource(id = R.drawable.ic_soccer),
                contentDescription = "Soccer Icon",
                modifier = Modifier.padding(4.dp)
            )
        }

        "BASK" -> {
            Image(
                painter = painterResource(id = R.drawable.ic_basketball),
                contentDescription = "Basketball Icon",
                modifier = Modifier.padding(4.dp)
            )
        }

        "TENN" -> {
            Image(
                painter = painterResource(id = R.drawable.ic_tennis),
                contentDescription = "Tennis Icon",
                modifier = Modifier.padding(4.dp)
            )
        }

        "TABL" -> {
            Image(
                painter = painterResource(id = R.drawable.ic_table_tennis),
                contentDescription = "Table Tennis Icon",
                modifier = Modifier.padding(4.dp)
            )
        }

        "VOLL" -> {
            Image(
                painter = painterResource(id = R.drawable.ic_volleyball),
                contentDescription = "Volleyball Icon",
                modifier = Modifier.padding(4.dp)
            )
        }

        "ESPS" -> {
            Image(
                painter = painterResource(id = R.drawable.ic_esports),
                contentDescription = "Esports Icon",
                modifier = Modifier.padding(4.dp)
            )
        }

        "ICEH" -> {
            Image(
                painter = painterResource(id = R.drawable.ic_hockey),
                contentDescription = "Ice Hokey Icon",
                modifier = Modifier.padding(4.dp)
            )
        }

        "HAND" -> {
            Image(
                painter = painterResource(id = R.drawable.ic_handball),
                contentDescription = "Handball Icon",
                modifier = Modifier.padding(4.dp)
            )
        }

        "BCHV" -> {
            Image(
                painter = painterResource(id = R.drawable.ic_beach_volley),
                contentDescription = "Beach Volley Icon",
                modifier = Modifier.padding(4.dp)
            )
        }

        "SNOO" -> {
            Image(
                painter = painterResource(id = R.drawable.ic_snooker),
                contentDescription = "Snooker Icon",
                modifier = Modifier.padding(4.dp)
            )
        }

        "BADM" -> {
            Image(
                painter = painterResource(id = R.drawable.ic_badminton),
                contentDescription = "Badminton Icon",
                modifier = Modifier.padding(4.dp)
            )
        }

        else -> {
            Image(
                painter = painterResource(id = R.drawable.ic_image_not_supported),
                contentDescription = "Not Supported Icon",
                modifier = Modifier.padding(4.dp)
            )
        }


    }


}

@Preview
@Composable
private fun PreviewComp() {
    CategoryBar(category = Category(id = "TENN", name = "Tennis"), isExpanded = true, {})
}