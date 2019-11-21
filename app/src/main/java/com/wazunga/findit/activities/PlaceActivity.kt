package com.wazunga.findit.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.wazunga.findit.R
import com.wazunga.findit.adapters.PlacesAdapter
import com.wazunga.nhulox97.findit.models.Place
import com.wazunga.nhulox97.findit.models.Result

class PlaceActivity : AppCompatActivity() {

    lateinit var mRecyclerView: RecyclerView
    val mAdapter: PlacesAdapter = PlacesAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_place)

        val extras = intent.extras
        val res = extras?.getString("response")
        val places = getPlaces(res!!)

        setUpRecyclerView(places)
    }

    private fun setUpRecyclerView(places: ArrayList<Place>) {
        mRecyclerView = findViewById(R.id.rv_place_list) as RecyclerView
        mRecyclerView.setHasFixedSize(true)
        mRecyclerView.layoutManager = LinearLayoutManager(this)
        mAdapter.placesAdapter(places, this)
        mRecyclerView.adapter = mAdapter
    }

    private fun getPlaces(res: String): ArrayList<Place> {
        val result = Gson().fromJson(res, Result::class.java)
        return result.results
    }
}
