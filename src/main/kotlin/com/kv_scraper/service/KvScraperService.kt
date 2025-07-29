package com.kv_scraper.service

import com.kv_scraper.model.PropertyTick
import com.kv_scraper.model.dto.KvPropertyDataDTO
import java.io.IOException
import okhttp3.OkHttpClient
import okhttp3.Request
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.springframework.stereotype.Service

@Service
class KvScraperService {

    private val httpClient = OkHttpClient()

    fun scrapeAndSavePropertyLog(propertyTick: PropertyTick) {
        val document = scrapeDocumentWithOkHttp(propertyTick.url) ?: return
        val KvPropertyDataDTO = parseDocument(document)
    }

    fun scrapeDocumentWithOkHttp(url: String): Document? {
        val request = Request.Builder()
            .url(url)
            .header("User-Agent", "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/135.0.0.0 Safari/537.36")
            .build()

        return try {
            httpClient.newCall(request).execute().use { response ->
                if (!response.isSuccessful) {
                    println("HTTP error scraping $url: ${response.code}")
                    return null
                }
                val body = response.body?.string()
                if (body != null) Jsoup.parse(body) else null
            }
        } catch (e: Exception) {
            println("Error scraping page $url: ${e.message}")
            null
        }
    }


    private fun parseDocument(document: Document): KvPropertyDataDTO {
        val price = document.selectFirst("div.label.campaign")?.attr("data-price")
        val thAction = document.selectFirst(".meta-table .table-lined th")?.text()
        val isReserved = thAction?.contains("Broneeritud") == true

        return KvPropertyDataDTO(
            price = price?.toDouble() ?: 0.0,
            isReserved = isReserved,
        )
    }

    companion object {

    }
}