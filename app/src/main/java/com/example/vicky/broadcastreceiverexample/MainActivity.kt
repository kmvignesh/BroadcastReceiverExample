package com.example.vicky.broadcastreceiverexample

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var receiver : BroadcastReceiver
    lateinit var context : Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        context = this
        val filter = IntentFilter()
        filter.addAction(Intent.ACTION_POWER_CONNECTED)
        filter.addAction(Intent.ACTION_POWER_DISCONNECTED)
        receiver = object : BroadcastReceiver(){
            override fun onReceive(context: Context?, intent: Intent?) {
                Toast.makeText(context,intent?.action,Toast.LENGTH_SHORT).show()
            }
        }
        registerReceiver(receiver,filter)

        btn_launch.setOnClickListener({
            sendBroadcast(Intent(context,MyReceiver::class.java))
        })

    }

    override fun onDestroy() {
        unregisterReceiver(receiver)
        super.onDestroy()
    }
}
