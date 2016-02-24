package com.cultureoftech.kotlinweather.permissions

import com.cultureoftech.kotlinweather.base.ActivityPresenter
import com.cultureoftech.kotlinweather.ui.PresenterRoot
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by bgogetap on 2/21/16.
 */
@Singleton
class PermissionHelper @Inject constructor(val activityPresenter: ActivityPresenter) :
        PresenterRoot<PermissionHelper.Delegate>(), PermissionRationaleDialog.PermissionRationaleListener {

    var dialogShowing: Boolean = false

    fun hasPermission(permission: Permission): Boolean {
        return getView()?.hasPermission(permission) ?: false
    }

    fun requestPermission(permission: Permission) {
        if (!dialogShowing) {
            requestOrShowRationale(permission)
        }
    }

    private fun requestOrShowRationale(permission: Permission) {
        if (getView()?.shouldShowRationale(permission) ?: false) {
            showRationale(permission)
        } else {
            requestPermissionInternal(permission)
        }
    }

    private fun requestPermissionInternal(permission: Permission) {
        getView()?.requestPermission(permission)
    }

    fun showRationale(permission: Permission) {
        if (!dialogShowing) {
            getView()?.showRationaleDialog(permission)
        }
    }

    override fun rationaleAccepted(permission: Permission) {
        dialogShowing = false
        requestPermissionInternal(permission)
    }

    override fun rationaleDeclined(permission: Permission) {
        dialogShowing = false
        // TODO Use activity presenter to call into activity to go to system settings page for app
    }

    override fun viewAttached() {
    }

    override fun viewDetached() {
    }

    interface Delegate {

        fun hasPermission(permission: Permission): Boolean
        fun shouldShowRationale(permission: Permission): Boolean
        fun requestPermission(permission: Permission)
        fun showRationaleDialog(permission: Permission)
        fun goToAppSystemSettings()
    }
}