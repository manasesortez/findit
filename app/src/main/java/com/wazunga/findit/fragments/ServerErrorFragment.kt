package com.wazunga.findit.fragments


import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.airbnb.lottie.LottieAnimationView
import com.wazunga.findit.R
import com.wazunga.findit.activities.CategoryActivity

/**
 * A simple [Fragment] subclass.
 */
class ServerErrorFragment : Fragment() {

    private lateinit var animation500: LottieAnimationView
    private lateinit var btn500: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_server_error, container, false)

        initComponents(view)

        btn500.setOnClickListener {
            startActivity(
                Intent(
                    context,
                    CategoryActivity::class.java
                )
            )
        }

        return view
    }


    private fun initComponents(view: View?) {
        btn500 = view!!.findViewById(R.id.btn_500)
        animation500 = view.findViewById(R.id.animation_server_error)

        animation500.repeatCount = 50
        animation500.speed = 1.0F
        animation500.pauseAnimation()
        animation500.playAnimation()
    }

    override fun onDetach() {
        super.onDetach()
        animation500.cancelAnimation()
    }

    override fun onStop() {
        super.onStop()
        animation500.pauseAnimation()
    }

    override fun onResume() {
        super.onResume()
        animation500.resumeAnimation()
    }
}
