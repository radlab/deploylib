package deploylib

import scala.concurrent.SyncVar

object Future {
	def apply[A](f: => A): Future[A] = new Future(f)
}


class Future[A](f: => A) {
	val result = new SyncVar[Either[A, Throwable]]
	val thread = new Thread {override def run() = {result.set(tryCatch(f))}}
	thread.start()

	def isDone: Boolean = {
		result.isSet
	}

	def success: Boolean = {
		result.get match {
			case Left(a) => true
			case Right(t) => false
		}
	}

	def apply(): A = {
		result.get match {
			case Left(a) => a
			case Right(t) => throw t
		}
	}

	private def tryCatch[A](left: => A): Either[A, Throwable] = {
		try {
			Left(left)
		} catch {
			case t => Right(t)
		}
	}

	override def toString(): String = {
		val result = new StringBuilder
		result.append("<Future ")
		if(isDone){
			result.append("completed ")
			if(success)
				result.append("sucessfully")
			else
				result.append("with exception")
		}
		else
			result.append("running")
		result.append(">")
		result.toString()
	}
}