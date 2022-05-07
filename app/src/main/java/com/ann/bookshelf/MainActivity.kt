package com.ann.bookshelf

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import com.ann.bookshelf.ui.theme.BookshelfTheme

class MainActivity : AppCompatActivity()  {

    //: AppCompatActivity()
    //: ComponentActivity()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)

        setContent {
            BookshelfTheme{

            }
        }
    }
}