package com.lokalhy.newsreader.base

import androidx.fragment.app.FragmentActivity
import com.airbnb.mvrx.*
import kotlin.reflect.KClass


inline fun <T, reified VM : BaseMvRxViewModel<S>, reified S : MvRxState> T.viewModel(
        viewModelClass: KClass<VM> = VM::class,
        crossinline keyFactory: () -> String = { viewModelClass.java.name }
) where T : FragmentActivity = lifecycleAwareLazy(this) {

    if (this !is MvRxViewModelStoreOwner) throw IllegalArgumentException("Your Activity must be a MvRxViewModelStoreOwner!")

    MvRxViewModelProvider.get(viewModelClass.java, S::class.java, ActivityViewModelContext(this, intent.extras?.get(MvRx.KEY_ARG)), keyFactory())
}

fun <T, V> Async<T>.map(mapper: (T) -> V): Async<V> {

    return when (this) {
        is Success -> Success(mapper(this()))
        is Fail -> Fail(this.error)
        Uninitialized -> Uninitialized
        is Loading -> Loading()
    }
}

inline fun <T> Async<T>.onSuccess(runner: (T) -> Unit): T? {
    if (this is Success) {
        val data = this()
        runner(data)
        return data
    }
    return null
}