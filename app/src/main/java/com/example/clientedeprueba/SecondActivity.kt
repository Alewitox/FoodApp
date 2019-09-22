package com.example.clientedeprueba

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley

import kotlinx.android.synthetic.main.activity_second.*
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.android.synthetic.main.content_second.*

class SecondActivity : AppCompatActivity() {

    var one = 0
    var two = 0
    var three = 0
    var four = 0



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)



        firstButton.setOnClickListener{
            if (one<3){
                requestToServerOne()
            } else {
                one=0
            }

        }

        secondButton.setOnClickListener{
            if (two<3){
                requestToServerTwo()
            } else {
                two=0
            }
        }
        thirdButton.setOnClickListener{
            if (three<3){
                requestToServerThree()
            } else {
                three=0
            }
        }
        fourthButton.setOnClickListener{
            if (four<3){
                requestToServerFourth()
            } else {
                four=0
            }
        }
        chicoteButton.setOnClickListener {
            requestToServerChicote()
        }
    }



    private fun requestToServerOne(){
        // Instantiate the RequestQueue with the cache and network. Start the queue.
        val Queue = Volley.newRequestQueue(this)

        val url = "http://192.168.1.38:40000/first"

        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, url, null,
            Response.Listener { response ->
                var respuestaOne = response.getJSONArray("list").getJSONObject(one).getString("first")
                one++
                FirstView.text=respuestaOne
            },
            Response.ErrorListener { error ->
                // TODO: Handle error
            }
        )

        Queue.add(jsonObjectRequest)


    }

    private fun requestToServerTwo(){
        // Instantiate the RequestQueue with the cache and network. Start the queue.
        val Queue = Volley.newRequestQueue(this)

        val url = "http://192.168.1.38:40000/second"

        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, url, null,
            Response.Listener { response ->
                var respuestaTwo = response.getJSONArray("list").getJSONObject(two).getString("second")
                two++
                secondView.text=respuestaTwo
            },
            Response.ErrorListener { error ->
                // TODO: Handle error
            }
        )

        Queue.add(jsonObjectRequest)


    }

    private fun requestToServerThree(){
        // Instantiate the RequestQueue with the cache and network. Start the queue.
        val Queue = Volley.newRequestQueue(this)

        val url = "http://192.168.1.38:40000/third"

        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, url, null,
            Response.Listener { response ->
                var respuestaThree = response.getJSONArray("list").getJSONObject(three).getString("third")
                three++
                ThirdView.text=respuestaThree
            },
            Response.ErrorListener { error ->
                // TODO: Handle error
            }
        )

        Queue.add(jsonObjectRequest)


    }

    private fun requestToServerFourth(){
        // Instantiate the RequestQueue with the cache and network. Start the queue.
        val Queue = Volley.newRequestQueue(this)

        val url = "http://192.168.1.38:40000/fourth"

        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, url, null,
            Response.Listener { response ->
                var respuestaFour = response.getJSONArray("list").getJSONObject(four).getString("fourth")
                four++
                fourthView.text=respuestaFour
            },
            Response.ErrorListener { error ->
                // TODO: Handle error
            }
        )

        Queue.add(jsonObjectRequest)


    }


    private fun requestToServerChicote(){
        // Instantiate the RequestQueue with the cache and network. Start the queue.
        val Queue2 = Volley.newRequestQueue(this)

        val url = "http://192.168.1.38:40000/chicote"

        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, url, null,
            Response.Listener { response ->
                var respuestaChicote = response.getString("chef")
                textView5.setText(respuestaChicote)
            },
            Response.ErrorListener { error ->
                // TODO: Handle error
            }
        )

        Queue2.add(jsonObjectRequest)


    }
}
