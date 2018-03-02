package com.bandonleon.mvp

/**
 * Created by dombhuphaibool on 2/27/18.
 */
interface Presenter {

    fun create()
    fun destroy()

    fun bind(view: View)
    fun unbind()

    fun activate()
    fun deactivate()

    fun restore()
}