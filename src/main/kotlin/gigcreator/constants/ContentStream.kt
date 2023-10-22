package gigcreator.constants

import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody
import java.io.InputStream
import java.io.OutputStream

class ContentStream(private val input: InputStream,
                    private var count: Long,
                    private val maxBufferSize: Int = 1024 * 1024): StreamingResponseBody {

    override fun writeTo(output: OutputStream) {
        try {
            val buffer = ByteArray(if (count < maxBufferSize) count.toInt() else maxBufferSize)
            while (count > 0L) {
                val amount = input.read(buffer, 0, if (count < buffer.size) count.toInt() else buffer.size)
                if (amount < 0) return
                output.write(buffer, 0, amount)
                output.flush()
                count -= amount
            }
        } finally {
            input.close()
        }
    }
}