package com.cultureoftech.kotlinweather.details

import android.content.Context
import android.support.design.widget.CoordinatorLayout
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.cultureoftech.kotlinweather.R
import com.cultureoftech.kotlinweather.ui.ViewRoot
import com.cultureoftech.kotlinweather.utils.ResourcesHelper
import com.cultureoftech.kotlinweather.utils.bindView
import com.cultureoftech.kotlinweather.weather.CityForecastResponse
import javax.inject.Inject

/**
 * Created by bgogetap on 2/21/16.
 */
class DetailsView(context: Context, val screen: DetailsScreen) :
        CoordinatorLayout(context), DetailsContract.View, ViewRoot {

    @Inject lateinit var presenter: DetailsPresenter

    var cityName: String?

    val container by bindView<LinearLayout>(R.id.content_container)

    init {
        LayoutInflater.from(context).inflate(R.layout.view_details, this, true)
        screen.component()?.inject(this)
        cityName = screen.component()?.cityName()
        setBackgroundColor(ResourcesHelper.getColor(context, R.color.colorPrimaryLight))
    }

    override fun setData(response: CityForecastResponse?) {
        response?.list?.forEach {
            val view = ForecastItemView(context, screen)
            view.setData(it)
            container.addView(view)
        }
    }

    override fun getTitle(): String {
        return context.getString(R.string.forecast_format, cityName)
    }

    override fun onAttachedToWindow() {
        presenter.takeView(this)
        super.onAttachedToWindow()
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        presenter.dropView(this)
    }

}
