package com.wazunga.findit.fragments


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.airbnb.lottie.LottieAnimationView
import com.wazunga.findit.R
import com.wazunga.findit.activities.Slide1

/**
 * A simple [Fragment] subclass.
 */
class ConnectedFragment : Fragment() {

    private lateinit var animationConnected: LottieAnimationView
    private lateinit var demoButton: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(
            R.layout.fragment_connected, container,
            false
        )

        initComponents(view)

        demoButton.setOnClickListener { startActivity(Intent(context, Slide1::class.java)) }

        return view
    }

    private fun initComponents(view: View?) {
        demoButton = view!!.findViewById(R.id.start_demo)
        animationConnected = view.findViewById(R.id.animation_not_connected)

        animationConnected.repeatCount = 50
        animationConnected.speed = 1.0F
        animationConnected.pauseAnimation()
        animationConnected.playAnimation()
    }

    override fun onDetach() {
        super.onDetach()
        animationConnected.cancelAnimation()
    }

    override fun onStop() {
        super.onStop()
        animationConnected.pauseAnimation()
    }

    override fun onResume() {
        super.onResume()
        animationConnected.resumeAnimation()
    }
}
