import java.io.DataOutputStream
import java.io.IOException
import java.io.InputStream
import java.net.URL
import javax.net.ssl.HttpsURLConnection
import org.apache.commons.io.IOUtils

object InitialPaymentKLARNA_INSTALLMENTS {
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
				+ "&paymentBrand=KLARNA_INSTALLMENTS"
				+ "&paymentType=PA"
				+ "&customer.givenName=Jane"
				+ "&customer.surname=Jones"
				+ "&billing.country=SE"
				+ "&cart.items[0].merchantItemId=1" 
				+ "&cart.items[0].discount=0.00"	
				+ "&cart.items[0].quantity=5"
				+ "&cart.items[0].name=Product 1"
				+ "&cart.items[0].price=1.00"
				+ "&cart.items[0].tax=6.00"
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