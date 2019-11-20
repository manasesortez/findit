package com.wazunga.findit.fragments


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.airbnb.lottie.LottieAnimationView
import com.wazunga.findit.activities.NetworkActivity
import com.wazunga.findit.R

/**
 * A simple [Fragment] subclass.
 */
class NotConnectedFragment : Fragment() {

    private lateinit var animationNotConnected: LottieAnimationView
    private lateinit var tryAgainButton: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(
            R.layout.fragment_not_connected, container,
            false
        )

        initComponents(view)

        tryAgainButton.setOnClickListener {
            startActivity(Intent(context, NetworkActivity::class.java))
        }

        return view
    }

    private fun initComponents(view: View?) {
        animationNotConnected = view!!.findViewById(R.id.animation_not_connected)
        tryAgainButton = view.findViewById(R.id.try_again)

        animationNotConnected.repeatCount = 50
        animationNotConnected.speed = 1.0F
        animationNotConnected.pauseAnimation()
        animationNotConnected.playAnimation()
    }

    override fun onDetach() {
        super.onDetach()
        animationNotConnected.cancelAnimation()
    }

    override fun onStop() {
        super.onStop()
        animationNotConnected.pauseAnimation()
    }

    override fun onResume() {
        super.onResume()
        animationNotConnected.resumeAnimation()
    }
}
