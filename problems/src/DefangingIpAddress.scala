// https://leetcode.com/problems/defanging-an-ip-address/

object DefangingIpAddress extends App {
  def defangIPaddr(address: String): String = {
     address.replace(".", "[.]")
  }

  println(defangIPaddr("1.1.1.1"))
  println(defangIPaddr("255.100.50.0"))
}
