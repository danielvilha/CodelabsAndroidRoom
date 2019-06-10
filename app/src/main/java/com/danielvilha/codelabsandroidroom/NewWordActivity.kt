package com.danielvilha.codelabsandroidroom

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.danielvilha.codelabsandroidroom.databinding.ActivityNewWordBinding

/**
 * Created by danielvilha on 2019-06-07
 */
class NewWordActivity : AppCompatActivity() {

    // Binding
    private lateinit var bind: ActivityNewWordBinding

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = DataBindingUtil.setContentView(this, R.layout.activity_new_word)

        bind.callback = object : Callback {
            override fun onClick(view: View) {
                val replyIntent = Intent()
                if (TextUtils.isEmpty(bind.editWord.text)) {
                    setResult(Activity.RESULT_CANCELED, replyIntent)
                } else {
                    val word = bind.editWord.text.toString()
                    replyIntent.putExtra(EXTRA_REPLY, word)
                    setResult(Activity.RESULT_OK, replyIntent)
                }
                finish()
            }
        }
    }

    interface Callback {
        fun onClick(view: View)
    }

    companion object {
        const val EXTRA_REPLY = "com.example.android.wordlistsql.REPLY"
    }
}