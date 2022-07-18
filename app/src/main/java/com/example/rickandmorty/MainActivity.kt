package com.example.rickandmorty
import android.os.Bundle
import android.view.Menu
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.rickandmorty.network.Character
import java.util.*


class MainActivity : AppCompatActivity() {

    var cachedList = ArrayList<Character>()
    var displayList = ArrayList<Character>()


    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: MainAdapter

    val viewModel: SharedViewModel by lazy {
        ViewModelProvider(this).get(SharedViewModel::class.java)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(findViewById(R.id.my_toolbar))


        viewModel.fetchCharacters("1") // Coroutine call

        viewModel.characters.observe(this) { response ->
            if (response == null) {
                Toast.makeText(this@MainActivity, "Unsuccessful network call!", Toast.LENGTH_SHORT)
                    .show()
                return@observe
            }

            if (response != null) {
                cachedList.addAll(response.results)
                displayList.addAll(response.results)
            }

            response?.let {
                adapter = MainAdapter(this@MainActivity, displayList)
                recyclerView = findViewById<RecyclerView>(R.id.charactersRv)
                recyclerView?.layoutManager =
                    StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
                recyclerView?.adapter = adapter
            }
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        val menuItem = menu?.findItem(R.id.action_search)


        if (menuItem != null) {
            val searchView = menuItem.actionView as SearchView

            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    searchView.clearFocus()
                    searchView.setQuery("", false)
                    searchView.onActionViewCollapsed()
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    if (newText!!.isNotEmpty()) {
                        displayList.clear()
                        val search = newText.toLowerCase(Locale.getDefault())

                        cachedList.forEach {
                            if (it.name.toLowerCase(Locale.getDefault()).contains(search))
                                displayList.add(it)
                        }
                        recyclerView?.adapter?.notifyDataSetChanged()
                    } else {
                        displayList.clear()
                        displayList.addAll(cachedList)

                        recyclerView?.adapter?.notifyDataSetChanged()
                    }
                    return true
                }

            })
        }

        return super.onCreateOptionsMenu(menu)
    }

}