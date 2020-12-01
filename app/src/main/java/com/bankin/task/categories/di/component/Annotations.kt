package com.bankin.task.categories.di.component

import androidx.lifecycle.ViewModel
import dagger.MapKey
import javax.inject.Scope
import kotlin.reflect.KClass

/**
 * Annotation for having custom keys for view model factory map
 */
@MapKey
@Target(AnnotationTarget.FUNCTION)
annotation class ViewModelKey(val value: KClass<out ViewModel>)

/**
 * Scopes dependencies injected into activity to be preserved as long as the activity component is available
 */
@Scope
annotation class ActivityScope
