import java.io.DataOutputStream
import java.io.IOException
import java.io.InputStream
import java.net.URL
import javax.net.ssl.HttpsURLConnection
import org.apache.commons.io.IOUtils

object InitialPaymentGIROPAY {
	def main(args: Array[String]) {
		println(initialPayment);
	}
	def initialPayment : String = {
	  val url = "https://test.oppwa.com/v1/payments"
	 
	  val data = ("authentication.userId=8a8294174b7ecb28014b9699220015cc"
				+ "&authentication.password=sy6KJsT8" 
				+ "&authentication.entityId=8a8294184c0378fe014c046e80340da9"
				+ "&amount=92.12"
				+ "&currency=EUR"
				+ "&paymentBrand=GIROPAY"
				+ "&paymentType=PA"
				+ "&bankAccount.bic=TESTDETT421"
				+ "&bankAccount.iban=DE14940593100000012346"
				+ "&bankAccount.country=DE"
				+ "&shopperResultUrl=https://docs.oppwa.com"
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