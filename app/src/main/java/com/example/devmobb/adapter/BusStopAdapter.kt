package com.example.devmobb.adapter

import BusStop
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.devmobb.R
import com.example.devmobb.ui.home.StationMapsActivity
import currentBusStopLocation
import busStopSelected

class BusStopAdapter(private val busStop:List<BusStop>, private val context: Context) : RecyclerView.Adapter<BusStopAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val cardView : CardView = itemView.findViewById(R.id.cardView)
        var name : TextView = itemView.findViewById(R.id.name)
        val distance : TextView = itemView.findViewById(R.id.distance)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_view_busstop, parent, false)
        return ViewHolder(view)
    }

    // Pour chaque view_id on met a jour les composants de la view (cardView: CardView, name:TextView)
    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val busStop = busStop[position]
        holder.name.text = busStop.stop_name

        if(currentBusStopLocation != null) {
            holder.distance.text = "${String.format("%.1f", currentBusStopLocation!!.distanceTo(busStop!!.toLocation())/1000)}KM"
        } else {
            holder.distance.text = "- KM"
        }

        /*if(station.availableBikes != null && 0 == station.availableBikes.toInt()) {
            holder.name.setTextColor(context.getColor(R.color.empty_bike))
        }*/

        holder.cardView.setOnClickListener {
            val intent = Intent(context, StationMapsActivity::class.java)
            intent.putExtra("station", busStop.stop_name)
            busStopSelected = busStop
            context.startActivity(intent)
        }
    }

    // On retourne le nombre d'élements dans la liste des stations
    override fun getItemCount(): Int {
        return busStop.size
    }
}