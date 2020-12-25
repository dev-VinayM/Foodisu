package com.vmcorp.foodisu.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.vmcorp.foodisu.R

class SplashActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

/*        Thread.sleep(3000) // Sleeping in the UI thread is almost always wrong.
        val intent = Intent(this, HomeActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)*/

        Handler().postDelayed(Runnable {
            kotlin.run {
                val intent = Intent(this, HomeActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)
            }
        }, 3000)
    }
}


/**
 * Handler : Communicating back to Ui thread from other thread.
 *          Uses - 1) perform action on same thread in future
 *                 2) perform action on different thread
 *    post() enqueues Runnable objects
 *    sendMessage() enqueues Message object (in bundle form)
 */

/**
 * Intent Flags :
 *              1. FLAG_NEW_TASK
 *              2. FLAG_CLEAR_TASK
 *              3. FLAG_SINGLE_TOP
 *              4. FLAG_CLEAR_TOP
 */
