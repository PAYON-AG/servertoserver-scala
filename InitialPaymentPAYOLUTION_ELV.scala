import java.io.DataOutputStream
import java.io.IOException
import java.io.InputStream
import java.net.URL
import javax.net.ssl.HttpsURLConnection
import org.apache.commons.io.IOUtils

object InitialPaymentPAYOLUTION_ELV {
	def main(args: Array[String]) {
		println(initialPayment);
	}
	def initialPayment : String = {
	  val url = "https://test.oppwa.com/v1/payments"
	 
	  val data = ("authentication.userId=8a8294174b7ecb28014b9699220015cc"
				+ "&authentication.password=sy6KJsT8" 
				+ "&authentication.entityId=8a8294174b7ecb28014b9699a3cf15d1"
				+ "&amount=92.12"
				+ "&currency=EUR"
				+ "&paymentBrand=PAYOLUTION_ELV"
				+ "&paymentType=DB"
				+ "&customer.givenName=Jane"
				+ "&customer.surname=Jones"
				+ "&customer.email=test@test.com"
				+ "&customer.ip=123.123.123.123"
				+ "&billing.city=Munich"
				+ "&billing.country=DE"
				+ "&billing.street1=123 Street"
				+ "&billing.postcode=A1 2BC"
				+ "&customParameters[PAYOLUTION_ITEM_PRICE_1]=2.00"
				+ "&customParameters[PAYOLUTION_ITEM_DESCR_1]=Test item #1"
				+ "&customParameters[PAYOLUTION_ITEM_PRICE_2]=3.00"
				+ "&customParameters[PAYOLUTION_ITEM_DESCR_2]=Test item #2"
				+ "&testMode=EXTERNAL"
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