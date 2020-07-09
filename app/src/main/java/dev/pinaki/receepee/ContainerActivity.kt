package dev.pinaki.receepee

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class ContainerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.AppTheme)
        setContentView(R.layout.container_activity)
    }
}