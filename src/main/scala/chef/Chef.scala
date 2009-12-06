package deploylib.chef

import deploylib._

/**
 * Contains helper methods for services deployed using Chef.
 */
object Chef {
  
}

/**
 * A framework for managing services that are deployed using Chef. 
 */
abstract class ChefService(remoteMachine: RemoteMachine) extends Service(remoteMachine) {

  /**
   * The name of the chef recipe used to deploy this service.
   */
  val recipeName: String

  /**
   * The JSON config used to deploy this service.
   */
  var jsonConfig: JSONObject

}