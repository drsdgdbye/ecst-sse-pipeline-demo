import Dependencies.Versions._
import sbt._
object Dependencies {
	object Versions {
		val AkkaTyped = "2.8.0"
		val AkkaHttp = "10.5.1"
		val Slick = "3.4.1"
		val CH = "0.4.5"
		val HikariCP = "5.0.1"
		val Logback = "1.4.6"
		val KafkaIntgr = "0.1.5"
		val Scalatest = "3.2.15"
	}
	
	// akka platform
	val akkaActorTyped = "com.typesafe.akka" %% "akka-actor-typed" % AkkaTyped
	val akkaHttp = "com.typesafe.akka" %% "akka-http" % AkkaHttp
	val akkaStreamTyped = "com.typesafe.akka" %% "akka-stream-typed" % AkkaTyped
	
	// db mapping
	val clickHouse = "com.clickhouse" % "clickhouse-jdbc" % CH
	val hikariCP = "com.zaxxer" % "HikariCP" % Versions.HikariCP
	val slick = "com.typesafe.slick" %% "slick" % Slick
	
	val kafkaIntegration = "bims" %% "kafka-integration" % KafkaIntgr
	
	// json support
	val sprayJson = "com.typesafe.akka" %% "akka-http-spray-json" % AkkaHttp
	
	// logging
	val logback = "ch.qos.logback" % "logback-classic" % Versions.Logback
	
	// testing
	val scalatest = "org.scalatest" %% "scalatest" % Versions.Scalatest % Test
	
	val implDependencies: Seq[ModuleID] = Seq(
		akkaActorTyped,
		akkaHttp,
		akkaStreamTyped,
		slick,
		clickHouse,
		hikariCP,
		kafkaIntegration,
		sprayJson,
		logback,
		scalatest
	)
}
