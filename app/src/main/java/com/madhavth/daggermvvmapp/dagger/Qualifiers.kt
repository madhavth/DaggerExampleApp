package com.madhavth.daggermvvmapp.dagger

import javax.inject.Qualifier
import javax.inject.Scope

@Qualifier
annotation class ApplicationContext

@Qualifier
annotation class ActivityContext

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class applicationScope