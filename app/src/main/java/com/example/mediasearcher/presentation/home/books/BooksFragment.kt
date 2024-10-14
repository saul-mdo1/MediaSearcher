package com.example.mediasearcher.presentation.home.books

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mediasearcher.R
import com.example.mediasearcher.databinding.BooksFragmentLayoutBinding
import com.example.mediasearcher.presentation.details.books.BookDetailsActivity
import com.example.mediasearcher.utils.BOOK_ID_TAG
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class BooksFragment : Fragment() {

    private lateinit var layout: BooksFragmentLayoutBinding
    private val viewModel: BooksViewModel by viewModel()
    private lateinit var booksAdapter: BooksRVAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Timber.d("BooksFragment_TAG: onCreateView: ")
        setBinding(layoutInflater, container)
        initObservers()
        initViews()
        return layout.root
    }

    private fun setBinding(inflater: LayoutInflater, container: ViewGroup?) {
        Timber.d("BooksFragment_TAG: setBinding: ")
        layout = DataBindingUtil.inflate(
            inflater,
            R.layout.books_fragment_layout,
            container,
            false
        )
        layout.viewModel = viewModel
        layout.lifecycleOwner = requireActivity()
    }

    private fun initObservers() {
        Timber.d("BooksFragment_TAG: initObservers: ")
        viewModel.booksList.observe(requireActivity()) { books ->
            if (books.isEmpty()) {
                viewModel.emptyList.postValue(true)
                return@observe
            }

            booksAdapter.itemList = books.map {
                BookItemViewModel().apply {
                    book = it
                }
            }
        }
    }

    private fun initViews() {
        Timber.d("BooksFragment_TAG: initViews: ")
        viewModel.loading.postValue(false)

        booksAdapter = BooksRVAdapter { bookItem ->
            val intent = Intent(activity, BookDetailsActivity::class.java)
            val bundle = Bundle().apply {
                putString(BOOK_ID_TAG, bookItem.id)
            }
            intent.putExtras(bundle)
            startActivity(intent)
        }

        layout.rvBooks.apply {
            layoutManager = LinearLayoutManager(requireActivity(), RecyclerView.VERTICAL, false)
            adapter = booksAdapter
        }

        initSearchView()
    }

    private fun initSearchView() {
        Timber.d("BooksFragment_TAG: initSearchView: ")
        layout.svBook.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText.isNullOrBlank()) {
                    viewModel.emptyList.postValue(true)
                    viewModel.booksList.postValue(emptyList())
                    viewModel.totalVisible.postValue(false)
                    initViews()
                }
                return true
            }

            override fun onQueryTextSubmit(query: String?): Boolean {
                if (!query.isNullOrBlank()) {
                    viewModel.loading.postValue(true)
                    viewModel.searchBooks(query)
                    layout.svBook.clearFocus()
                }
                return true
            }
        })
    }
}