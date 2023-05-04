package dm.drsdgdbye

import app.SseServer
import infrastructure.sse.SseApi

import akka.actor.typed.ActorSystem
import akka.actor.typed.scaladsl.Behaviors
import slick.jdbc.JdbcBackend.Database

import scala.concurrent.ExecutionContext

object Main extends App {
	implicit val system: ActorSystem[Nothing] = ActorSystem(Behaviors.empty, "work-permit")
	implicit val ec: ExecutionContext = system.executionContext
	
	SseServer(
		SseApi()
	)
}
