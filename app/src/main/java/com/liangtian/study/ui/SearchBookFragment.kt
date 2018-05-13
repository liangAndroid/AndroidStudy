package com.liangtian.study.ui

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.liangtian.study.R
import com.liangtian.study.api.BookService
import com.liangtian.study.bean.SearchBooks
import com.liangtian.study.di.Injectable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * A fragment representing a list of Items.
 * Activities containing this fragment MUST implement the
 * [SearchBookFragment.OnListFragmentInteractionListener] interface.
 */
class SearchBookFragment : Fragment(),Injectable {
    private lateinit var query: String
    private var listener: OnListFragmentInteractionListener? = null
    @Inject
    lateinit var bookService: BookService
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            query = it.getString(ARG_QUERY)
        }
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        bookService.searchBooks(query!!)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    if(!isDetached) {
                        if (view is RecyclerView) {
                            ((view as RecyclerView).adapter as MySearchBookRecyclerViewAdapter).update(it.books)
                        }
                    }
                }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_searchbook_list, container, false)
        // Set the adapter
        if (view is RecyclerView) {
            with(view) {
                layoutManager = LinearLayoutManager(context)
                adapter = MySearchBookRecyclerViewAdapter(null, listener)
            }
        }
        return view
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnListFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnListFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson
     * [Communicating with Other Fragments](http://developer.android.com/training/basics/fragments/communicating.html)
     * for more information.
     */
    interface OnListFragmentInteractionListener {
        fun onItemClick(item: SearchBooks.SearchBook)
    }

    companion object {
        const val ARG_QUERY = "query_text"

        @JvmStatic
        fun newInstance(query: String) =
                SearchBookFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_QUERY, query)
                    }
                }
    }
}
