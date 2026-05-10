package com.gyleedev.githuboauthexample

import android.app.Activity
import android.content.Intent.FLAG_ACTIVITY_SINGLE_TOP
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.browser.auth.AuthTabIntent
import androidx.browser.customtabs.CustomTabsIntent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri


@Composable
fun GithubOAuthExampleScreen(
    modifier: Modifier = Modifier
) {
    val clientId by remember { mutableStateOf(BuildConfig.CLIENT_ID) }
    val redirectUri by remember { mutableStateOf(BuildConfig.REDIRECT_URI) }
    val loginUrl by remember {
        mutableStateOf(
            Uri.Builder()
                .scheme("https")
                .authority("github.com")
                .appendPath("login")
                .appendPath("oauth")
                .appendPath("authorize")
                .appendQueryParameter("client_id", clientId)
                .build()
                .toString()
        )
    }


    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->
        // 사용자가 브라우저를 닫았을 때 호출됨

        if (result.resultCode == Activity.RESULT_OK) {
            val intent = result.data

            // 전달된 인텐트 데이터가 있는지 확인
            if (intent != null) {
                val resultUri = intent.data

                // 리다이렉트된 Uri가 존재하는지 확인
                if (resultUri != null) {
                    val code = resultUri.getQueryParameter("code")

                    // 최종적으로 코드를 추출하여 상태를 업데이트
                    if (code != null) {
                        println("성공")
                    }
                }
            }
        }
        // 결과 코드만 확인 가능, 리다이렉트된 URL의 데이터는 없음.
        if (result.resultCode == Activity.RESULT_CANCELED) {
            println("코드를 받을 수 없습니다. ")
        }
    }

    Column(modifier = modifier.fillMaxSize()) {
        Button(
            onClick = {
                // 일반적인 CustomTabsIntent를 생성합니다.
                val intent = CustomTabsIntent.Builder().build().intent
                intent.data = loginUrl.toUri()
                intent.flags = FLAG_ACTIVITY_SINGLE_TOP

                // 런처를 실행하지만, 결과는 이 런처로 돌아오지 않습니다.
                launcher.launch(intent)
            }
        ) {
            //커스텀 탭으로 인증하기
            Text(
                text = "Authenticate with CustomTab"
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                // AuthTabIntent를 생성합니다.
                val authTabIntent = AuthTabIntent.Builder().build()

                // 런처를 통해 인증을 시작합니다.
                // 리다이렉트 스킴을 미리 알려주어 결과를 런처로 가로챕니다.
                val scheme = redirectUri.toUri().scheme ?: ""
                authTabIntent.launch(
                    launcher,
                    loginUrl.toUri(),
                    scheme
                )
            }
        ) {
            Text(
                // AuthTab 으로 인증하기
                text = "Authenticate with AuthTab"
            )
        }
    }
}