package genson.amor.stopwatch_alarm

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

/**
 * Created by Genson on 12/02/2018.
 */
class AlarmReciever : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {

        Log.d("We are in the receiver!", "Yay!")

        val getResult: String = intent!!.getStringExtra("extra")

        val service = Intent(context, RingService::class.java)
        service.putExtra("extra", getResult)
        context!!.startService(service)
    }
}