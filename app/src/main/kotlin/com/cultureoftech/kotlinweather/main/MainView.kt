package com.cultureoftech.kotlinweather.main

import android.content.Context
import android.support.design.widget.CoordinatorLayout
import android.support.v4.view.MenuItemCompat
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.SearchView
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.widget.FrameLayout
import com.cultureoftech.kotlinweather.R
import com.cultureoftech.kotlinweather.ui.ViewRoot
import com.cultureoftech.kotlinweather.utils.bindView
import com.cultureoftech.kotlinweather.weather.CityWeatherResponse
import timber.log.Timber
import javax.inject.Inject

/**
 * Created by bgogetap on 2/19/16.
 */
class MainView(context: Context?, mainScreen: MainScreen) :
        CoordinatorLayout(context), SwipeRefreshLayout.OnRefreshListener, MainContract.View, SearchView.OnQueryTextListener, ViewRoot {

    @Inject lateinit var presenter: MainPresenter

    val swipeRefreshLayout: SwipeRefreshLayout by bindView(R.id.swipe_refresh_layout)
    val container: FrameLayout by bindView(R.id.content_container)

    private var searchView: SearchView? = null
    private var searchItem: MenuItem? = null

    lateinit var weatherView: WeatherView

    init {
        LayoutInflater.from(context).inflate(R.layout.view_main, this, true);
        mainScreen.component()?.inject(this)
        swipeRefreshLayout.setOnRefreshListener(this)
        weatherView = WeatherView(context!!)
        container.addView(weatherView)
        weatherView.setOnClickListener { presenter.loadDetailsForCity() }
    }

    override fun onAttachedToWindow() {
        presenter.takeView(this)
        super.onAttachedToWindow()
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        presenter.dropView(this)
    }

    override fun setData(response: CityWeatherResponse?) {
        swipeRefreshLayout.isRefreshing = false
        weatherView.setData(response)
    }

    override fun initSearch(menu: Menu?) {
        searchItem = menu?.findItem(R.id.menu_search)
        searchView = MenuItemCompat.getActionView(searchItem) as SearchView
        searchView?.isSubmitButtonEnabled = true
        searchView?.setOnQueryTextListener(this)
    }

    override fun onQueryTextChange(query: String?): Boolean {
        return true
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        Timber.d(query)
        if (query != null && query.isNotEmpty()) {
            presenter.loadDataForCity(query)
        }
        return true
    }

    override fun onRefresh() {
        presenter.loadData(true)
    }

    override fun getTitle(): String {
        return context.getString(R.string.app_name)
    }
}