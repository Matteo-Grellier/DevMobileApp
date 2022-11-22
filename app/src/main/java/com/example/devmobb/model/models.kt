import android.location.Location
import kotlinx.serialization.*

var currentLocation: Location? = null
var stationSelected:Station? = null
var allStations:List<Station>? = null

@Serializable
data class Station (
    val id: Long,
    val name: String,
    val status: String,
    val recordId: String,
    val lattitude: Double,
    val longitude: Double,
    val bikeStands: Long,
    val address: String,
    val availableBikes: Long,
    val availableBikeStands: Long
) {
    fun toLocation(): Location {
        val location = Location("")
        location.latitude = lattitude
        location.longitude = longitude
        return location
    }

    fun showDetails(): CharSequence? {
        return "🚲${availableBikes} 📣${availableBikes} ✅${bikeStands}"
    }
}

var currentBusStopLocation: Location? = null
var busStopSelected:BusStop? = null
var allBusStop:List<BusStop>? = null

data class BusStop (
    val stop_id: String,
    val stop_name: String,
    val stop_coordinates: Double,
    val latitude: Double,
    val longitude: Double,
){
    fun toLocation(): Location {
        val location = Location("")
        location.latitude = latitude
        location.longitude = longitude
        return location
    }
}