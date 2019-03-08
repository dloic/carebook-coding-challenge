package com.jrd.carebook

import com.google.gson.JsonParser
import com.jrd.carebook.model.Constants
import org.junit.Assert.assertEquals
import org.junit.Test
import java.io.BufferedReader
import java.io.FileInputStream
import java.io.IOException
import java.io.InputStreamReader


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class JsonConsistencyUnitTest {
    @Throws(IOException::class)
    fun readJsonFile(filename: String): String {
        val br = BufferedReader(InputStreamReader(FileInputStream(filename)))
        val sb = StringBuilder()
        var line = br.readLine()
        while (line != null) {
            sb.append(line)
            line = br.readLine()
        }
        return sb.toString()
    }

    /**
     * Just make sure that the response from the server is a valid JSON object
     */
    @Test
    fun jsonIsDecodedWell() {
        val jsonLocation = Constants.TEST_JSON_FOLDER
        val parser = JsonParser()
        val jsonFile = readJsonFile(jsonLocation)
        val parse = parser.parse(jsonFile)
        val birthDate = parse.asJsonObject.get("birthDate").asString
        val active = parse.asJsonObject.get("active").asBoolean
        val deceasedBoolean = parse.asJsonObject.get("deceasedBoolean").asBoolean
        assertEquals(birthDate, "1924-10-10")
        assertEquals(active, true)
        assertEquals(deceasedBoolean, false)
    }
}
