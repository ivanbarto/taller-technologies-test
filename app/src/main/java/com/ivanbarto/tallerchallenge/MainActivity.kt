package com.ivanbarto.tallerchallenge

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Surface
import com.ivanbarto.tallerchallenge.ui.theme.TallerChallengeTheme
import com.ivanbarto.tallerchallenge.users.presentation.composables.UsersScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            TallerChallengeTheme {
                Surface {
                    UsersScreen()
                }
            }
        }
    }
}
