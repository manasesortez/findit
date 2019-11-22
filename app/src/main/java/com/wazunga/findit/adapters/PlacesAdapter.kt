package com.wazunga.findit.adapters

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.wazunga.findit.R
import com.wazunga.nhulox97.findit.models.Place

/*
* Created by wazunga.nhulox97 on 20/11/19.
*/

class PlacesAdapter : RecyclerView.Adapter<PlacesAdapter.ViewHolder>() {
    var places: ArrayList<Place> = ArrayList()
    lateinit var context: Context
    lateinit var vh: ViewHolder

    fun placesAdapter(places: ArrayList<Place>, context: Context) {
        this.context = context
        this.places = places

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(layoutInflater.inflate(R.layout.item_place_list, parent, false))
    }

    override fun getItemCount(): Int {
        return places.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = places[position]
        holder.bind(item, context)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val placeName = view.findViewById(R.id.tv_place_name) as TextView
        val rating = view.findViewById(R.id.tv_rating) as TextView
        val vicinity = view.findViewById(R.id.tv_vicinity) as TextView
        val openInMap = view.findViewById(R.id.tv_open_in_map) as TextView
        val openNow = view.findViewById(R.id.tv_open_now) as TextView


        fun bind(place: Place, context: Context) {
            placeName.text = place.name
            rating.text = place.rating.toString()
            vicinity.text = place.vicinity
            if (place.opening_hours.open_now)
                openNow.text = "open"
            else {
                openNow.text = "closed"
            }
            openNow.text = place.opening_hours.open_now.toString()
            openInMap.text = "Open in map"
            openInMap.setOnClickListener {
                val gmmIntentUri =
                    Uri.parse(
                        "geo:${place.geometry.location.lat}," +
                                "${place.geometry.location.lng}?q=${place.geometry.location.lat}," +
                                "${place.geometry.location.lng}"
                    )
                val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
                startActivity(context, mapIntent, Bundle())
            }
        }
    }
}