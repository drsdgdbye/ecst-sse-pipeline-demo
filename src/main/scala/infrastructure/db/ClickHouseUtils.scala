package dm.drsdgdbye
package infrastructure.db

import com.zaxxer.hikari.{HikariConfig, HikariDataSource}

import java.sql.ResultSet
import javax.sql.DataSource
import scala.concurrent.{ExecutionContext, Future}
import scala.util.Using

object ClickHouseUtils {
	type DsUrl = String
	type DsDriver = String
	
	def connect(url: DsUrl, driver: DsDriver): HikariDataSource = {
		val ds = new HikariDataSource({
			val conf = new HikariConfig()
			conf.setJdbcUrl(url)
			conf.setDriverClassName(driver)
			conf
		})
		ds
	}
	
	def executeQuery[T](ds: DataSource)(query: String)(task: ResultSet => T)(implicit ec: ExecutionContext): Future[T] = Future {
		Using(ds.getConnection)(conn => task(conn.createStatement.executeQuery(query))).get
	}
}
