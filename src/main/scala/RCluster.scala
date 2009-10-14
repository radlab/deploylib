package deploylib

import java.io.File
import scala.util.matching.Regex

class RClusterNode(num: Int) extends RemoteMachine with RunitController {
	val hostname = "r" + num + ".millennium.berkeley.edu"
	val username = "marmbrus"
	val rootDirectory = new File("/scratch/marmbrus/")
	val privateKey = new File("/Users/marmbrus/.ssh/id_rsa")
	val runitBinaryPath = new File("/work/marmbrus/runit")

	def setupRunit() {
		executeCommand("mkdir -p " + serviceRoot) match {
			case ExecuteResponse(Some(0), "", "") => logger.debug("Service Directory Created")
			case e: ExecuteResponse => logger.fatal("Unexpected execution result while creating service directory: " + e)
		}

		val runsvdirCommand = new Regex("runsvdir " + serviceRoot)

		executeCommand("ps ax | grep runsvdir") match {
			case ExecuteResponse(Some(0), whatsRunning, "") => {
				runsvdirCommand.findFirstIn(whatsRunning) match {
					case Some(_) => logger.debug("runsvdir already running on " + this)
					case None => {
						logger.debug("Starting runsv since we only found " + whatsRunning)
						logger.debug(executeCommand("/bin/bash -c \"PATH=" + runitBinaryPath + " /usr/bin/nohup " + runsvdirCmd + " " + serviceRoot + "\"&"))
					}
				}
			}
			case e: ExecuteResponse => logger.fatal("Unexpected execution result while checking for runsvdir: " + e) 
		}
	}
}

object RCluster {
	val nodes = (1 to 40).toList.map(new RClusterNode(_))

	def activeNodes() = {
		val check = nodes.map((n) => (n, Future(n.executeCommand("hostname"))))
		Thread.sleep(5000)
		check.filter((c) => c._2.isDone && c._2.success).map((c) => c._1)
	}
}

object r1 extends RClusterNode(1)
object r2 extends RClusterNode(2)
object r3 extends RClusterNode(3)
object r4 extends RClusterNode(4)
object r5 extends RClusterNode(5)
object r6 extends RClusterNode(6)
object r7 extends RClusterNode(7)
object r8 extends RClusterNode(8)
object r9 extends RClusterNode(9)
object r10 extends RClusterNode(10)
object r11 extends RClusterNode(11)
object r12 extends RClusterNode(12)
object r13 extends RClusterNode(13)
object r14 extends RClusterNode(14)
object r15 extends RClusterNode(15)
object r16 extends RClusterNode(16)
object r17 extends RClusterNode(17)
object r18 extends RClusterNode(18)
object r19 extends RClusterNode(19)
object r20 extends RClusterNode(20)
object r21 extends RClusterNode(21)
object r22 extends RClusterNode(22)
object r23 extends RClusterNode(23)
object r24 extends RClusterNode(24)
object r25 extends RClusterNode(25)
object r26 extends RClusterNode(26)
object r27 extends RClusterNode(27)
object r28 extends RClusterNode(28)
object r29 extends RClusterNode(29)
object r30 extends RClusterNode(30)
object r31 extends RClusterNode(31)
object r32 extends RClusterNode(32)
object r33 extends RClusterNode(33)
object r34 extends RClusterNode(34)
object r35 extends RClusterNode(35)
object r36 extends RClusterNode(36)
object r37 extends RClusterNode(37)
object r38 extends RClusterNode(38)
object r39 extends RClusterNode(39)
object r40 extends RClusterNode(40)

