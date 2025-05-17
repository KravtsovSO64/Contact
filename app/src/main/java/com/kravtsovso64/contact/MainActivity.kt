package com.kravtsovso64.contact

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kravtsovso64.jetpackcompose.R


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ContactDetails(  Contact(
                name = "Евгений",
                familyName = "Лукашин",
                phone = "+7 929 323 12 32",
                surname = "Андреевич",
                address = "г.Москва, 3-ий квартал, ул. Зарубина",
                imageRes = null,
                email = "yabloco@pomidor.ru",
                isFavorite = true
            )
            )
        }
    }
}

@Composable
fun ContactDetails(contact: Contact) {

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.25F),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            DrawIcon(contact)
            Row(
                modifier = Modifier.padding(0.dp, 16.dp,0.dp,0.dp)
            ) {
                contact.surname?.let {
                    Text(
                        text = contact.name + " " + contact.surname,
                        textAlign = TextAlign.Center,
                        style = TextStyle(
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Medium)
                    )
                }  ?: Text(
                    text = contact.name,
                    textAlign = TextAlign.Center,
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium
                    )
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(18.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = contact.familyName,
                    textAlign = TextAlign.Center,
                    style = TextStyle(fontSize = 18.sp))
                if (contact.isFavorite) {
                    Image(
                        modifier = Modifier
                            .padding(8.dp,0.dp),
                        alignment = Alignment.Center,
                        contentScale = ContentScale.Fit,
                        painter = painterResource(android.R.drawable.star_big_on),
                        contentDescription = null
                    )
                }
            }
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.75F),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            InfoRow(stringResource(R.string.phone), contact.phone)
            InfoRow(stringResource(R.string.address), contact.address)
            contact.email?.let {
                InfoRow(stringResource(R.string.email), contact.email)
            }
        }
    }

}

@Composable
fun DrawIcon(contact: Contact) {
    Box(
        contentAlignment = Alignment.Center
    ) {
        if (contact.imageRes != null) {
            Image(
                modifier = Modifier.size(120.dp, 60.dp),
                painter = painterResource(contact.imageRes),
                contentDescription = null,
            )
        } else {
            Icon(
                painter = painterResource(R.drawable.circle),
                contentDescription = null,
                tint = Color.LightGray
            )

            Text(
                text = "${contact.name.take(1)}${contact.familyName.take(1)}",
                style = TextStyle(
                    fontWeight = FontWeight.Medium
                )
            )
        }
    }
}

@Composable
fun InfoRow(title: String, description: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(0.dp, 8.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier.weight(1f),
            textAlign = TextAlign.End,
            text =  "$title: ",
            fontStyle = FontStyle.Italic,
            fontSize = 14.sp
        )
        Text(
            modifier = Modifier.weight(1f),
            text = description,
            fontSize = 14.sp
        )
    }
}

@Preview(showSystemUi = true)
@Composable
fun ContactDetailsPreview1() {
    MaterialTheme{
        ContactDetails(
            contact = Contact(
                name = "Евгений",
                familyName = "Лукашин",
                phone = "+7 929 323 12 32",
                surname = "Андреевич",
                address = "г.Москва, 3-ий квартал, ул. Зарубина",
                imageRes = null,
                email = "yabloco@pomidor.ru",
                isFavorite = true
            )
        )
    }
}

@Preview(showSystemUi = true)
@Composable
fun ContactDetailsPreview2() {
    MaterialTheme{
        ContactDetails(
            contact = Contact(
                name = "Василий",
                familyName = "Кузякин",
                phone = "---",
                surname = null,
                address = "г.Москва, 3-ий квартал, ул. Зарубина",
                imageRes = R.drawable.mok_image,
                email = null,
                isFavorite = false
            )
        )
    }
}