import java.io.DataOutputStream
import java.io.IOException
import java.io.InputStream
import java.net.URL
import javax.net.ssl.HttpsURLConnection
import org.apache.commons.io.IOUtils

object BackofficeREVERSAL {
	def main(args: Array[String]) {
		println(backofficeOperation);
	}
	def backofficeOperation: String = {
	  val url = "https://test.oppwa.com/v1/payments/8a82944a4cdca5cc014ce0799bcf501e"
	 
	  val data = ("authentication.userId=8a8294174b7ecb28014b9699220015cc"
				+ "&authentication.password=sy6KJsT8" 
				+ "&authentication.entityId=8a8294174b7ecb28014b9699a3cf15d1"
				+ "&paymentType=RV"
	  ) 
	  val conn = new URL(url).openConnection()

	  conn match {
		case secureConn: HttpsURLConnection  => secureConn.setRequestMethod("POST")
		case _ => throw new ClassCastException
	  }
	  conn.setDoInput(true)
	  conn.setDoOutput(true)
	  IOUtils.write(data, conn.getOutputStream())
	  conn.connect()
	 
	  return IOUtils.toString(conn.getInputStream())
	}
}