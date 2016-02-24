package com.cultureoftech.kotlinweather.details

import com.cultureoftech.kotlinweather.dagger.ForDetails
import com.cultureoftech.kotlinweather.ui.PresenterRoot
import com.cultureoftech.kotlinweather.ui.UiManager
import com.cultureoftech.kotlinweather.utils.StringHelper
import com.cultureoftech.kotlinweather.weather.CityForecastResponse
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Named

/**
 * Created by bgogetap on 2/21/16.
 */
@ForDetails
class DetailsPresenter @Inject constructor(
        @Named("cityId") val cityId: Long,
        val forecastLoader: ForecastLoader,
        val uiManager: UiManager,
        val stringHelper: StringHelper
): PresenterRoot<DetailsView>(), DetailsContract.Presenter {

    var data: CityForecastResponse? = null

    override fun viewAttached() {
        loadData()
    }

    override fun loadData() {
        if (data == null) {
            forecastLoader.getForecastForCity(cityId).subscribe({
                data = it
                Timber.d(it.toString())
                Timber.d(it.list[0].getDate().toString())
            }, { Timber.e("Error loading forecast", it.stackTrace)}, { setData() })
        } else {
            setData()
        }
    }

    fun setData() {
        getView()?.setData(data)
    }

    override fun viewDetached() {

    }
}