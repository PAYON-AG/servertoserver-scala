import java.io.DataOutputStream
import java.io.IOException
import java.io.InputStream
import java.net.URL
import javax.net.ssl.HttpsURLConnection
import org.apache.commons.io.IOUtils

object asyncStatus {
	def main(args: Array[String]) {
		println(getPaymentStatus("8a8294494ce19bdf014ce55532d32b92"));
	}
	def getPaymentStatus(paymentId: String) : String  = {
	  val url = "https://test.oppwa.com/v1/payments/" + paymentId  +
			"?authentication.userId=8a8294174b7ecb28014b9699220015cc" +
			"&authentication.password=sy6KJsT8" +
			"&authentication.entityId=8a8294174b7ecb28014b9699a3cf15d1"
	 
	  val conn = new URL(url).openConnection().asInstanceOf[HttpsURLConnection]
	  conn.setRequestMethod("GET")

	  conn.connect()
	  if (conn.getResponseCode() >= 400) {
		return IOUtils.toString(conn.getErrorStream())
	  }
	  else {
		return IOUtils.toString(conn.getInputStream())
	  }
	}
}