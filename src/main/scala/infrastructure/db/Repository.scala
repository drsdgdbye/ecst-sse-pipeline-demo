package dm.drsdgdbye
package infrastructure.db

import domain.ObservableId

import javax.sql.DataSource
import scala.concurrent.{ExecutionContext, Future}

trait Repository[T] {
	val ds: DataSource
	
	def findAllBy(begin: String, ending: String, ooId: ObservableId)(ec: ExecutionContext): Future[Seq[T]]
	
	def insert(t: T)(ec: ExecutionContext): Future[Int]
}
