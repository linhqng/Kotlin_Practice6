package com.example.practicee6practiceee

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.widget.EditText
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        editText.text = Editable.Factory.getInstance().newEditable("https://nopbai.live/data/data.json")
        button.setOnClickListener {
            var text = editText.text
            val intent = Intent(this, SlideActivity::class.java)
            intent.putExtra("url",text)
            startActivity(intent)
        }
    }
}
