    package com.example.test2.ui

    import android.content.Intent
    import android.os.Bundle
    import android.widget.Button
    import android.widget.EditText
    import androidx.activity.ComponentActivity
    import androidx.activity.compose.setContent
    import androidx.compose.foundation.layout.fillMaxSize
    import androidx.compose.material3.MaterialTheme
    import androidx.compose.material3.Surface
    import androidx.compose.material3.Text
    import androidx.compose.runtime.Composable
    import androidx.compose.ui.Modifier
    import androidx.compose.ui.tooling.preview.Preview
    import com.example.test2.R
    import com.example.test2.ui.Database_Files.LocalDatabase
    import com.example.test2.ui.ui.theme.Test2Theme

    class Admin_Change : ComponentActivity() {
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContent {
                Test2Theme {

                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colorScheme.background
                    ) {

                            setContentView(R.layout.admin_change_activity)

                            val create = findViewById<Button>(R.id.admin_create1)
                            val change = findViewById<Button>(R.id.change_button)
                            val suspend = findViewById<Button>(R.id.admin_suspend2)

                            val username = findViewById<EditText>(R.id.usr2)
                            val password = findViewById<EditText>(R.id.pwd1)
                            val oldusr = findViewById<EditText>(R.id.usr_old_usr)
                            val pwd2 = findViewById<EditText>(R.id.pwd2)
                            val home = findViewById<Button>(R.id.admin_home)


                        create.setOnClickListener(){
                            val intent = Intent(this, Admin_Create::class.java)
                            startActivity(intent)
                        }

                        suspend.setOnClickListener(){
                            val intent = Intent(this, AdminSuspend::class.java)
                            startActivity(intent)
                        }
                        home.setOnClickListener {
                            val intent = Intent(this, AdminHome::class.java)
                            startActivity(intent)

                        }

                        change.setOnClickListener {
                            val DB = LocalDatabase(this)
                            DB.updateConsumer(oldusr.text.toString(), password.text.toString(), username.text.toString(), pwd2.text.toString())
                        }




                    }
                }
            }
        }
    }