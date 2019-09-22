package com.example.clientedeprueba

import android.content.Intent
import android.media.AudioManager
import android.media.MediaPlayer
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    var number = 0
    var advi = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        val url = "http://192.168.1.38:40000/restaurante.mp3" // your URL here
        val mediaPlayer: MediaPlayer? = MediaPlayer().apply {
            setAudioStreamType(AudioManager.STREAM_MUSIC)
            setDataSource(url)
            prepare()
            start()
        }

        menuButton.setOnClickListener {
            var intent = Intent(this, SecondActivity::class.java)
            startActivity(intent)
        }

        decideButton.setOnClickListener{
            if (number<6){
                requestToServer()
            } else {
                number=0
            }

        }

        dietFoodButton.setOnClickListener{
            if (advi<5){
                requestToServerSecond()
            } else {
                advi=0
            }
        }
    }

    private fun requestToServer(){
        // Instantiate the RequestQueue with the cache and network. Start the queue.
        val Queue = Volley.newRequestQueue(this)

        val url = "http://192.168.1.38:40000/food"

        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, url, null,
            Response.Listener { response ->
                var respuesta = response.getJSONArray("list").getJSONObject(number).getString("food")
                number++
                selectedFoodTxt.text=respuesta
            },
            Response.ErrorListener { error ->
                // TODO: Handle error
            }
        )

        Queue.add(jsonObjectRequest)


    }

    private fun requestToServerSecond(){
        // Instantiate the RequestQueue with the cache and network. Start the queue.
        val Queue = Volley.newRequestQueue(this)

        val url = "http://192.168.1.38:40000/advice"

        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, url, null,
            Response.Listener { response ->
                var dietresponse = response.getJSONArray("list").getJSONObject(advi).getString("advice")
                advi++
                dietFoodTxt.text=dietresponse
            },
            Response.ErrorListener { error ->
                // TODO: Handle error
            }
        )

        Queue.add(jsonObjectRequest)


    }




    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
