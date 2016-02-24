package com.cultureoftech.kotlinweather.base

import android.content.pm.PackageManager.PERMISSION_GRANTED
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import com.cultureoftech.kotlinweather.permissions.Permission
import com.cultureoftech.kotlinweather.permissions.PermissionHelper
import com.cultureoftech.kotlinweather.permissions.PermissionRationaleDialog
import com.cultureoftech.kotlinweather.utils.VersionHelper
import javax.inject.Inject

/**
 * Created by bgogetap on 2/16/16.
 */
open class BaseActivity: AppCompatActivity(), PermissionHelper.Delegate {

    @Inject lateinit var permissionHelper: PermissionHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MyApplication.component.inject(this)
        permissionHelper.takeView(this)
    }

    // Permissions
    override fun hasPermission(permission: Permission): Boolean {
        return VersionHelper.isBelowMarshmallow() ||
                ContextCompat.checkSelfPermission(this, permission.permissionString) == PERMISSION_GRANTED
    }

    override fun shouldShowRationale(permission: Permission): Boolean {
        return ActivityCompat.shouldShowRequestPermissionRationale(this, permission.permissionString)
    }

    override fun requestPermission(permission: Permission) {
        permissionHelper.dialogShowing = true
        ActivityCompat.requestPermissions(this, arrayOf(permission.permissionString), permission.id)
    }

    override fun showRationaleDialog(permission: Permission) {
        val dialog = PermissionRationaleDialog.newInstance(permission)
        dialog.show(supportFragmentManager, "rationale_dialog")
        permissionHelper.dialogShowing = true
    }

    override fun goToAppSystemSettings() {
        //TODO
    }

    override fun onDestroy() {
        permissionHelper.dropView(this)
        super.onDestroy()
    }
}