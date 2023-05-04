package dm.drsdgdbye
package app

import akka.actor.typed.ActorSystem
import infrastructure.sse.SseApi

import akka.http.scaladsl.Http

import scala.concurrent.ExecutionContextExecutor
import scala.concurrent.duration.{DurationInt, FiniteDuration}
import scala.util.{Failure, Success}

object SseServer {
	def apply(api: SseApi)(implicit system: ActorSystem[_]): Unit = {
		implicit val ec: ExecutionContextExecutor = system.executionContext
		val logger = system.log
		
		val config = system.settings.config.getConfig("akka.http.server")
		val host = config.getString("host")
		val port = config.getInt("port")
		
		val shutdownDeadline: FiniteDuration = 10.seconds
		
		Http().newServerAt(host, port)
			.bind(api.route)
			.map(_.addToCoordinatedShutdown(shutdownDeadline))
			.foreach { server =>
				logger.info(s"HTTP service listening on: ${ server.localAddress }")
				
				server.whenTerminationSignalIssued.onComplete { _ =>
					logger.info("Shutdown of HTTP service initiated")
				}
				
				server.whenTerminated.onComplete {
					case Success(_) => logger.info("Shutdown of HTTP endpoint completed")
					case Failure(_) => logger.error("Shutdown of HTTP endpoint failed")
				}
			}
	}
}
