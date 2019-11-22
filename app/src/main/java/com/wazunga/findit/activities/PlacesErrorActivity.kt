package com.wazunga.findit.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.wazunga.findit.R
import com.wazunga.findit.fragments.NotFoundFragment
import com.wazunga.findit.fragments.ServerErrorFragment
import com.wazunga.findit.utils.RequestKeys

class PlacesErrorActivity : AppCompatActivity() {

    private lateinit var notFoundFragment: NotFoundFragment
    private lateinit var serverErrorFragment: ServerErrorFragment
    private lateinit var mIntent: Bundle
    private val rk = RequestKeys()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_places_error)

        initComponents()
        setView(mIntent.getString(rk.TAG_QUEUE)!!)
    }

    private fun initComponents() {
        notFoundFragment = NotFoundFragment()
        serverErrorFragment = ServerErrorFragment()
        mIntent = intent.extras!!
    }

    private fun setView(status: String) {
        when (status) {
            rk.CODE_400 -> {
                replaceFragment(notFoundFragment)
            }
            rk.CODE_500 -> {
                replaceFragment(serverErrorFragment)
            }
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        val manager = supportFragmentManager
        val transaction = manager.beginTransaction()
        transaction.replace(R.id.container_error, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

}
