import java.io.DataOutputStream
import java.io.IOException
import java.io.InputStream
import java.net.URL
import javax.net.ssl.HttpsURLConnection
import org.apache.commons.io.IOUtils

object RegistrationDIRECTDEBIT_SEPA {
	def main(args: Array[String]) {
		println(registration);
	}
	def registration : String = {
	  val url = "https://test.oppwa.com/v1/registrations"
	 
	  val data = ("authentication.userId=8a8294174b7ecb28014b9699220015cc"
				+ "&authentication.password=sy6KJsT8"
				+ "&authentication.entityId=8a8294174b7ecb28014b9699a3cf15d1"
				+ "&paymentBrand=DIRECTDEBIT_SEPA"
				+ "&bankAccount.bic=MARKDEF1100"
				+ "&bankAccount.iban=DE23100000001234567890"
				+ "&bankAccount.country=DE"
				+ "&bankAccount.holder=Jane Jones"
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