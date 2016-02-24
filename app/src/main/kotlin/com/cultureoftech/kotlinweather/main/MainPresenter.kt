package com.cultureoftech.kotlinweather.main

import android.content.Context
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.support.design.widget.CoordinatorLayout
import android.support.design.widget.Snackbar
import android.view.Menu
import android.view.MenuItem
import com.cultureoftech.kotlinweather.R
import com.cultureoftech.kotlinweather.base.ActivityPresenter
import com.cultureoftech.kotlinweather.base.ActivityResultDelegate
import com.cultureoftech.kotlinweather.dagger.ForMain
import com.cultureoftech.kotlinweather.details.DetailsScreen
import com.cultureoftech.kotlinweather.permissions.Permission
import com.cultureoftech.kotlinweather.permissions.PermissionHelper
import com.cultureoftech.kotlinweather.ui.PresenterRoot
import com.cultureoftech.kotlinweather.ui.UiManager
import com.cultureoftech.kotlinweather.weather.CityWeatherResponse
import timber.log.Timber
import javax.inject.Inject

/**
 * Created by bgogetap on 2/20/16.
 */
@ForMain
class MainPresenter @Inject constructor(
        val sharedPrefs: SharedPreferences,
        val uiManager: UiManager,
        val permissionHelper: PermissionHelper,
        val loader: WeatherLoader,
        val activityPresenter: ActivityPresenter
) : PresenterRoot<MainView>(), MainContract.Presenter, ActivityResultDelegate {

    private var data: CityWeatherResponse? = null
    private var city: String = ""

    override fun viewAttached() {
        activityPresenter.registerResultDelegate(this)
        activityPresenter.resetToolbarMenu()
        city = sharedPrefs.getString("last_city", "Omaha")
        loadData(false)
    }

    override fun loadData(forceRefresh: Boolean) {
        if (data == null || forceRefresh) {
            loadDataForCity(city)
        } else {
            setData()
        }
    }

    override fun loadDataForCity(city: String) {
        val query = city.replace(" ", "_")
        sharedPrefs.edit().putString("last_city", query)
        loader.getWeatherForCity(query).subscribe({
            Timber.d(it.toString())
            data = it
            setData()
        }, { Timber.e("Error loading weather", it.message) })
    }

    override fun loadDataForLocation(location: Location) {
        loader.getWeatherForLocation(location.latitude, location.longitude).subscribe({
            Timber.d(it.toString())
            data = it
            sharedPrefs.edit().putString("last_city", it.name)
            setData()
        }, { Timber.e("Error loading weather from location", it.message) })
    }

    override fun loadDetailsForCity() {
        val cityId: Long = data?.id ?: -1L
        if (cityId != -1L) {
            uiManager.push(DetailsScreen(cityId, data?.name))
        }
        //TODO please try again with a different city
    }

    private fun setData() {
        getView()?.setData(data)
    }

    override fun onCreateOptionsMenu(menu: Menu?) {
        if (!uiManager.isTopScreen(MainScreen::class.java)) return
        getView()?.initSearch(menu)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if (permissions[0].equals(Permission.LOCATION.permissionString)) {
            val granted = grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED
            if (granted) {
                getLocation()
            } else {
                if (getView() != null) {
                    Snackbar.make(getView() as CoordinatorLayout,
                            R.string.cant_provide_location_based_weather, Snackbar.LENGTH_LONG)
                            .show()
                }
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.menu_location -> {
                getLocation()
                return true
            }
            R.id.menu_refresh -> loadData(true)
        }
        return false
    }

    private fun getLocation() {
        if (permissionHelper.hasPermission(Permission.LOCATION)) {
            val locationManager: LocationManager = getView()?.context?.getSystemService(Context.LOCATION_SERVICE) as LocationManager
            val lastKnownLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
            if (lastKnownLocation != null) {
                loadDataForLocation(lastKnownLocation)
            } else {
                // TODO "Try searching for a city"
            }
        } else {
            permissionHelper.requestPermission(Permission.LOCATION)
        }
    }

    override fun viewDetached() {
        activityPresenter.unregisterResultDelegate(this)
    }
}