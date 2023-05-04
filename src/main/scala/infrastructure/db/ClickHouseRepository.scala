package dm.drsdgdbye
package infrastructure.db

import domain.TrackSegment.unpackResultSet
import domain.{ObservableId, TrackSegment}

import com.typesafe.config.ConfigFactory
import com.zaxxer.hikari.HikariDataSource

import scala.concurrent.{ExecutionContext, Future}

object ClickHouseRepository extends Repository[TrackSegment] {
	private val config = ConfigFactory.load()
	private val dbConfig = config.getConfig("db")
	
	val ds: HikariDataSource = ClickHouseUtils.connect(
		dbConfig.getString("url"),
		dbConfig.getString("driver")
		)
	override def findAllBy(begin: String, ending: String, ooId: ObservableId)(implicit ec: ExecutionContext): Future[List[TrackSegment]] = {
		ClickHouseUtils.executeQuery(ds)(
			s"""
				 |select * from telemetry where time between toDateTime('$begin') and
				 |toDateTime('$ending') and
				 |carrier = $ooId order by time;
				 |""".stripMargin
			)(unpackResultSet)
	}
	
	override def insert(t: TrackSegment)(implicit ec: ExecutionContext): Future[Int] = {
		ClickHouseUtils.executeQuery(ds)(
			s"""
				 |insert into telemetry;
				 |""".stripMargin
		)(unpackResultSet).map(_ => 1)
	}
}
