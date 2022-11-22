package com.example.devmobb.adapter

import Station
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.devmobb.R
import com.example.devmobb.ui.home.StationMapsActivity
import com.example.devmobb.ui.station.StationDetailActivity
import currentLocation
import stationSelected
import kotlin.math.roundToInt

class StationAdapter(private val stations:List<Station>, private val context: Context) : RecyclerView.Adapter<StationAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val cardView : CardView = itemView.findViewById(R.id.cardView)
        var name : TextView = itemView.findViewById(R.id.name)
        val address : TextView = itemView.findViewById(R.id.address)
        val status : ImageView = itemView.findViewById(R.id.status)
        val availability : TextView = itemView.findViewById(R.id.availability)
        val distance : TextView = itemView.findViewById(R.id.distance)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_view_station, parent, false)
        return ViewHolder(view)
    }

    // Pour chaque view_id on met a jour les composants de la view (cardView: CardView, name:TextView)
    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val station = stations[position]
        holder.name.text = station.name
        holder.address.text = station.address
        holder.availability.text = "🚲${station.availableBikes} 📣${station.availableBikes} ✅${station.bikeStands}"

        if(currentLocation != null) {
            holder.distance.text = "${String.format("%.1f", currentLocation!!.distanceTo(station!!.toLocation())/1000)}KM"
        } else {
            holder.distance.text = "- KM"
        }

        if("OPEN" == station.status) {
            holder.status.setImageResource(R.drawable.ic_baseline_radio_button_checked_24)
        } else {
            holder.status.setImageResource(R.drawable.ic_baseline_radio_button_unchecked_24)
        }

        /*if(station.availableBikes != null && 0 == station.availableBikes.toInt()) {
            holder.name.setTextColor(context.getColor(R.color.empty_bike))
        }*/

        holder.cardView.setOnClickListener {
            val intent = Intent(context, StationMapsActivity::class.java)
            intent.putExtra("station", station.name)
            stationSelected = station
            context.startActivity(intent)
        }
    }

    // On retourne le nombre d'élements dans la liste des stations
    override fun getItemCount(): Int {
        return stations.size
    }
}