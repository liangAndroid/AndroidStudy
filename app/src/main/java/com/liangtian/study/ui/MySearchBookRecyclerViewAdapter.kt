package com.liangtian.study.ui


import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.liangtian.study.R
import com.liangtian.study.bean.SearchBooks
import com.liangtian.study.ui.SearchBookFragment.OnListFragmentInteractionListener
import kotlinx.android.synthetic.main.fragment_searchbook.view.*

/**
 *
 */
class MySearchBookRecyclerViewAdapter(
        private var mValues: List<SearchBooks.SearchBook>?,
        private val mListener: OnListFragmentInteractionListener?)
    : RecyclerView.Adapter<MySearchBookRecyclerViewAdapter.ViewHolder>() {

    private val mOnClickListener: View.OnClickListener

    init {
        mOnClickListener = View.OnClickListener { v ->
            val item = v.tag as SearchBooks.SearchBook
            // Notify the active callbacks interface (the activity, if the fragment is attached to
            // one) that an item has been selected.
            mListener?.onItemClick(item)
        }
    }


    fun update(books: List<SearchBooks.SearchBook>){
        mValues = books
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.fragment_searchbook, parent, false)
        return ViewHolder(view)
    }



    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mValues!![position]
        Glide.with(holder.mView.context).load("http://statics.zhuishushenqi.com" + item.cover).into(holder.mView.icon)
        holder.mView.title.text = item.title
        holder.mView.author.text = item.author
        with(holder.mView) {
            tag = item
            setOnClickListener(mOnClickListener)
        }
    }

    override fun getItemCount(): Int = mValues?.size ?: 0

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView)
}
