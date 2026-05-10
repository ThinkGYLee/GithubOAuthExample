package com.gyleedev.githuboauthexample

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
        enableEdgeToEdge()
        setContent {
            GithubOAuthExampleTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    GithubOAuthExampleScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}