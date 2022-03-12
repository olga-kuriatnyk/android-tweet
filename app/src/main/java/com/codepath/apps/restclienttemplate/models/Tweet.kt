package com.codepath.apps.restclienttemplate.models

import com.codepath.apps.restclienttemplate.TimeFormatter
import org.json.JSONArray
import org.json.JSONObject

class Tweet {

    var body: String = ""
    var createAt: String = ""
    var user: User? = null
    var timeStamp: String = ""

    companion object {
        fun fromJson(jsonObject: JSONObject) : Tweet {
            val tweet = Tweet()
            tweet.body = jsonObject.getString("text")
            tweet.createAt = jsonObject.getString("created_at")
            tweet.user = User.fromJson(jsonObject.getJSONObject("user"))
            tweet.timeStamp = getFormattedTimestamp(jsonObject.getString("created_at"))  // jsonObject.getString("created_at")
            return tweet
        }

        fun getFormattedTimestamp(createdAt: String) : String {

            return TimeFormatter.getTimeDifference(createdAt)
        }

        fun fromJsonArray(jsonArray: JSONArray): List<Tweet> {
            val tweets = ArrayList<Tweet>()

            for (i in 0 until jsonArray.length()) {
                tweets.add(fromJson(jsonArray.getJSONObject(i)))
            }
            return tweets
        }
    }
}