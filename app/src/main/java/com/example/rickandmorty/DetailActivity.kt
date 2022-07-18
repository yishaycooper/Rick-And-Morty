package com.example.rickandmorty
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider


class DetailActivity : AppCompatActivity() {

    val viewModel: SharedViewModel by lazy {
        ViewModelProvider(this).get(SharedViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val name = findViewById<TextView>(R.id.name_detail)
        val status = findViewById<TextView>(R.id.status_detail)
        val species = findViewById<TextView>(R.id.species_detail)
        val gender = findViewById<TextView>(R.id.gender_detail)
        val character = intent.extras?.get("UserObj") as? com.example.rickandmorty.network.Character
        val origin = findViewById<TextView>(R.id.origin_name)

        name.text = character?.name
        status.text = character?.status
        species.text = character?.species
        gender.text = character?.gender
        origin.text = character?.origin?.name

        val locationName = findViewById<TextView>(R.id.location_name)
        val locationDimension = findViewById<TextView>(R.id.location_dimension)
        val locationCreated = findViewById<TextView>(R.id.location_created)


        if (character != null) {
            viewModel.fetchLocation(character.id)
        }

        viewModel.location.observe(this) { response ->
            if (response == null) {
                Toast.makeText(this, "Unsuccessful network call!", Toast.LENGTH_SHORT)
                    .show()
                return@observe
            }

            response?.let {
                locationName.text = it.name
                locationDimension.text = it.dimension
                locationCreated.text = it.created
            }
        }
    }

    override fun finish() {
        super.finish()
            this.overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
    }


}