package kz.ticker.android.base

object Constant {

    /**
     * Endpoint
     */
    var BASE_URL: String = "http://newsapi.org/"


    /**
     * Connection timeout duration
     */
    const val CONNECT_TIMEOUT = (60 * 1000).toLong()
    /**
     * Connection Read timeout duration
     */
    const val READ_TIMEOUT = (60 * 1000).toLong()
    /**
     * Connection write timeout duration
     */
    const val WRITE_TIMEOUT = (60 * 1000).toLong()


    const val DEFAULT_PER_PAGE_COUNT = "20"


    const val PLACEHOLDER_IMG_URL = "http://www.stleos.uq.edu.au/wp-content/uploads/2016/08/image-placeholder-350x350.png"

}