package com.cultureoftech.kotlinweather.base

import android.view.Menu
import android.view.MenuItem

/**
 * Created by bgogetap on 2/21/16.
 */
interface ActivityResultDelegate {

    fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray);

    fun onOptionsItemSelected(item: MenuItem?): Boolean;

    fun onCreateOptionsMenu(menu: Menu?);
}