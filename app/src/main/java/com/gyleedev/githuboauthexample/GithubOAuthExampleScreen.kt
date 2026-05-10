package com.gyleedev.githuboauthexample

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext


@Composable
fun GithubOAuthExampleScreen(
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    Column(modifier = modifier.fillMaxSize()) {
        Button(
            onClick = {}
        ) {
            Text(
                text = "CustomTab 으로 인증하기"
            )
        }

        Button(
            onClick = {}
        ) {
            Text(
                text = "AuthTab 으로 인증하기"
            )
        }
    }
}