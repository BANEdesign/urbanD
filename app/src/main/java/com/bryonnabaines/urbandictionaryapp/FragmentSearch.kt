package com.bryonnabaines.urbandictionaryapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bryonnabaines.urbandictionaryapp.api.Api
import com.bryonnabaines.urbandictionaryapp.api.Word
import com.bryonnabaines.urbandictionaryapp.api.WordViewModel
import javax.inject.Inject
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_search.*
import kotlinx.android.synthetic.main.fragment_search.view.*


class FragmentSearch : Fragment(), WordsListAdapter.OnItemClickListener {

    @Inject
    lateinit var api: Api
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    lateinit var viewModel: WordViewModel
    lateinit var recyclerView: RecyclerView
    private var wordsListAdapter: WordsListAdapter? = null
    private var sortOption = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        AndroidSupportInjection.inject(this)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(WordViewModel::class.java)
        wordsListAdapter = WordsListAdapter(this)
        arguments?.let { bundle ->
            sortOption = bundle.getInt("sort_by")
        }
        viewModel.wordLiveData.observe( this, Observer { wordsList ->
            toggleProgressBar(false)
            if (wordsList.list != null) {
                sortBy(sortOption, wordsList.list)
            } else {
                androidx.appcompat.app.AlertDialog.Builder(context!!) //todo remove !!
                    .setMessage("error loading words list please try again")
            }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.wordsRecyclerView
        setupAdapter()
        searchView.queryHint = "Search Urban Dictionary"
        searchView.isIconfiedByDefault
        searchView.isSubmitButtonEnabled = true
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                getDefinitions(query)
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                // do nothing if not submitted
                return false
            }
        })

    }

    private fun sortBy(sortOption: Int, wordsList: List<Word>) {
        val sortedList = mutableListOf<Word>()
        when (sortOption) {
            -1 -> {
                // Default not sorted
                sortedList.addAll(wordsList)
            }
            MOST_LIKES -> {
                sortedList.addAll(wordsList.sortedByDescending { it.thumbsUp })
                Toast.makeText(context, "Sorting By Most Likes!", Toast.LENGTH_SHORT).show()
            }
            MOST_UNLIKES -> {
                sortedList.addAll(wordsList.sortedByDescending { it.thumbsDown })
                Toast.makeText(context, "Sorting By Most UnLikes!", Toast.LENGTH_SHORT).show()
            }
        }
        wordsListAdapter?.addWords(sortedList)
        wordsListAdapter?.notifyDataSetChanged()
    }

    private fun getDefinitions(term: String) {
        if (term.isNotBlank()) viewModel.getDefinition(api, term)
        toggleProgressBar(true)
    }

    private fun toggleProgressBar(show: Boolean) {
        when (show) {
            true ->  progressBar.visibility = View.VISIBLE

            false -> progressBar.visibility = View.GONE
        }
    }

    private fun setupAdapter() {
        recyclerView.layoutManager = LinearLayoutManager(this.context)
        recyclerView.adapter = wordsListAdapter
    }

    override fun onItemClick(item: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    companion object {
        const val MOST_LIKES = 0
        const val MOST_UNLIKES = 1
    }
}
