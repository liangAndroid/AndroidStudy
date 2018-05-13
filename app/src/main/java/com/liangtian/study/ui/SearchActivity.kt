package com.liangtian.study.ui

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.SearchView
import android.text.TextUtils
import android.view.Menu
import android.widget.Toast
import com.liangtian.study.R
import com.liangtian.study.bean.SearchBooks
import com.liangtian.study.di.Injectable
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import kotlinx.android.synthetic.main.activity_search.*
import javax.inject.Inject

class SearchActivity : AppCompatActivity(), Injectable, SearchBookFragment.OnListFragmentInteractionListener, HasSupportFragmentInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    override fun supportFragmentInjector(): AndroidInjector<Fragment> {
        return dispatchingAndroidInjector
    }

    override fun onItemClick(item: SearchBooks.SearchBook) {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        setSupportActionBar(toolbar)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.search, menu)
        val searchView = menu!!.findItem(R.id.action_search).actionView as SearchView
        searchView.queryHint = getString(R.string.search_hint)
        searchView.setOnQueryTextFocusChangeListener { v, b ->
            Toast.makeText(this, b.toString(), Toast.LENGTH_SHORT).show()
        }
        searchView.isIconified = false
        searchView.onActionViewExpanded()
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (TextUtils.isEmpty(query)) {
                    return true
                }
                supportFragmentManager.beginTransaction().replace(R.id.fl_fragment, SearchBookFragment.newInstance(query!!)).commit()
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
//                Toast.makeText(applicationContext, newText, Toast.LENGTH_SHORT).show()
                return true
            }
        })
        searchView.setOnSearchClickListener {
            Toast.makeText(applicationContext, searchView.query, Toast.LENGTH_SHORT).show()
        }
        return true
    }
}
