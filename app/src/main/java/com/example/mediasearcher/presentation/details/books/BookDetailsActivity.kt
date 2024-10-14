package com.example.mediasearcher.presentation.details.books

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.mediasearcher.R
import com.example.mediasearcher.databinding.BookDetailsActivityLayoutBinding
import com.example.mediasearcher.utils.BOOK_ID_TAG
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class BookDetailsActivity : AppCompatActivity() {

    private lateinit var layout: BookDetailsActivityLayoutBinding
    private val viewModel: BookDetailsViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        Timber.d("BookDetailsActivity_TAG: onCreate: ")
        super.onCreate(savedInstanceState)
        layout = DataBindingUtil.setContentView(this, R.layout.book_details_activity_layout)
        layout.viewModel = viewModel
        layout.lifecycleOwner = this

        getBookID()
        initViews()
    }

    private fun getBookID() {
        Timber.d("BookDetailsActivity_TAG: getBookID: ")
        val id = intent.extras?.getString(BOOK_ID_TAG)
        id?.apply {
            viewModel.getBookById(id)
        }
    }

    private fun initViews() {
        Timber.d("BookDetailsActivity_TAG: initViews: ")
        layout.tvIsbn.setOnLongClickListener {
            val textView = it as TextView
            copyTextToClipboard(textView.text.toString())
            true
        }
    }

    private fun copyTextToClipboard(text: String) {
        Timber.d("BookDetailsActivity_TAG: copyTextToClipboard: $text ")
        val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clip = ClipData.newPlainText(getString(R.string.txt_isbn), text)
        clipboard.setPrimaryClip(clip)

        Toast.makeText(this, getString(R.string.text_copied_to_clipboard), Toast.LENGTH_SHORT)
            .show()
    }
}