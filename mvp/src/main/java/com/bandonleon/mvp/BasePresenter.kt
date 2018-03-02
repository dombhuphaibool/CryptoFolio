package com.bandonleon.mvp

import android.support.annotation.CallSuper
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * Created by dombhuphaibool on 2/27/18.
 */
abstract class BasePresenter<V : View> : Presenter {

    private var _view: V? = null
    protected val view: V
        get() = _view ?: throw IllegalStateException("view is currently null, access is only valid between onBind() and onUnbind()")

    protected val isBound: Boolean
        get() = (_view != null)

    private var disposables: CompositeDisposable? = null

    // --------------------------------------------------------------------------------------------
    // region                              Framework calls
    // --------------------------------------------------------------------------------------------
    override fun create() {
        onCreate()
    }

    override fun destroy() {
        onDestroy()
    }

    override fun bind(view: View) {
        onBind(view as V)
    }

    override fun unbind() {
        onUnbind()
    }

    override fun activate() {
        onActivate()
    }

    override fun deactivate() {
        onDeactivate()
    }

    override fun restore() {
        onRestore()
    }
    // endregion Framework calls

    // --------------------------------------------------------------------------------------------
    // region                                 Lifecycle
    // --------------------------------------------------------------------------------------------
    @CallSuper
    protected open fun onCreate() {}

    @CallSuper
    protected open fun onDestroy() {}

    @CallSuper
    protected open fun onBind(view: V) {
        _view = view
        disposables = CompositeDisposable()
    }

    @CallSuper
    protected open fun onUnbind() {
        disposables?.dispose()
        disposables = null
        _view = null
    }

    @CallSuper
    protected open fun onActivate() {}

    @CallSuper
    protected open fun onDeactivate() {}

    @CallSuper
    protected open fun onRestore() {}

    // endregion Lifecycle

    // --------------------------------------------------------------------------------------------
    // region                                Rx Disposable
    // --------------------------------------------------------------------------------------------
    protected fun addDisposable(disposable: Disposable) {
        disposables?.add(disposable)
    }

    protected fun clearDisposables() {
        disposables?.clear()
    }
    // endregion Rx Disposable
}