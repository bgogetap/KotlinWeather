package com.cultureoftech.kotlinweather.permissions

import android.app.Dialog
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v7.app.AlertDialog
import com.cultureoftech.kotlinweather.base.MyApplication
import javax.inject.Inject

/**
 * Created by bgogetap on 2/21/16.
 */
class PermissionRationaleDialog : DialogFragment() {

    @Inject lateinit var permissionHelper: PermissionHelper

    companion object {
        fun newInstance(permission: Permission): PermissionRationaleDialog {
            val dialog = PermissionRationaleDialog()
            val bundle = Bundle()
            bundle.putInt("permission_type", permission.id)
            dialog.arguments = bundle
            dialog.isCancelable = false
            return dialog
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        MyApplication.component.inject(this)
        val permission: Permission = Permission.fromId(arguments.getInt("permission_type"))
        return AlertDialog.Builder(activity)
                .setTitle(permission.rationaleTitle)
                .setMessage(permission.rationaleMessage)
                .setPositiveButton("OK", { dialogInterface, i -> permissionHelper.rationaleAccepted(permission) })
                .setNegativeButton("Cancel", { dialogInterface, i -> permissionHelper.rationaleDeclined(permission) })
                .create()
    }

    interface  PermissionRationaleListener {

        fun rationaleAccepted(permission: Permission)

        fun rationaleDeclined(permission: Permission)
    }
}