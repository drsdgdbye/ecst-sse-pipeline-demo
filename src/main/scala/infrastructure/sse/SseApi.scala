package dm.drsdgdbye
package infrastructure.sse

import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.{ExceptionHandler, Route}
import spray.json.DeserializationException

case class SseApi() {
	def route: Route =
		Route.seal(
			pathPrefix("api" / "v1") {
				get {
					complete(StatusCodes.OK)
				}
			}
		)
	
	implicit def exceptionHandler: ExceptionHandler =
		ExceptionHandler {
			case ex: DeserializationException =>
				complete(StatusCodes.BadRequest, ex.msg)
		}
}
