package com.wazunga.findit.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.wazunga.findit.R
import com.wazunga.findit.fragments.ConnectedFragment
import com.wazunga.findit.fragments.NotConnectedFragment
import com.wazunga.findit.utils.Keys
import com.wazunga.nhulox97.findit.utils.Network


class NetworkActivity : AppCompatActivity() {

    private lateinit var connectedFragment: ConnectedFragment
    private lateinit var notConnectedFragment: NotConnectedFragment
    private val keys = Keys()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_network)

        initComponents()

        if (Network.networkExists(this))
            setView(keys.CONNECTED)
        else
            setView(keys.NOT_CONNECTED)
    }

    private fun initComponents() {
        connectedFragment = ConnectedFragment()
        notConnectedFragment =
            NotConnectedFragment()
    }

    private fun setView(option: String) {
        when (option) {
            keys.CONNECTED -> {
                replaceFragment(connectedFragment)
            }
            keys.NOT_CONNECTED -> {
                replaceFragment(notConnectedFragment)
            }
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        val manager = supportFragmentManager
        val transaction = manager.beginTransaction()
        transaction.replace(R.id.fragment_container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}
