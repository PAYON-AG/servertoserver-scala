import java.io.DataOutputStream
import java.io.IOException
import java.io.InputStream
import java.net.URL
import javax.net.ssl.HttpsURLConnection
import org.apache.commons.io.IOUtils

object InitialPaymentBRADESCO {
	def main(args: Array[String]) {
		println(initialPayment);
	}
	def initialPayment : String = {
	  val url = "https://test.oppwa.com/v1/payments"
	 
	  val data = ("authentication.userId=8a8294174b7ecb28014b9699220015cc"
				+ "&authentication.password=sy6KJsT8" 
				+ "&authentication.entityId=8a8294174b7ecb28014b9699a3cf15d1"
				+ "&amount=1.03"
				+ "&currency=BRL"
				+ "&paymentBrand=BRADESCO"
				+ "&paymentType=PA"
				+ "&customer.givenName=Braziliano"
				+ "&customer.surname=Babtiste"
				+ "&billing.city=Brasilia"
				+ "&billing.country=BR"
				+ "&billing.state=DE"
				+ "&billing.street1=Amazonstda"
				+ "&billing.postcode=12345678"
				+ "&customParameters[BRADESCO_cpfsacado]=11111111111"
				+ "&shopperResultUrl=https://docs.oppwa.com"
				+ "&testMode=EXTERNAL"
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