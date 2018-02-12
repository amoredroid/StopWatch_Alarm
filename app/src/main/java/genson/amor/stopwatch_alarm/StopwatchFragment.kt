package genson.amor.stopwatch_alarm

/**
 * Created by Genson on 12/02/2018.
 */
import android.annotation.SuppressLint
import android.os.Handler
import android.os.SystemClock
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView


class StopWatchFragment : Fragment() {

    var handler: Handler? = null
    var hour: TextView? = null
    var minute: TextView? = null
    var seconds: TextView? = null
    var milli_seconds: TextView? = null

    internal var MillisecondTime: Long = 0
    internal var StartTime: Long = 0
    internal var TimeBuff: Long = 0
    internal var UpdateTime = 0


    internal var Seconds: Int = 0
    internal var Minutes: Int = 0
    internal var MilliSeconds: Int = 0
    internal var Hour: Int = 0

    internal var flag: Boolean = true

    var startButton: ImageButton? = null


    @SuppressLint("WrongViewCast")
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val rootView = inflater!!.inflate(R.layout.fragment_stopwatch, container, false)

        bindViews(rootView)


        return rootView
    }

    @SuppressLint("WrongViewCast")
    private fun bindViews(view: View) {
        startButton = view.findViewById(R.id.startButton)
        hour = view.findViewById(R.id.hour)
        minute = view.findViewById(R.id.minute)
        seconds = view.findViewById(R.id.seconds)
        milli_seconds = view.findViewById(R.id.milli_second)



        startButton?.setOnClickListener {
            if (flag == false) {
                TimeBuff += MillisecondTime
                handler?.removeCallbacks(runnable)
                startButton?.setImageResource(R.drawable.ic_play_arrow_black_24dp)
                flag = true
            } else if (flag == true) {
                startButton?.setImageResource(R.drawable.ic_pause_black_24dp)
                StartTime = SystemClock.uptimeMillis()
                handler?.postDelayed(runnable, 0)
                flag = false
            }

        }

        handler = Handler()

    }

    var runnable: Runnable = object : Runnable {

        @SuppressLint("SetTextI18n")
        override fun run() {

            MillisecondTime = SystemClock.uptimeMillis() - StartTime

            UpdateTime = (TimeBuff + MillisecondTime).toInt()

            Seconds = (UpdateTime / 1000)

            Minutes = Seconds / 60

            Seconds = Seconds % 60

            MilliSeconds = (UpdateTime % 1000)


            if (Hour.toString().length < 2) {
                hour?.text = "0" + Hour.toString()
            } else {
                hour?.text = Hour.toString()
            }
            if (Minutes.toString().length < 2) {
                minute?.text = "0" + Minutes.toString()
            } else {
                minute?.text = Minutes.toString()
            }
            if (Seconds.toString().length < 2) {
                seconds?.text = "0" + Seconds.toString()
            } else {
                seconds?.text = Seconds.toString()
            }

            milli_seconds?.text = MilliSeconds.toString()

            handler?.postDelayed(this, 0)
        }
    }
}
