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
class NotFoundFragment : Fragment() {

    private lateinit var animation404: LottieAnimationView
    private lateinit var btn404: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_not_found, container, false)

        initComponents(view)

        btn404.setOnClickListener {
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
        btn404 = view!!.findViewById(R.id.btn_404)
        animation404 = view.findViewById(R.id.animation_not_found)

        animation404.repeatCount = 50
        animation404.speed = 1.0F
        animation404.pauseAnimation()
        animation404.playAnimation()
    }

    override fun onDetach() {
        super.onDetach()
        animation404.cancelAnimation()
    }

    override fun onStop() {
        super.onStop()
        animation404.pauseAnimation()
    }

    override fun onResume() {
        super.onResume()
        animation404.resumeAnimation()
    }

}
