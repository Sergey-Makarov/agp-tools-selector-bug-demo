package com.example.tools_selector_bug_demo

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.tools_selector_bug_demo.ui.theme.ToolsSelectorBugDemoTheme
import com.fingerprintjs.android.fingerprint.Fingerprinter
import com.fingerprintjs.android.fingerprint.FingerprinterFactory

class MainActivity : ComponentActivity() {
    val fingerprinter = FingerprinterFactory.create(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // calling fingerprinter just to avoid shrinking
        fingerprinter.getFingerprint(
            version = Fingerprinter.Version.V_5
        ) {
            Log.d("Test", "Got fingerprint: $it")
        }

        setContent {
            ToolsSelectorBugDemoTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ToolsSelectorBugDemoTheme {
        Greeting("Android")
    }
}