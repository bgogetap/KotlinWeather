package com.cultureoftech.kotlinweather.base

import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.FrameLayout
import com.cultureoftech.kotlinweather.R
import com.cultureoftech.kotlinweather.main.MainScreen
import com.cultureoftech.kotlinweather.ui.*
import com.cultureoftech.kotlinweather.utils.bindView
import java.util.*
import javax.inject.Inject

class MainActivity : BaseActivity(), UiManager.View {

    @Inject lateinit var backStack: BackStack
    @Inject lateinit var presenter: ActivityPresenter
    @Inject lateinit var uiManager: UiManager

    private var animationRunning: Boolean = false
    private var currentMenuResource: Int = 0
    private val container: FrameLayout by bindView(R.id.fl_container)
    private val toolbar: Toolbar by bindView<Toolbar>(R.id.toolbar)
    private val resultDelegates: LinkedHashSet<ActivityResultDelegate?> = LinkedHashSet()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        MyApplication.component.inject(this)
        presenter.takeView(this)
        uiManager.takeView(this)
        setSupportActionBar(toolbar)
        toolbar.setNavigationOnClickListener { pop() }
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        if (backStack.size() == 0) {
            push(MainScreen())
        } else {
            backStack.forEach { it.view = null }
            val topItem: BackStackItem = backStack.peek()
            backStack.forEach {
                val isTop: Boolean = topItem == it
                addView(it)
                it.view?.visibility = if (isTop) VISIBLE else GONE
                if (isTop) {
                    currentMenuResource = it.screen.menuResource()
                }
            }
        }
        handleBackIcon()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        if (currentMenuResource > 0) {
            menuInflater.inflate(currentMenuResource, menu)
        }
        resultDelegates.forEach { it?.onCreateOptionsMenu(menu) }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        resultDelegates.forEach { if (it?.onOptionsItemSelected(item) ?: false) return true}
        return super.onOptionsItemSelected(item)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if (permissions.size == 0) return
        permissionHelper.dialogShowing = false
        resultDelegates.forEach { it?.onRequestPermissionsResult(requestCode, permissions, grantResults) }
    }

    override fun push(screen: Screen<*>) {
        if (animationRunning) return
        animationRunning = true
        val v: View = addLayout(screen)
        if (container.childCount > 1) {
            screen.animationConfiguration()
                    .animateIn(container, v, Runnable {
                        animationRunning = false
                        container.getChildAt(container.childCount - 2).visibility = GONE
                    })
        } else {
            animationRunning = false
        }
        handleBackIcon()
    }

    override fun pop() {
        if (animationRunning) return
        animationRunning = true
        if (backStack.size() > 1) {
            container.getChildAt(container.childCount - 2).visibility = VISIBLE
            val item: BackStackItem = backStack.pop()
            if (backStack.peek().view is ViewRoot) {
                updateTitle((backStack.peek().view as ViewRoot).getTitle())
            }
            if (item.view != null) {
                item.screen.animationConfiguration().animateOut(container, item.view!!, Runnable {
                    container.removeView(item.view)
                    animationRunning = false
                })
            }
            item.screen.destroyComponent()
            currentMenuResource = backStack.peek().screen.menuResource()
            resetToolbarMenu()
            handleBackIcon()
        } else {
            finish()
        }
    }

    private fun addLayout(screen: Screen<*>): View {
        val view: View = screen.view(this)
        val item: BackStackItem = BackStackItem(screen, view)
        backStack.push(item)
        currentMenuResource = item.screen.menuResource()
        resetToolbarMenu()
        addView(item)
        return view
    }

    private fun addView(backStackItem: BackStackItem) {
        if (backStackItem.view == null || backStackItem.view?.context != this) {
            backStackItem.view = backStackItem.screen.view(this)
        }
        if (backStackItem.view?.parent != null) {
            (backStackItem.view?.parent as ViewGroup).removeView(backStackItem.view)
        }
        if (backStackItem.view is ViewRoot) {
            updateTitle((backStackItem.view as ViewRoot).getTitle())
        }
        container.addView(backStackItem.view)
    }

    override fun updateTitle(title: CharSequence) {
        toolbar.title = title
    }

    override fun setLoadingSpinner(show: Boolean) {
    }

    fun registerResultDelegate(delegate: ActivityResultDelegate) {
        resultDelegates.add(delegate)
    }

    fun unregisterResultDelegate(delegate: ActivityResultDelegate) {
        resultDelegates.remove(delegate)
    }

    fun resetToolbarMenu() {
        invalidateOptionsMenu()
    }

    fun handleBackIcon() {
        if (backStack.size() > 1) {
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        } else {
            supportActionBar?.setDisplayHomeAsUpEnabled(false)
        }
    }

    override fun onBackPressed() {
        pop()
    }

    override fun onDestroy() {
        presenter.dropView(this)
        uiManager.dropView(this)
        super.onDestroy()
    }
}
