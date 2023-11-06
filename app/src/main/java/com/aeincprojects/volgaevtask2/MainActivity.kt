package com.aeincprojects.volgaevtask2

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    private lateinit var mailPlainText: EditText
    private lateinit var mailButton: Button
    private lateinit var browserPlainText: EditText
    private lateinit var browserButton: Button
    private lateinit var callButton: Button
    private lateinit var callPlainText: EditText
    private lateinit var mapButton: Button
    private lateinit var mapPlainText1: EditText
    private lateinit var mapPlainText2: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mailPlainText = findViewById(R.id.mail_plainText)
        mailButton = findViewById(R.id.button_mail)
        browserPlainText = findViewById(R.id.browser_plainText)
        browserButton = findViewById(R.id.browser_button)
        callPlainText = findViewById(R.id.call_plainText)
        callButton = findViewById(R.id.call_button)
        mapPlainText1 = findViewById(R.id.map_plainTextFirst)
        mapPlainText2 = findViewById(R.id.map_plainTextSecond)
        mapButton = findViewById(R.id.map_button)


        mailButton.setOnClickListener {
            try {
                val intent = Intent(Intent.ACTION_SEND)
                intent.type = "plain/text"
                intent.putExtra(Intent.EXTRA_EMAIL, arrayOf(mailPlainText.text.toString()))
                intent.putExtra(Intent.EXTRA_SUBJECT, "subject")
                intent.putExtra(Intent.EXTRA_TEXT, "mail body")
                startActivity(Intent.createChooser(intent, "Send Email"))
            }catch (e: Exception) {
                Toast.makeText(this, "Некорректный адрес", Toast.LENGTH_SHORT).show()
            }
        }
        browserButton.setOnClickListener {
            try {
                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(browserPlainText.text.toString()))
                startActivity(browserIntent)
            }catch (e: Exception) {
                Toast.makeText(this, "Некорректная ссылка", Toast.LENGTH_SHORT).show()
            }
        }
        callButton.setOnClickListener {
            try {
                val callIntent = Intent(Intent.ACTION_DIAL);
                val number = callPlainText.text.toString()
                callIntent.data = Uri.parse("tel:$number")
                startActivity(callIntent)
            }catch (e: Exception) {
                Toast.makeText(this, "Ошибка в номере", Toast.LENGTH_SHORT).show()
            }
        }
        mapButton.setOnClickListener {
            try {
                val mapIntent = Intent(Intent.ACTION_VIEW)
                mapIntent.data = Uri.parse("geo:${mapPlainText1.text},${mapPlainText2.text}")
                startActivity(mapIntent)
            }catch (e: Exception) {
                Toast.makeText(this, "Ошибка с координатами", Toast.LENGTH_SHORT).show()
            }
        }
    }

}