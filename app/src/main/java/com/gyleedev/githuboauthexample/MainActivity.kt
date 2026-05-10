package com.gyleedev.githuboauthexample

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.gyleedev.githuboauthexample.ui.theme.GithubOAuthExampleTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        println("onCreate - Activity Hash: ${this.hashCode()}")
        enableEdgeToEdge()
        setContent {
            GithubOAuthExampleTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    GithubOAuthExampleScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }

    // 2. CustomTab 방식에서 리다이렉트될 때 호출됨
    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        println("onNewIntent 호출됨! - Activity Hash: ${this.hashCode()}")
        handleDeepLink(intent)
    }

    private fun handleDeepLink(intent: Intent?) {
        val data = intent?.data
        if (data != null) {
            println("Deep Link 데이터 수신: $data")
            println("추출된 code: ${data.getQueryParameter("code")}")
        }
    }
}