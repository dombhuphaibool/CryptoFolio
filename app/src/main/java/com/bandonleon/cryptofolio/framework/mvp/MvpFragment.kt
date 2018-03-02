package com.bandonleon.cryptofolio.framework.mvp

import android.os.Bundle
import android.support.v4.app.Fragment
import com.bandonleon.mvp.Presenter
import com.bandonleon.mvp.View

/**
 * Created by dombhuphaibool on 2/27/18.
 */
abstract class MvpFragment : Fragment(), View {

    private var presenter: Presenter? = null

    protected abstract fun createPresenter(): Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = createPresenter()
    }

    override fun onDestroy() {
        presenter?.destroy()
        super.onDestroy()
    }

    override fun onViewCreated(view: android.view.View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter?.bind(this)
    }

    override fun onDestroyView() {
        presenter?.unbind()
        super.onDestroyView()
    }
    override fun onResume() {
        super.onResume()
        presenter?.activate()
    }

    override fun onPause() {
        presenter?.deactivate()
        super.onPause()
    }
}