package com.example.faridaziz.kopinema.repo

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Response
import org.json.JSONObject
import java.io.IOException

/**
 * @class [BoardRepository]
 * Merupakan untuk menunjukkan repository
 * yang biasanya digunakan untuk mengirimkan data.
 */

class BoardRepository {
    var data = MutableLiveData<String>()
    val service = Service()

    fun send(params: Map<String, String>): LiveData<String> {
        service.sending(params)
                .enqueue(listenService)

        return data
    }

    private val listenService = object : Callback {
        override fun onFailure(call: Call, e: IOException) {
            Log.e(javaClass.simpleName, "Response ERROR")
            Log.e(javaClass.simpleName, ""+e.message)
        }

        override fun onResponse(call: Call, response: Response) {
            if (response.body() != null) {
                val res = response.body()!!.string()
                val resObj = JSONObject(res)

                data.postValue(resObj.getString("response"))
            }
        }
    }
}