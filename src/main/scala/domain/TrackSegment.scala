package dm.drsdgdbye
package domain

import java.sql.{ResultSet, Timestamp}
import scala.collection.mutable.ArrayBuffer

case class TrackSegment(
	                         id: TrackSegmentId
	                       , time: Timestamp
	                       , speed: Speed
	                       , alt: Altitude
	                       , pressure: Pressure
	                       , direction: Direction
	                       , activity: Activity
	                       , end: End
	                       , start: Start
	                       , observableId: ObservableId
	                       , coordX: CoordX
	                       , coordY: CoordY
                       )

object TrackSegment {
	def unpackResultSet(res: ResultSet): List[TrackSegment] = {
		val tracks = new ArrayBuffer[TrackSegment]()
		var id = 0
		
		while (res.next()) {
			val track = new TrackSegment(
				id
				, res.getTimestamp("time")
				, res.getFloat("speed")
				, res.getDouble("alt")
				, res.getDouble("pressure")
				, res.getFloat("direction")
				, res.getFloat("activity")
				, res.getBoolean("end")
				, res.getBoolean("start")
				, res.getInt("carrier")
				, res.getDouble("x_wgs84")
				, res.getDouble("y_wgs84")
				)
			id += 1
			tracks += track
		}
		tracks.toList
	}
}

