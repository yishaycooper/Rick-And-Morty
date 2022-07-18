package com.example.rickandmorty
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.rickandmorty.databinding.ActivityDetailBinding


class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding

    val viewModel: SharedViewModel by lazy {
        ViewModelProvider(this).get(SharedViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val character = intent.extras?.get("UserObj") as? com.example.rickandmorty.network.Character

        binding.nameDetail.text = character?.name
        binding.statusDetail.text = character?.status
        binding.speciesDetail.text = character?.species
        binding.genderDetail.text = character?.gender
        binding.originName.text = character?.origin?.name


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
                binding.locationName.text = it.name
                binding.locationDimension.text = it.dimension
                binding.locationCreated.text = it.created
            }
        }
    }

    override fun finish() {
        super.finish()
            this.overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
    }


}